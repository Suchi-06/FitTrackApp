import React from 'react';
import '../index.css';

const Footer = () => {
  return (
    <footer className="footer bg-warning py-3"> 
      <div className="container d-flex justify-content-between align-items-center">
        <span className="text-dark fw-bold">&copy; 2024 FitTrack Track Smart . All rights reserved.</span> {/* Matches header text color */}
        
        <div className="social-icons d-flex">
          <a href="https://www.facebook.com" className="social-icon me-3 text-dark">
            <i className="fab fa-facebook-f"></i>
          </a>
          <a href="https://www.x.com" className="social-icon me-3 text-dark">
            <i className="fab fa-twitter"></i>
          </a>
          <a href="https://www.instagram.com" className="social-icon text-dark">
            <i className="fab fa-instagram"></i>
          </a>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
