import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import { RootState } from "../../store/store";

interface usernameState {
    username: string;
}

const initialState: usernameState = {
    username: '',
};

export const usernameSlice = createSlice({
    name: 'username',
    initialState,
    reducers: {
        setUsername: (state, action: PayloadAction<string>) => {
            state.username = action.payload;
        },
    },
});

export const {setUsername} = usernameSlice.actions;
export default usernameSlice.reducer;
export const selectUsername = (state: RootState) => state.username.username;