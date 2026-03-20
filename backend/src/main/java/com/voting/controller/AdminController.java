package com.voting.controller;

import com.voting.model.Voter;
import com.voting.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController{

    @Autowired
    private VoterService voterService;

    @PostMapping("/register")
    public String register(@RequestBody Voter voter){
        return voterService.registerVoter(voter);
    }
}