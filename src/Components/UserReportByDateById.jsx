import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { fetchUserReport } from '../Services/UserService';

const UserReportByDateById = () => {
    const [userId, setUserId] = useState('');
    const [date, setDate] = useState('');
    const [result, setResult] = useState(null);
    const [message, setMessage]= useState();
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!userId || !date) {
            setError('User ID and Date are mandatory');
            return;
        }

        setError('');

        try {
            const response = await fetchUserReport(userId, date);
            setResult(response.data);
            setMessage(`Report fetched for ${response.data.userName} for ${response.data.reportDate}`);
        } catch (err) {
            setError('Error fetching report');
            setMessage('Report not generated because of insufficient data.');
        }
    };

    return (
        <div className="container mt-4">
            <h2>User Report by Date and ID</h2>
            <form onSubmit={handleSubmit} className="mb-4">
                <div className="mb-3">
                    <label htmlFor="userId" className="form-label">User ID</label>
                    <input
                        type="text"
                        id="userId"
                        className="form-control w-25"
                        value={userId}
                        onChange={(e) => setUserId(e.target.value)}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="date" className="form-label">Date (YYYY-MM-DD)</label>
                    <input
                        type="date"
                        id="date"
                        className="form-control w-25"
                        value={date}
                        onChange={(e) => setDate(e.target.value)}
                        
                        placeholder="YYYY-MM-DD"
                    />
                </div>
                {error && <div className="text-danger mt-2">{error}</div>}
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
            {message && <p className='text-success'>{message}</p>}
            {result && (
                <div className="card">
                    <div className="card-body">
                        <h5 className="card-title">User Report</h5>
                        <p className="card-text"><strong>User ID:</strong> {result.userId}</p>
                        <p className="card-text"><strong>User Name:</strong> {result.userName}</p>
                        <p className="card-text"><strong>Report Date:</strong> {result.reportDate}</p>
                        <p className="card-text"><strong>Calories Consumed:</strong> {result.caloriesConsumed}</p>
                        <p className="card-text"><strong>Calories Burned:</strong> {result.caloriesBurned}</p>
                    </div>
                </div>
            )}
        </div>
    );
};

export default UserReportByDateById;
