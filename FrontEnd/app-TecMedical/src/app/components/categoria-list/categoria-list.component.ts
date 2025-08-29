import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../../services/categoria.service';
import {Router} from '@angular/router';
import { Categoria } from '../../models/categoria.model';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-categoria-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './categoria-list.component.html',
  styleUrls: ['./categoria-list.component.css']
})
export class CategoriaListComponent implements OnInit{
  categorias: Categoria[] = [];
  mostrarTodas: boolean = false;
  constructor(private categoriaService: CategoriaService) {}

  listar(): void {
    if (this.mostrarTodas) {
      this.categoriaService.listarTodas().subscribe(data => {
        this.categorias = data;
      });
    } else {
      this.categoriaService.listarActivas().subscribe(data => {
        this.categorias = data;
      });
    }
  }

  ngOnInit():void{
    this.listar();
  }
  toggleFiltro(): void {
    this.mostrarTodas = !this.mostrarTodas;
    this.listar();
  }

  desactivar(id: number): void {
    this.categoriaService.desactivar(id).subscribe(() => {
      this.listar();
    });
  }

  activar(id: number): void {
    this.categoriaService.activar(id).subscribe(() => {
      this.listar();
    });
  }
}