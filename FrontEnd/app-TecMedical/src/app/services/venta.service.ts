import { Injectable } from '@angular/core';


/* -------inicio ------- */
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';



export interface DetalleVentaRequest {
  productoId: number;
  cantidad: number;
  precioUnitario: number;
}

export interface VentaRequest {
  clienteId: number;
  usuarioId: number;
  detalles: DetalleVentaRequest[];
}

export interface DetalleVentaResponse {
  idDetalle: number;
  producto: string;
  cantidad: number;
  precioUnitario: number;
  subtotal: number;
}

export interface VentaResponse {
  idVenta: number;
  cliente: string;
  vendedor: string;
  fecha: string; // Angular maneja string de LocalDateTime
  total: number;
  detalles: DetalleVentaResponse[];
}

/* ------- cierre------- */

@Injectable({
  providedIn: 'root'
})
export class VentaService {


   private apiUrl = 'http://localhost:8080/api/ventas';
  private username = 'gloria_vend'; 
  private password = '123456';

  constructor(private http: HttpClient) { }


  private getAuthHeaders(): HttpHeaders {
    const token = btoa(`${this.username}:${this.password}`);
    return new HttpHeaders({
      'Authorization': `Basic ${token}`
    });
  }


  listarVentas(): Observable<VentaResponse[]> {
    return this.http.get<VentaResponse[]>(this.apiUrl, { headers: this.getAuthHeaders() });
  }


  buscarPorId(id: number): Observable<VentaResponse> {
    return this.http.get<VentaResponse>(`${this.apiUrl}/${id}`, { headers: this.getAuthHeaders() });
  }

   registrarVenta(venta: VentaRequest): Observable<VentaResponse> {
    return this.http.post<VentaResponse>(this.apiUrl, venta);
  }
}
