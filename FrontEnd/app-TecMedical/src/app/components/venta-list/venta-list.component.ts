import { Component, OnInit } from '@angular/core';


import { CommonModule } from '@angular/common';
import { VentaService, VentaResponse, DetalleVentaResponse } from '../../services/venta.service';



@Component({
  selector: 'venta-list-comp',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './venta-list.component.html',
  styleUrls: ['./venta-list.component.css']
})
export class VentaListComponent  implements OnInit  {

   ventas: VentaResponse[] = [];
  error: string = '';

  constructor(private ventaService: VentaService) { }

  ngOnInit(): void {
    this.cargarVentas();
  }

  cargarVentas(): void {
    this.ventaService.listarVentas().subscribe({
      next: (data) => {
        this.ventas = data;
      },
      error: (err) => {
        console.error(err);
        this.error = 'No se pudieron cargar las ventas. Revisa la consola.';
      }
    });
  }

  calcularSubtotal(det: DetalleVentaResponse): number {
    return det.cantidad * det.precioUnitario;
  }
}
