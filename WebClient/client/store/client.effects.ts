import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import { of } from 'rxjs';
import * as ClientActions from './client.actions';
import { ClientService } from '../services/client.service';

@Injectable()
export class ClientEffects {

    loadClient$ = createEffect(() =>
        this.actions$.pipe(
            ofType(ClientActions.loadClient),
            mergeMap(() =>
                this.clientService.load().pipe(
                    map(() => ClientActions.loadClientSuccess()),
                    catchError((error) => of(ClientActions.loadClientFailure({ error: error.message })))
                )
            )
        )
    );

    constructor(
        private actions$: Actions,
        private clientService: ClientService
    ) {}
}