import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'ventas', pathMatch: 'full' },

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
