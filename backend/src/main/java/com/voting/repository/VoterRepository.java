package com.voting.repository;

import com.voting.model.Voter;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface VoterRepository extends MongoRepository<Voter, String>{
    Optional<Voter> findByAadhaarId(String aadhaarId);
}