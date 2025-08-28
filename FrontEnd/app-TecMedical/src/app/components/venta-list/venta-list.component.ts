import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';

import { CommonModule } from '@angular/common';
import { VentaService, VentaConFechaDate, DetalleVentaResponse } from '../../services/venta.service';



@Component({
  selector: 'venta-list-comp',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './venta-list.component.html',
  styleUrls: ['./venta-list.component.css']
})
export class VentaListComponent  implements OnInit  {

   ventas: VentaConFechaDate[] = [];
  error: string = '';

  constructor(private ventaService: VentaService, private router: Router) { }

  ngOnInit(): void {
    this.cargarVentas();
  }

  cargarVentas(): void {
  this.ventaService.listarVentas().subscribe({
    next: (data) => {

    this.ventas = data.map(venta => ({
      ...venta,
     fechaDate: new Date(
     venta.fecha[0],
     venta.fecha[1] - 1,
     venta.fecha[2],
     venta.fecha[3],
     venta.fecha[4],
     venta.fecha[5]
     )

      }));
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

  verDetalle(idVenta: number): void {
  this.router.navigate(['/ventas', idVenta]);
}
}
