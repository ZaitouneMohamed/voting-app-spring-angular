package com.voting.voting_app.repository;

import com.voting.voting_app.model.OptionVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionVoteRepository extends JpaRepository<OptionVote ,Long> {
}
