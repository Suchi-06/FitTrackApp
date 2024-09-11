import React from 'react';
import { BrowserRouter as Router,Routes,Route,NavLink } from 'react-router-dom';
import Home from './Home';
import UpdateUserDetailsById from './UpdateUserDetailsById';
import AddUserActivity from './AddUserActivity';
import AddMeal from './AddMeal';
import AddActivityType from './AddActivityType';
import FindActivitiesByUserId from './FindActivitiesByUserId';
import ListUserActivitiesByDate from './ListUserActivitiesByDate';
import CalculateBMI from './CalculateBMI';
import UserReportByDateById from './UserReportByDateById';
import AddGoal from './AddGoal';
import FindGoalsByUserId from './FindGoalsByUserId';


const apiButtons = [
  {name: 'Home', path:'/home'},
  {name: 'Update User', path:'/updateuser'},
  {name: 'Calculate BMI by UserId', path:'/calculatebmi'},
  {name: 'Add Physical Activity', path:'/addactivity'},
  {name: 'Add User Activity', path:'/adduseractivity'},
  {name: 'Add Meal', path:'/addmeal'},
  {name: 'Add Goal', path:'/addgoal'},
  {name: 'List of User Activities by UserId', path:'/listactivitybyuserid'},
  {name: 'Leaderboard for Users', path:'/listactivitybydate'},
  {name: 'Generate User Report', path:'/report'},
  {name: 'List Goals by UserId', path:'/listgoalsbyuserid'}
];

const Dashboard = () => {

  return (
    <div className='app'>
        <div className='center-container'>
          {/* Left Side: API Calls */}
          <div className='api-calls'>
            {apiButtons.map((button, index) => (
              <NavLink 
                key={index} 
                to={button.path} 
                className={'api-button'}
              >
                {button.name}
              </NavLink>
            ))}
          </div>
          <div className='api-display'>
            <Routes>
              <Route path="/home" element={<Home />} />
              <Route path="/updateuser" element={<UpdateUserDetailsById />} />
              <Route path="/adduseractivity" element={<AddUserActivity />} />
              <Route path="/addmeal" element={<AddMeal />}/>
              <Route path="/addactivity" element={<AddActivityType />} />
              <Route path="/listactivitybyuserid" element={<FindActivitiesByUserId />} />
              <Route path="/listactivitybydate" element={<ListUserActivitiesByDate />} />
              <Route path="/calculatebmi" element={<CalculateBMI />} />
              <Route path="/report" element={<UserReportByDateById />} />
              <Route path="/addgoal" element={<AddGoal />} />
              <Route path="/listgoalsbyuserid" element={<FindGoalsByUserId />} />
              <Route path="/" element={<div><h2>Select an API to see results.</h2></div>} />
            </Routes>
          </div>
        </div>
      </div>
    )
}

export default Dashboard
