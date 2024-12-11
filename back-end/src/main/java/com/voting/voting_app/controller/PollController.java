    package com.voting.voting_app.controller;

    import com.voting.voting_app.model.OptionVote;
    import com.voting.voting_app.model.Poll;
    import com.voting.voting_app.repository.PollRepository;
    import com.voting.voting_app.request.Vote;
    import com.voting.voting_app.service.PollService;
    import org.apache.coyote.Response;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping("/api/polls")
    @CrossOrigin("http://localhost:4200/")
    public class PollController
    {
        @Autowired


        private PollService pollService;

        @PostMapping
        public Poll createPoll(@RequestBody Poll poll)
        {
            return pollService.createPoll(poll);
        }

        @GetMapping
        public List<Poll> getAllPolls()
        {
            return pollService.getAllPolls();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Poll> getPollById(@PathVariable Long id) {
            return pollService.getPoll(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.noContent().build());
        }

        @PostMapping("/vote")
        public void voteOnPoll(@RequestBody Vote vote)
        {
            pollService.vote(vote.getPollId() , vote.getOptionIndex());
        }

    }
