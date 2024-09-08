import axios from "axios";

const url1 = 'http://localhost:8080/api/goals/addgoal';
export const addgoal = (goal) => axios.post(url1,goal);


const url2 = 'http://localhost:8080/api/findgoalslistbyuserid';
export const findGoalsByUserId = (userId) =>  axios.get(`${url2}?userId=${userId}`); 
