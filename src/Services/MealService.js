import axios from "axios";

const url1 = 'http://localhost:8080/api/meals/addmeal';
export const addmeal = (meal) => axios.post(url1,meal);
