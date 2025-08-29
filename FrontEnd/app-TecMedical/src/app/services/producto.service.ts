

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Categoria } from '../models/categoria.model';

export interface Producto {
  id: number;
  nombre: string;
  precio: number;
}

export interface ProductoFull
{
  idProducto?: number;
  nombre: string;
  descripcion?: string;
  precio: number;
  stock: number;
  estado?:boolean;
  categoria: Categoria;
}

@Injectable({
  providedIn: 'root'
})
export class ProductoService {
  private apiUrl = 'http://localhost:8080/api/productos';
  private username = 'gloria_vend';
  private password = '123456';

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = btoa(`${this.username}:${this.password}`);
    return new HttpHeaders({
      Authorization: `Basic ${token}`,
    });
  }

  listarProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.apiUrl);
  }

  listarTodos(): Observable<ProductoFull[]> {
    return this.http.get<ProductoFull[]>(`${this.apiUrl}/todos`,
      {headers: this.getAuthHeaders()}
    );
  }

  crear(producto: Producto): Observable<Producto> {
    return this.http.post<Producto>(this.apiUrl, producto, {
      headers: this.getAuthHeaders()
    });
  }

  buscarPorId(id: number): Observable<Producto> {
    return this.http.get<Producto>(`${this.apiUrl}/${id}`, {
      headers: this.getAuthHeaders()
    });
  }

  desactivar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, {
      headers: this.getAuthHeaders()
    });
  }
}
