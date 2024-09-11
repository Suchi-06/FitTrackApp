import React, { useState } from 'react';
import { register } from '../Services/UserService'; // Make sure this function is properly defined
import { toast } from 'react-toastify';
import 'bootstrap/dist/css/bootstrap.min.css';
import logo from '../assets/images/logo.jpg';

const Register = () => {
  const [userName, setUserName] = useState('');
  const [userPassword, setUserPassword] = useState('');
  const [genderRefId, setGenderRefId] = useState('');
  const [userAge, setUserAge] = useState('');
  const [userHeight, setUserHeight] = useState('');
  const [userWeight, setUserWeight] = useState('');
  const [userEmail, setUserEmail] = useState('');
  const [errors, setErrors] = useState({});
  const [message, setMessage] = useState('');
  const [emailUnique, setEmailUnique] = useState('');

  const validate = () => {
    const errors = {};
    const passwordRegex = /^(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{4,}$/;
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (!userName) errors.userName = 'User Name cannot be blank.';
    if (!userPassword) {
      errors.userPassword = 'User Password cannot be null.';
    } else if (!passwordRegex.test(userPassword)) {
      errors.userPassword =
        'Password must contain at least 4 characters, including 1 number and 1 special character.';
    }
    if (!genderRefId) errors.genderRefId = 'User Gender is mandatory.';
    if (!userAge) errors.userAge = 'User Age is mandatory.';
    if (!userHeight) errors.userHeight = 'User Height is mandatory.';
    if (!userWeight) errors.userWeight = 'User Weight is mandatory.';
    if (!userEmail) {
      errors.userEmail = 'Email cannot be blank.';
    } else if (!emailRegex.test(userEmail)) {
      errors.userEmail = 'Invalid Email format.';
    }
    return errors;
  };

  const handleInputChange = (e, field) => {
    const value = e.target.value;
    setErrors((prevErrors) => ({ ...prevErrors, [field]: '' })); // Reset the error for this field

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

  const handleBlur = async (field) => {
    if (field === 'userEmail') {
      try {
        const response = await checkuseremail(userEmail); // Assumed service for checking email uniqueness
        setEmailUnique(response.data);
        if (response.data) {
          setErrors((prevErrors) => ({ ...prevErrors, userEmail: 'Email already exists.' }));
        } else {
          setErrors((prevErrors) => ({ ...prevErrors, userEmail: '' }));
        }
      } catch (error) {
        console.error('Error checking email:', error);
      }
    }
  };

  const saveUser = async (e) => {
    e.preventDefault();
    const errors = validate();
    if (Object.keys(errors).length > 0) {
      setErrors(errors);
      return;
    }
    const user = { userName, userPassword, genderRefId, userAge, userHeight, userWeight, userEmail };
    console.log('Request Payload:', user);

    try {
      const response = await register(user);
      setMessage(`User successfully created with ID: ${response.data.id}`);
      console.log('Response:', response);

      // Show success message with user ID
      toast.success(`Registration successful for user ID ${response.data.id}`);

      setErrors({});
    } catch (error) {
      console.error('Error:', error.message);
      toast.error('Registration failed. Please try again.');
    }
  };

  return (
<div className='d-flex justify-content-center align-items-center vh-100 bg-warning'>
<div className='container'>
      <div className='row justify-content-center'>
        <div className='col-lg-8'>
          <div className='card mt-5'>
          <div className='card-header d-flex align-items-center'>
              <img src={logo} alt='FitTrack Logo' className='img-fluid' style={{ width: '80px' }} />
              <h4 className='mb-0'>USER REGISTRATION</h4>
            </div>
            <div className='card-body'>
              <form>
                <div className='row'>
                  <div className='form-group col-md-6'>
                    <label>User Name</label>
                    <input
                      type='text'
                      value={userName}
                      onChange={(e) => handleInputChange(e, 'userName')}
                      placeholder='Username'
                      className={`form-control ${errors.userName ? 'is-invalid' : ''}`}
                    />
                    {errors.userName && <div className='invalid-feedback'>{errors.userName}</div>}
                  </div>
                  <div className='form-group col-md-6'>
                    <label>Password</label>
                    <input
                      type='password'
                      value={userPassword}
                      onChange={(e) => handleInputChange(e, 'userPassword')}
                      placeholder='Password'
                      className={`form-control ${errors.userPassword ? 'is-invalid' : ''}`}
                    />
                    {errors.userPassword && (
                      <div className='invalid-feedback'>{errors.userPassword}</div>
                    )}
                  </div>
                </div>
                <div className='row'>
                  <div className='form-group col-md-6'>
                    <label>Email</label>
                    <input
                      type='email'
                      value={userEmail}
                      onChange={(e) => handleInputChange(e, 'userEmail')}
                      placeholder='Email'
                      className={`form-control ${errors.userEmail ? 'is-invalid' : ''}`}
                      onBlur={() => handleBlur('userEmail')}
                    />
                    {errors.userEmail && <div className='invalid-feedback'>{errors.userEmail}</div>}
                  </div>
                  <div className='form-group col-md-6'>
                    <label>Gender</label>
                    <select
                      value={genderRefId}
                      onChange={(e) => handleInputChange(e, 'genderRefId')}
                      className={`form-control ${errors.genderRefId ? 'is-invalid' : ''}`}
                    >
                      <option value=''>Select Gender</option>
                      <option value='1'>Male</option>
                      <option value='2'>Female</option>
                    </select>
                    {errors.genderRefId && (
                      <div className='invalid-feedback'>{errors.genderRefId}</div>
                    )}
                  </div>
                </div>
                <div className='row'>
                  <div className='form-group col-md-4'>
                    <label>Age</label>
                    <input
                      type='number'
                      value={userAge}
                      onChange={(e) => handleInputChange(e, 'userAge')}
                      placeholder='Age'
                      className={`form-control ${errors.userAge ? 'is-invalid' : ''}`}
                    />
                    {errors.userAge && <div className='invalid-feedback'>{errors.userAge}</div>}
                  </div>
                  <div className='form-group col-md-4'>
                    <label>Height (cm)</label>
                    <input
                      type='number'
                      value={userHeight}
                      onChange={(e) => handleInputChange(e, 'userHeight')}
                      placeholder='Height'
                      className={`form-control ${errors.userHeight ? 'is-invalid' : ''}`}
                    />
                    {errors.userHeight && (
                      <div className='invalid-feedback'>{errors.userHeight}</div>
                    )}
                  </div>
                  <div className='form-group col-md-4'>
                    <label>Weight (kg)</label>
                    <input
                      type='number'
                      value={userWeight}
                      onChange={(e) => handleInputChange(e, 'userWeight')}
                      placeholder='Weight'
                      className={`form-control ${errors.userWeight ? 'is-invalid' : ''}`}
                    />
                    {errors.userWeight && (
                      <div className='invalid-feedback'>{errors.userWeight}</div>
                    )}
                  </div>
                </div>
                <button type='submit' className='btn btn-primary btn-block mt-3' onClick={saveUser}>
                  Register
                </button>
                <p className='text-center mt-3'>
                  Already have an account? <a href='/login'>Login</a>
                </p>
                {message && <div className='alert alert-success mt-3'>{message}</div>}
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    </div>
  );
};

export default Register;
