import React from 'react';
import './App.css';

function App() {
  // Create an object of office to display details like Name, Rent and Address
  const sampleOffice = {
    name: "Tech Hub Premium",
    rent: 75000,
    address: "123 Business District, Mumbai, Maharashtra",
    image: "https://images.unsplash.com/photo-1497366216548-37526070297c?w=400&h=300&fit=crop"
  };

  // Create a list of office objects to display more data
  const officeSpaces = [
    {
      id: 1,
      name: "Modern Co-Working Space",
      rent: 45000,
      address: "456 Innovation Street, Bangalore, Karnataka",
      image: "https://images.unsplash.com/photo-1497366811353-6870744d04b2?w=400&h=300&fit=crop",
      features: ["WiFi", "Conference Room", "Parking"]
    },
    {
      id: 2,
      name: "Executive Business Center",
      rent: 85000,
      address: "789 Corporate Avenue, Delhi, NCR",
      image: "https://images.unsplash.com/photo-1497366754035-f200968a6e72?w=400&h=300&fit=crop",
      features: ["Reception", "Security", "Cafeteria"]
    },
    {
      id: 3,
      name: "Creative Studio Space",
      rent: 35000,
      address: "321 Art District, Pune, Maharashtra",
      image: "https://images.unsplash.com/photo-1497215842964-222b430dc094?w=400&h=300&fit=crop",
      features: ["Natural Light", "Open Space", "Kitchen"]
    },
    {
      id: 4,
      name: "Premium Office Suite",
      rent: 120000,
      address: "654 Financial Center, Hyderabad, Telangana",
      image: "https://images.unsplash.com/photo-1497215728101-856f4ea42174?w=400&h=300&fit=crop",
      features: ["Private Office", "Meeting Rooms", "Gym"]
    },
    {
      id: 5,
      name: "Startup Hub",
      rent: 25000,
      address: "987 Tech Park, Chennai, Tamil Nadu",
      image: "https://images.unsplash.com/photo-1497366412874-3415097a27e7?w=400&h=300&fit=crop",
      features: ["24/7 Access", "Printer", "Lounge"]
    },
    {
      id: 6,
      name: "Corporate Headquarters",
      rent: 200000,
      address: "111 Business Plaza, Gurgaon, Haryana",
      image: "https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?w=400&h=300&fit=crop",
      features: ["Multiple Floors", "Auditorium", "VIP Parking"]
    }
  ];

  // Function to determine rent color based on amount
  const getRentColor = (rent) => {
    return rent < 60000 ? 'red' : 'green';
  };

  // Function to format rent with currency
  const formatRent = (rent) => {
    return `₹${rent.toLocaleString()}/month`;
  };

  return (
    <div className="App">
      {/* Create an element to display the heading of the page */}
      <header className="app-header">
        <h1 className="main-heading">🏢 Office Space Rental Portal</h1>
        <p className="subtitle">Find Your Perfect Workspace</p>
      </header>

      <main className="main-content">
        {/* Featured Office Section */}
        <section className="featured-office">
          <h2 className="section-heading">✨ Featured Office Space</h2>
          <div className="office-card featured">
            {/* Attribute to display the image of the office space */}
            <img 
              src={sampleOffice.image} 
              alt={sampleOffice.name}
              className="office-image"
            />
            <div className="office-details">
              <h3 className="office-name">{sampleOffice.name}</h3>
              <p className="office-address">📍 {sampleOffice.address}</p>
              <p className="office-rent" style={{ color: getRentColor(sampleOffice.rent) }}>
                💰 {formatRent(sampleOffice.rent)}
              </p>
            </div>
          </div>
        </section>

        {/* All Office Spaces Section */}
        <section className="office-listings">
          <h2 className="section-heading">🏢 Available Office Spaces</h2>
          <div className="office-grid">
            {/* Loop through the office space items to display more data */}
            {officeSpaces.map((office) => (
              <div key={office.id} className="office-card">
                {/* Attribute to display the image of each office space */}
                <img 
                  src={office.image} 
                  alt={office.name}
                  className="office-image"
                />
                <div className="office-details">
                  <h3 className="office-name">{office.name}</h3>
                  <p className="office-address">📍 {office.address}</p>
                  {/* Apply CSS to display rent color: Red if below 60000, Green if above */}
                  <p className="office-rent" style={{ color: getRentColor(office.rent) }}>
                    💰 {formatRent(office.rent)}
                  </p>
                  <div className="office-features">
                    <strong>Features:</strong>
                    <ul className="features-list">
                      {office.features.map((feature, index) => (
                        <li key={index} className="feature-item">{feature}</li>
                      ))}
                    </ul>
                  </div>
                  <button className="contact-btn">Contact Now</button>
                </div>
              </div>
            ))}
          </div>
        </section>

        {/* Statistics Section */}
        <section className="statistics">
          <h2 className="section-heading">📊 Rental Statistics</h2>
          <div className="stats-grid">
            <div className="stat-card">
              <h3>Total Offices</h3>
              <p className="stat-number">{officeSpaces.length + 1}</p>
            </div>
            <div className="stat-card">
              <h3>Budget-Friendly (Under ₹60,000)</h3>
              <p className="stat-number red">
                {officeSpaces.filter(office => office.rent < 60000).length}
              </p>
            </div>
            <div className="stat-card">
              <h3>Premium (Above ₹60,000)</h3>
              <p className="stat-number green">
                {officeSpaces.filter(office => office.rent >= 60000).length}
              </p>
            </div>
          </div>
        </section>
      </main>

      <footer className="app-footer">
        <p>© 2025 Office Space Rental Portal - Find Your Dream Workspace</p>
      </footer>
    </div>
  );
}

export default App;
