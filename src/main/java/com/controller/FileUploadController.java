package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bean.FileInfo;
import com.bean.ResponseMessage;
import com.dao.FileDao;

@RestController
@CrossOrigin
public class FileUploadController {

	@Autowired
	FileDao fileDao;

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,@RequestHeader("demo") int demo) {

		System.out.println("demo"+demo);
		String message = "";
		

		//MIME TYPE..
		System.out.println(file.getContentType());
		if(file.getContentType().equals("image/jpeg")) {
		
			fileDao.saveFile(file);
			message = "file uploaded...." + file.getOriginalFilename();
			return new ResponseEntity<ResponseMessage>(new ResponseMessage(message), HttpStatus.OK);	
		}
		
		message = "please check file type only jpeg is allowd..."+file.getOriginalFilename();
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(message), HttpStatus.CONFLICT);
		

	}

	@GetMapping(value = "/files")
	public ResponseEntity<List<FileInfo>> getFileList() {

		System.out.println("method called....");
		
		
		List<File> fileList = fileDao.getAllFiles();

		List<FileInfo> fileInfos = new ArrayList<>();

		for (File x : fileList) {

			fileInfos.add(new FileInfo(x.getName()));
		}
		return new ResponseEntity<List<FileInfo>>(fileInfos, HttpStatus.OK);

	}

}
