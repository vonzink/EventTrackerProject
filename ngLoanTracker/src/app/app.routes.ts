import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { UnderwritingComponent } from './components/underwriting/underwriting';
import { StatusComponent }       from './components/status/status';
import { DocumentationComponent } from './components/documentation/documentation';

export const appRoutes: Routes = [
  { path: '',           component: Home },
  { path: 'uw/:id',     component: UnderwritingComponent },
  { path: 'status/:id', component: StatusComponent },
  { path: 'docs/:id',   component: DocumentationComponent },
  { path: '**',         redirectTo: '' }
];
