package com.voting.controller;

import com.voting.model.Voter;
import com.voting.model.Candidate;
import com.voting.repository.CandidateRepository;
import com.voting.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController{

    @Autowired
    private VoterService voterService;

    @Autowired 
    private CandidateRepository candidateRepository;

    @PostMapping("/register")
    public String register(@RequestBody Voter voter){
        return voterService.registerVoter(voter);
    }

    @GetMapping("/getByAadhaar")
    public Voter getVoter(@RequestParam String aadhaarId){
        return voterService.getByAadhaar(aadhaarId).orElse(null);
    }

    @GetMapping("/candidates")
    public List<Candidate> getCandidates(){
        return candidateRepository.findAll();
    }
    
}