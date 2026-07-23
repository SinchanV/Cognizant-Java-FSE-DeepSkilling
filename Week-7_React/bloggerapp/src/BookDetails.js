import React, { useState } from 'react';

function BookDetails() {
    const [selectedBook, setSelectedBook] = useState(null);
    const [showDetails, setShowDetails] = useState(false);
    const [userType, setUserType] = useState('guest'); // guest, member, premium
    const [isLoading, setIsLoading] = useState(false);

    const books = [
        {
            id: 1,
            title: "The Great Gatsby",
            author: "F. Scott Fitzgerald",
            genre: "Classic Fiction",
            price: 299,
            rating: 4.5,
            pages: 180,
            published: "1925",
            description: "A classic American novel exploring themes of wealth, love, and the American Dream in the Jazz Age.",
            image: "📚",
            availability: "In Stock",
            reviews: 1250,
            isPremium: false
        },
        {
            id: 2,
            title: "To Kill a Mockingbird",
            author: "Harper Lee",
            genre: "Drama",
            price: 350,
            rating: 4.8,
            pages: 281,
            published: "1960",
            description: "A powerful story of racial injustice and childhood innocence in the American South.",
            image: "📖",
            availability: "In Stock",
            reviews: 2100,
            isPremium: false
        },
        {
            id: 3,
            title: "1984",
            author: "George Orwell",
            genre: "Dystopian Fiction",
            price: 450,
            rating: 4.7,
            pages: 328,
            published: "1949",
            description: "A dystopian social science fiction novel about totalitarian control and surveillance.",
            image: "📕",
            availability: "Limited Stock",
            reviews: 1890,
            isPremium: true
        },
        {
            id: 4,
            title: "Pride and Prejudice",
            author: "Jane Austen",
            genre: "Romance",
            price: 320,
            rating: 4.6,
            pages: 432,
            published: "1813",
            description: "A romantic novel about manners, upbringing, morality, education, and marriage.",
            image: "💕",
            availability: "Out of Stock",
            reviews: 1650,
            isPremium: false
        }
    ];

    const handleBookSelect = (book) => {
        setIsLoading(true);
        setTimeout(() => {
            setSelectedBook(book);
            setShowDetails(true);
            setIsLoading(false);
        }, 1000);
    };

    const renderStars = (rating) => {
        const stars = [];
        const fullStars = Math.floor(rating);
        const hasHalfStar = rating % 1 !== 0;

        for (let i = 0; i < fullStars; i++) {
            stars.push('⭐');
        }
        if (hasHalfStar) {
            stars.push('⭐');
        }
        return stars.join('');
    };

    return (
        <div className="book-details">
            <div className="component-header">
                <h2>📚 Book Details Component</h2>
                <p>Demonstrating Various Conditional Rendering Techniques</p>
            </div>

            {/* User Type Selector */}
            <div className="user-type-selector">
                <h3>Select User Type:</h3>
                <div className="user-buttons">
                    <button 
                        onClick={() => setUserType('guest')}
                        className={userType === 'guest' ? 'active' : ''}
                    >
                        👥 Guest
                    </button>
                    <button 
                        onClick={() => setUserType('member')}
                        className={userType === 'member' ? 'active' : ''}
                    >
                        🎯 Member
                    </button>
                    <button 
                        onClick={() => setUserType('premium')}
                        className={userType === 'premium' ? 'active' : ''}
                    >
                        💎 Premium
                    </button>
                </div>
            </div>

            {/* Conditional Rendering Example 1: Ternary Operator */}
            <div className="conditional-section">
                <h3>🔄 Conditional Rendering Examples:</h3>
                
                {/* 1. Simple Ternary Operator */}
                <div className="example-box">
                    <h4>1. Ternary Operator:</h4>
                    {userType === 'guest' ? (
                        <div className="guest-message">
                            🚫 Limited access - Please register to view all books
                        </div>
                    ) : (
                        <div className="member-message">
                            ✅ Welcome {userType}! You have full access
                        </div>
                    )}
                </div>

                {/* 2. Logical AND (&&) Operator */}
                <div className="example-box">
                    <h4>2. Logical AND (&&) Operator:</h4>
                    {userType === 'premium' && (
                        <div className="premium-badge">
                            💎 Premium Member - Access to exclusive books!
                        </div>
                    )}
                    {books.some(book => book.isPremium) && userType !== 'premium' && (
                        <div className="upgrade-message">
                            🔒 Some books require Premium membership
                        </div>
                    )}
                </div>

                {/* 3. Nested Conditional Rendering */}
                <div className="example-box">
                    <h4>3. Nested Conditional Rendering:</h4>
                    {userType !== 'guest' ? (
                        userType === 'premium' ? (
                            <div className="premium-features">
                                <h5>Premium Features:</h5>
                                <ul>
                                    <li>✅ Access to all books</li>
                                    <li>✅ Exclusive premium titles</li>
                                    <li>✅ Free shipping</li>
                                    <li>✅ Early access to new releases</li>
                                </ul>
                            </div>
                        ) : (
                            <div className="member-features">
                                <h5>Member Features:</h5>
                                <ul>
                                    <li>✅ Access to most books</li>
                                    <li>✅ Member discounts</li>
                                    <li>❌ Premium titles locked</li>
                                </ul>
                            </div>
                        )
                    ) : (
                        <div className="guest-info">
                            <p>📋 Register now to unlock member benefits!</p>
                        </div>
                    )}
                </div>
            </div>

            {/* Book List with Multiple Conditional Rendering */}
            <div className="books-grid">
                {books.map(book => (
                    <div key={book.id} className="book-card">
                        <div className="book-image">
                            <span className="book-icon">{book.image}</span>
                            
                            {/* 4. Conditional CSS Classes */}
                            <div className={`availability-badge ${
                                book.availability === 'In Stock' ? 'in-stock' :
                                book.availability === 'Limited Stock' ? 'limited-stock' : 'out-of-stock'
                            }`}>
                                {book.availability}
                            </div>
                        </div>

                        <div className="book-info">
                            <h4>{book.title}</h4>
                            <p className="author">by {book.author}</p>
                            <p className="genre">{book.genre}</p>
                            
                            {/* 5. Switch Case using Objects */}
                            <div className="price-display">
                                {(() => {
                                    switch(userType) {
                                        case 'guest':
                                            return <span className="price">₹{book.price}</span>;
                                        case 'member':
                                            return (
                                                <div>
                                                    <span className="original-price">₹{book.price}</span>
                                                    <span className="discounted-price">₹{Math.round(book.price * 0.9)}</span>
                                                    <span className="discount-badge">10% OFF</span>
                                                </div>
                                            );
                                        case 'premium':
                                            return (
                                                <div>
                                                    <span className="original-price">₹{book.price}</span>
                                                    <span className="discounted-price">₹{Math.round(book.price * 0.8)}</span>
                                                    <span className="discount-badge">20% OFF</span>
                                                </div>
                                            );
                                        default:
                                            return <span className="price">₹{book.price}</span>;
                                    }
                                })()}
                            </div>

                            <div className="rating">
                                {renderStars(book.rating)} ({book.reviews} reviews)
                            </div>

                            {/* 6. Multiple Condition Check */}
                            {book.isPremium && userType !== 'premium' ? (
                                <button className="premium-lock" disabled>
                                    🔒 Premium Only
                                </button>
                            ) : book.availability === 'Out of Stock' ? (
                                <button className="out-of-stock-btn" disabled>
                                    📦 Out of Stock
                                </button>
                            ) : (
                                <button 
                                    onClick={() => handleBookSelect(book)}
                                    className="view-details-btn"
                                    disabled={isLoading}
                                >
                                    {isLoading ? '⏳ Loading...' : '👁️ View Details'}
                                </button>
                            )}
                        </div>
                    </div>
                ))}
            </div>

            {/* 7. Conditional Rendering with Loading State */}
            {isLoading && (
                <div className="loading-overlay">
                    <div className="loading-spinner">
                        <div className="spinner"></div>
                        <p>Loading book details...</p>
                    </div>
                </div>
            )}

            {/* 8. Complex Conditional Rendering for Selected Book */}
            {showDetails && selectedBook && !isLoading && (
                <div className="book-details-modal">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h3>{selectedBook.title}</h3>
                            <button 
                                onClick={() => {setShowDetails(false); setSelectedBook(null);}}
                                className="close-btn"
                            >
                                ❌
                            </button>
                        </div>
                        
                        <div className="modal-body">
                            <div className="book-meta">
                                <p><strong>Author:</strong> {selectedBook.author}</p>
                                <p><strong>Genre:</strong> {selectedBook.genre}</p>
                                <p><strong>Pages:</strong> {selectedBook.pages}</p>
                                <p><strong>Published:</strong> {selectedBook.published}</p>
                                <p><strong>Rating:</strong> {renderStars(selectedBook.rating)} ({selectedBook.reviews} reviews)</p>
                            </div>
                            
                            <div className="book-description">
                                <h4>Description:</h4>
                                <p>{selectedBook.description}</p>
                            </div>

                            {/* 9. Render Props Pattern Simulation */}
                            <div className="action-buttons">
                                {userType === 'guest' ? (
                                    <button className="register-btn">
                                        📝 Register to Purchase
                                    </button>
                                ) : selectedBook.availability === 'Out of Stock' ? (
                                    <button className="notify-btn">
                                        🔔 Notify When Available
                                    </button>
                                ) : (
                                    <div className="purchase-actions">
                                        <button className="add-to-cart-btn">
                                            🛒 Add to Cart
                                        </button>
                                        <button className="buy-now-btn">
                                            ⚡ Buy Now
                                        </button>
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            )}

            {/* 10. Element Variables */}
            {(() => {
                let statusMessage;
                if (userType === 'guest') {
                    statusMessage = <div className="status-message guest">🚪 Browsing as Guest</div>;
                } else if (userType === 'member') {
                    statusMessage = <div className="status-message member">👤 Logged in as Member</div>;
                } else {
                    statusMessage = <div className="status-message premium">💎 Premium Member Active</div>;
                }
                return statusMessage;
            })()}
        </div>
    );
}

export default BookDetails;
