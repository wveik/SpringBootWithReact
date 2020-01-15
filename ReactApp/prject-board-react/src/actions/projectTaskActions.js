import axios from "axios";
import {GET_ERRORS, GET_PROJECT_TASKS, DELETE_PROJECT_TASK} from "./types";

export const addProjectTask = (project_task, history) => async dispatch => {
    try {
        await axios.post("http://localhost:8080/v1/api/board", project_task);
        history.push("/");

        dispatch({type: GET_ERRORS, payload: {}});
    } catch (error) {
        var data = null;

        if (error.response.data != null) 
            data = error.response.data;
        
        dispatch({type: GET_ERRORS, payload: data});
    }
};

export const getBacklog = () => async dispatch => {
    const res = await axios.get("http://localhost:8080/v1/api/board/all");
    dispatch({type: GET_PROJECT_TASKS, payload: res.data});
};

export const deleteProjectTask = pt_id => async dispatch => {
    if (window.confirm(`You are deleting project task ${pt_id}, this action cannot be undone`)) {
        await axios.delete(`http://localhost:8080/v1/api/board/${pt_id}`);
        dispatch({type: DELETE_PROJECT_TASK, payload: pt_id});
    }
};