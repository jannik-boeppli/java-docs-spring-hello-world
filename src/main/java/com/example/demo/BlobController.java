package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.io.File; 

@RestController
@RequestMapping("/blob")
public class BlobController {

    @Value("azure-blob://uploadtarget")
    private Resource blobFile;

    @GetMapping("/readBlobFile")
    public String readBlobFile() throws IOException {
        return StreamUtils.copyToString(
                this.blobFile.getInputStream(),
                Charset.defaultCharset());
    }

    @PostMapping("/writeBlobFile")
    public String writeBlobFile(@RequestBody String data) throws IOException {
        try (OutputStream os = ((WritableResource) this.blobFile).getOutputStream()) {
            os.write(data.getBytes());
        }
        return "file was updated";
    }

    @PostMapping("/")
    public String uploadFileToStorage(@RequestBody File uploadFile){
        try{
            File targetFolder = (File) blobFile;
            if(targetFolder.isDirectory()){
                uploadFile.setPath(targetFolder.getPath() + "/" + uploadFile.getName());
                uploadFile.createNewFile();
            }
        }catch(Exception e){
            return "File upload failed";
        }
        
        return "File upload successful";
    }


    @GetMapping("/")
    public String resource() throws IOException {
        return blobFile.toString();
    }


}