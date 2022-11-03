package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServlet;

@SpringBootApplication
@RestController
@RequestMapping("")
public class DemoApplication extends SpringBootServletInitializer {


	private String PAGE_HEADER = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><title>File Upload</title></head><body>"; 

	private String FILE_UPLOAD = "<h1>This is a HTML header</h1><h3>File upload:</h3><div>Select a file to upload:</div><form action=\"blob\" method=\"post\" enctype=\"multipart/form-data\"><input type=\"file\" name=\"file\" size=\"50\"/><input type=\"submit\" value=\"Upload File\" /></form>";

	private String PAGE_FOOTER = "</body></html>";

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/")
	String getPage() {
		return "PAGE_HEADER + FILE_UPLOAD + PAGE_FOOTER";
	}
}
