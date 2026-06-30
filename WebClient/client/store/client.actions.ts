import {createAction, props} from '@ngrx/store';

export const loadClient = createAction('[Client] Load Client');
export const loadClientSuccess = createAction('[Client] Load Client Success');
export const loadClientFailure = createAction(
  '[Client] Load Client Failure',
  props<{ error: string }>()
);
