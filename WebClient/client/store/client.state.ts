export interface ClientState {
    isLoading: boolean;
    error: string | null;
}

export const initialClientState: ClientState = {
    isLoading: false,
    error: null
};