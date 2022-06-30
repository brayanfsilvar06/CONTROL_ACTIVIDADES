import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CtrlActividadComponent } from './Actividades/ctrl-actividad/ctrl-actividad.component';

const routes: Routes = [
  {
    path: 'Actividades/CtrlActividad',
    component: CtrlActividadComponent
  },
  {
    path: '',
    component: CtrlActividadComponent,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
