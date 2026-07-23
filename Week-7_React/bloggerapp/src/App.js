import React, { useState } from 'react';
import './App.css';
import './styles/animations.css';
import BookDetails from './components/BookDetails';
import BlogDetails from './components/BlogDetails';
import CourseDetails from './components/CourseDetails';

function App() {
  const [activeComponent, setActiveComponent] = useState('books');
  const [isDarkMode, setIsDarkMode] = useState(false);
  const [showWelcome, setShowWelcome] = useState(true);

  // Conditional rendering helper
  const renderActiveComponent = () => {
    switch(activeComponent) {
      case 'books':
        return <BookDetails />;
      case 'blogs':
        return <BlogDetails />;
      case 'courses':
        return <CourseDetails />;
      default:
        return <div className="error-view">❌ Component not found</div>;
    }
  };

  return (
    <div className={`App ${isDarkMode ? 'dark-mode' : 'light-mode'}`}>
      {/* Conditional Welcome Banner */}
      {showWelcome && (
        <div className="welcome-banner">
          <div className="welcome-content">
            <h1>🎯 Blogger App - Conditional Rendering Showcase</h1>
            <p>Explore different conditional rendering techniques across 3 components</p>
            <button 
              onClick={() => setShowWelcome(false)}
              className="close-welcome"
            >
              ❌ Close
            </button>
          </div>
        </div>
      )}

      {/* Header with Navigation */}
      <header className="app-header">
        <div className="header-content">
          <div className="logo-section">
            <h2>📚 Blogger App</h2>
            <span className="subtitle">Conditional Rendering Demo</span>
          </div>

          {/* Navigation with Conditional Active States */}
          <nav className="main-navigation">
            <button
              onClick={() => setActiveComponent('books')}
              className={`nav-btn ${activeComponent === 'books' ? 'active' : ''}`}
            >
              📚 Book Details
              {activeComponent === 'books' && <span className="active-indicator">●</span>}
            </button>
            
            <button
              onClick={() => setActiveComponent('blogs')}
              className={`nav-btn ${activeComponent === 'blogs' ? 'active' : ''}`}
            >
              📝 Blog Details
              {activeComponent === 'blogs' && <span className="active-indicator">●</span>}
            </button>
            
            <button
              onClick={() => setActiveComponent('courses')}
              className={`nav-btn ${activeComponent === 'courses' ? 'active' : ''}`}
            >
              🎓 Course Details
              {activeComponent === 'courses' && <span className="active-indicator">●</span>}
            </button>
          </nav>

          {/* Theme Toggle with Conditional Icon */}
          <div className="theme-controls">
            <button
              onClick={() => setIsDarkMode(!isDarkMode)}
              className="theme-toggle"
              title={isDarkMode ? 'Switch to Light Mode' : 'Switch to Dark Mode'}
            >
              {isDarkMode ? '☀️' : '🌙'}
            </button>
          </div>
        </div>
      </header>

      {/* Breadcrumb with Conditional Rendering */}
      <div className="breadcrumb">
        <span className="breadcrumb-home">🏠 Home</span>
        <span className="breadcrumb-separator">→</span>
        <span className="breadcrumb-current">
          {activeComponent === 'books' ? '📚 Books' :
           activeComponent === 'blogs' ? '📝 Blogs' :
           activeComponent === 'courses' ? '🎓 Courses' : 'Unknown'}
        </span>
      </div>

      {/* Main Content Area */}
      <main className="main-content">
        {/* Conditional Loading Simulation */}
        {(() => {
          // Simulate different loading states
          const loadingStates = {
            books: false,
            blogs: false,
            courses: false
          };

          if (loadingStates[activeComponent]) {
            return (
              <div className="loading-container">
                <div className="loading-spinner"></div>
                <p>Loading {activeComponent}...</p>
              </div>
            );
          }

          return (
            <div className="component-container">
              {renderActiveComponent()}
            </div>
          );
        })()}
      </main>

      {/* Conditional Rendering Examples Info Panel */}
      <aside className="info-panel">
        <h3>🔍 Conditional Rendering Techniques Demonstrated:</h3>
        <div className="techniques-list">
          {/* Conditional list based on active component */}
          {activeComponent === 'books' && (
            <div className="techniques-for-books">
              <h4>📚 In Book Details:</h4>
              <ul>
                <li>✅ Ternary Operator (? :)</li>
                <li>✅ Logical AND (&&)</li>
                <li>✅ Nested Conditional Rendering</li>
                <li>✅ Conditional CSS Classes</li>
                <li>✅ Switch Case using Objects</li>
                <li>✅ Multiple Condition Checks</li>
                <li>✅ Loading State Management</li>
                <li>✅ Complex Modal Rendering</li>
                <li>✅ Render Props Pattern</li>
                <li>✅ Element Variables</li>
              </ul>
            </div>
          )}
          
          {activeComponent === 'blogs' && (
            <div className="techniques-for-blogs">
              <h4>📝 In Blog Details:</h4>
              <ul>
                <li>✅ Switch-Case Rendering</li>
                <li>✅ Complex Filtering Logic</li>
                <li>✅ Role-Based Conditional Rendering</li>
                <li>✅ Dynamic Component Rendering</li>
                <li>✅ Conditional Navigation</li>
                <li>✅ State-Based UI Changes</li>
                <li>✅ Multi-Level Conditionals</li>
                <li>✅ Permission-Based Rendering</li>
                <li>✅ Search Result Conditionals</li>
                <li>✅ Status Bar Rendering</li>
              </ul>
            </div>
          )}
          
          {activeComponent === 'courses' && (
            <div className="techniques-for-courses">
              <h4>🎓 In Course Details:</h4>
              <ul>
                <li>✅ Level-Based Access Control</li>
                <li>✅ Enrollment Status Rendering</li>
                <li>✅ Progress Tracking Display</li>
                <li>✅ Dynamic Filtering & Sorting</li>
                <li>✅ View Mode Switching</li>
                <li>✅ Conditional Recommendations</li>
                <li>✅ Achievement Celebrations</li>
                <li>✅ Pricing Display Logic</li>
                <li>✅ Feature Availability Checks</li>
                <li>✅ Learning Path Guidance</li>
              </ul>
            </div>
          )}
        </div>

        {/* Overall Statistics */}
        <div className="overall-stats">
          <h4>📊 Total Techniques: 30+</h4>
          <p>Each component demonstrates 10+ different conditional rendering approaches!</p>
        </div>
      </aside>

      {/* Footer with Conditional Content */}
      <footer className="app-footer">
        <div className="footer-content">
          <div className="footer-info">
            <p>© 2025 Blogger App - Conditional Rendering Showcase</p>
            <p>
              Currently viewing: 
              <strong>
                {activeComponent === 'books' ? ' 📚 Book Details Component' :
                 activeComponent === 'blogs' ? ' 📝 Blog Details Component' :
                 activeComponent === 'courses' ? ' 🎓 Course Details Component' : ' Unknown Component'}
              </strong>
            </p>
          </div>
          
          <div className="footer-stats">
            <span>Theme: {isDarkMode ? '🌙 Dark' : '☀️ Light'}</span>
            <span>Active: {activeComponent}</span>
            <span>Welcome: {showWelcome ? 'Shown' : 'Hidden'}</span>
          </div>
        </div>
      </footer>

      {/* Floating Action Button with Conditional Functionality */}
      <div className="floating-actions">
        {!showWelcome && (
          <button
            onClick={() => setShowWelcome(true)}
            className="fab help-btn"
            title="Show Welcome Message"
          >
            ❓
          </button>
        )}
        
        <button
          onClick={() => {
            const components = ['books', 'blogs', 'courses'];
            const currentIndex = components.indexOf(activeComponent);
            const nextIndex = (currentIndex + 1) % components.length;
            setActiveComponent(components[nextIndex]);
          }}
          className="fab next-btn"
          title="Next Component"
        >
          ⏭️
        </button>
      </div>
    </div>
  );
}

export default App;
