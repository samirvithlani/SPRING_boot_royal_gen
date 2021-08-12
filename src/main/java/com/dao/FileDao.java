package com.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class FileDao {

	Path folder = Paths.get("upload");
	

	public void saveFile(MultipartFile file) {

		try {
			Files.copy(file.getInputStream(), this.folder.resolve(file.getOriginalFilename()));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<File> getAllFiles(){
		
		//NIO to IO
		File f  = folder.toFile();
		File[] fileList = f.listFiles();
		List<File> listOfFiles = new ArrayList<>();
		
		for(File x:fileList) {
			
			listOfFiles.add(x);
			
		}
		
		return listOfFiles;		
		
	}

}
