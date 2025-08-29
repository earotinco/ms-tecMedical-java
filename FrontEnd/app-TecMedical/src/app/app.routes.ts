import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'usuario/login', pathMatch: 'full' },

  // Usuario - Login
   {
    path: 'login',
    loadComponent: () =>
      import('./components/usuario/login/login.component').then(m => m.LoginComponent)
  },

  //  Cliente - Listar
  {
    path: 'clientes',
    loadComponent: () =>
      import('./components/cliente/cliente-listar/cliente-listar.component').then(m => m.ClienteListarComponent)
  },

  // Cliente - Crear
  {
    path: 'clientes/nuevo',
    loadComponent: () =>
      import('./components/cliente/cliente-form/cliente-form.component').then(m => m.ClienteFormComponent)
  },

  {
    path: 'ventas',
    loadComponent: () =>
      import('./components/venta-list/venta-list.component').then(
        (m) => m.VentaListComponent
      ),
  },

  {
    path: 'ventas/nuevo',
    loadComponent: () =>
      import('./components/venta-form/venta-form.component').then(
        (m) => m.VentaFormComponent
      ),
  },

  //  Ventas - Detalle
  {
    path: 'ventas/:id',
    loadComponent: () =>
      import('./components/venta-detail/venta-detail.component').then(
        (m) => m.VentaDetailComponent
      ),
  },
  {
    path: 'categorias',
    loadComponent: () =>
      import('./components/categoria-list/categoria-list.component').then(
        (m) => m.CategoriaListComponent
      ),
  },
];
