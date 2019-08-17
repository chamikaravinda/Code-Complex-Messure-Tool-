package com.spm.codecomplexity.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.codecomplexity.model.Upload;

public interface UploadRepo extends MongoRepository<Upload, String>{

	public Upload findBy_id( String id );
}
