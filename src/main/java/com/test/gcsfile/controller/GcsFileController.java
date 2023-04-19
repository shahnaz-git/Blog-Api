package com.test.gcsfile.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.gcsfile.service.GcsFileService;

@RestController
public class GcsFileController {
	
	@Autowired
	GcsFileService fileService;
	
	@GetMapping("/getallfile")
	  public ResponseEntity<List<String>> listOfFiles() throws FileNotFoundException, IOException {

	    List<String> files = fileService.listOfFiles();

	    return ResponseEntity.ok(files);
	  }
	
	 @PostMapping("/uploadfile")
	  public ResponseEntity<String> uploadFile(
	          @RequestParam("file") MultipartFile file) throws IOException {

	    fileService.uploadFile(file);

	    return ResponseEntity.ok("File uploaded successfully");
	  }
}
