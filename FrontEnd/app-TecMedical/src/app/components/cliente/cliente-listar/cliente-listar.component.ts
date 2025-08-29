import { Component, OnInit } from '@angular/core';
import { ClienteService } from '../../../services/cliente.service';
import { Cliente } from '../../../models/cliente.model';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-cliente-listar',
   standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './cliente-listar.component.html',
  styleUrl: './cliente-listar.component.css'
})
export class ClienteListarComponent implements OnInit {
  clientes: Cliente[] = [];

  constructor(private clienteService: ClienteService) {}

  ngOnInit(): void {
    this.clienteService.listar().subscribe(data => {
      this.clientes = data;
    });
  }

  eliminar(id: number): void {
    this.clienteService.eliminar(id).subscribe(() => {
      this.clientes = this.clientes.filter(c => c.id !== id);
    });
  }
}
