package com.spm.codecomplexity.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spm.codecomplexity.dao.UploadRepo;
import com.spm.codecomplexity.exceptions.CustomErrorResponse;
import com.spm.codecomplexity.model.ControlStructureType;
import com.spm.codecomplexity.model.Upload;

@Service
public class RecursiveService {

	@Autowired
	UploadRepo uploadRepo;

	@Autowired
	ControlStructureService controlStructureService;

	@Autowired
	StatmentSizeService statmentSizeSerive;

	public int getRecursiveSize(String id) {

		int value;
		
		int TW = controlStructureService.getTotalCtc(id)/* +nestedControlStructureService.getTotalCnc(id)+inhetritanceService.getTotalCi(id)*/; 

		int	Cps = getStatmentSize(id) * TW;
		
		return Cps*2;
		
	}

	public int getStatmentSize(String id) {
		Upload file = uploadRepo.findBy_id(id);
		File readFile = new File(getClass().getClassLoader().getResource("tempFile.txt").getFile());

		BufferedReader reader = null;
		OutputStream os = null;

		int size = 0;
		try {

			os = new FileOutputStream(readFile);
			os.write(file.getFile());
			reader = new BufferedReader(new FileReader(readFile));
			String line = reader.readLine();

			while (line != null) {
				line = reader.readLine();
				size += statmentSizeSerive.calculateComplexityDueToStatmentSize(line);
			}

			os.close();
			reader.close();
			return size;

		} catch (Exception e) {

			System.out.println("Error " + e);
			e.printStackTrace();
			CustomErrorResponse errors = new CustomErrorResponse();
			errors.setError("Unknown error");
			errors.setStatus(HttpStatus.BAD_GATEWAY.value());

			return 0;
		}

	}
}
