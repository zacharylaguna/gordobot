import { Component, OnInit } from '@angular/core';

import { USER } from '../mock-user';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {
  user = USER;
  constructor() { }

  ngOnInit(): void {
  }
  flipMic(): void {
    this.user.mic = !this.user.mic;
  }
}
