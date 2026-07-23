import React, { useState } from 'react';

const BlogDetails = () => {
  // State for various conditional rendering scenarios
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userRole, setUserRole] = useState('reader');
  const [currentView, setCurrentView] = useState('all');
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('all');

  // Sample blog data
  const blogs = [
    {
      id: 1,
      title: "Getting Started with React Hooks",
      excerpt: "Learn the fundamentals of React Hooks and how they can simplify your component logic.",
      category: "tutorial",
      author: "Sarah Johnson",
      publishDate: "2024-01-15",
      readTime: 8,
      likes: 245,
      comments: 23,
      tags: ["react", "hooks", "javascript"],
      isPremium: false,
      isPublished: true,
      viewCount: 1250
    },
    {
      id: 2,
      title: "Advanced State Management Patterns",
      excerpt: "Explore complex state management techniques including Context API and custom hooks.",
      category: "advanced",
      author: "Mike Chen",
      publishDate: "2024-01-12",
      readTime: 15,
      likes: 189,
      comments: 45,
      tags: ["react", "state", "context"],
      isPremium: true,
      isPublished: true,
      viewCount: 890
    },
    {
      id: 3,
      title: "Building Responsive Web Applications",
      excerpt: "Master the art of creating applications that work seamlessly across all devices.",
      category: "design",
      author: "Emma Wilson",
      publishDate: "2024-01-10",
      readTime: 12,
      likes: 156,
      comments: 18,
      tags: ["css", "responsive", "design"],
      isPremium: false,
      isPublished: true,
      viewCount: 2100
    },
    {
      id: 4,
      title: "Future of Web Development",
      excerpt: "A comprehensive look at emerging technologies and trends shaping the web development landscape.",
      category: "trends",
      author: "David Rodriguez",
      publishDate: "2024-01-08",
      readTime: 20,
      likes: 78,
      comments: 12,
      tags: ["future", "trends", "technology"],
      isPremium: true,
      isPublished: false,
      viewCount: 245
    },
    {
      id: 5,
      title: "JavaScript Performance Optimization",
      excerpt: "Techniques and best practices for writing high-performance JavaScript applications.",
      category: "performance",
      author: "Lisa Park",
      publishDate: "2024-01-05",
      readTime: 18,
      likes: 312,
      comments: 67,
      tags: ["javascript", "performance", "optimization"],
      isPremium: false,
      isPublished: true,
      viewCount: 1850
    }
  ];

  // Filter blogs based on current conditions
  const getFilteredBlogs = () => {
    return blogs.filter(blog => {
      // Search filter
      const matchesSearch = blog.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
                           blog.excerpt.toLowerCase().includes(searchTerm.toLowerCase()) ||
                           blog.tags.some(tag => tag.toLowerCase().includes(searchTerm.toLowerCase()));
      
      // Category filter
      const matchesCategory = selectedCategory === 'all' || blog.category === selectedCategory;
      
      // User role and premium access
      const hasAccess = !blog.isPremium || userRole === 'premium' || userRole === 'admin';
      
      // Published status for different roles
      const canView = blog.isPublished || userRole === 'admin' || (userRole === 'author' && blog.author === 'Current User');
      
      return matchesSearch && matchesCategory && hasAccess && canView;
    });
  };

  return (
    <div className="blog-details">
      <div className="component-header">
        <h2>📝 Blog Details Component</h2>
        <p>Advanced Conditional Rendering with User Roles & Filters</p>
      </div>

      {/* 1. Authentication State Management */}
      <div className="conditional-section">
        <h3>1. Complex Authentication Flow</h3>
        <div className="user-controls">
          <div>
            <button 
              className={isLoggedIn ? "logout-btn" : "login-btn"}
              onClick={() => setIsLoggedIn(!isLoggedIn)}
            >
              {isLoggedIn ? "Logout" : "Login"}
            </button>
            
            {/* Conditional role selector - only shown when logged in */}
            {isLoggedIn && (
              <div className="role-selector">
                <label>Role: </label>
                <select value={userRole} onChange={(e) => setUserRole(e.target.value)}>
                  <option value="reader">Reader</option>
                  <option value="author">Author</option>
                  <option value="premium">Premium User</option>
                  <option value="admin">Administrator</option>
                </select>
              </div>
            )}
          </div>
          
          {/* Status indicator with nested conditionals */}
          <div>
            {isLoggedIn ? (
              <span style={{ color: '#27ae60', fontWeight: 'bold' }}>
                ✅ Logged in as {userRole === 'admin' ? '👑 Administrator' : 
                                userRole === 'premium' ? '⭐ Premium User' :
                                userRole === 'author' ? '✍️ Author' : '👤 Reader'}
              </span>
            ) : (
              <span style={{ color: '#e74c3c', fontWeight: 'bold' }}>
                ❌ Not logged in - Limited access
              </span>
            )}
          </div>
        </div>
      </div>

      {/* 2. View Mode Switching */}
      <div className="conditional-section">
        <h3>2. Dynamic View Switching</h3>
        <div className="view-navigation">
          {['all', 'published', 'draft', 'premium'].map(view => (
            <button
              key={view}
              className={currentView === view ? 'active' : ''}
              onClick={() => setCurrentView(view)}
              disabled={!isLoggedIn && (view === 'draft' || view === 'premium')}
            >
              {view.charAt(0).toUpperCase() + view.slice(1)}
              {/* Conditional badge showing count */}
              {view === 'premium' && (
                <span style={{ marginLeft: '5px', fontSize: '0.8em' }}>
                  ({blogs.filter(b => b.isPremium).length})
                </span>
              )}
            </button>
          ))}
        </div>
      </div>

      {/* 3. Advanced Filtering System */}
      <div className="conditional-section">
        <h3>3. Multi-layered Filtering</h3>
        <div className="filters-section">
          <div className="search-bar">
            <input
              type="text"
              placeholder="Search blogs, tags, or content..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>
          
          <div className="category-filters">
            {['all', 'tutorial', 'advanced', 'design', 'trends', 'performance'].map(category => (
              <button
                key={category}
                className={selectedCategory === category ? 'active' : ''}
                onClick={() => setSelectedCategory(category)}
              >
                {category.charAt(0).toUpperCase() + category.slice(1)}
              </button>
            ))}
          </div>
        </div>
      </div>

      {/* 4. Permission-based Rendering */}
      <div className="conditional-section">
        <h3>4. Role-based Content Access</h3>
        <div className="example-box">
          {(() => {
            // Complex permission logic
            if (!isLoggedIn) {
              return (
                <div className="status-message guest">
                  <h4>🔒 Guest Access</h4>
                  <p>• View public blog previews</p>
                  <p>• Read first paragraph only</p>
                  <p>• Limited to 3 articles per day</p>
                </div>
              );
            }
            
            const permissions = {
              reader: [
                "Read all published articles",
                "Comment on articles",
                "Save articles to reading list"
              ],
              author: [
                "All reader permissions",
                "Create and edit own articles",
                "View draft articles",
                "Access writing analytics"
              ],
              premium: [
                "All reader permissions", 
                "Access premium content",
                "Ad-free reading experience",
                "Download articles for offline reading"
              ],
              admin: [
                "Full platform access",
                "Manage all articles",
                "User management",
                "Platform analytics",
                "Content moderation"
              ]
            };
            
            return (
              <div className="status-message member">
                <h4>✅ {userRole.toUpperCase()} Permissions</h4>
                <ul>
                  {permissions[userRole]?.map((permission, index) => (
                    <li key={index}>• {permission}</li>
                  ))}
                </ul>
              </div>
            );
          })()}
        </div>
      </div>

      {/* 5. Complex Blog Grid with Multiple Conditions */}
      <div className="conditional-section">
        <h3>5. Smart Content Display</h3>
        
        {/* Conditional empty state messages */}
        {!isLoggedIn && currentView === 'premium' ? (
          <div className="example-box">
            <p>🔐 Please log in to view premium content</p>
          </div>
        ) : getFilteredBlogs().length === 0 ? (
          <div className="example-box">
            <p>
              {searchTerm ? 
                `No blogs found matching "${searchTerm}"` : 
                selectedCategory !== 'all' ? 
                  `No blogs found in "${selectedCategory}" category` :
                  "No blogs available for your current access level"
              }
            </p>
          </div>
        ) : (
          <div className="blogs-grid">
            {getFilteredBlogs().map(blog => (
              <div key={blog.id} className="blog-card">
                <div className="blog-header">
                  <div className="blog-icon">
                    {blog.category === 'tutorial' ? '📚' :
                     blog.category === 'advanced' ? '🔬' :
                     blog.category === 'design' ? '🎨' :
                     blog.category === 'trends' ? '🚀' :
                     blog.category === 'performance' ? '⚡' : '📝'}
                  </div>
                  
                  <div className="blog-meta">
                    <span className="category">{blog.category}</span>
                    {blog.isPremium && <span className="premium-badge">Premium</span>}
                    {!blog.isPublished && userRole === 'admin' && (
                      <span className="draft-badge">Draft</span>
                    )}
                  </div>
                </div>
                
                <div className="blog-content">
                  <h3>{blog.title}</h3>
                  
                  {/* Conditional excerpt based on user access */}
                  <p className="blog-excerpt">
                    {!isLoggedIn ? 
                      blog.excerpt.substring(0, 50) + "..." :
                      blog.excerpt
                    }
                    {!isLoggedIn && (
                      <span style={{ color: '#e74c3c', fontWeight: 'bold' }}>
                        {" "}[Login to read more]
                      </span>
                    )}
                  </p>
                  
                  <div className="blog-info">
                    <span>👤 {blog.author}</span>
                    <span>📅 {blog.publishDate}</span>
                    <span>⏱️ {blog.readTime} min read</span>
                  </div>
                  
                  {/* Conditional stats based on user role */}
                  {(userRole === 'admin' || userRole === 'author') && (
                    <div className="blog-stats">
                      <span>👁️ {blog.viewCount} views</span>
                      <span>❤️ {blog.likes} likes</span>
                      <span>💬 {blog.comments} comments</span>
                    </div>
                  )}
                  
                  <div className="blog-tags">
                    {blog.tags.map(tag => (
                      <span key={tag} className="tag">#{tag}</span>
                    ))}
                  </div>
                  
                  <div className="blog-actions">
                    {/* Complex button logic */}
                    {!isLoggedIn ? (
                      <button className="read-btn" disabled>
                        🔒 Login to Read
                      </button>
                    ) : blog.isPremium && userRole === 'reader' ? (
                      <button className="read-btn" disabled>
                        ⭐ Premium Only
                      </button>
                    ) : !blog.isPublished && userRole !== 'admin' ? (
                      <button className="read-btn" disabled>
                        📝 Coming Soon
                      </button>
                    ) : (
                      <>
                        <button className="read-btn">
                          {userRole === 'premium' ? '📖 Read Premium' : '📖 Read Article'}
                        </button>
                        {isLoggedIn && (
                          <button className="comments-btn">
                            💬 {blog.comments}
                          </button>
                        )}
                      </>
                    )}
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>

      {/* 6. Dynamic Statistics Panel */}
      <div className="conditional-section">
        <h3>6. Dynamic Content Statistics</h3>
        <div className="example-box">
          {isLoggedIn && (
            <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))', gap: '15px' }}>
              <div style={{ textAlign: 'center', padding: '15px', background: '#ecf0f1', borderRadius: '8px' }}>
                <h4>📊 Available Content</h4>
                <p><strong>{getFilteredBlogs().length}</strong> articles</p>
              </div>
              
              {userRole === 'premium' && (
                <div style={{ textAlign: 'center', padding: '15px', background: '#f39c12', color: 'white', borderRadius: '8px' }}>
                  <h4>⭐ Premium Access</h4>
                  <p><strong>{blogs.filter(b => b.isPremium).length}</strong> exclusive articles</p>
                </div>
              )}
              
              {(userRole === 'admin' || userRole === 'author') && (
                <div style={{ textAlign: 'center', padding: '15px', background: '#e74c3c', color: 'white', borderRadius: '8px' }}>
                  <h4>📝 Draft Content</h4>
                  <p><strong>{blogs.filter(b => !b.isPublished).length}</strong> unpublished</p>
                </div>
              )}
              
              {userRole === 'admin' && (
                <div style={{ textAlign: 'center', padding: '15px', background: '#27ae60', color: 'white', borderRadius: '8px' }}>
                  <h4>👑 Total Platform</h4>
                  <p><strong>{blogs.length}</strong> total articles</p>
                </div>
              )}
            </div>
          )}
          
          {!isLoggedIn && (
            <div style={{ textAlign: 'center', padding: '20px', background: '#e74c3c', color: 'white', borderRadius: '8px' }}>
              <h4>🔐 Limited Access</h4>
              <p>Log in to see detailed statistics and access more content</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default BlogDetails;
