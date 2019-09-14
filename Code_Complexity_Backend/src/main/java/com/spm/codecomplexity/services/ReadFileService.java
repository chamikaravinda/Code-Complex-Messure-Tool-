package com.spm.codecomplexity.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.codecomplexity.dao.UploadRepo;
import com.spm.codecomplexity.model.SingleLine;
import com.spm.codecomplexity.model.Upload;

@Service 
public class ReadFileService {
	
	@Autowired
	UploadRepo uploadRepo;

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	public List<SingleLine> readFile(String id) {
		LOG.info("Start to read the file");

		//to hold the current line number that reads
		int Lineindex = 0;

		ArrayList<SingleLine> list = new ArrayList<SingleLine>();
		Upload file = uploadRepo.findBy_id(id);

		File readFile = new File(getClass().getClassLoader().getResource("tempFile.txt").getFile());

		try {

			OutputStream os = new FileOutputStream(readFile);

			os.write(file.getFile());

			BufferedReader reader = new BufferedReader(new FileReader(readFile));

			String line = reader.readLine();

			while (line != null) {
				Lineindex++;
				
				SingleLine obj = new SingleLine();

				line = reader.readLine();
				obj.setStatement(line+" ");
				obj.setLineNumber(Lineindex);
				list.add(obj);
			}
			
			LOG.info("File read succesfully");

			
			reader.close();
			os.close();

		} catch (Exception e) {
			LOG.error("Error in reading the file :" + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

}
