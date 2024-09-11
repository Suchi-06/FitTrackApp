import React from 'react';
import logo from '../assets/images/logo.jpg';

const Header = ({ onLogout }) => {
  return (
    <header>
      <nav className="navbar navbar-expand-lg bg-warning py-2">
        <div className="container d-flex align-items-center justify-content-between">
          <a className="navbar-brand d-flex align-items-center">
            <img
              src={logo}
              alt="FitTrack Logo"
              className="me-2"
              width="50"
              height="50"
            />
            <span className="text-dark fw-bold fs-3">FITTRACK - TRACK SMART</span> {/* Changed to text-dark */}
          </a>
          <div className="d-flex align-items-center">
            <button
              className="btn btn-outline-dark btn-lg ms-2"
              onClick={onLogout}
            >
              <strong>Logout</strong>
            </button>
          </div>
        </div>
      </nav>
    </header>
  );
};

export default Header;
