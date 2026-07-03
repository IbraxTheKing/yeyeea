import {createFeatureSelector, createSelector} from '@ngrx/store';
import {ClientState} from './client.state';

export const selectClientState = createFeatureSelector<ClientState>('client');

export const selectIsLoading = createSelector(
  selectClientState,
  (state: ClientState) => state.isLoading
);

export const selectError = createSelector(
  selectClientState,
  (state: ClientState) => state.error
);
