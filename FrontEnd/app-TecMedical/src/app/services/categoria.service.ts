import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Categoria } from '../models/categoria.model';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {
  private apiUrl = 'http://localhost:8080/api/categorias';
  private username = 'gloria_vend';
  private password = '123456';
  constructor(private http: HttpClient) { }

  private getAuthHeaders(): HttpHeaders {
    const token = btoa(`${this.username}:${this.password}`);
    return new HttpHeaders({
      Authorization: `Basic ${token}`,
    });
  }

  listarActivas(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(this.apiUrl);
  }
  
  listarTodas(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(`${this.apiUrl}/todos`, {
      headers: this.getAuthHeaders(),
    });
  }

  crear(categoria: Categoria): Observable<Categoria> {
    return this.http.post<Categoria>(this.apiUrl, categoria, {
      headers: this.getAuthHeaders(),
    });
  }
  buscarPorId(id: number): Observable<Categoria> {
    return this.http.get<Categoria>(`${this.apiUrl}/${id}`, {
      headers: this.getAuthHeaders(),
    });
  }

  desactivar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, {
      headers: this.getAuthHeaders(),
    });
  }

  activar(id: number): Observable<void> {
  return this.http.put<void>(`${this.apiUrl}/${id}/activar`, {}, {
    headers: this.getAuthHeaders(),
  });
}
}

