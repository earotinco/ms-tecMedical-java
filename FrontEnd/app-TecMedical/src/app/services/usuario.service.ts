import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../models/usuario.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
 private loginUrl = 'http://localhost:8080/api/auth/login';

 /**/

 private username: string = '';
  private password: string = '';

  /**/
  constructor(private http: HttpClient) {}

  login(usuario: Usuario): Observable<any> {
    return this.http.post<any>(this.loginUrl, usuario);
  }
}
