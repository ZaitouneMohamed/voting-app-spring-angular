package com.voting.voting_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class OptionVote
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Add an identifier for the entity

    private String voteOption;

    private Long voteCount = 0L;
}
