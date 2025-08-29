import { Routes } from '@angular/router';

export const routes: Routes = [
  // Redirección inicial
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  //  Usuario - Login
  {
    path: 'login',
    loadComponent: () =>
      import('./components/usuario/login/login.component').then(m => m.LoginComponent)
  },

  // Cliente - Listar
  {
    path: 'clientes',
    loadComponent: () =>
      import('./components/cliente/cliente-listar/cliente-listar.component').then(m => m.ClienteListarComponent)
  },

  //  Cliente - Crear
  {
    path: 'clientes/nuevo',
    loadComponent: () =>
      import('./components/cliente/cliente-form/cliente-form.component').then(m => m.ClienteFormComponent)
  },

  //  Ventas - Listar
  {
    path: 'ventas',
    loadComponent: () =>
      import('./components/venta-list/venta-list.component').then(m => m.VentaListComponent)
  },

  //  Ventas - Detalle
  {
    path: 'ventas/:id',
    loadComponent: () =>
      import('./components/venta-detail/venta-detail.component').then(m => m.VentaDetailComponent)
  }
];


