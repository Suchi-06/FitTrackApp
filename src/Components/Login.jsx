import React, { useState } from 'react';
import { login } from '../Services/UserService';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useNavigate } from 'react-router-dom';
import logo from '../assets/images/logo.jpg';
import 'bootstrap/dist/css/bootstrap.min.css'; // Ensure Bootstrap CSS is imported

const Login = ({ onLogin }) => {
  const [userEmail, setUserEmail] = useState('');
  const [userPassword, setUserPassword] = useState('');
  const [error, setError] = useState({ userEmail: '', userPassword: '', general: '' });
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    // Reset error state
    setError({ userEmail: '', userPassword: '', general: '' });
  
    let hasError = false;
  
    // Validation for non-blank fields
    if (!userEmail.trim()) {
      setError((prevError) => ({ ...prevError, userEmail: 'User email cannot be blank' }));
      hasError = true;
    }
    if (!userPassword.trim()) {
      setError((prevError) => ({ ...prevError, userPassword: 'Password cannot be blank' }));
      hasError = true;
    }
  
    if (hasError) {
      return;
    }
  
    try {
      const response = await login(userEmail, userPassword);
  
      if (response.data) {
        const userId = response.data.id;  
        localStorage.setItem('userId', userId);  // Store userId in localStorage
        const userName = response.data.userName;
        localStorage.setItem('userName', userName);
        toast.success('Login successful');
        onLogin(userEmail);
        
        navigate('/home');
      } else {
        setError((prevError) => ({ ...prevError, general: 'Invalid username or password' }));
      }
    } catch (err) {
      console.error(err);
      const errorMessage = err.response?.data || 'Failed to login. Please try again.';
  
      // Set general error message for both cases
      setError((prevError) => ({ ...prevError, general: 'Invalid username or password' }));
    }
  };
  

  return (
    <div className='d-flex justify-content-center align-items-center vh-100 bg-warning'>
      <div className='container p-4 bg-light rounded shadow' style={{ maxWidth: '400px' }}>
        <div className='text-center mb-4'>
          <img src={logo} alt='FitTrack Logo' className='img-fluid' style={{ width: '150px' }} />
        </div>
        <form onSubmit={handleSubmit}>
          <div className='mb-3'>
            <label htmlFor='email' className='form-label'>Email</label>
            <input
              type='email'
              className={`form-control ${error.userEmail ? 'is-invalid' : ''}`}
              placeholder='Enter Email'
              onChange={(e) => setUserEmail(e.target.value)}
              required
            />
            {error.userEmail && <div className='invalid-feedback'>{error.userEmail}</div>}
          </div>
          <div className='mb-3'>
            <label htmlFor='password' className='form-label'>Password</label>
            <input
              type='password'
              className={`form-control ${error.userPassword ? 'is-invalid' : ''}`}
              placeholder='Enter Password'
              onChange={(e) => setUserPassword(e.target.value)}
              required
            />
            {error.userPassword && <div className='invalid-feedback'>{error.userPassword}</div>}
          </div>
          <div className='d-grid'>
            <button className='btn btn-primary'>Sign in</button>
          </div>
          {error.general && <div className='text-danger mt-2'>{error.general}</div>}
          <div className='text-end mt-3'>
            <p>Don't have an account? <a href='/register'>Register</a></p>
          </div>
        </form>
      </div>
      <ToastContainer />
    </div>
  );
};

export default Login;
