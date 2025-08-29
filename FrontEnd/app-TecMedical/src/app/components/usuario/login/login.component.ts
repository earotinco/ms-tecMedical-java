import { Component } from '@angular/core';
import { UsuarioService } from '../../../services/usuario.service';
import { Usuario } from '../../../models/usuario.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  usuario: Usuario = { username: '', password: '' };

  constructor(private usuarioService: UsuarioService, private router: Router ) {}

  login(): void {
    this.usuarioService.login(this.usuario).subscribe({
      next: res => {
        console.log(' Login exitoso', res);
        alert('Bienvenido');
        this.router.navigate(['/ventas']);
      },
      error: err => {
        console.error(' Error de login', err);
          alert(`Error ${err.status}: ${err.error?.message || err.message}`);
      }
    });
  }
}
