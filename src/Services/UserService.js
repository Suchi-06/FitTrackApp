import axios from "axios";

const url1='http://localhost:8080/api/login';
export const login = (userEmail, userPassword) => axios.post(url1, { userEmail, userPassword});

const url2='http://localhost:8080/api/registeruser';
export const register = (user) => axios.post(url2, user);

const url3='http://localhost:8080/api/updateuser';
export const updateUser = (user) => axios.post(url3,user);

const url4='http://localhost:8080/api/findbyuserid';
export const getUser = (userId) => axios.get(`${url4}?userId=${userId}`);

const url5='http://localhost:8080/api/bmi';
export const calculatebmi = (userId) => axios.get(`${url5}?userId=${userId}`);

const url6='http://localhost:8080/api/userreport';
export const fetchUserReport = (userId, date) => axios.get(`${url6}?userId=${userId}&date=${date}`);

const url7='http://localhost:8080/api/checkuseremail';
export const checkuseremail = (userEmail) => axios.get(url7,userEmail);




