import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-side-bar',
  standalone: true,
  imports: [],
  templateUrl: './side-bar.component.html',
  styleUrl: './side-bar.component.css'
})
export class SideBarComponent {


constructor(private router: Router) {}

irAVentas() {
  this.router.navigate(['/ventas']);
}

irAClientes() {
  this.router.navigate(['/clientes']);
}

irACategorias() {
  this.router.navigate(['/categorias']);
}

irAProductos() {
  this.router.navigate(['/productos']);
}


}
