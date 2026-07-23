import React from 'react';

function GuestPage({ onLogin }) {
    // Sample flight data for browsing
    const flights = [
        {
            id: 1,
            airline: "Air India",
            from: "Mumbai",
            to: "Delhi",
            departure: "08:30 AM",
            arrival: "10:45 AM",
            duration: "2h 15m",
            price: "₹6,500",
            seats: "Available"
        },
        {
            id: 2,
            airline: "IndiGo",
            from: "Bangalore",
            to: "Chennai",
            departure: "02:15 PM",
            arrival: "03:30 PM",
            duration: "1h 15m",
            price: "₹4,200",
            seats: "Available"
        },
        {
            id: 3,
            airline: "SpiceJet",
            from: "Delhi",
            to: "Goa",
            departure: "11:00 AM",
            arrival: "01:30 PM",
            duration: "2h 30m",
            price: "₹8,900",
            seats: "Available"
        },
        {
            id: 4,
            airline: "Vistara",
            from: "Hyderabad",
            to: "Mumbai",
            departure: "06:45 PM",
            arrival: "08:15 PM",
            duration: "1h 30m",
            price: "₹7,300",
            seats: "Available"
        },
        {
            id: 5,
            airline: "GoAir",
            from: "Kolkata",
            to: "Pune",
            departure: "09:20 AM",
            arrival: "12:10 PM",
            duration: "2h 50m",
            price: "₹5,800",
            seats: "Available"
        }
    ];

    return (
        <div className="guest-page">
            <div className="page-header">
                <h1>✈️ Flight Search - Browse Flights</h1>
                <p className="guest-message">
                    Welcome Guest! Browse available flights below. 
                    <strong> Please login to book tickets.</strong>
                </p>
                <button onClick={onLogin} className="login-btn">
                    🔑 Login to Book Tickets
                </button>
            </div>

            <div className="flights-container">
                <h2>Available Flights</h2>
                <div className="flights-grid">
                    {flights.map(flight => (
                        <div key={flight.id} className="flight-card guest">
                            <div className="flight-header">
                                <h3 className="airline">{flight.airline}</h3>
                                <span className="price">{flight.price}</span>
                            </div>
                            
                            <div className="flight-route">
                                <div className="route-info">
                                    <span className="city">{flight.from}</span>
                                    <span className="time">{flight.departure}</span>
                                </div>
                                <div className="route-arrow">
                                    <span className="duration">{flight.duration}</span>
                                    <div className="arrow">→</div>
                                </div>
                                <div className="route-info">
                                    <span className="city">{flight.to}</span>
                                    <span className="time">{flight.arrival}</span>
                                </div>
                            </div>
                            
                            <div className="flight-footer">
                                <span className="seats">✅ {flight.seats}</span>
                                <button className="book-btn disabled" disabled>
                                    🔒 Login Required
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            </div>

            <div className="guest-features">
                <h3>🌟 Available Features for Guests:</h3>
                <ul>
                    <li>✅ Browse all available flights</li>
                    <li>✅ View flight details and prices</li>
                    <li>✅ Check flight schedules</li>
                    <li>❌ Book tickets (Login required)</li>
                    <li>❌ Manage bookings (Login required)</li>
                </ul>
            </div>
        </div>
    );
}

export default GuestPage;
