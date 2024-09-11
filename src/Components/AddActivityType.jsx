import React, { useState } from "react";
import { addActivityType } from '../Services/ActivityService'; 

const AddActivityType = () => {
  const [activityName, setActivityName] = useState("");
  const [message, setMessage] = useState('');
  const [error, setError] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!activityName) {
      setError("Activity name is required");
      return;
    }

    const newActivity = {
      description: activityName,
    };

    // Submit form data to the API
    addActivityType(newActivity)
      .then((response) => {
        console.log("Activity added successfully", response.data);
        setMessage(`${response.data.description} Activity Type added with activity type id ${response.data.activityTypeId}`);
        setActivityName(""); // Reset form
        setError(""); // Clear any errors
      })
      .catch((error) => {
        console.error("Error adding activity", error);
        setError("Error adding Activity Type")
      });
  };

  return (
    <div className="container">
      <h2>Add New Activity Type</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Enter the Activity Type to be Added</label>
          <input
            type="text"
            className="form-control w-50"
            value={activityName}
            onChange={(e) => setActivityName(e.target.value)}
          />
          {error && <span className="text-danger">{error}</span>}
        </div>
        <div>
        <button type="submit" className="btn btn-primary mt-2">Add Activity</button>
        </div>
        {message && <p className="text-success mt-2">{message}</p>}
      </form>
    </div>
  );
};

export default AddActivityType;

