package com.voting.voting_app.service;

import com.voting.voting_app.model.OptionVote;
import com.voting.voting_app.model.Poll;
import com.voting.voting_app.repository.PollRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollService
{
    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll createPoll(Poll poll)
    {
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls()
    {
        // Sort by createdDate in descending order
        return pollRepository.findAll(Sort.by(Sort.Order.desc("createdDate")));

    }

    public Optional<Poll> getPoll(Long id)
    {
        return pollRepository.findById(id);
    }

    public void vote(Long pollId , int optionIndex)
    {
        // get poll from db
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("pull not found"));

        // get options list of poll
        List<OptionVote> options = poll.getOptions();

        // validation
        if (optionIndex<0 || optionIndex >= options.size())
        {
            throw new IllegalArgumentException("invalid option index");
        }

        // get selected option
        OptionVote selectedOption = options.get(optionIndex);

        // increment vote
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);

        // save data
        pollRepository.save(poll);


    }

}
