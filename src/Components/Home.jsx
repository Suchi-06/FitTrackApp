import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

import home1 from '../assets/images/home1.jpg';
import home2 from '../assets/images/home2.jpg';
import home3 from '../assets/images/home3.jpg';
import home4 from '../assets/images/home4.jpg';
import home5 from '../assets/images/home5.jpeg';
import home6 from '../assets/images/home6.PNG';

const Home = () => {
  const [userId, setUserId] = useState(null);

  // Retrieve userId from localStorage when the component mounts
  useEffect(() => {
    const storedUserId = localStorage.getItem('userId');
    setUserId(storedUserId);
  }, []);

  const gridItems = [
    {
      src: home1,
      alt: 'First item',
      title: 'Track Activities',
      description: 'Log your daily activities and track your progress.',
    },
    {
      src: home2,
      alt: 'Second item',
      title: 'Monitor Diet',
      description: 'Keep an eye on your diet to achieve your fitness goals.',
    },
    {
      src: home3,
      alt: 'Third item',
      title: 'Set Goals',
      description: 'Define and achieve your fitness goals.',
    },
    {
      src: home4,
      alt: 'Fourth item',
      title: 'Track Sleep',
      description: 'Ensure you’re getting enough rest for optimal performance.',
    },
    {
      src: home5,
      alt: 'Fifth item',
      title: 'Analyze Progress',
      description: 'Visualize your progress with detailed reports.',
    },
    {
      src: home6,
      alt: 'Sixth item',
      title: 'Connect with Friends',
      description: 'Share your achievements and stay motivated.',
    },
  ];

  return (
    <div className="container mt-5">
      {userId && (
        <div className="alert alert-info" role="alert">
          Welcome back! User ID: <strong>{userId}</strong>
        </div>
      )}

      <div className="row">
        {gridItems.map((item, index) => (
          <div key={index} className="col-md-4 mb-4">
            <div className="card h-100">
              <img
                src={item.src}
                className="card-img-top"
                alt={item.alt}
                style={{ height: '200px', objectFit: 'cover' }}
              />
              <div className="card-body">
                <h5 className="card-title">{item.title}</h5>
                <p className="card-text">{item.description}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Home;
