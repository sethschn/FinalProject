import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-card-display',
  templateUrl: './card-display.component.html',
  styleUrls: ['./card-display.component.css']
})
export class CardDisplayComponent implements OnInit {

  activities:Array<string>= ['','','','','','','','','','','','']

  constructor() { }

  ngOnInit(): void {
  }

}
