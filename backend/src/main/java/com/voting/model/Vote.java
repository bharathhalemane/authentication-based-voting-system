package com.voting.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "votes")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    
    @Id
    private String id;

    private String voterId;
    private String candidateId;
    private LocalDateTime timestamp;
}
