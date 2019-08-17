package com.spm.codecomplexity.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.codecomplexity.dao.UploadRepo;
import com.spm.codecomplexity.model.Upload;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/codecm")
public class ControlStructureController {

	@Autowired
	UploadRepo uploadRepo;
	
	@GetMapping("/controlStructure/analyse/{id}")
	public int analyseControlStructure( @PathVariable String id ) {
		
		Upload file = uploadRepo.findBy_id(id);
		
		//BufferedReader reader = new BufferedReader(new FileReader());
		
		File readFile = new File( getClass().getClassLoader().getResource("tempFile.txt").getFile());
		
		int lineCount = 0;
		
		int size = 0;
		
		try {
			
			OutputStream os = new FileOutputStream(readFile);
			
			os.write(file.getFile());

			BufferedReader reader = new BufferedReader(new FileReader(readFile));
			
			String line = reader.readLine();
			while (line != null) {
				
				++lineCount;
				
				line = reader.readLine();
				
				size += calculateComplexityDueToControlStructures(line);
			}
			reader.close();
			os.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		/*
		ByteArrayOutputStream bos = null;
        try {
            File f = new File("");
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
		*/
		return size;
	}
	
	public int calculateComplexityDueToControlStructures(String line) throws Exception{
		
		int count = 0;
		
		try {
			String regex = "if.*?";
			
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(line);
			
			
			while (matcher.find()) {
				count++;
				//System.out.println("found: " + count + " : " + matcher.start() + " - " + matcher.end());
			}
			
		}catch(Exception e) {
			System.out.println("Exceptoin : " + e );
		}
	
		return count;
	}
}
