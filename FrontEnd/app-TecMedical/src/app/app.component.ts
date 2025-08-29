import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SideBarComponent } from './components/fragments/side-bar/side-bar.component';




//import { Router, NavigationEnd } from '@angular/router';
//import { filter } from 'rxjs/operators';



@Component({
  selector: 'app-root',
  standalone:true,
  imports: [RouterOutlet, ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'app-TecMedical';



}
