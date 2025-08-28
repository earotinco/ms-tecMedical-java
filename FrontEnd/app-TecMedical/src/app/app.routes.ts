import { Routes } from '@angular/router';

//import { provideRouter } from '@angular/router';
import { VentaListComponent } from './components/venta-list/venta-list.component';

export const routes: Routes = [

  { path: '', redirectTo: 'ventas', pathMatch: 'full' },
  { path: 'ventas', component: VentaListComponent
   },

];


//export const appRouterProviders = provideRouter(routes);
