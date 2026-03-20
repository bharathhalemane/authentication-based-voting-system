package com.voting.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "voters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voter {

    @Id
    private String id;

    private String name;
    private int age;
    private String aadhaarId;
    private String biometricData;
    private boolean hasVoted;
}