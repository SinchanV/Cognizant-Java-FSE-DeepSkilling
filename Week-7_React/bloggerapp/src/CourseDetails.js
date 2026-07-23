import React, { useState, useEffect } from 'react';

function CourseDetails() {
    const [enrollmentStatus, setEnrollmentStatus] = useState('none'); // none, enrolled, completed
    const [userLevel, setUserLevel] = useState('beginner'); // beginner, intermediate, advanced
    const [selectedCourse, setSelectedCourse] = useState(null);
    const [viewMode, setViewMode] = useState('grid'); // grid, list, detailed
    const [filterDifficulty, setFilterDifficulty] = useState('all');
    const [sortBy, setSortBy] = useState('popularity');
    const [showPrerequisites, setShowPrerequisites] = useState({});
    const [completedLessons, setCompletedLessons] = useState({});

    const courses = [
        {
            id: 1,
            title: "React Fundamentals",
            instructor: "Sarah Johnson",
            difficulty: "beginner",
            duration: "8 weeks",
            price: 2999,
            originalPrice: 4999,
            rating: 4.8,
            students: 12450,
            image: "⚛️",
            category: "Frontend Development",
            description: "Master the fundamentals of React including components, state, and props.",
            prerequisites: ["HTML", "CSS", "Basic JavaScript"],
            skills: ["React Components", "JSX", "State Management", "Event Handling"],
            lessons: 45,
            projects: 5,
            certificate: true,
            language: "English",
            lastUpdated: "2025-01-01",
            isFree: false,
            isPopular: true,
            hasVideoContent: true,
            downloadableResources: true
        },
        {
            id: 2,
            title: "Advanced JavaScript",
            instructor: "Mike Chen",
            difficulty: "advanced",
            duration: "12 weeks",
            price: 0,
            originalPrice: 0,
            rating: 4.9,
            students: 8790,
            image: "🚀",
            category: "Programming",
            description: "Deep dive into advanced JavaScript concepts, async programming, and modern ES6+ features.",
            prerequisites: ["JavaScript Basics", "ES6", "DOM Manipulation"],
            skills: ["Async/Await", "Promises", "Closures", "Prototypes"],
            lessons: 60,
            projects: 8,
            certificate: true,
            language: "English",
            lastUpdated: "2024-12-15",
            isFree: true,
            isPopular: false,
            hasVideoContent: true,
            downloadableResources: false
        },
        {
            id: 3,
            title: "Python for Beginners",
            instructor: "Dr. Emily Watson",
            difficulty: "beginner",
            duration: "6 weeks",
            price: 1999,
            originalPrice: 2999,
            rating: 4.6,
            students: 15670,
            image: "🐍",
            category: "Programming",
            description: "Start your programming journey with Python. Learn syntax, data structures, and basic programming concepts.",
            prerequisites: ["None"],
            skills: ["Python Syntax", "Data Types", "Functions", "OOP"],
            lessons: 35,
            projects: 4,
            certificate: true,
            language: "English",
            lastUpdated: "2025-01-10",
            isFree: false,
            isPopular: true,
            hasVideoContent: true,
            downloadableResources: true
        },
        {
            id: 4,
            title: "Data Science with R",
            instructor: "Prof. David Kumar",
            difficulty: "intermediate",
            duration: "10 weeks",
            price: 3999,
            originalPrice: 5999,
            rating: 4.7,
            students: 5420,
            image: "📊",
            category: "Data Science",
            description: "Learn statistical analysis and data visualization using R programming language.",
            prerequisites: ["Statistics Basics", "Mathematics"],
            skills: ["R Programming", "Data Analysis", "Statistics", "Visualization"],
            lessons: 55,
            projects: 6,
            certificate: true,
            language: "English",
            lastUpdated: "2024-11-20",
            isFree: false,
            isPopular: false,
            hasVideoContent: false,
            downloadableResources: true
        }
    ];

    // Complex filtering logic
    const filteredCourses = courses.filter(course => {
        const matchesDifficulty = filterDifficulty === 'all' || course.difficulty === filterDifficulty;
        const matchesLevel = userLevel === 'beginner' ? 
            course.difficulty !== 'advanced' : 
            userLevel === 'intermediate' ? 
                course.difficulty !== 'beginner' : 
                true;
        
        return matchesDifficulty && matchesLevel;
    });

    // Sorting logic with conditional rendering
    const sortedCourses = [...filteredCourses].sort((a, b) => {
        switch(sortBy) {
            case 'popularity':
                return b.students - a.students;
            case 'rating':
                return b.rating - a.rating;
            case 'price':
                return a.price - b.price;
            case 'duration':
                return parseInt(a.duration) - parseInt(b.duration);
            default:
                return 0;
        }
    });

    const togglePrerequisites = (courseId) => {
        setShowPrerequisites(prev => ({
            ...prev,
            [courseId]: !prev[courseId]
        }));
    };

    const markLessonComplete = (courseId, lessonIndex) => {
        setCompletedLessons(prev => ({
            ...prev,
            [`${courseId}_${lessonIndex}`]: !prev[`${courseId}_${lessonIndex}`]
        }));
    };

    const renderStars = (rating) => {
        return '⭐'.repeat(Math.floor(rating)) + (rating % 1 ? '⭐' : '');
    };

    // Component for course card rendering
    const CourseCard = ({ course, viewMode }) => (
        <div className={`course-card ${viewMode} ${course.isPopular ? 'popular' : ''}`}>
            {/* Conditional popularity badge */}
            {course.isPopular && <div className="popular-badge">🔥 Popular</div>}
            
            {/* Conditional free badge */}
            {course.isFree && <div className="free-badge">🆓 Free</div>}

            <div className="course-image">
                <span className="course-icon">{course.image}</span>
                
                {/* Conditional difficulty indicator */}
                <div className={`difficulty-badge ${course.difficulty}`}>
                    {course.difficulty === 'beginner' ? '🟢' : 
                     course.difficulty === 'intermediate' ? '🟡' : '🔴'} 
                    {course.difficulty}
                </div>
            </div>

            <div className="course-content">
                <h3>{course.title}</h3>
                <p className="instructor">👨‍🏫 {course.instructor}</p>
                <p className="category">📂 {course.category}</p>

                {/* Conditional description based on view mode */}
                {viewMode !== 'grid' && (
                    <p className="description">{course.description}</p>
                )}

                <div className="course-stats">
                    <span className="rating">
                        {renderStars(course.rating)} ({course.rating})
                    </span>
                    <span className="students">👥 {course.students.toLocaleString()}</span>
                    <span className="duration">⏱️ {course.duration}</span>
                </div>

                {/* Conditional pricing display */}
                <div className="pricing">
                    {course.isFree ? (
                        <span className="free-price">FREE</span>
                    ) : course.originalPrice > course.price ? (
                        <div className="discount-pricing">
                            <span className="original-price">₹{course.originalPrice}</span>
                            <span className="current-price">₹{course.price}</span>
                            <span className="discount">
                                {Math.round(((course.originalPrice - course.price) / course.originalPrice) * 100)}% OFF
                            </span>
                        </div>
                    ) : (
                        <span className="regular-price">₹{course.price}</span>
                    )}
                </div>

                {/* Conditional features display */}
                <div className="course-features">
                    {course.hasVideoContent && <span className="feature">🎥 Video</span>}
                    {course.downloadableResources && <span className="feature">📁 Resources</span>}
                    {course.certificate && <span className="feature">🏆 Certificate</span>}
                </div>

                {/* Conditional prerequisites */}
                {course.prerequisites.length > 0 && course.prerequisites[0] !== "None" && (
                    <div className="prerequisites-section">
                        <button 
                            onClick={() => togglePrerequisites(course.id)}
                            className="prerequisites-toggle"
                        >
                            {showPrerequisites[course.id] ? '🔼' : '🔽'} Prerequisites
                        </button>
                        
                        {showPrerequisites[course.id] && (
                            <div className="prerequisites-list">
                                {course.prerequisites.map((prereq, index) => (
                                    <span key={index} className="prerequisite">{prereq}</span>
                                ))}
                            </div>
                        )}
                    </div>
                )}

                {/* Action buttons with conditional rendering */}
                <div className="course-actions">
                    {enrollmentStatus === 'none' ? (
                        <>
                            <button 
                                onClick={() => {setSelectedCourse(course); setEnrollmentStatus('enrolled');}}
                                className="enroll-btn"
                            >
                                {course.isFree ? '📚 Enroll Free' : '💳 Enroll Now'}
                            </button>
                            <button 
                                onClick={() => setSelectedCourse(course)}
                                className="preview-btn"
                            >
                                👁️ Preview
                            </button>
                        </>
                    ) : enrollmentStatus === 'enrolled' && selectedCourse?.id === course.id ? (
                        <div className="enrolled-actions">
                            <button 
                                onClick={() => setViewMode('detailed')}
                                className="continue-btn"
                            >
                                ▶️ Continue Learning
                            </button>
                            <span className="enrollment-status">✅ Enrolled</span>
                        </div>
                    ) : enrollmentStatus === 'completed' && selectedCourse?.id === course.id ? (
                        <div className="completed-actions">
                            <button className="review-btn">🔄 Review Course</button>
                            <button className="certificate-btn">🏆 Download Certificate</button>
                            <span className="completion-status">🎉 Completed</span>
                        </div>
                    ) : (
                        <button 
                            onClick={() => {setSelectedCourse(course); setEnrollmentStatus('enrolled');}}
                            className="enroll-btn"
                        >
                            {course.isFree ? '📚 Enroll Free' : '💳 Enroll Now'}
                        </button>
                    )}
                </div>
            </div>
        </div>
    );

    return (
        <div className="course-details">
            <div className="component-header">
                <h2>🎓 Course Details Component</h2>
                <p>Comprehensive Conditional Rendering Examples</p>
            </div>

            {/* User Level and Status Controls */}
            <div className="user-controls">
                <div className="level-selector">
                    <h3>Your Level:</h3>
                    <div className="level-buttons">
                        {['beginner', 'intermediate', 'advanced'].map(level => (
                            <button
                                key={level}
                                onClick={() => setUserLevel(level)}
                                className={userLevel === level ? 'active' : ''}
                            >
                                {level === 'beginner' ? '🟢 Beginner' :
                                 level === 'intermediate' ? '🟡 Intermediate' : '🔴 Advanced'}
                            </button>
                        ))}
                    </div>
                </div>

                <div className="enrollment-simulator">
                    <h3>Enrollment Status:</h3>
                    <select 
                        value={enrollmentStatus} 
                        onChange={(e) => setEnrollmentStatus(e.target.value)}
                    >
                        <option value="none">Not Enrolled</option>
                        <option value="enrolled">Enrolled</option>
                        <option value="completed">Completed</option>
                    </select>
                </div>
            </div>

            {/* Conditional Welcome Message */}
            <div className="welcome-section">
                {(() => {
                    if (userLevel === 'beginner') {
                        return (
                            <div className="beginner-welcome">
                                <h3>🌟 Welcome to Learning!</h3>
                                <p>Perfect courses for beginners are highlighted. Start your journey!</p>
                            </div>
                        );
                    } else if (userLevel === 'intermediate') {
                        return (
                            <div className="intermediate-welcome">
                                <h3>🚀 Ready for the Next Level!</h3>
                                <p>Great! You can access beginner and intermediate courses.</p>
                            </div>
                        );
                    } else {
                        return (
                            <div className="advanced-welcome">
                                <h3>🎯 Expert Path</h3>
                                <p>Access all courses including advanced level content.</p>
                            </div>
                        );
                    }
                })()}
            </div>

            {/* Filters and Controls */}
            <div className="controls-section">
                <div className="filters">
                    <div className="difficulty-filter">
                        <label>Filter by Difficulty:</label>
                        <select 
                            value={filterDifficulty} 
                            onChange={(e) => setFilterDifficulty(e.target.value)}
                        >
                            <option value="all">All Levels</option>
                            <option value="beginner">Beginner</option>
                            <option value="intermediate">Intermediate</option>
                            <option value="advanced">Advanced</option>
                        </select>
                    </div>

                    <div className="sort-options">
                        <label>Sort by:</label>
                        <select value={sortBy} onChange={(e) => setSortBy(e.target.value)}>
                            <option value="popularity">Popularity</option>
                            <option value="rating">Rating</option>
                            <option value="price">Price</option>
                            <option value="duration">Duration</option>
                        </select>
                    </div>

                    <div className="view-modes">
                        <button 
                            onClick={() => setViewMode('grid')}
                            className={viewMode === 'grid' ? 'active' : ''}
                        >
                            🔲 Grid
                        </button>
                        <button 
                            onClick={() => setViewMode('list')}
                            className={viewMode === 'list' ? 'active' : ''}
                        >
                            📋 List
                        </button>
                    </div>
                </div>
            </div>

            {/* Results Summary with Conditional Messages */}
            <div className="results-summary">
                {sortedCourses.length === 0 ? (
                    <div className="no-results">
                        <p>😔 No courses match your current level and filters.</p>
                        <button onClick={() => {setFilterDifficulty('all'); setUserLevel('advanced');}}>
                            🔓 Show All Courses
                        </button>
                    </div>
                ) : (
                    <div className="results-info">
                        <p>
                            📊 Showing {sortedCourses.length} courses 
                            {filterDifficulty !== 'all' && ` (${filterDifficulty} level)`}
                            {userLevel !== 'advanced' && ` suitable for ${userLevel} level`}
                        </p>
                        
                        {/* Conditional recommendations */}
                        {userLevel === 'beginner' && sortedCourses.some(c => c.difficulty === 'advanced') && (
                            <p className="level-warning">
                                ⚠️ Some advanced courses are hidden. Level up to access them!
                            </p>
                        )}
                    </div>
                )}
            </div>

            {/* Main Content Area */}
            {viewMode !== 'detailed' ? (
                <div className={`courses-container ${viewMode}`}>
                    {sortedCourses.map(course => (
                        <CourseCard key={course.id} course={course} viewMode={viewMode} />
                    ))}
                </div>
            ) : selectedCourse ? (
                /* Detailed Course View */
                <div className="detailed-course-view">
                    <button 
                        onClick={() => setViewMode('grid')}
                        className="back-to-courses"
                    >
                        ← Back to Courses
                    </button>
                    
                    <div className="course-detail-header">
                        <h2>{selectedCourse.title}</h2>
                        <div className="course-progress">
                            {enrollmentStatus === 'enrolled' && (
                                <div className="progress-section">
                                    <h4>📚 Course Progress</h4>
                                    <div className="lessons-list">
                                        {Array.from({length: selectedCourse.lessons}, (_, i) => (
                                            <div key={i} className="lesson-item">
                                                <input
                                                    type="checkbox"
                                                    checked={completedLessons[`${selectedCourse.id}_${i}`] || false}
                                                    onChange={() => markLessonComplete(selectedCourse.id, i)}
                                                />
                                                <span className={completedLessons[`${selectedCourse.id}_${i}`] ? 'completed' : ''}>
                                                    Lesson {i + 1}: Sample Lesson Title
                                                </span>
                                            </div>
                                        ))}
                                    </div>
                                    
                                    {Object.keys(completedLessons).filter(key => 
                                        key.startsWith(`${selectedCourse.id}_`) && completedLessons[key]
                                    ).length === selectedCourse.lessons && (
                                        <div className="completion-celebration">
                                            <h3>🎉 Congratulations! Course Completed!</h3>
                                            <button 
                                                onClick={() => setEnrollmentStatus('completed')}
                                                className="mark-complete-btn"
                                            >
                                                🏆 Claim Certificate
                                            </button>
                                        </div>
                                    )}
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            ) : null}

            {/* Statistics Section with Conditional Data */}
            <div className="statistics-section">
                <h3>📈 Learning Statistics</h3>
                <div className="stats-grid">
                    <div className="stat-item">
                        <h4>Available Courses</h4>
                        <p>{sortedCourses.length}</p>
                    </div>
                    <div className="stat-item">
                        <h4>Free Courses</h4>
                        <p>{sortedCourses.filter(c => c.isFree).length}</p>
                    </div>
                    <div className="stat-item">
                        <h4>Your Level Access</h4>
                        <p>{userLevel === 'beginner' ? 'Basic' : userLevel === 'intermediate' ? 'Standard' : 'Full'}</p>
                    </div>
                    <div className="stat-item">
                        <h4>Enrollment Status</h4>
                        <p>{enrollmentStatus === 'none' ? 'Browsing' : enrollmentStatus}</p>
                    </div>
                </div>
            </div>

            {/* Conditional Footer Messages */}
            <div className="footer-messages">
                {enrollmentStatus === 'none' && (
                    <p className="cta-message">🚀 Enroll in a course to start your learning journey!</p>
                )}
                {enrollmentStatus === 'enrolled' && selectedCourse && (
                    <p className="encouragement">💪 Keep going! You're enrolled in {selectedCourse.title}</p>
                )}
                {enrollmentStatus === 'completed' && (
                    <p className="completion-message">🎓 Amazing! You've completed a course. Ready for the next challenge?</p>
                )}
            </div>
        </div>
    );
}

export default CourseDetails;
