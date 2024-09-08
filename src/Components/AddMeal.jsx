import React, { useState } from "react";
import { addmeal } from '../Services/MealService'; 

const AddMeal = () => {
  const [userId, setUserId] = useState("");
  const [mealDate, setMealDate] = useState("");
  const [mealTime, setMealTime] = useState("");
  const [caloriesConsumed, setCaloriesConsumed] = useState("");
  const [message, setMessage] = useState('');
  const [errors, setErrors] = useState({});

  const validate = () => {
    let tempErrors = {};
    if (!userId) tempErrors.userId = "User ID is required";
    if (!mealDate) tempErrors.mealDate = "Meal date is required";
    if (!mealTime) tempErrors.mealTime = "Meal time is required";
    if (!caloriesConsumed) tempErrors.caloriesConsumed = "Calories consumed is required";
    setErrors(tempErrors);
    return Object.keys(tempErrors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validate()) {
      const newMeal = {
        userId,
        mealDate,
        mealTime,
        caloriesConsumed,
      };

      // Submit form data using your service function
      addmeal(newMeal)
        .then((response) => {
          console.log("Meal added successfully", response.data);
          setMessage(`Meal added successfully for user id ${response.data.userId}`);
          // Reset form
          setUserId("");
          setMealDate("");
          setMealTime("");
          setCaloriesConsumed("");
        })
        .catch((error) => {
          console.error("Error adding meal", error);
        });
    }
  };

  return (
    <div className="container">
      <h2>Add Meal</h2>
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
          <label>Meal Date</label>
          <input
            type="date"
            className="form-control w-50"
            value={mealDate}
            onChange={(e) => setMealDate(e.target.value)}
            pattern="\d{4}-\d{2}-\d{2}"
            placeholder="YYYY-MM-DD"
          />
          {errors.mealDate && <span className="text-danger">{errors.mealDate}</span>}
        </div>
        <div className="form-group">
          <label>Meal Time</label>
          <input
            type="time"
            className="form-control w-50"
            value={mealTime}
            onChange={(e) => setMealTime(e.target.value)}
          />
          {errors.mealTime && <span className="text-danger">{errors.mealTime}</span>}
        </div>
        <div className="form-group">
          <label>Calories Consumed (kcal)</label>
          <input
            type="number"
            className="form-control w-50"
            value={caloriesConsumed}
            onChange={(e) => setCaloriesConsumed(e.target.value)}
            placeholder="in kcal"
          />
          {errors.caloriesConsumed && <span className="text-danger">{errors.caloriesConsumed}</span>}
        </div >
        <div>
        <button type="submit" className="btn btn-primary">
          Add Meal
        </button>
        </div>
        {message && <p className="text-success mt-2">{message}</p>}
      </form>
    </div>
  );
};

export default AddMeal;
