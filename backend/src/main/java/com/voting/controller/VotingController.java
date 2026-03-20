package com.voting.controller;

import com.voting.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vote")
public class VotingController {

    @Autowired
    private VotingService votingService;

    @PostMapping("/submit")
    public String vote(@RequestParam String voterId, @RequestParam String candidateId){
        return votingService.castVote(voterId, candidateId);
    }
}