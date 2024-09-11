import React, { useState, useEffect } from 'react';
import { addgoal } from '../Services/GoalService';
import { activitytypelist } from '../Services/ActivityService'; 

const AddGoal = () => {
  const [userId, setUserId] = useState('');
  const [activityTypeId, setActivityTypeId] = useState('');
  const [durationMinutes, setDurationMinutes] = useState('');
  const [activityTypes, setActivityTypes] = useState([]);
  const [message, setMessage] = useState('');
  const [errors, setErrors] = useState({});

  // Fetch activity types from your custom API function
  useEffect(() => {
    activitytypelist()
      .then(response => {
        console.log(response.data);
        setActivityTypes(response.data);
      })
      .catch(error => {
        console.error('Error fetching activity types:', error);
      });
  }, []);

  // Form validation
  const validate = () => {
    const newErrors = {};
    if (!userId) newErrors.userId = 'User ID is required';
    if (!activityTypeId) newErrors.activityTypeId = 'Activity type is required';
    if (!durationMinutes) {
      newErrors.durationMinutes = 'Duration is required';
    } else if (isNaN(durationMinutes) || durationMinutes <= 0) {
      newErrors.durationMinutes = 'Duration must be a positive number';
    }
    setErrors(newErrors);
    console.log("Validation errors:", newErrors); 
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validate()) {
      // Call your addgoal function to save the goal
      const goal = {
        userId,
        activityTypeId,
        durationMinutes,
      };
      console.log("Submitting goal:", goal);
      addgoal(goal)
        .then(response => {
          setMessage(`Goal added successfully for user with id ${response.data.userId}`);
          console.log('Goal saved successfully', response.data);
          // Reset form fields
          setUserId('');
          setActivityTypeId('');
          setDurationMinutes('');
          setErrors({});
        })
        .catch(error => {
          console.error('Error saving goal:', error);
          
          if (error.response && error.response.data) {
            setErrors({ general: error.response.data.message });
          } else {
            setErrors({ general: 'Error saving goal' });
          }
        });
    }
  };

  return (
    <div className="container mt-4">
      <h2>Add Goal</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="userId">User ID</label>
          <input
            type="text"
            className={`form-control w-50 ${errors.userId ? 'is-invalid' : ''}`}
            id="userId"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
          />
          {errors.userId && <div className="invalid-feedback">{errors.userId}</div>}
        </div>

        <div className="form-group">
          <label htmlFor="activityTypeId">Activity Type</label>
          <select
            className={`form-control w-50 ${errors.activityTypeId ? 'is-invalid' : ''}`}
            id="activityTypeId"
            value={activityTypeId}
            onChange={(e) => setActivityTypeId(e.target.value)}
          >
            <option value="">Select Activity</option>
            {activityTypes.map((activity) => (
              <option key={activity.activityTypeId} value={activity.activityTypeId}>
                {activity.description}
              </option>
            ))}
          </select>
          {errors.activityTypeId && <div className="invalid-feedback">{errors.activityTypeId}</div>}
        </div>

        <div className="form-group">
          <label htmlFor="durationMinutes">Duration (in minutes)</label>
          <input
            type="number"
            className={`form-control w-50 ${errors.durationMinutes ? 'is-invalid' : ''}`}
            id="durationMinutes"
            value={durationMinutes}
            onChange={(e) => setDurationMinutes(e.target.value)}
          />
          {errors.durationMinutes && <div className="invalid-feedback">{errors.durationMinutes}</div>}
        </div>
         <div>
        <button type="submit" className="btn btn-primary mt-3">Add Goal</button>
        </div>
        {message && <p className="text-success mt-2">{message}</p>}
      </form>
    </div>
  );
};

export default AddGoal;
