import React, { Component } from 'react';
import Post from './Post';

class Posts extends Component {
  constructor(props) {
    super(props);
    // Initialize the component with a list of Post in state
    this.state = {
      posts: []
    };
  }

  // Method responsible for using Fetch API to get posts
  loadPosts() {
    // English blog post titles and descriptions
    const englishBlogPosts = [
      {
        id: 1,
        title: "Getting Started with React Development",
        body: "React is a powerful JavaScript library for building user interfaces. This comprehensive guide covers the fundamentals of React development, including components, state management, and modern best practices for creating dynamic web applications.",
        userId: 1
      },
      {
        id: 2,
        title: "Modern JavaScript ES6+ Features Every Developer Should Know",
        body: "Explore the latest JavaScript features including arrow functions, destructuring, async/await, and modules. These modern ES6+ features will help you write cleaner, more efficient code and stay up-to-date with current development standards.",
        userId: 1
      },
      {
        id: 3,
        title: "Building Responsive Web Applications with CSS Grid and Flexbox",
        body: "Learn how to create beautiful, responsive layouts using CSS Grid and Flexbox. This tutorial covers practical examples and techniques for building modern web applications that work perfectly on all devices and screen sizes.",
        userId: 2
      },
      {
        id: 4,
        title: "Introduction to Node.js and Backend Development",
        body: "Discover the power of server-side JavaScript with Node.js. This article covers setting up a Node.js environment, working with Express.js, handling databases, and building RESTful APIs for your web applications.",
        userId: 2
      },
      {
        id: 5,
        title: "Database Design Principles and Best Practices",
        body: "Understanding proper database design is crucial for any application. Learn about normalization, indexing, relationships, and performance optimization techniques that will help you build efficient and scalable database systems.",
        userId: 3
      },
      {
        id: 6,
        title: "Version Control with Git: A Complete Developer's Guide",
        body: "Master Git version control system with this comprehensive guide. Learn branching strategies, merge conflicts resolution, collaboration workflows, and advanced Git techniques that every professional developer should know.",
        userId: 3
      },
      {
        id: 7,
        title: "Cybersecurity Best Practices for Web Developers",
        body: "Security should be a top priority in web development. This article covers essential security practices including input validation, authentication, encryption, and common vulnerabilities to protect your applications from threats.",
        userId: 4
      },
      {
        id: 8,
        title: "Cloud Computing and AWS Services for Beginners",
        body: "Get started with cloud computing using Amazon Web Services. Learn about EC2, S3, RDS, and other essential AWS services. Understand how cloud platforms can scale your applications and reduce infrastructure costs.",
        userId: 4
      },
      {
        id: 9,
        title: "Mobile App Development with React Native",
        body: "Build cross-platform mobile applications using React Native. This guide covers setup, navigation, state management, and deployment strategies for creating native mobile apps using JavaScript and React principles.",
        userId: 5
      },
      {
        id: 10,
        title: "Machine Learning Fundamentals for Developers",
        body: "Introduction to machine learning concepts and algorithms. Learn about supervised and unsupervised learning, neural networks, and how to integrate ML models into your applications using popular frameworks and tools.",
        userId: 5
      }
    ];

    try {
      // Simulate API delay for realistic loading experience
      setTimeout(() => {
        const posts = englishBlogPosts.map(post => new Post(post.id, post.title, post.body, post.userId));
        this.setState({ posts: posts });
      }, 1000);
    } catch (error) {
      console.error('Error loading posts:', error);
      alert('Error loading blog posts: ' + error.message);
    }
  }

  // componentDidMount hook to make calls to loadPosts()
  componentDidMount() {
    this.loadPosts();
  }

  // componentDidCatch hook to handle errors and display alert messages
  componentDidCatch(error, errorInfo) {
    console.error('Error caught by componentDidCatch:', error, errorInfo);
    alert('An error occurred in the Posts component: ' + error.message);
  }

  // render method to display the title and body of posts
  render() {
    const { posts } = this.state;

    return (
      <div className="posts-container">
        <h1>Technology Blog - English Posts</h1>
        <p className="blog-description">Discover the latest insights in web development, programming, and technology through our comprehensive English language articles.</p>
        {posts.length === 0 ? (
          <div className="loading">
            <p>Loading English blog posts...</p>
          </div>
        ) : (
          <div className="posts-list">
            <p className="posts-count">Featured Articles: {posts.length}</p>
            {posts.map(post => (
              <article key={post.id} className="post-item">
                <h2 className="post-title">{post.title}</h2>
                <div className="post-meta">
                  <span>Article #{post.id}</span> | <span>By Author {post.userId}</span> | <span>Technology & Development</span>
                </div>
                <p className="post-content">{post.body}</p>
                <div className="post-footer">
                  <span className="read-time">📖 5 min read</span>
                  <span className="language-tag">🇺🇸 English</span>
                </div>
                <hr />
              </article>
            ))}
          </div>
        )}
      </div>
    );
  }
}

export default Posts;
