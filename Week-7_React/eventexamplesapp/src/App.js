import React, { useState } from 'react';
import './App.css';
import CurrencyConvertor from './CurrencyConvertor';

function App() {
  // State for counter
  const [counter, setCounter] = useState(0);
  
  // Method to increment the counter
  const incrementCounter = () => {
    setCounter(prevCounter => prevCounter + 1);
  };
  
  // Method to say hello with static message
  const sayHello = () => {
    alert('Hello! Welcome to Event Examples App - React Learning Journey!');
  };
  
  // Method to decrement the counter
  const decrementCounter = () => {
    setCounter(prevCounter => prevCounter - 1);
  };
  
  // Multiple methods handler for Increment button
  const handleIncrementWithMultipleMethods = () => {
    incrementCounter(); // a. To increment the value
    sayHello();         // b. Say Hello followed by a static message
  };
  
  // Function that takes "welcome" as an argument
  const sayMessage = (message) => {
    alert(`${message}! Thank you for using our Event Examples App!`);
  };
  
  // Synthetic event handler for OnPress
  const handleOnPress = (e) => {
    e.preventDefault();
    console.log('Synthetic Event Object:', e);
    alert('I was clicked! This is a synthetic event example.');
  };

  return (
    <div className="App">
      {/* Header Section */}
      <header className="app-header">
        <h1>🎯 Event Examples App</h1>
        <p>Learn React Event Handling with Interactive Examples</p>
      </header>

      <main className="main-content">
        {/* Counter Section */}
        <section className="counter-section">
          <h2>📊 Counter with Event Handlers</h2>
          <div className="counter-display">
            <span className="counter-value">{counter}</span>
          </div>
          
          <div className="counter-buttons">
            {/* 1. Increment button that invokes multiple methods */}
            <button 
              onClick={handleIncrementWithMultipleMethods}
              className="btn increment-btn"
              title="Increments counter AND shows hello message"
            >
              ➕ Increment (Multiple Methods)
            </button>
            
            {/* Decrement button */}
            <button 
              onClick={decrementCounter}
              className="btn decrement-btn"
            >
              ➖ Decrement
            </button>
          </div>
          
          <div className="info-box">
            <p><strong>Increment Button:</strong> Calls two methods:</p>
            <ul>
              <li>✅ Increments the counter value</li>
              <li>✅ Shows "Hello" message with static text</li>
            </ul>
          </div>
        </section>

        {/* Welcome Button Section */}
        <section className="welcome-section">
          <h2>👋 Function with Arguments</h2>
          {/* 2. Button that invokes function with "welcome" as argument */}
          <button 
            onClick={() => sayMessage('Welcome')}
            className="btn welcome-btn"
          >
            🎉 Say Welcome
          </button>
          <p className="description">
            This button calls a function that takes "welcome" as an argument
          </p>
        </section>

        {/* Synthetic Event Section */}
        <section className="synthetic-event-section">
          <h2>⚡ Synthetic Event Example</h2>
          {/* 3. Button with synthetic event "OnPress" */}
          <button 
            onClick={handleOnPress}
            className="btn synthetic-btn"
            onMouseEnter={(e) => console.log('Mouse entered button:', e.type)}
            onMouseLeave={(e) => console.log('Mouse left button:', e.type)}
          >
            🖱️ OnPress Event (Check Console)
          </button>
          <p className="description">
            This button demonstrates synthetic events. Click and check the console!
          </p>
          
          <div className="event-info">
            <h4>Synthetic Event Features:</h4>
            <ul>
              <li>✅ Cross-browser compatibility</li>
              <li>✅ Event object logging</li>
              <li>✅ Mouse enter/leave events</li>
              <li>✅ Click event handling</li>
            </ul>
          </div>
        </section>

        {/* Currency Converter Section */}
        <section className="currency-section">
          <h2>💰 Form Event Handling</h2>
          <CurrencyConvertor />
          <div className="converter-info">
            <h4>Form Events Demonstrated:</h4>
            <ul>
              <li>✅ onSubmit event with preventDefault()</li>
              <li>✅ onChange event for input handling</li>
              <li>✅ Form validation</li>
              <li>✅ State management with events</li>
            </ul>
          </div>
        </section>

        {/* Additional Event Examples */}
        <section className="additional-events">
          <h2>🎨 More Event Examples</h2>
          <div className="event-examples-grid">
            <div className="event-card">
              <h4>Keyboard Events</h4>
              <input 
                type="text"
                placeholder="Type something..."
                onKeyDown={(e) => console.log('Key down:', e.key)}
                onKeyUp={(e) => console.log('Key up:', e.key)}
                className="event-input"
              />
            </div>
            
            <div className="event-card">
              <h4>Focus Events</h4>
              <input 
                type="text"
                placeholder="Click to focus..."
                onFocus={(e) => e.target.style.backgroundColor = '#e3f2fd'}
                onBlur={(e) => e.target.style.backgroundColor = 'white'}
                className="event-input"
              />
            </div>
            
            <div className="event-card">
              <h4>Mouse Events</h4>
              <div 
                className="hover-box"
                onMouseOver={(e) => e.target.textContent = 'Mouse Over! 🎯'}
                onMouseOut={(e) => e.target.textContent = 'Hover over me!'}
              >
                Hover over me!
              </div>
            </div>
          </div>
        </section>
      </main>

      <footer className="app-footer">
        <p>© 2025 Event Examples App - Master React Event Handling</p>
      </footer>
    </div>
  );
}

export default App;
