import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { getUser, updateUser } from '../Services/UserService'; 
import { toast } from 'react-toastify';
import '../App.css';

const UpdateUserDetailsById = () => {
    const [userId, setUserId] = useState('');
    const [userName, setUserName] = useState('');
    const [userPassword, setUserPassword] = useState('');
    const [genderRefId, setGenderRefId] = useState('');
    const [userAge, setUserAge] = useState('');
    const [userHeight, setUserHeight] = useState('');
    const [userWeight, setUserWeight] = useState('');
    const [userEmail, setUserEmail] = useState('');
    const [errors, setErrors] = useState({});
    const [message, setMessage] = useState('');
    const navigate = useNavigate();
    const { id } = useParams(); 
    const loggedInUserId = localStorage.getItem('userId');

    useEffect(() => {
        if (id) {
            setUserId(id); 
            fetchUser(id);
        }
    }, [id]);

    const fetchUser = async (userId) => {
        setErrors(prevErrors => ({ ...prevErrors, fetchError: '' }));
        
        if (userId.trim() === '') {
            setErrors({ fetchError: 'User ID cannot be blank.' });
            return;
        }
        // Check if the input userId matches the logged-in userId
        if (userId !== loggedInUserId) {
            setErrors({ fetchError: 'You are not authorized to update this user.' });
            return;
        }
        try {
            const response = await getUser(userId); 
            const user = response.data;
            setUserName(user.userName);
            setUserPassword(user.userPassword);
            setGenderRefId(user.genderRefId);
            setUserAge(user.userAge);
            setUserHeight(user.userHeight);
            setUserWeight(user.userWeight);
            setUserEmail(user.userEmail);
        } catch (error) {
            console.error('Error fetching user details:', error);
            setErrors({ fetchError: 'Failed to fetch user details.' });
        }
    };
    const handleInputChange = (e, field) => {
        const value = e.target.value;
        setErrors(prevErrors => ({ ...prevErrors, [field]: '' }));  

        switch (field) {
            case 'userName':
                setUserName(value);
                break;
            case 'userPassword':
                setUserPassword(value);
                break;      
            case 'genderRefId':
                setGenderRefId(value);
                break;
            case 'userAge':
                setUserAge(value);
                break;
            case 'userHeight':
                setUserHeight(value);
                break;
            case 'userWeight':
                setUserWeight(value);
                break;
            case 'userEmail':
                setUserEmail(value);
                break;
            default:
                break;
        }
    };

    const validate = () => {
        const errors = {};
        const passwordRegex = /^(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{4,}$/;
        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!userName) errors.userName = "User Name cannot be blank.";
        if (!userPassword) {
            errors.userPassword = "User Password cannot be null.";
        } else if (!passwordRegex.test(userPassword)) {
            errors.userPassword = "Password must contain at least 4 characters, include at least 1 number and 1 special character.";
        }
        if (!genderRefId) errors.genderRefId = "User Gender is mandatory.";
        if (!userAge) errors.userAge = "User Age is mandatory.";
        if (!userHeight) errors.userHeight = "User Height is mandatory.";
        if (!userWeight) errors.userWeight = "User Weight is mandatory.";
        if (!userEmail) {
            errors.userEmail = "Email cannot be blank.";
        } else if (!emailRegex.test(userEmail)) {
            errors.userEmail = "Invalid Email format.";
        }
        return errors;
    };

    const saveUser = async (e) => {
        e.preventDefault();
        const errors = validate();
        if (Object.keys(errors).length > 0) {
            setErrors(errors);
            return;
        }
        const user = { id: userId, userName, userPassword, genderRefId, userAge, userHeight, userWeight, userEmail };
        console.log('Request Payload:', user);

        try {
            const response = await updateUser(user);
            setMessage(`User Details successfully updated with ID: ${response.data.id}`);
            console.log('Response:', response);
  
          toast.success(`Details Updated for user ID ${response.data.id}`);
          
          setErrors({});
        } catch (error) {
            console.error('Error:', error.message);
            toast.error('Failed to update user.');
        }
    };

    const handleFetchUser = (e) => {
        e.preventDefault();
        fetchUser(userId);
    };

    return (
        <div className='container'>
            <div className='row justify-content-center'>
                <div className='register-card'>
                    <h2 className='text-center mt-3'>Update User</h2>
                    <div className='card-body'>
                        <form>
                            <div className='form-group'>
                                <label className='form-label'>Enter User ID to Update Details</label>
                                <input
                                    type="number"
                                    value={userId}
                                    onChange={(e) => setUserId(e.target.value)}
                                    placeholder="User ID"
                                    className={`form-control ${errors.fetchError ? 'is-invalid' : ''}`}
                                />
                                {errors.fetchError && <p className="text-danger">{errors.fetchError}</p>}
                            </div>
                            <button className='btn btn-secondary' onClick={handleFetchUser}>Fetch User Details</button>
                            <div className='row'>
                                <div className='form-group col-md-6'>
                                    <label className='form-label'>User Name</label>
                                    <input
                                        type="text"
                                        value={userName}
                                        onChange={(e) => handleInputChange(e, 'userName')}
                                        placeholder="Username"
                                        className={`form-control ${errors.userName ? 'is-invalid' : ''}`}
                                    />
                                    {errors.userName && <p className="text-danger">{errors.userName}</p>}
                                </div>
                                <div className='form-group col-md-6'>
                                    <label className='form-label'>Password</label>
                                    <input
                                        type="password"
                                        value={userPassword}
                                        onChange={(e) => handleInputChange(e, 'userPassword')}
                                        placeholder="Password"
                                        className={`form-control ${errors.userPassword ? 'is-invalid' : ''}`}
                                    />
                                    {errors.userPassword && <p className="text-danger">{errors.userPassword}</p>}
                                </div>
                            </div>
                            <div className='row'>
                                <div className='form-group col-md-6'>
                                    <label className='form-label'>Email</label>
                                    <input
                                        type="email"
                                        value={userEmail}
                                        onChange={(e) => handleInputChange(e, 'userEmail')}
                                        placeholder="Email"
                                        className={`form-control ${errors.userEmail ? 'is-invalid' : ''}`}
                                    />
                                    {errors.userEmail && <p className="text-danger">{errors.userEmail}</p>}
                                </div>
                                <div className='form-group col-md-6'>
                                    <label className='form-label'>Gender</label>
                                    <select
                                        value={genderRefId}
                                        onChange={(e) => handleInputChange(e, 'genderRefId')}
                                        className={`form-control ${errors.genderRefId ? 'is-invalid' : ''}`}
                                    >
                                        <option value="">Select Gender</option>
                                        <option value="1">Male</option>
                                        <option value="2">Female</option>
                                    </select>
                                    {errors.genderRefId && <p className="text-danger">{errors.genderRefId}</p>}
                                </div>
                            </div>
                            <div className='row'>
                                <div className='form-group col-md-4'>
                                    <label className='form-label'>Age</label>
                                    <input
                                        type="number"
                                        value={userAge}
                                        onChange={(e) => handleInputChange(e, 'userAge')}
                                        placeholder="Age"
                                        className={`form-control ${errors.userAge ? 'is-invalid' : ''}`}
                                    />
                                    {errors.userAge && <p className="text-danger">{errors.userAge}</p>}
                                </div>
                                <div className='form-group col-md-4'>
                                    <label className='form-label'>Height (cm)</label>
                                    <input
                                        type="number"
                                        value={userHeight}
                                        onChange={(e) => handleInputChange(e, 'userHeight')}
                                        placeholder="Height"
                                        className={`form-control ${errors.userHeight ? 'is-invalid' : ''}`}
                                    />
                                    {errors.userHeight && <p className="text-danger">{errors.userHeight}</p>}
                                </div>
                                <div className='form-group col-md-4'>
                                    <label className='form-label'>Weight (kg)</label>
                                    <input
                                        type="number"
                                        value={userWeight}
                                        onChange={(e) => handleInputChange(e, 'userWeight')}
                                        placeholder="Weight"
                                        className={`form-control ${errors.userWeight ? 'is-invalid' : ''}`}
                                    />
                                    {errors.userWeight && <p className="text-danger">{errors.userWeight}</p>}
                                </div>
                            </div>
                            
                            <div className='text-center'>
                                <button className='btn btn-primary' onClick={saveUser}>UPDATE</button>
                            </div>
                           
                            {message && <p className="text-success mt-2">{message}</p>}
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default UpdateUserDetailsById;
