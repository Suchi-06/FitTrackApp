import React, { useState } from "react";
import { listUserActivitiesByDate } from '../Services/ActivityService'; // Assuming you have this service function

const ListUserActivitiesByDate = () => {
  const [activityDate, setActivityDate] = useState("");
  const [activities, setActivities] = useState([]);
  const [message , setMessage ] = useState('');
  const [error, setError] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!activityDate) {
      setError("Activity date is required");
      return;
    }

    // Fetch activities by date
    listUserActivitiesByDate(activityDate)
      .then((response) => {
        setActivities(response.data);
        setMessage(`List of Activities fetched successfully`);
        setError(""); // Clear any errors
      })
      .catch((error) => {
        console.error("Error fetching activities", error);
        setMessage(`Error in fetching data`);
        setError("Error fetching activities");
      });
  };

  return (
    <div className="container">
      <h2>List User Activities Leader Board</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Activity Date</label>
          <input
            type="date"
            className="form-control w-50"
            value={activityDate}
            onChange={(e) => setActivityDate(e.target.value)}
            
            placeholder="YYYY-MM-DD"
          />
          {error && <span className="text-danger">{error}</span>}
        </div>
        <button type="submit" className="btn btn-primary mt-2">Find Activities</button>
      </form>

      {activities.length > 0 && (
        <table className="table mt-4">
          <thead>
            <tr>
              <th>User Name</th>
              <th>Activity Name</th>
              <th>Duration (Minutes)</th>
              <th>Calories Burned</th>
            </tr>
          </thead>
          <tbody>
            {activities.map((activity) => (
              <tr key={activity.activityDate}>
                <td>{activity.userName}</td>
                <td>{activity.activityName}</td>
                <td>{activity.durationMinutes}</td> 
                <td>{activity.caloriesBurned}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default ListUserActivitiesByDate;

