import React, { useState } from "react";
import { findUserActivitiesByUserId } from '../Services/ActivityService'; 

const FindUserActivitiesByUserId = () => {
  const [userId, setUserId] = useState("");
  const [activities, setActivities] = useState([]);
  const [message , setMessage ] = useState('');
  const [error, setError] = useState("");
  

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!userId) {
      setError("User ID is required");
      return;
    }

    // Fetch activities by user ID
    findUserActivitiesByUserId(userId)
      .then((response) => {
        setMessage(`User Activities fetched successfully for user`);
        setActivities(response.data);
        setError(""); // Clear any errors
      })
      .catch((error) => {
        console.error("Error fetching activities", error);
        setError("Error fetching activities");
      });
  };

  return (
    <div className="container">
      <h2>Find User Activities</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>User ID</label>
          <input
            type="number"
            className="form-control w-50"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
          />
          {error && <span className="text-danger">{error}</span>}
        </div>
        <button type="submit" className="btn btn-primary mt-2">Find Activities</button>
      </form>

      {activities.length > 0 && (
        <table className="table mt-4">
          <thead>
            <tr>
              <th>Activity ID</th>
              <th>Activity Name</th>
              <th>Activity Date</th>
              <th>Calories Burned</th>
              <th>Duration</th>
            </tr>
          </thead>
          <tbody>
            {activities.map((activity) => (
              <tr key={activity.activityId}>
                <td>{activity.activityId}</td>
                <td>{activity.activityName}</td>
                <td>{activity.activityDate}</td>
                <td>{activity.caloriesBurned}</td>
                <td>{activity.duration}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
      {message && <p className="text-success mt-2">{message}</p>}
     
    </div>
  );
};

export default FindUserActivitiesByUserId;
