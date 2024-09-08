import React, { useState } from 'react';
import { findGoalsByUserId } from '../Services/GoalService'; 

const FindGoalsByUserId = () => {
  const [userId, setUserId] = useState('');
  const [goals, setGoals] = useState([]);
  const [errors, setErrors] = useState('');
  const [message, setMessage] = useState('');
  const [loading, setLoading] = useState(false);
  

  // Form validation
  const validate = () => {
    if (!userId) {
      setErrors('User ID is required');
      return false;
    }
    setErrors('');
    return true;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validate()) {
      setLoading(true);
      // Fetch goals by user ID
      findGoalsByUserId(userId)
        .then((response) => {
          setGoals(response.data);
          setMessage(`Goals fetched successfully for user`);
          setLoading(false);
        })
        .catch((error) => {
          console.error('Error fetching goals:', error);
          setLoading(false);
        });
    }
  };

  return (
    <div className="container mt-4">
      <h2>Find Goals by User ID</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="userId">User ID</label>
          <input
            type="text"
            className={`form-control w-25 ${errors ? 'is-invalid' : ''}`}
            id="userId"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
          />
          {errors && <div className="invalid-feedback">{errors}</div>}
        </div>

        <button type="submit" className="btn btn-primary mt-3">
          {loading ? 'Loading...' : 'Find Goals'}
        </button>
      </form>

      {goals.length > 0 && (
        <div className="mt-4">
          <h3>Goals List</h3>
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Goal Id</th>
                <th>Activity Name</th>
                <th>Duration (minutes)</th>
              </tr>
            </thead>
            <tbody>
              {goals.map((goal) => (
                <tr key={goal.goalId}>
                  <td>{goal.goalId}</td>
                  <td>{goal.activityName}</td>
                  <td>{goal.duration}</td>
                </tr>
              ))}
            </tbody>
          </table>
          {message && <p className="text-success mt-2">{message}</p>}
        </div>
      )}

     
    </div>
  );
};

export default FindGoalsByUserId;
