import React, { useState, useEffect } from "react";
import { activitytypelist, adduseractivity } from '../Services/ActivityService'; 

const AddUserActivity = () => {
  const [userId, setUserId] = useState("");
  const [activityType, setActivityType] = useState("");
  const [activityDate, setActivityDate] = useState("");
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");
  const [caloriesBurned, setCaloriesBurned] = useState("");
  const [activityTypes, setActivityTypes] = useState([]);
  const [message, setMessage] = useState('');
  const [errors, setErrors] = useState({});

  useEffect(() => {
    // Fetch the list of activities from your service
    activitytypelist()
      .then((response) => {
        console.log(response.data);
        setActivityTypes(response.data); 
      })
      .catch((error) => {
        console.error("Error fetching activity types", error);
      });
  }, []);

  const validate = () => {
    let tempErrors = {};
    if (!userId) tempErrors.userId = "User ID is required";
    if (!activityType) tempErrors.activityType = "Activity type is required";
    if (!activityDate) tempErrors.activityDate = "Activity date is required";
    if (!startTime) tempErrors.startTime = "Start time is required";
    if (!endTime) tempErrors.endTime = "End time is required";
    if (!caloriesBurned) tempErrors.caloriesBurned = "Calories burned is required";
    setErrors(tempErrors);
    return Object.keys(tempErrors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validate()) {
      const newActivity = {
        userId,
        activityTypeId: activityType,
        activityDate,
        startTime,
        endTime,
        caloriesBurned,
      };

      // Submit form data using your service function
      adduseractivity(newActivity)
        .then((response) => {
          console.log("Activity added successfully", response.data);
          setMessage(`Activity added for the user id: ${response.data.userId}`);
          // Reset form
          setUserId("");
          setActivityType("");
          setActivityDate("");
          setStartTime("");
          setEndTime("");
          setCaloriesBurned("");
        })
        .catch((error) => {
          console.error("Error adding activity", error);
          setErrors("Error adding user activity");
        });
    }
  };

  return (
    <div className="container">
      <h2>Add User Activity</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>User ID</label>
          <input
            type="number"
            className="form-control w-50"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
          />
          {errors.userId && <span className="text-danger">{errors.userId}</span>}
        </div>
        <div className="form-group">
          <label>Activity Type</label>
          <select
            className="form-control w-50"
            value={activityType}
            onChange={(e) => setActivityType(e.target.value)}
          >
            <option value="">Select an Activity</option>
            {activityTypes.map((type) => (
              <option key={type.activityTypeId} value={type.activityTypeId}>
                {type.description}
              </option>
            ))}
          </select>
          {errors.activityType && <span className="text-danger">{errors.activityType}</span>}
        </div>
        <div className="form-group">
          <label>Activity Date</label>
          <input
            type="date"
            className="form-control w-50"
            value={activityDate}
            onChange={(e) => setActivityDate(e.target.value)}
           
            placeholder="YYYY-MM-DD"
          />
          {errors.activityDate && <span className="text-danger">{errors.activityDate}</span>}
        </div>
        <div className="form-group">
          <label>Start Time</label>
          <input
            type="time"
            className="form-control w-50"
            value={startTime}
            onChange={(e) => setStartTime(e.target.value)}
          />
          {errors.startTime && <span className="text-danger">{errors.startTime}</span>}
        </div>
        <div className="form-group">
          <label>End Time</label>
          <input
            type="time"
            className="form-control w-50"
            value={endTime}
            onChange={(e) => setEndTime(e.target.value)}
          />
          {errors.endTime && <span className="text-danger">{errors.endTime}</span>}
        </div>
        <div className="form-group">
          <label>Calories Burned (kcal)</label>
          <input
            type="number"
            className="form-control w-50"
            value={caloriesBurned}
            onChange={(e) => setCaloriesBurned(e.target.value)}
            placeholder="in kcal"
          />
          {errors.caloriesBurned && <span className="text-danger">{errors.caloriesBurned}</span>}
        </div>
        <div>
        <button type="submit" className="btn btn-primary">
          Add Activity
        </button>
        </div>
        {message && <p className="text-success mt-2">{message}</p>}
      </form>
    </div>
  );
};

export default AddUserActivity;
