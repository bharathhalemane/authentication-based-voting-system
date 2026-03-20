package com.voting.repository;

import com.voting.model.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteRepository extends MongoRepository<Vote, String>{
    
}