import { Component, OnInit } from '@angular/core';
import { Rating } from './rating';
import { RatingService } from './rating.service';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent implements OnInit {

  ratings: Rating[];

  constructor(private ratingsService: RatingService) {
    this.ratings = [];
  }

  ngOnInit(): void {
    this.ratingsService.getAll().subscribe(
      e => this.ratings = e
    );
  }

}