package com.voting.service;

import com.voting.model.Voter;
import com.voting.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoterService{

    @Autowired
    private VoterRepository voterRepository;

    public String registerVoter(Voter voter){

        if(voter.getAge() < 18){
            return "Voter must be 18+";
        }

        Optional<Voter> existing = voterRepository.findByAadhaarId(voter.getAadhaarId());

        if(existing.isPresent()){
            return "Voter already registered";
        }

        voter.setHasVoted(false);
        voterRepository.save(voter);

        return "Voter registered successfully";
    }
    
}