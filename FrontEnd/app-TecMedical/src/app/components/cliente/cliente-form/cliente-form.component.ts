import { Component } from '@angular/core';
import { ClienteService } from '../../../services/cliente.service';
import { Cliente } from '../../../models/cliente.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cliente-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cliente-form.component.html',
  styleUrl: './cliente-form.component.css'
})
export class ClienteFormComponent {

 cliente: Cliente = {
    nombre: '',
    apellido: '',
    dni: '',
    correo: '',
    telefono: '',
    direccion: ''
  };

  constructor(private clienteService: ClienteService) {}

  guardar(): void {
    this.clienteService.crear(this.cliente).subscribe(() => {
      alert('Cliente creado correctamente');
    });
  }
}
