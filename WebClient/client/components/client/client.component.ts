import {Component, inject, OnInit} from '@angular/core';
import {Store} from '@ngrx/store';

@Component({
  selector: 'app-client',
  standalone: true,
  templateUrl: './client.component.html',
  styleUrl: './client.component.scss'
})
export class ClientComponent implements OnInit {

  private store = inject(Store);

  ngOnInit(): void {
  }
}
