import { createReducer, on } from '@ngrx/store';
import { ClientState, initialClientState } from './client.state';
import * as ClientActions from './client.actions';

export const clientReducer = createReducer(
    initialClientState,

    on(ClientActions.loadClient, (state): ClientState => ({
        ...state,
        isLoading: true,
        error: null
    })),

    on(ClientActions.loadClientSuccess, (state): ClientState => ({
        ...state,
        isLoading: false
    })),

    on(ClientActions.loadClientFailure, (state, { error }): ClientState => ({
        ...state,
        isLoading: false,
        error
    }))
);