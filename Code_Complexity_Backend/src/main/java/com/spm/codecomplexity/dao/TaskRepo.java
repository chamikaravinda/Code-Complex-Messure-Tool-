package com.spm.codecomplexity.dao;

import com.spm.codecomplexity.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepo extends MongoRepository<Task, String> {

    public Task findByAssigneeNameLike( String name );
}