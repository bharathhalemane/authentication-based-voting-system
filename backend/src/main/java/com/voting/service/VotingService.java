package com.voting.service;

import com.voting.model.*;
import com.voting.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service 
public class VotingService{

    @Autowired
    private VoterRepository voterRepository;

    @Autowired 
    private CandidateRepository candidateRepository;

    @Autowired
    private VoteRepository  voteRepository;

    public String castVote(String voterId, String candidateId){

        Optional<Voter> voterOpt = voterRepository.findById(voterId);

        if(voterOpt.isEmpty()){
            return "Voter Not Found";
        }

        Voter voter = voterOpt.get();

        if(voter.isHasVoted()){
            return "Already voted!";
        }

        Vote vote = new Vote(null, voterId, candidateId, LocalDateTime.now());
        voteRepository.save(vote);

        voter.setHasVoted(true);
        voterRepository.save(voter);

        Candidate candidate = candidateRepository.findById(candidateId).get();

        candidate.setVoteCount(candidate.getVoteCount() + 1);
        candidateRepository.save(candidate);

        return "Vote cast successfully";

    }
}