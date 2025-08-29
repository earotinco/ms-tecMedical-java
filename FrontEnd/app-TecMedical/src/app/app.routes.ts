import { Routes } from '@angular/router';


export const routes: Routes = [

  { path: '', redirectTo: 'ventas', pathMatch: 'full' },

{
  path: 'ventas',
  loadComponent: () =>
    import('./components/venta-list/venta-list.component').then(m => m.VentaListComponent)
},
{

  path: 'ventas/nueva',
    loadComponent: () =>
      import('./components/venta-form/venta-form.component').then(m => m.VentaFormComponent)
  },


{
    path: 'ventas/:id',
    loadComponent: () =>
      import('./components/venta-detail/venta-detail.component').then(m => m.VentaDetailComponent)
  }



];


