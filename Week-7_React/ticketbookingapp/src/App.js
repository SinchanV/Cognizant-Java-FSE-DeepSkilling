import React, { useState } from 'react';
import './App.css';
import GuestPage from './GuestPage';
import UserPage from './UserPage';

function App() {
  // State to track if user is logged in
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [username, setUsername] = useState('');

  // Handle login
  const handleLogin = () => {
    const name = prompt('Enter your username:');
    if (name && name.trim()) {
      setUsername(name.trim());
      setIsLoggedIn(true);
    }
  };

  // Handle logout
  const handleLogout = () => {
    setIsLoggedIn(false);
    setUsername('');
    alert('You have been successfully logged out!');
  };

  return (
    <div className="App">
      {/* Header with Navigation */}
      <header className="app-header">
        <div className="header-content">
          <h1>🛫 FlightBook Pro</h1>
          <div className="nav-section">
            {isLoggedIn ? (
              <div className="user-info">
                <span className="welcome-text">Welcome, {username}</span>
                <button onClick={handleLogout} className="nav-btn logout">
                  🚪 Logout
                </button>
              </div>
            ) : (
              <button onClick={handleLogin} className="nav-btn login">
                🔑 Login
              </button>
            )}
          </div>
        </div>
      </header>

      {/* Main Content - Conditional Rendering */}
      <main className="main-content">
        {isLoggedIn ? (
          <UserPage onLogout={handleLogout} username={username} />
        ) : (
          <GuestPage onLogin={handleLogin} />
        )}
      </main>

      {/* Footer */}
      <footer className="app-footer">
        <div className="footer-content">
          <p>© 2025 FlightBook Pro - Your Trusted Flight Booking Partner</p>
          <div className="footer-links">
            <span>Status: {isLoggedIn ? '🟢 Logged In' : '🔴 Guest Mode'}</span>
          </div>
        </div>
      </footer>

      {/* Features Info */}
      <div className="features-info">
        <h3>🌟 App Features:</h3>
        <div className="features-grid">
          <div className="feature-card">
            <h4>👥 Guest Mode</h4>
            <ul>
              <li>Browse all flights</li>
              <li>View prices & schedules</li>
              <li>No booking access</li>
            </ul>
          </div>
          <div className="feature-card">
            <h4>🔐 User Mode</h4>
            <ul>
              <li>Full flight booking</li>
              <li>Manage bookings</li>
              <li>Passenger details</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
