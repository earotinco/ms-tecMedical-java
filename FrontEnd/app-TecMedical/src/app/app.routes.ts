import { Routes } from '@angular/router';
import { MainLayoutComponent } from './layouts/main-layout/main-layout.component';
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';

export const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
     { path: 'ventas', loadComponent: () => import('./components/venta-list/venta-list.component').then(m => m.VentaListComponent) },
      { path: 'ventas/nuevo', loadComponent: () => import('./components/venta-form/venta-form.component').then(m => m.VentaFormComponent) },
      { path: 'ventas/:id', loadComponent: () => import('./components/venta-detail/venta-detail.component').then(m => m.VentaDetailComponent) },
      { path: 'clientes', loadComponent: () => import('./components/cliente/cliente-listar/cliente-listar.component').then(m => m.ClienteListarComponent) },
      { path: 'clientes/nuevo', loadComponent: () => import('./components/cliente/cliente-form/cliente-form.component').then(m => m.ClienteFormComponent) },
      { path: 'categorias', loadComponent: () => import('./components/categoria-list/categoria-list.component').then(m => m.CategoriaListComponent) },
    ]
  },
  {
    path: '',
    component: AuthLayoutComponent,
    children: [
      { path: 'login', loadComponent: () => import('./components/usuario/login/login.component').then(m => m.LoginComponent) }
    ]
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];
