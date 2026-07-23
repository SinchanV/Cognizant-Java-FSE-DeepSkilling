import React, { useState } from 'react';
import './App.css';
import ListofPlayers from './ListofPlayers';
import IndianPlayers from './IndianPlayers';

function App() {
  // Flag variable to toggle between components
  const [flag, setFlag] = useState(true);

  return (
    <div className="App" style={{ backgroundColor: '#f8f9fa', minHeight: '100vh', padding: '20px' }}>
      <div style={{ textAlign: 'center', marginBottom: '30px' }}>
        <h1 style={{ color: '#2c3e50', fontSize: '2.5rem', marginBottom: '20px' }}>
          🏏 Cricket App Dashboard
        </h1>
        
        <div style={{ marginBottom: '20px' }}>
          <button 
            onClick={() => setFlag(true)}
            style={{ 
              backgroundColor: flag ? '#3498db' : '#bdc3c7',
              color: 'white',
              border: 'none',
              padding: '12px 24px',
              margin: '0 10px',
              borderRadius: '25px',
              cursor: 'pointer',
              fontSize: '16px',
              fontWeight: 'bold',
              transition: 'all 0.3s ease'
            }}
          >
            Show List of Players
          </button>
          
          <button 
            onClick={() => setFlag(false)}
            style={{ 
              backgroundColor: !flag ? '#e74c3c' : '#bdc3c7',
              color: 'white',
              border: 'none',
              padding: '12px 24px',
              margin: '0 10px',
              borderRadius: '25px',
              cursor: 'pointer',
              fontSize: '16px',
              fontWeight: 'bold',
              transition: 'all 0.3s ease'
            }}
          >
            Show Indian Players
          </button>
        </div>
        
        <div style={{ 
          backgroundColor: '#34495e', 
          color: 'white', 
          padding: '10px 20px', 
          borderRadius: '20px', 
          display: 'inline-block',
          fontSize: '18px',
          fontWeight: 'bold'
        }}>
          Current View: {flag ? 'List of Players (Flag = true)' : 'Indian Players (Flag = false)'}
        </div>
      </div>

      {/* Simple if-else using flag variable */}
      <div>
        {flag ? <ListofPlayers /> : <IndianPlayers />}
      </div>
    </div>
  );
}

export default App;
