import { Component } from '@angular/core';
import { UsuarioService } from '../../../services/usuario.service';
import { Usuario } from '../../../models/usuario.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'] 
})
export class LoginComponent {

  usuario: Usuario = { usuario: '', password: '' };

  constructor(private usuarioService: UsuarioService) {}

  login(): void {
    this.usuarioService.login(this.usuario).subscribe({
      next: res => {
        console.log(' Login exitoso', res);
        alert('Bienvenido');
      },
      error: err => {
        console.error(' Error de login', err);
        alert('Credenciales incorrectas');
      }
    });
  }
}
