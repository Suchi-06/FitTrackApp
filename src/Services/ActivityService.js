import axios from "axios";

const url1 ='http://localhost:8080/api/useractivitylistbyuserid';
export const findUserActivitiesByUserId = (userId) =>  axios.get(`${url1}?userId=${userId}`); 

const url2 ='http://localhost:8080/api/getallActivitiesList';
export const activitytypelist = () => axios.get(url2);

const url3 = 'http://localhost:8080/api/adduseractivity';
export const adduseractivity = (activity) => axios.post(url3,activity);

const url4 = 'http://localhost:8080/api/addactivitytypetolist';
export const addActivityType = (activity) => axios.post(url4, activity);

const url5 = 'http://localhost:8080/api/useractivitylistbydate';
export const listUserActivitiesByDate = (activityDate) => axios.get(`${url5}?date=${activityDate}`);







