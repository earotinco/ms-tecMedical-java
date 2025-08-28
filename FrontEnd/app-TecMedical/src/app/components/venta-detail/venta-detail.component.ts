
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

import { VentaService, VentaConFechaDate } from '../../services/venta.service';


@Component({
  selector: 'venta-detail-comp',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './venta-detail.component.html',
  styleUrls: ['./venta-detail.component.css']
})

export class VentaDetailComponent implements OnInit {
  venta: VentaConFechaDate | null = null;
  error: string = '';

  constructor(private route: ActivatedRoute, private ventaService: VentaService, private router: Router) {}

  ngOnInit(): void {
    const idVenta = Number(this.route.snapshot.paramMap.get('id'));
    this.ventaService.buscarPorId(idVenta).subscribe({
      next: (data) => {
        this.venta = {
          ...data,
          fechaDate: new Date(
            data.fecha[0],
            data.fecha[1] - 1,
            data.fecha[2],
            data.fecha[3],
            data.fecha[4],
            data.fecha[5]
          )
        };
      },
      error: (err) => {
        console.error(err);
        this.error = 'No se pudo cargar la venta.';
      }
    });
  }

  calcularSubtotal(det: any): number {
    return det.cantidad * det.precioUnitario;
  }

volverAlListado(): void {
  this.router.navigate(['/ventas']);
}


}
