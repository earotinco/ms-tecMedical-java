import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormArray,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';


import { Router } from '@angular/router';
import { ProductoService, Producto } from '../../services/producto.service';
import { VentaService, VentaRequest } from '../../services/venta.service';
import { MatExpansionModule } from '@angular/material/expansion';

@Component({
  selector: 'app-venta-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatSnackBarModule,
    MatExpansionModule,
  ],
  templateUrl: './venta-form.component.html',
  styleUrls: ['./venta-form.component.css'],
})
export class VentaFormComponent implements OnInit {
  productos: Producto[] = [];
  ventaForm: FormGroup;
  totalGeneral: number = 0;

  // ✅ Método para comparar productos en el mat-select
  compararProductos = (p1: Producto, p2: Producto) =>
    p1 && p2 ? p1.id === p2.id : p1 === p2;

  constructor(
    private fb: FormBuilder,
    private productoService: ProductoService,
    private ventaService: VentaService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    this.ventaForm = this.fb.group({
      clienteId: ['', Validators.required],
      usuarioId: [2],
      detalles: this.fb.array([]),
    });
  }

  ngOnInit(): void {
    this.productoService.listarProductos().subscribe({
      next: (data: Producto[]) => {
        this.productos = data;
      },
      error: () => {
        this.snackBar.open('Error al cargar productos', 'Cerrar', {
          duration: 3000,
        });
      },
    });
  }

  get detalles(): FormArray {
    return this.ventaForm.get('detalles') as FormArray;
  }

  agregarDetalle(): void {
    const detalleForm = this.fb.group({
      producto: [null, Validators.required],
      cantidad: [1, [Validators.required, Validators.min(1)]],
      precioUnitario: [{ value: 0, disabled: true }],
      subtotal: [{ value: 0, disabled: true }],
    });

    this.detalles.push(detalleForm);
  }

  volverAlListado(): void {
  this.router.navigate(['/ventas']);
}

  eliminarDetalle(index: number): void {
    this.detalles.removeAt(index);
    this.calcularTotalGeneral();
  }

  actualizarPrecioYSubtotal(index: number): void {
    const detalle = this.detalles.at(index);
    const producto = detalle.get('producto')?.value;
    const cantidad = detalle.get('cantidad')?.value;

    console.log('Producto seleccionado:', producto);

    if (producto) {
      const subtotal = producto.precio * cantidad;
      detalle.patchValue({
        precioUnitario: producto.precio,
        subtotal: subtotal,
      });

      detalle.get('precioUnitario')?.updateValueAndValidity();
      detalle.get('subtotal')?.updateValueAndValidity();

      this.calcularTotalGeneral();
    }
  }

  calcularTotalGeneral(): void {
    this.totalGeneral = this.detalles.controls.reduce((acc, detalle) => {
      const subtotal = detalle.get('subtotal')?.value || 0;
      return acc + subtotal;
    }, 0);
  }

  enviarVenta(): void {
    if (this.detalles.length === 0) {
      this.snackBar.open('Debe agregar al menos un producto', 'Cerrar', {
        duration: 3000,
      });
      return;
    }

    if (!this.ventaForm.valid) {
      this.snackBar.open(
        'Formulario inválido. Verifique los campos.',
        'Cerrar',
        { duration: 3000 }
      );
      return;
    }

    const detalles = this.detalles
      .getRawValue()
      .filter((d: any) => d.producto && d.producto.idProducto)
      .map((d: any) => ({
        productoId: d.producto.idProducto,
        cantidad: d.cantidad,
        precioUnitario: d.precioUnitario,
      }));

    const usuarioId = this.ventaForm.value.usuarioId;
    const venta: VentaRequest = {
      clienteId: this.ventaForm.value.clienteId,
      detalles: detalles,
      ...(usuarioId && { usuarioId }),
    };

    console.log('Venta enviada:', venta);
    console.log('Detalles crudos:', this.detalles.getRawValue());

    this.ventaService.registrarVenta(venta).subscribe({
      next: () => {
        this.snackBar.open('Venta registrada con éxito', 'Cerrar', {
          duration: 3000,
        });
        this.ventaForm.reset();
        this.detalles.clear();
        this.totalGeneral = 0;
        this.router.navigate(['/ventas']);
      },
      error: () => {
        this.snackBar.open('Error al registrar venta', 'Cerrar', {
          duration: 3000,
        });
      },
    });


  }


}
