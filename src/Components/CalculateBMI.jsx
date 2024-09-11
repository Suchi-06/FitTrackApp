import React, { useState } from 'react';
import { calculatebmi } from '../Services/UserService';
import 'bootstrap/dist/css/bootstrap.min.css';

const CalculateBMI = () => {
    const [userId, setUserId] = useState('');
    const [result, setResult] = useState(null);
    const [message, setMessage]= useState('');
    const [error, setError] = useState('');
    const loggedInUserId = localStorage.getItem('userId');

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        if (!userId) {
            setError('User ID is mandatory');
            return;
        }
        
        if (userId !== loggedInUserId) {
            setError('You can only calculate BMI for your own user ID.');
            return;
        }
        setError('');
        
        try {
            const response = await calculatebmi(userId) ;
            setResult(response.data);
            setMessage(`BMI Report fetched successfully for userId: ${response.data.userId}`);
        } catch (err) {
            console.error('Error fetching bmi report: ',error);
            setError('Error fetching BMI data');
            setMessage('User not Found');
        }
    };

    return (
        <div className="container mt-4">
            <h2>Calculate BMI</h2>
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
                    {error && <div className="text-danger mt-2">{error}</div>}
                </div>
                <button type="submit" className="btn btn-primary" >Submit</button>
            </form>
             {message && <p className='text-success'>{message}</p>}
            {result && (
                <div className="card">
                    <div className="card-body">
                        <h5 className="card-title">BMI Result</h5>
                        <p className="card-text"><strong>User ID:</strong> {result.userId}</p>
                        <p className="card-text"><strong>User Name:</strong> {result.userName}</p>
                        <p className="card-text"><strong>BMI:</strong> {result.bmi}</p>
                        <p className="card-text"><strong>Health Status:</strong> {result.healthStatus}</p>
                    </div>
                </div>
            )}
        </div>
    );
};

export default CalculateBMI;

