import { Poll } from '../poll.models';
import { PollService } from './../poll.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-poll',
  templateUrl: './poll.component.html',
  styleUrl: './poll.component.css'
})
export class PollComponent implements OnInit{
  polls: Poll[] = [];

  ngOnInit(): void {
    this.loadPools();
  }

  constructor( private pollService: PollService) {

  }

  loadPools() {
    this.pollService.getPolls().subscribe({
      next: (data) => {
        this.polls= data;
      },
      error: (err) => {
        console.error("error found" , err);

      },
    });
  }

}
