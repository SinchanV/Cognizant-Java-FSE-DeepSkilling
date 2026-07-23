import React, { useState, useEffect } from 'react';

function BlogDetails() {
    const [currentView, setCurrentView] = useState('list'); // list, create, edit, view
    const [selectedCategory, setSelectedCategory] = useState('all');
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [userRole, setUserRole] = useState('reader'); // reader, author, admin
    const [searchTerm, setSearchTerm] = useState('');
    const [showComments, setShowComments] = useState({});
    const [selectedBlog, setSelectedBlog] = useState(null);

    const blogs = [
        {
            id: 1,
            title: "Getting Started with React Hooks",
            category: "Technology",
            author: "John Doe",
            date: "2025-01-15",
            excerpt: "Learn the fundamentals of React Hooks and how they can simplify your component logic.",
            content: "React Hooks revolutionized how we write React components. In this comprehensive guide, we'll explore useState, useEffect, and custom hooks...",
            image: "⚛️",
            likes: 245,
            comments: 23,
            readTime: "5 min read",
            status: "published",
            isPremium: false,
            tags: ["React", "JavaScript", "Frontend"]
        },
        {
            id: 2,
            title: "The Future of Artificial Intelligence",
            category: "AI",
            author: "Jane Smith",
            date: "2025-01-10",
            excerpt: "Exploring the potential impact of AI on various industries and everyday life.",
            content: "Artificial Intelligence is rapidly evolving and transforming industries. From healthcare to transportation, AI is creating new possibilities...",
            image: "🤖",
            likes: 189,
            comments: 45,
            readTime: "8 min read",
            status: "published",
            isPremium: true,
            tags: ["AI", "Technology", "Future"]
        },
        {
            id: 3,
            title: "Sustainable Living in 2025",
            category: "Lifestyle",
            author: "Mike Johnson",
            date: "2025-01-08",
            excerpt: "Practical tips for reducing your carbon footprint and living more sustainably.",
            content: "Sustainability is more important than ever. Here are practical steps you can take to live more sustainably in 2025...",
            image: "🌱",
            likes: 156,
            comments: 18,
            readTime: "6 min read",
            status: "published",
            isPremium: false,
            tags: ["Sustainability", "Environment", "Lifestyle"]
        },
        {
            id: 4,
            title: "Digital Marketing Trends",
            category: "Business",
            author: "Sarah Wilson",
            date: "2025-01-05",
            excerpt: "The latest trends shaping digital marketing strategies in the modern world.",
            content: "Digital marketing continues to evolve. Stay ahead with these emerging trends and strategies...",
            image: "📊",
            likes: 201,
            comments: 31,
            readTime: "7 min read",
            status: "draft",
            isPremium: false,
            tags: ["Marketing", "Business", "Digital"]
        }
    ];

    const categories = ['all', 'Technology', 'AI', 'Lifestyle', 'Business'];

    // Filter blogs based on multiple conditions
    const filteredBlogs = blogs.filter(blog => {
        const matchesCategory = selectedCategory === 'all' || blog.category === selectedCategory;
        const matchesSearch = blog.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
                             blog.excerpt.toLowerCase().includes(searchTerm.toLowerCase());
        const hasAccess = !blog.isPremium || isLoggedIn;
        const isVisible = blog.status === 'published' || (userRole === 'admin' || userRole === 'author');
        
        return matchesCategory && matchesSearch && hasAccess && isVisible;
    });

    const toggleComments = (blogId) => {
        setShowComments(prev => ({
            ...prev,
            [blogId]: !prev[blogId]
        }));
    };

    // Conditional rendering helper functions
    const renderBlogCard = (blog) => (
        <div key={blog.id} className="blog-card">
            <div className="blog-header">
                <span className="blog-icon">{blog.image}</span>
                <div className="blog-meta">
                    <span className="category">{blog.category}</span>
                    {/* Conditional badge rendering */}
                    {blog.isPremium && <span className="premium-badge">💎 Premium</span>}
                    {blog.status === 'draft' && <span className="draft-badge">📝 Draft</span>}
                </div>
            </div>

            <div className="blog-content">
                <h3>{blog.title}</h3>
                <p className="blog-excerpt">{blog.excerpt}</p>
                
                <div className="blog-info">
                    <span className="author">By {blog.author}</span>
                    <span className="date">{blog.date}</span>
                    <span className="read-time">{blog.readTime}</span>
                </div>

                <div className="blog-stats">
                    <span className="likes">❤️ {blog.likes}</span>
                    <span className="comments">💬 {blog.comments}</span>
                </div>

                <div className="blog-tags">
                    {blog.tags.map(tag => (
                        <span key={tag} className="tag">#{tag}</span>
                    ))}
                </div>
            </div>

            <div className="blog-actions">
                {/* Complex conditional rendering for actions */}
                {!isLoggedIn ? (
                    <button className="login-required" disabled>
                        🔐 Login to Read
                    </button>
                ) : blog.isPremium && userRole === 'reader' ? (
                    <button className="upgrade-required" disabled>
                        💎 Premium Required
                    </button>
                ) : (
                    <>
                        <button 
                            onClick={() => {setSelectedBlog(blog); setCurrentView('view');}}
                            className="read-btn"
                        >
                            📖 Read Full Article
                        </button>
                        <button 
                            onClick={() => toggleComments(blog.id)}
                            className="comments-btn"
                        >
                            {showComments[blog.id] ? '🔼 Hide Comments' : '🔽 Show Comments'}
                        </button>
                    </>
                )}

                {/* Author/Admin specific actions */}
                {(userRole === 'admin' || (userRole === 'author' && blog.author === 'Current User')) && (
                    <div className="admin-actions">
                        <button 
                            onClick={() => {setSelectedBlog(blog); setCurrentView('edit');}}
                            className="edit-btn"
                        >
                            ✏️ Edit
                        </button>
                        {userRole === 'admin' && (
                            <button className="delete-btn">
                                🗑️ Delete
                            </button>
                        )}
                    </div>
                )}
            </div>

            {/* Conditional comments section */}
            {showComments[blog.id] && isLoggedIn && (
                <div className="comments-section">
                    <h4>Comments:</h4>
                    {blog.comments > 0 ? (
                        <div className="comments-list">
                            {/* Sample comments */}
                            <div className="comment">
                                <strong>User1:</strong> Great article! Very informative.
                            </div>
                            <div className="comment">
                                <strong>User2:</strong> Thanks for sharing these insights.
                            </div>
                        </div>
                    ) : (
                        <p className="no-comments">No comments yet. Be the first to comment!</p>
                    )}
                    <div className="add-comment">
                        <textarea placeholder="Add your comment..." rows="3"></textarea>
                        <button className="submit-comment">💬 Post Comment</button>
                    </div>
                </div>
            )}
        </div>
    );

    return (
        <div className="blog-details">
            <div className="component-header">
                <h2>📝 Blog Details Component</h2>
                <p>Advanced Conditional Rendering Techniques</p>
            </div>

            {/* User Status and Role Selector */}
            <div className="user-controls">
                <div className="login-section">
                    <h3>User Status:</h3>
                    <button 
                        onClick={() => setIsLoggedIn(!isLoggedIn)}
                        className={isLoggedIn ? 'logout-btn' : 'login-btn'}
                    >
                        {isLoggedIn ? '🚪 Logout' : '🔑 Login'}
                    </button>
                    
                    {/* Conditional role selector - only show when logged in */}
                    {isLoggedIn && (
                        <div className="role-selector">
                            <label>Role:</label>
                            <select 
                                value={userRole} 
                                onChange={(e) => setUserRole(e.target.value)}
                            >
                                <option value="reader">👤 Reader</option>
                                <option value="author">✍️ Author</option>
                                <option value="admin">👑 Admin</option>
                            </select>
                        </div>
                    )}
                </div>
            </div>

            {/* View Navigation */}
            <div className="view-navigation">
                <button 
                    onClick={() => setCurrentView('list')}
                    className={currentView === 'list' ? 'active' : ''}
                >
                    📋 Blog List
                </button>
                
                {/* Conditional navigation based on user permissions */}
                {isLoggedIn && (userRole === 'author' || userRole === 'admin') && (
                    <button 
                        onClick={() => setCurrentView('create')}
                        className={currentView === 'create' ? 'active' : ''}
                    >
                        ➕ Create Blog
                    </button>
                )}
                
                {userRole === 'admin' && (
                    <button 
                        onClick={() => setCurrentView('analytics')}
                        className={currentView === 'analytics' ? 'active' : ''}
                    >
                        📊 Analytics
                    </button>
                )}
            </div>

            {/* Main Content Area with Switch-Case like rendering */}
            <div className="main-content">
                {(() => {
                    switch(currentView) {
                        case 'list':
                            return (
                                <div className="blog-list-view">
                                    {/* Filters Section */}
                                    <div className="filters-section">
                                        <div className="search-bar">
                                            <input
                                                type="text"
                                                placeholder="🔍 Search blogs..."
                                                value={searchTerm}
                                                onChange={(e) => setSearchTerm(e.target.value)}
                                            />
                                        </div>

                                        <div className="category-filters">
                                            {categories.map(category => (
                                                <button
                                                    key={category}
                                                    onClick={() => setSelectedCategory(category)}
                                                    className={selectedCategory === category ? 'active' : ''}
                                                >
                                                    {category === 'all' ? '🌐 All' : `📂 ${category}`}
                                                </button>
                                            ))}
                                        </div>
                                    </div>

                                    {/* Results Count with Conditional Messages */}
                                    <div className="results-info">
                                        {filteredBlogs.length === 0 ? (
                                            <div className="no-results">
                                                {!isLoggedIn ? (
                                                    <p>🔐 Login to access more blogs</p>
                                                ) : searchTerm ? (
                                                    <p>🔍 No blogs found matching "{searchTerm}"</p>
                                                ) : (
                                                    <p>📭 No blogs available in this category</p>
                                                )}
                                            </div>
                                        ) : (
                                            <p>📊 Showing {filteredBlogs.length} of {blogs.length} blogs</p>
                                        )}
                                    </div>

                                    {/* Blog Cards */}
                                    <div className="blogs-grid">
                                        {filteredBlogs.map(renderBlogCard)}
                                    </div>
                                </div>
                            );

                        case 'create':
                            return (
                                <div className="create-blog-view">
                                    <h3>✍️ Create New Blog Post</h3>
                                    <form className="blog-form">
                                        <input type="text" placeholder="Blog Title" />
                                        <select>
                                            <option value="">Select Category</option>
                                            {categories.slice(1).map(cat => (
                                                <option key={cat} value={cat}>{cat}</option>
                                            ))}
                                        </select>
                                        <textarea placeholder="Write your blog content..." rows="10"></textarea>
                                        <div className="form-actions">
                                            <button type="button" className="save-draft">📝 Save as Draft</button>
                                            <button type="submit" className="publish">🚀 Publish</button>
                                        </div>
                                    </form>
                                </div>
                            );

                        case 'edit':
                            return (
                                <div className="edit-blog-view">
                                    <h3>✏️ Edit Blog Post</h3>
                                    {selectedBlog && (
                                        <form className="blog-form">
                                            <input type="text" defaultValue={selectedBlog.title} />
                                            <select defaultValue={selectedBlog.category}>
                                                {categories.slice(1).map(cat => (
                                                    <option key={cat} value={cat}>{cat}</option>
                                                ))}
                                            </select>
                                            <textarea defaultValue={selectedBlog.content} rows="10"></textarea>
                                            <div className="form-actions">
                                                <button type="button" onClick={() => setCurrentView('list')}>❌ Cancel</button>
                                                <button type="submit" className="update">💾 Update</button>
                                            </div>
                                        </form>
                                    )}
                                </div>
                            );

                        case 'view':
                            return (
                                <div className="view-blog">
                                    {selectedBlog && (
                                        <div className="full-blog">
                                            <button 
                                                onClick={() => setCurrentView('list')}
                                                className="back-btn"
                                            >
                                                ← Back to List
                                            </button>
                                            <div className="blog-full-content">
                                                <h1>{selectedBlog.title}</h1>
                                                <div className="blog-meta-full">
                                                    <span>By {selectedBlog.author}</span>
                                                    <span>{selectedBlog.date}</span>
                                                    <span>{selectedBlog.readTime}</span>
                                                </div>
                                                <div className="blog-body">
                                                    <p>{selectedBlog.content}</p>
                                                </div>
                                            </div>
                                        </div>
                                    )}
                                </div>
                            );

                        case 'analytics':
                            return (
                                <div className="analytics-view">
                                    <h3>📊 Blog Analytics (Admin Only)</h3>
                                    <div className="analytics-cards">
                                        <div className="stat-card">
                                            <h4>Total Blogs</h4>
                                            <p className="stat-number">{blogs.length}</p>
                                        </div>
                                        <div className="stat-card">
                                            <h4>Published</h4>
                                            <p className="stat-number">{blogs.filter(b => b.status === 'published').length}</p>
                                        </div>
                                        <div className="stat-card">
                                            <h4>Drafts</h4>
                                            <p className="stat-number">{blogs.filter(b => b.status === 'draft').length}</p>
                                        </div>
                                        <div className="stat-card">
                                            <h4>Premium Content</h4>
                                            <p className="stat-number">{blogs.filter(b => b.isPremium).length}</p>
                                        </div>
                                    </div>
                                </div>
                            );

                        default:
                            return <div>🚫 Invalid view</div>;
                    }
                })()}
            </div>

            {/* Status Bar with Multiple Conditions */}
            <div className="status-bar">
                {isLoggedIn ? (
                    <div className="logged-in-status">
                        <span>✅ Logged in as {userRole}</span>
                        {userRole === 'admin' && <span>| 👑 Full access granted</span>}
                        {userRole === 'author' && <span>| ✍️ Can create and edit blogs</span>}
                        {userRole === 'reader' && <span>| 👁️ Read-only access</span>}
                    </div>
                ) : (
                    <div className="guest-status">
                        <span>👥 Browsing as guest | Limited access</span>
                    </div>
                )}
            </div>
        </div>
    );
}

export default BlogDetails;
