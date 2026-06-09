import { Routes } from '@angular/router';
import { provideState } from '@ngrx/store';
import { provideEffects } from '@ngrx/effects';
import { clientReducer } from './store/client.reducer';
import { ClientEffects } from './store/client.effects';

export const ClientRoutes: Routes = [
    {
        path: '',
        loadComponent: () => import('./components/client/client.component').then(m => m.ClientComponent),
        providers: [
            provideState('client', clientReducer),
            provideEffects(ClientEffects)
        ]
    }
];