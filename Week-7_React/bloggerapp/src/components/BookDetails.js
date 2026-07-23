import React, { useState } from 'react';

const BookDetails = () => {
  // State for demonstrating different conditional rendering scenarios
  const [userType, setUserType] = useState('guest');
  const [isLoading, setIsLoading] = useState(false);
  const [selectedBook, setSelectedBook] = useState(null);
  const [showDetails, setShowDetails] = useState(false);

  // Sample book data
  const books = [
    {
      id: 1,
      title: "Advanced React Patterns",
      author: "John Doe",
      genre: "Technology",
      price: 45.99,
      originalPrice: 59.99,
      inStock: true,
      stockCount: 15,
      rating: 4.8,
      isPremium: false,
      reviews: 245
    },
    {
      id: 2,
      title: "Mastering JavaScript",
      author: "Jane Smith",
      genre: "Programming",
      price: 39.99,
      originalPrice: 49.99,
      inStock: true,
      stockCount: 3,
      rating: 4.9,
      isPremium: true,
      reviews: 189
    },
    {
      id: 3,
      title: "Data Structures & Algorithms",
      author: "Mike Johnson",
      genre: "Computer Science",
      price: 55.99,
      originalPrice: 55.99,
      inStock: false,
      stockCount: 0,
      rating: 4.7,
      isPremium: true,
      reviews: 332
    },
    {
      id: 4,
      title: "Web Development Fundamentals",
      author: "Sarah Wilson",
      genre: "Web Development",
      price: 29.99,
      originalPrice: 39.99,
      inStock: true,
      stockCount: 25,
      rating: 4.6,
      isPremium: false,
      reviews: 156
    }
  ];

  // Simulate loading for demonstration
  const handleLoadBooks = () => {
    setIsLoading(true);
    setTimeout(() => {
      setIsLoading(false);
    }, 2000);
  };

  const handleBookSelect = (book) => {
    setSelectedBook(book);
    setShowDetails(true);
  };

  return (
    <div className="book-details">
      <div className="component-header">
        <h2>📚 Book Details Component</h2>
        <p>Demonstrating Multiple Conditional Rendering Techniques</p>
      </div>

      {/* 1. Ternary Operator - User Type Selection */}
      <div className="conditional-section">
        <h3>1. Ternary Operator - User Authentication</h3>
        <div className="user-type-selector">
          <p>Current User Type: <strong>{userType}</strong></p>
          <div className="user-buttons">
            <button 
              className={userType === 'guest' ? 'active' : ''}
              onClick={() => setUserType('guest')}
            >
              Guest User
            </button>
            <button 
              className={userType === 'member' ? 'active' : ''}
              onClick={() => setUserType('member')}
            >
              Member
            </button>
            <button 
              className={userType === 'premium' ? 'active' : ''}
              onClick={() => setUserType('premium')}
            >
              Premium Member
            </button>
          </div>
        </div>

        <div className="example-box">
          {userType === 'guest' ? (
            <div className="guest-message">
              🚫 Please login to view book details and make purchases
            </div>
          ) : userType === 'member' ? (
            <div className="member-message">
              ✅ Welcome Member! You can view all books and purchase non-premium content
            </div>
          ) : (
            <div className="premium-badge">
              👑 Premium Member - Full Access to All Content!
            </div>
          )}
        </div>
      </div>

      {/* 2. Logical AND Operator */}
      <div className="conditional-section">
        <h3>2. Logical AND (&&) Operator</h3>
        <div className="example-box">
          {userType !== 'guest' && (
            <div>
              <p>✨ Exclusive member benefits:</p>
              <ul>
                <li>Free shipping on orders over $30</li>
                <li>Early access to new releases</li>
                <li>Member-only discounts</li>
              </ul>
            </div>
          )}
          
          {userType === 'premium' && (
            <div>
              <p>🌟 Premium exclusive features:</p>
              <ul>
                <li>Access to premium books</li>
                <li>Unlimited downloads</li>
                <li>Priority customer support</li>
              </ul>
            </div>
          )}
        </div>
      </div>

      {/* 3. Short-circuit Evaluation with Logical OR */}
      <div className="conditional-section">
        <h3>3. Logical OR (||) Operator</h3>
        <div className="example-box">
          <p>
            Welcome, {userType === 'guest' && 'Guest'} 
            {userType === 'member' && 'Valued Member'} 
            {userType === 'premium' && 'Premium Member'} 
            {!userType && 'User'}!
          </p>
          <p>
            Your status: {(userType === 'premium' && 'VIP Access') || 
                         (userType === 'member' && 'Standard Access') || 
                         'Limited Access'}
          </p>
        </div>
      </div>

      {/* 4. Switch Case Simulation using Object Mapping */}
      <div className="conditional-section">
        <h3>4. Switch-Case Style Conditional Rendering</h3>
        <div className="example-box">
          {(() => {
            const userBenefits = {
              guest: (
                <div style={{color: '#e74c3c'}}>
                  <h4>Guest Benefits:</h4>
                  <p>• Browse book catalog</p>
                  <p>• Read book descriptions</p>
                  <p>• Create an account</p>
                </div>
              ),
              member: (
                <div style={{color: '#27ae60'}}>
                  <h4>Member Benefits:</h4>
                  <p>• Purchase books</p>
                  <p>• Access free samples</p>
                  <p>• Save to wishlist</p>
                  <p>• Rate and review books</p>
                </div>
              ),
              premium: (
                <div style={{color: '#f39c12'}}>
                  <h4>Premium Benefits:</h4>
                  <p>• All member benefits</p>
                  <p>• Access premium content</p>
                  <p>• Unlimited downloads</p>
                  <p>• Exclusive author events</p>
                  <p>• Personal recommendations</p>
                </div>
              )
            };
            
            return userBenefits[userType] || <p>Unknown user type</p>;
          })()}
        </div>
      </div>

      {/* 5. Loading State Conditional Rendering */}
      <div className="conditional-section">
        <h3>5. Loading State Management</h3>
        <div className="example-box">
          <button onClick={handleLoadBooks} disabled={isLoading}>
            {isLoading ? 'Loading Books...' : 'Load Books'}
          </button>
          
          {isLoading && (
            <div className="loading-overlay">
              <div className="loading-spinner">
                <div className="spinner"></div>
                <p>Fetching latest books...</p>
              </div>
            </div>
          )}
        </div>
      </div>

      {/* 6. Nested Conditional Rendering */}
      <div className="conditional-section">
        <h3>6. Nested Conditionals</h3>
        <div className="example-box">
          {userType !== 'guest' ? (
            <div>
              {userType === 'member' ? (
                <div>
                  <p>🎯 Recommended for Members:</p>
                  {books.filter(book => !book.isPremium).length > 0 ? (
                    <p>We found {books.filter(book => !book.isPremium).length} books perfect for you!</p>
                  ) : (
                    <p>No regular books available at the moment.</p>
                  )}
                </div>
              ) : (
                <div>
                  <p>👑 All Books Available for Premium Members:</p>
                  {books.length > 0 ? (
                    <p>Browse our complete collection of {books.length} books!</p>
                  ) : (
                    <p>Our collection is being updated.</p>
                  )}
                </div>
              )}
            </div>
          ) : (
            <div>
              <p>🔒 Please log in to see personalized recommendations</p>
            </div>
          )}
        </div>
      </div>

      {/* 7. Element Variables */}
      <div className="conditional-section">
        <h3>7. Element Variables</h3>
        <div className="example-box">
          {(() => {
            let statusElement;
            
            if (userType === 'guest') {
              statusElement = (
                <div className="status-message guest">
                  <h4>Guest Mode Active</h4>
                  <p>Sign up today to unlock full features!</p>
                </div>
              );
            } else if (userType === 'member') {
              statusElement = (
                <div className="status-message member">
                  <h4>Member Dashboard</h4>
                  <p>Welcome back! Ready to discover new books?</p>
                </div>
              );
            } else {
              statusElement = (
                <div className="status-message premium">
                  <h4>Premium Experience</h4>
                  <p>Enjoy unlimited access to our entire library!</p>
                </div>
              );
            }
            
            return statusElement;
          })()}
        </div>
      </div>

      {/* 8. Conditional Classes and Styles */}
      <div className="conditional-section">
        <h3>8. Conditional CSS Classes</h3>
        <div className="example-box">
          <div 
            className={`user-badge ${userType === 'premium' ? 'premium-user' : ''} ${userType === 'member' ? 'member-user' : ''}`}
            style={{
              backgroundColor: userType === 'premium' ? '#f39c12' : userType === 'member' ? '#27ae60' : '#e74c3c',
              color: 'white',
              padding: '10px',
              borderRadius: '5px',
              textAlign: 'center',
              fontWeight: 'bold'
            }}
          >
            {userType.toUpperCase()} STATUS
          </div>
        </div>
      </div>

      {/* 9. Array Method Conditional Rendering */}
      <div className="conditional-section">
        <h3>9. Books Grid with Multiple Conditions</h3>
        
        {!isLoading && (
          <div className="books-grid">
            {books
              .filter(book => {
                // Filter based on user type and premium access
                if (userType === 'guest') return false;
                if (userType === 'member' && book.isPremium) return false;
                return true;
              })
              .map(book => (
                <div key={book.id} className="book-card">
                  <div className="book-image">
                    <div className="book-icon">📖</div>
                    
                    {/* Conditional availability badge */}
                    <div className={`availability-badge ${
                      book.inStock 
                        ? book.stockCount > 10 
                          ? 'in-stock' 
                          : 'limited-stock'
                        : 'out-of-stock'
                    }`}>
                      {book.inStock 
                        ? book.stockCount > 10 
                          ? 'In Stock'
                          : `Only ${book.stockCount} left`
                        : 'Out of Stock'
                      }
                    </div>
                  </div>
                  
                  <div className="book-info">
                    <h4>{book.title}</h4>
                    <p className="author">by {book.author}</p>
                    <p className="genre">{book.genre}</p>
                    
                    {/* Conditional pricing display */}
                    <div className="price-display">
                      {book.price < book.originalPrice ? (
                        <div className="discount-pricing">
                          <span className="original-price">${book.originalPrice}</span>
                          <span className="discounted-price">${book.price}</span>
                          <span className="discount-badge">
                            {Math.round((1 - book.price / book.originalPrice) * 100)}% OFF
                          </span>
                        </div>
                      ) : (
                        <span className="regular-price">${book.price}</span>
                      )}
                    </div>
                    
                    <p>⭐ {book.rating} ({book.reviews} reviews)</p>
                    
                    {/* Conditional action buttons */}
                    {book.inStock ? (
                      userType === 'member' && book.isPremium ? (
                        <button className="premium-lock" disabled>
                          🔒 Premium Only
                        </button>
                      ) : (
                        <button 
                          className="view-details-btn"
                          onClick={() => handleBookSelect(book)}
                        >
                          {userType === 'premium' ? 'Download Now' : 'View Details'}
                        </button>
                      )
                    ) : (
                      <button className="out-of-stock-btn" disabled>
                        Currently Unavailable
                      </button>
                    )}
                  </div>
                </div>
              ))}
          </div>
        )}
        
        {/* Conditional empty state */}
        {!isLoading && books.filter(book => {
          if (userType === 'guest') return false;
          if (userType === 'member' && book.isPremium) return false;
          return true;
        }).length === 0 && (
          <div className="example-box">
            <p>No books available for your current membership level.</p>
            {userType === 'member' && (
              <p>Upgrade to Premium to access our complete library!</p>
            )}
          </div>
        )}
      </div>

      {/* 10. Modal Conditional Rendering */}
      {showDetails && selectedBook && (
        <div className="book-details-modal" onClick={() => setShowDetails(false)}>
          <div className="modal-content" onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <h3>{selectedBook.title}</h3>
              <button 
                className="close-btn"
                onClick={() => setShowDetails(false)}
              >
                ✕
              </button>
            </div>
            <div className="modal-body">
              <p><strong>Author:</strong> {selectedBook.author}</p>
              <p><strong>Genre:</strong> {selectedBook.genre}</p>
              <p><strong>Price:</strong> ${selectedBook.price}</p>
              <p><strong>Rating:</strong> ⭐ {selectedBook.rating}</p>
              <p><strong>Reviews:</strong> {selectedBook.reviews}</p>
              
              {selectedBook.isPremium && (
                <div className="premium-badge">
                  👑 Premium Content
                </div>
              )}
              
              {userType === 'premium' ? (
                <button className="view-details-btn">
                  Add to Library
                </button>
              ) : (
                <button className="view-details-btn">
                  Add to Cart - ${selectedBook.price}
                </button>
              )}
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default BookDetails;
