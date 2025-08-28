import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { VentaListComponent } from "./components/venta-list/venta-list.component";

@Component({
  selector: 'app-root',
  standalone:true,
  imports: [RouterOutlet, VentaListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'app-TecMedical';
}
