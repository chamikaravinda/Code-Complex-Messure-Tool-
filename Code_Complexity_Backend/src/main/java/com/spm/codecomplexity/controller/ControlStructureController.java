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
import java.util.ArrayList;
import java.util.List;
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
import com.spm.codecomplexity.model.ControlStructureType;
import com.spm.codecomplexity.model.Upload;
import com.spm.codecomplexity.util.CommonConstants;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/codecm")
public class ControlStructureController {

	@Autowired
	UploadRepo uploadRepo;
	
	
	@GetMapping("/controlStructure/analyse/{id}")
	public List<ControlStructureType> analyseControlStructure( @PathVariable String id ) {
	
		List<ControlStructureType> list = new ArrayList<ControlStructureType>();
		
		return readFile(id);
	
	}
	
	@GetMapping("/controlStructure/total/ctc/{id}")
	public int getTotalCtc( @PathVariable String id ) {
	
		int total = 0;
		
		List<ControlStructureType> tempList = readFile(id);
		
		for( ControlStructureType tempObj : tempList ) {
			total += tempObj.getCtc();
		}
		
		return total;
	}
	
	public List<ControlStructureType> readFile( String id ) {
		
		ArrayList<ControlStructureType> list = new ArrayList<ControlStructureType>();
		Upload file = uploadRepo.findBy_id(id);
			
			File readFile = new File( getClass().getClassLoader().getResource("tempFile.txt").getFile());
			
			int lineCount = 0;
			
			int size = 0;
			
			try {
				
				
				OutputStream os = new FileOutputStream(readFile);
				
				os.write(file.getFile());
	
				BufferedReader reader = new BufferedReader(new FileReader(readFile));
				
				String line = reader.readLine();
				
				while (line != null) {
					
					ControlStructureType obj = new ControlStructureType();
					
					++lineCount;
					
					line = reader.readLine();
					
					obj.setStatement(line);
					obj = calculateComplexityDueToControlStructures(line,obj);
					
					obj.setCr( obj.getCs() + obj.getCtc() + obj.getCnc() + obj.getCi() + obj.getTw() + obj.getCps()  );
					
					list.add(obj);
				}
			
				reader.close();
				os.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return list;
	}
	
	public ControlStructureType calculateComplexityDueToControlStructures(String line, ControlStructureType obj) throws Exception{
		
		int count = 0;
		
		try {
			Pattern patternIf = Pattern.compile(CommonConstants.MATCH_CONTROL_STRUCTURE_TYPE_IF);
			Matcher matcherIf = patternIf.matcher(line);

			Pattern patternLoop = Pattern.compile(CommonConstants.MATCH_CONTROL_STRUCTURE_TYPE_LOOP);
			Matcher matchLoop = patternLoop.matcher(line);
			
			Pattern patternCatch = Pattern.compile(CommonConstants.MATCH_CONTROL_STRUCTURE_TYPE_CATCH);
			Matcher matchCatch = patternCatch.matcher(line);
			
		
			while (matcherIf.find()) {
				count++;
				//System.out.println("found: " + count + " : " + matcherIf.start() + " - " + matcherIf.end());
				
				String innerKeyword = CommonConstants.MATCH_CONTROL_STRUCTURE_TYPE_BREAKS;
				
				Pattern innerPattern = Pattern.compile(innerKeyword);
				Matcher innerMatcher = innerPattern.matcher(line);
				
				while(innerMatcher.find()) {
					++count;
					
				}
				
			}
			
			while (matchCatch.find()) {
				count++;
				//System.out.println("found: " + count + " : " + matcherIf.start() + " - " + matcherIf.end());
				
				String innerKeyword = CommonConstants.MATCH_CONTROL_STRUCTURE_TYPE_BREAKS;
				
				Pattern innerPattern = Pattern.compile(innerKeyword);
				Matcher innerMatcher = innerPattern.matcher(line);
				
				while(innerMatcher.find()) {
					++count;
					
				}
				
			}
			
			while (matchLoop.find()) {
				count+=2;
				//System.out.println("found: " + count + " : " + matcherIf.start() + " - " + matcherIf.end());
				
				String innerKeyword = CommonConstants.MATCH_CONTROL_STRUCTURE_TYPE_BREAKS;
				
				Pattern innerPattern = Pattern.compile(innerKeyword);
				Matcher innerMatcher = innerPattern.matcher(line);
				
				while(innerMatcher.find()) {
					count+=2;
					
				}
				
			}
			
			
		}catch(Exception e) {
			System.out.println("Exceptoin : " + e );
		}
	
		obj.setCtc(count);
		
		return obj;
	}
}
