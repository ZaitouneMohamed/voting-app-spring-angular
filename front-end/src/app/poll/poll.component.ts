import { Poll } from '../poll.models';
import { PollService } from './../poll.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-poll',
  templateUrl: './poll.component.html',
  styleUrl: './poll.component.css',
})
export class PollComponent implements OnInit {
  newPoll: Poll = {
    question: '',
    options: [
      { voteOption: '', voteCount: 0 },
      { voteOption: '', voteCount: 0 },
    ],
  };
  polls: Poll[] = [];

  ngOnInit(): void {
    this.loadPools();
  }

  constructor(private pollService: PollService) {}

  loadPools() {
    this.pollService.getPolls().subscribe({
      next: (data) => {
        this.polls = data;
      },
      error: (err) => {
        console.error('error found', err);
      },
    });
  }

  createPoll() {
    this.pollService.createPoll(this.newPoll).subscribe({
      next: (createdPoll) => {
        this.polls.push(createdPoll);
        this.resetPoll()
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  resetPoll() {
    this.newPoll = {
      question: '',
      options: [
        { voteOption: '', voteCount: 0 },
        { voteOption: '', voteCount: 0 },
      ],
    };
  }
}
