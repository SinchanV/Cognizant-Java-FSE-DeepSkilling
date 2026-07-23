import React, { useState } from 'react';

const CourseDetails = () => {
  // State for various conditional scenarios
  const [selectedLevel, setSelectedLevel] = useState('all');
  const [enrollmentStatus, setEnrollmentStatus] = useState({});
  const [viewMode, setViewMode] = useState('grid');
  const [sortBy, setSortBy] = useState('popularity');
  const [filterBy, setFilterBy] = useState('all');

  // Sample course data with rich conditional properties
  const courses = [
    {
      id: 1,
      title: "React Fundamentals",
      instructor: "Sarah Johnson",
      level: "beginner",
      price: 0,
      originalPrice: 0,
      duration: "6 weeks",
      students: 15420,
      rating: 4.8,
      reviews: 2341,
      isPopular: true,
      category: "frontend",
      skills: ["React", "JSX", "Components"],
      hasCaption: true,
      hasCertificate: true,
      language: "English",
      lastUpdated: "2024-01-15"
    },
    {
      id: 2,
      title: "Advanced JavaScript Patterns",
      instructor: "Mike Chen",
      level: "advanced",
      price: 89.99,
      originalPrice: 129.99,
      duration: "8 weeks",
      students: 8750,
      rating: 4.9,
      reviews: 1205,
      isPopular: false,
      category: "javascript",
      skills: ["ES6+", "Design Patterns", "Performance"],
      hasCaption: true,
      hasCertificate: true,
      language: "English",
      lastUpdated: "2024-01-10"
    },
    {
      id: 3,
      title: "Full-Stack Web Development",
      instructor: "Emma Wilson",
      level: "intermediate",
      price: 149.99,
      originalPrice: 199.99,
      duration: "12 weeks",
      students: 12300,
      rating: 4.7,
      reviews: 1890,
      isPopular: true,
      category: "fullstack",
      skills: ["Node.js", "React", "MongoDB", "Express"],
      hasCaption: false,
      hasCertificate: true,
      language: "English",
      lastUpdated: "2024-01-08"
    },
    {
      id: 4,
      title: "UI/UX Design Masterclass",
      instructor: "David Rodriguez",
      level: "beginner",
      price: 79.99,
      originalPrice: 79.99,
      duration: "5 weeks",
      students: 6890,
      rating: 4.6,
      reviews: 945,
      isPopular: false,
      category: "design",
      skills: ["Figma", "Prototyping", "User Research"],
      hasCaption: true,
      hasCertificate: false,
      language: "English",
      lastUpdated: "2024-01-05"
    },
    {
      id: 5,
      title: "Python for Data Science",
      instructor: "Lisa Park",
      level: "intermediate",
      price: 99.99,
      originalPrice: 149.99,
      duration: "10 weeks",
      students: 9876,
      rating: 4.8,
      reviews: 1456,
      isPopular: true,
      category: "data-science",
      skills: ["Python", "Pandas", "NumPy", "Matplotlib"],
      hasCaption: true,
      hasCertificate: true,
      language: "English",
      lastUpdated: "2024-01-12"
    },
    {
      id: 6,
      title: "Mobile App Development with React Native",
      instructor: "James Wilson",
      level: "advanced",
      price: 119.99,
      originalPrice: 159.99,
      duration: "9 weeks",
      students: 5432,
      rating: 4.5,
      reviews: 678,
      isPopular: false,
      category: "mobile",
      skills: ["React Native", "iOS", "Android", "Redux"],
      hasCaption: false,
      hasCertificate: true,
      language: "English",
      lastUpdated: "2024-01-03"
    }
  ];

  // Simulate enrollment status
  const handleEnrollment = (courseId) => {
    setEnrollmentStatus(prev => ({
      ...prev,
      [courseId]: prev[courseId] || 'enrolled'
    }));
  };

  // Get filtered and sorted courses
  const getProcessedCourses = () => {
    let filteredCourses = courses.filter(course => {
      // Level filter
      const levelMatch = selectedLevel === 'all' || course.level === selectedLevel;
      
      // Category filter
      const categoryMatch = filterBy === 'all' || course.category === filterBy;
      
      return levelMatch && categoryMatch;
    });

    // Sort courses
    return filteredCourses.sort((a, b) => {
      switch (sortBy) {
        case 'price-low':
          return a.price - b.price;
        case 'price-high':
          return b.price - a.price;
        case 'rating':
          return b.rating - a.rating;
        case 'students':
          return b.students - a.students;
        case 'newest':
          return new Date(b.lastUpdated) - new Date(a.lastUpdated);
        default: // popularity
          return b.students - a.students;
      }
    });
  };

  return (
    <div className="course-details">
      <div className="component-header">
        <h2>🎓 Course Details Component</h2>
        <p>Comprehensive Conditional Rendering for E-Learning Platform</p>
      </div>

      {/* 1. Level-based Filtering */}
      <div className="conditional-section">
        <h3>1. Skill Level Targeting</h3>
        <div className="user-type-selector">
          <p>Select your experience level:</p>
          <div className="level-buttons">
            {['all', 'beginner', 'intermediate', 'advanced'].map(level => (
              <button
                key={level}
                className={selectedLevel === level ? 'active' : ''}
                onClick={() => setSelectedLevel(level)}
              >
                {level === 'all' ? '🌟 All Levels' :
                 level === 'beginner' ? '🌱 Beginner' :
                 level === 'intermediate' ? '🌿 Intermediate' : '🌳 Advanced'}
              </button>
            ))}
          </div>
        </div>

        {/* Conditional recommendations based on level */}
        <div className="example-box">
          {(() => {
            const recommendations = {
              beginner: {
                message: "Perfect for getting started! These courses include:",
                features: ["Step-by-step guidance", "No prior experience needed", "Practical projects", "Community support"],
                icon: "🌱",
                color: "#27ae60"
              },
              intermediate: {
                message: "Ready to level up your skills:",
                features: ["Build on existing knowledge", "Real-world projects", "Advanced techniques", "Portfolio development"],
                icon: "🌿", 
                color: "#f39c12"
              },
              advanced: {
                message: "Master complex topics and patterns:",
                features: ["Expert-level content", "Industry best practices", "Architecture patterns", "Performance optimization"],
                icon: "🌳",
                color: "#e74c3c"
              },
              all: {
                message: "Explore courses at every skill level:",
                features: ["Beginner to advanced paths", "Flexible learning", "All topics covered", "Complete learning journey"],
                icon: "🌟",
                color: "#667eea"
              }
            };

            const current = recommendations[selectedLevel];
            return (
              <div style={{ borderLeft: `4px solid ${current.color}` }}>
                <h4 style={{ color: current.color }}>
                  {current.icon} {current.message}
                </h4>
                <ul>
                  {current.features.map((feature, index) => (
                    <li key={index}>• {feature}</li>
                  ))}
                </ul>
              </div>
            );
          })()}
        </div>
      </div>

      {/* 2. Advanced Filtering and Sorting Controls */}
      <div className="conditional-section">
        <h3>2. Smart Course Discovery</h3>
        <div className="controls-section">
          <div className="filters">
            <div>
              <label>Category: </label>
              <select value={filterBy} onChange={(e) => setFilterBy(e.target.value)}>
                <option value="all">All Categories</option>
                <option value="frontend">Frontend Development</option>
                <option value="javascript">JavaScript</option>
                <option value="fullstack">Full-Stack</option>
                <option value="design">UI/UX Design</option>
                <option value="data-science">Data Science</option>
                <option value="mobile">Mobile Development</option>
              </select>
            </div>
            
            <div>
              <label>Sort by: </label>
              <select value={sortBy} onChange={(e) => setSortBy(e.target.value)}>
                <option value="popularity">Most Popular</option>
                <option value="rating">Highest Rated</option>
                <option value="price-low">Price: Low to High</option>
                <option value="price-high">Price: High to Low</option>
                <option value="students">Most Students</option>
                <option value="newest">Newest First</option>
              </select>
            </div>
            
            <div className="view-modes">
              <button 
                className={viewMode === 'grid' ? 'active' : ''}
                onClick={() => setViewMode('grid')}
              >
                📱 Grid
              </button>
              <button 
                className={viewMode === 'list' ? 'active' : ''}
                onClick={() => setViewMode('list')}
              >
                📋 List
              </button>
            </div>
          </div>
        </div>
      </div>

      {/* 3. Dynamic Course Display */}
      <div className="conditional-section">
        <h3>3. Smart Course Catalog</h3>
        
        {/* Conditional empty state */}
        {getProcessedCourses().length === 0 ? (
          <div className="example-box">
            <h4>🔍 No courses found</h4>
            <p>Try adjusting your filters or explore different skill levels.</p>
            <button 
              onClick={() => {
                setSelectedLevel('all');
                setFilterBy('all');
              }}
              style={{
                padding: '10px 20px',
                background: '#667eea',
                color: 'white',
                border: 'none',
                borderRadius: '5px',
                cursor: 'pointer'
              }}
            >
              Reset Filters
            </button>
          </div>
        ) : (
          <div className={`courses-container ${viewMode}`}>
            {getProcessedCourses().map(course => (
              <div key={course.id} className={`course-card ${course.isPopular ? 'popular' : ''}`}>
                {/* Conditional badges */}
                {course.isPopular && (
                  <div className="popular-badge">🔥 Popular</div>
                )}
                {course.price === 0 && (
                  <div className="free-badge">FREE</div>
                )}
                
                <div className="course-image">
                  <div className="course-icon">
                    {course.category === 'frontend' ? '🖥️' :
                     course.category === 'javascript' ? '⚡' :
                     course.category === 'fullstack' ? '🔗' :
                     course.category === 'design' ? '🎨' :
                     course.category === 'data-science' ? '📊' :
                     course.category === 'mobile' ? '📱' : '💻'}
                  </div>
                  
                  <div className={`difficulty-badge ${course.level}`}>
                    {course.level.toUpperCase()}
                  </div>
                </div>
                
                <div className="course-content">
                  <h3>{course.title}</h3>
                  <p className="instructor">👨‍🏫 {course.instructor}</p>
                  
                  <div className="course-stats">
                    <span>👥 {course.students.toLocaleString()} students</span>
                    <span>⭐ {course.rating} ({course.reviews} reviews)</span>
                    <span>⏰ {course.duration}</span>
                  </div>
                  
                  {/* Conditional pricing display */}
                  <div className="pricing">
                    {course.price === 0 ? (
                      <div className="free-price">FREE</div>
                    ) : course.price < course.originalPrice ? (
                      <div className="discount-pricing">
                        <span className="original-price">${course.originalPrice}</span>
                        <span className="current-price">${course.price}</span>
                        <span className="discount">
                          {Math.round((1 - course.price / course.originalPrice) * 100)}% OFF
                        </span>
                      </div>
                    ) : (
                      <div className="current-price">${course.price}</div>
                    )}
                  </div>
                  
                  {/* Conditional course features */}
                  <div className="course-features">
                    {course.skills.slice(0, 3).map(skill => (
                      <span key={skill} className="feature">{skill}</span>
                    ))}
                    {course.skills.length > 3 && (
                      <span className="feature">+{course.skills.length - 3} more</span>
                    )}
                  </div>
                  
                  {/* Additional features with conditional rendering */}
                  <div style={{ margin: '10px 0', fontSize: '0.9rem', color: '#7f8c8d' }}>
                    {course.hasCaption && <span>📝 Captions • </span>}
                    {course.hasCertificate && <span>🏆 Certificate • </span>}
                    <span>🌐 {course.language}</span>
                  </div>
                  
                  {/* Complex enrollment button logic */}
                  {(() => {
                    const enrollmentState = enrollmentStatus[course.id];
                    
                    if (enrollmentState === 'enrolled') {
                      return (
                        <button className="continue-btn">
                          📚 Continue Learning
                        </button>
                      );
                    } else if (enrollmentState === 'completed') {
                      return (
                        <button className="review-btn">
                          ⭐ Leave a Review
                        </button>
                      );
                    } else {
                      return (
                        <button 
                          className="enroll-btn"
                          onClick={() => handleEnrollment(course.id)}
                        >
                          {course.price === 0 ? '🆓 Enroll Free' : `💳 Enroll Now - $${course.price}`}
                        </button>
                      );
                    }
                  })()}
                </div>
              </div>
            ))}
          </div>
        )}
      </div>

      {/* 4. Learning Path Recommendations */}
      <div className="conditional-section">
        <h3>4. Personalized Learning Paths</h3>
        <div className="example-box">
          {(() => {
            // Complex recommendation logic based on selected level
            if (selectedLevel === 'beginner') {
              const beginnerCourses = courses.filter(c => c.level === 'beginner');
              return (
                <div>
                  <h4>🛤️ Recommended Learning Path for Beginners:</h4>
                  <ol>
                    {beginnerCourses.map((course, index) => (
                      <li key={course.id} style={{ margin: '8px 0' }}>
                        <strong>{course.title}</strong> - {course.duration}
                        {course.price === 0 && <span style={{ color: '#27ae60', marginLeft: '10px' }}>✨ FREE</span>}
                      </li>
                    ))}
                  </ol>
                  {beginnerCourses.length > 0 && (
                    <p style={{ marginTop: '15px', color: '#7f8c8d' }}>
                      💡 Tip: Start with the free courses to build your foundation!
                    </p>
                  )}
                </div>
              );
            } else if (selectedLevel === 'intermediate') {
              return (
                <div>
                  <h4>🚀 Accelerated Path for Intermediate Learners:</h4>
                  <p>Build upon your existing knowledge with these specialized tracks:</p>
                  <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(250px, 1fr))', gap: '15px', marginTop: '15px' }}>
                    <div style={{ padding: '15px', background: '#ecf0f1', borderRadius: '8px' }}>
                      <h5>🖥️ Frontend Specialist</h5>
                      <p>Advanced React + UI/UX Design</p>
                    </div>
                    <div style={{ padding: '15px', background: '#ecf0f1', borderRadius: '8px' }}>
                      <h5>🔗 Full-Stack Developer</h5>
                      <p>Complete web development stack</p>
                    </div>
                    <div style={{ padding: '15px', background: '#ecf0f1', borderRadius: '8px' }}>
                      <h5>📊 Data-Driven Developer</h5>
                      <p>Python for Data Science path</p>
                    </div>
                  </div>
                </div>
              );
            } else if (selectedLevel === 'advanced') {
              const advancedCourses = courses.filter(c => c.level === 'advanced');
              return (
                <div>
                  <h4>🎯 Expert-Level Mastery:</h4>
                  <p>Master complex patterns and architectures:</p>
                  {advancedCourses.map(course => (
                    <div key={course.id} style={{ 
                      margin: '10px 0', 
                      padding: '10px', 
                      background: '#2c3e50', 
                      color: 'white', 
                      borderRadius: '5px' 
                    }}>
                      <strong>{course.title}</strong> - Advanced {course.category.replace('-', ' ')}
                      <br />
                      <small>⭐ {course.rating} rating • {course.students.toLocaleString()} students</small>
                    </div>
                  ))}
                </div>
              );
            } else {
              // All levels view
              const stats = {
                beginner: courses.filter(c => c.level === 'beginner').length,
                intermediate: courses.filter(c => c.level === 'intermediate').length,
                advanced: courses.filter(c => c.level === 'advanced').length,
                free: courses.filter(c => c.price === 0).length
              };
              
              return (
                <div>
                  <h4>🌈 Complete Learning Ecosystem:</h4>
                  <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(150px, 1fr))', gap: '15px' }}>
                    <div style={{ textAlign: 'center', padding: '15px', background: '#27ae60', color: 'white', borderRadius: '8px' }}>
                      <h4>{stats.beginner}</h4>
                      <p>Beginner Courses</p>
                    </div>
                    <div style={{ textAlign: 'center', padding: '15px', background: '#f39c12', color: 'white', borderRadius: '8px' }}>
                      <h4>{stats.intermediate}</h4>
                      <p>Intermediate Courses</p>
                    </div>
                    <div style={{ textAlign: 'center', padding: '15px', background: '#e74c3c', color: 'white', borderRadius: '8px' }}>
                      <h4>{stats.advanced}</h4>
                      <p>Advanced Courses</p>
                    </div>
                    <div style={{ textAlign: 'center', padding: '15px', background: '#667eea', color: 'white', borderRadius: '8px' }}>
                      <h4>{stats.free}</h4>
                      <p>Free Courses</p>
                    </div>
                  </div>
                </div>
              );
            }
          })()}
        </div>
      </div>

      {/* 5. Dynamic Progress Tracking */}
      <div className="conditional-section">
        <h3>5. Learning Progress Dashboard</h3>
        <div className="example-box">
          {Object.keys(enrollmentStatus).length === 0 ? (
            <div style={{ textAlign: 'center', padding: '20px' }}>
              <h4>📈 Your Learning Journey Starts Here</h4>
              <p>Enroll in courses to track your progress and achievements!</p>
            </div>
          ) : (
            <div>
              <h4>📊 Your Course Progress:</h4>
              <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))', gap: '15px' }}>
                {Object.entries(enrollmentStatus).map(([courseId, status]) => {
                  const course = courses.find(c => c.id === parseInt(courseId));
                  if (!course) return null;
                  
                  return (
                    <div key={courseId} style={{
                      padding: '15px',
                      background: status === 'completed' ? '#27ae60' : '#3498db',
                      color: 'white',
                      borderRadius: '8px'
                    }}>
                      <h5>{course.title}</h5>
                      <p>Status: {status === 'enrolled' ? '📚 In Progress' : '✅ Completed'}</p>
                      {status === 'enrolled' && (
                        <div style={{ marginTop: '10px' }}>
                          <div style={{ background: 'rgba(255,255,255,0.3)', height: '8px', borderRadius: '4px' }}>
                            <div style={{ 
                              background: 'white', 
                              height: '100%', 
                              width: `${Math.floor(Math.random() * 80) + 20}%`, 
                              borderRadius: '4px' 
                            }}></div>
                          </div>
                        </div>
                      )}
                    </div>
                  );
                })}
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default CourseDetails;
