import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Cliente } from '../models/cliente.model';
import { Observable } from 'rxjs';

import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

private apiUrl = 'http://localhost:8080/api/clientes';

  private username = 'gloria_vend';
  private password = '123456';



  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = btoa(`${this.username}:${this.password}`);
    return new HttpHeaders({
      Authorization: `Basic ${token}`,
    });
  }

  listar(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.apiUrl, {
          headers: this.getAuthHeaders(),
        });
  }

  crear(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(this.apiUrl, cliente, {
        headers: this.getAuthHeaders(),
      });
  }


   eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  actualizar(id: number, cliente: Cliente): Observable<Cliente> {
    
    return this.http.put<Cliente>(`${this.apiUrl}/${id}`, cliente);
  }

  buscarPorId(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.apiUrl}/${id}`);
  }
}
