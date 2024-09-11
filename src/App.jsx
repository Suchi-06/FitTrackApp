import React,{useState} from 'react';
import './App.css'
import {BrowserRouter as Router,Routes,Route,Navigate} from 'react-router-dom'
import Login from './Components/Login';
import Register from './Components/Register';
import Header from './Components/Header';
import Footer from './Components/Footer';
import Dashboard from './Components/Dashboard';
import 'bootstrap/dist/css/bootstrap.min.css';
import '@fortawesome/fontawesome-free/css/all.min.css';

function App() {
  
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = () => {
    
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
  };

  return (
    
     <Router>
      <div>
      {isLoggedIn ? (
          <>
            <Header  onLogout={handleLogout} />
            <Dashboard />
            <Footer />
          </>
        ) : (
        <Routes>
          <Route path="/login" element={<Login onLogin={handleLogin} />} />
          <Route path="/register" element={<Register />} />
          <Route path="*" element={<Navigate to="/login" />} />
        </Routes>
        )}
      </div>
    </Router>
    
  );
}
export default App;
