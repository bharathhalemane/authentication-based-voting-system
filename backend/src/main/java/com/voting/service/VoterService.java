package com.voting.service;

import com.voting.model.Voter;
import com.voting.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VoterService{

    @Autowired
    private VoterRepository voterRepository;

    public String registerVoter(Voter voter) {

        if (voter.getAge() < 18) {
            return "Voter must be 18+";
        }

        Optional<Voter> existing = voterRepository.findByAadhaarId(voter.getAadhaarId());

        if (existing.isPresent()) {
            return "Voter already registered";
        }

        voter.setHasVoted(false);
        voterRepository.save(voter);

        return "Voter registered successfully";
    }

    public Optional<Voter> getByAadhaar(String aadhaarId) {
        return voterRepository.findByAadhaarId(aadhaarId);
    }
    
    public String resetAllVoters() {
        List<Voter> voters = voterRepository.findAll();

        for (Voter v : voters) {
            v.setHasVoted(false);
        }

        voterRepository.saveAll(voters);

        return "All voters reset successfully";
    }
}