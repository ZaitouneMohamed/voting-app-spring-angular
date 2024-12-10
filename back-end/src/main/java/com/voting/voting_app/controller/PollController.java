    package com.voting.voting_app.controller;

    import com.voting.voting_app.model.OptionVote;
    import com.voting.voting_app.model.Poll;
    import com.voting.voting_app.repository.PollRepository;
    import org.apache.coyote.Response;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping("/api/polls")
    public class PollController
    {
        @Autowired
        private PollRepository pollRepository;

        @PostMapping
        public Poll createPoll(@RequestBody Poll poll)
        {
            return pollRepository.save(poll);
        }

        @GetMapping
        public List<Poll> getAllPolls()
        {
            return pollRepository.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Poll> getPollById(@PathVariable Long id) {
            Optional<Poll> poll = pollRepository.findById(id);
            return poll.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping("/{id}/vote")
        public ResponseEntity<Poll> addVote(@PathVariable Long id ,@RequestBody String vote)
        {
            Optional<Poll> pollOptional = pollRepository.findById(id);

            if (pollOptional.isPresent()) {
                Poll poll = pollOptional.get();

                // Find the option to vote for
                OptionVote selectedOption = poll.getOptions().stream()
                        .filter(option -> option.getVoteOption().equals(vote))
                        .findFirst()
                        .orElse(null);
                // If the option exists, increment the vote count
                if (selectedOption != null) {
                    selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);
                    pollRepository.save(poll); // Save the updated poll
                    return ResponseEntity.ok(poll); // Return the updated poll
                } else {
                    // If the option doesn't exist, return 400 Bad Request
                    return ResponseEntity.badRequest().body(null);
                }
            } else {
                // If the poll is not found, return 404 Not Found
                return ResponseEntity.notFound().build();
            }
        }
    }
