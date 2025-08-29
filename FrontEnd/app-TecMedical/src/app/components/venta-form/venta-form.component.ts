// venta-form.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';
import { ProductoService, Producto } from '../../services/producto.service';
import { VentaService, VentaRequest } from '../../services/venta.service';

@Component({
  selector: 'app-venta-form',
  templateUrl: './venta-form.component.html'
})
export class VentaFormComponent implements OnInit {
  productos: Producto[] = [];
  ventaForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private productoService: ProductoService,
    private ventaService: VentaService
  ) {
    this.ventaForm = this.fb.group({
      clienteId: [1], // Puedes cambiar esto por un selector de cliente
      usuarioId: [2], // Puedes obtenerlo del login o del interceptor
      detalles: this.fb.array([])
    });
  }

  ngOnInit(): void {
    this.productoService.listarProductos().subscribe((data: Producto[]) => {
  this.productos = data;
});
  }

  get detalles() {
    return this.ventaForm.get('detalles') as FormArray;
  }

  agregarDetalle() {
    this.detalles.push(this.fb.group({
      productoId: [''],
      cantidad: [1],
      precioUnitario: [{ value: 0, disabled: true }],
      subtotal: [{ value: 0, disabled: true }]
    }));
  }

  actualizarPrecioYSubtotal(index: number) {
    const detalle = this.detalles.at(index);
    const productoId = detalle.get('productoId')?.value;
    const cantidad = detalle.get('cantidad')?.value;

    const producto = this.productos.find(p => p.id === +productoId);
    if (producto) {
      const subtotal = producto.precio * cantidad;
      detalle.patchValue({
        precioUnitario: producto.precio,
        subtotal: subtotal
      });
    }
  }

  enviarVenta() {
    const detalles = this.detalles.getRawValue().map((d: any) => ({
      productoId: d.productoId,
      cantidad: d.cantidad,
      precioUnitario: d.precioUnitario
    }));

    const venta: VentaRequest = {
      clienteId: this.ventaForm.value.clienteId,
      usuarioId: this.ventaForm.value.usuarioId,
      detalles: detalles
    };

    this.ventaService.registrarVenta(venta).subscribe({
      next: (res) => alert('Venta registrada con éxito'),
      error: (err) => console.error('Error al registrar venta', err)
    });
  }
}
