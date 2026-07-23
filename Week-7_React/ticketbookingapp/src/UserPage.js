import React, { useState } from 'react';

function UserPage({ onLogout, username }) {
    const [bookings, setBookings] = useState([]);
    const [showBookingForm, setShowBookingForm] = useState(false);
    const [selectedFlight, setSelectedFlight] = useState(null);
    
    // Sample flight data for booking
    const flights = [
        {
            id: 1,
            airline: "Air India",
            from: "Mumbai",
            to: "Delhi",
            departure: "08:30 AM",
            arrival: "10:45 AM",
            duration: "2h 15m",
            price: 6500,
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
            price: 4200,
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
            price: 8900,
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
            price: 7300,
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
            price: 5800,
            seats: "Available"
        }
    ];

    const handleBookFlight = (flight) => {
        setSelectedFlight(flight);
        setShowBookingForm(true);
    };

    const handleConfirmBooking = (passengerDetails) => {
        const newBooking = {
            id: Date.now(),
            flight: selectedFlight,
            passenger: passengerDetails,
            bookingDate: new Date().toLocaleDateString(),
            status: "Confirmed"
        };
        
        setBookings([...bookings, newBooking]);
        setShowBookingForm(false);
        setSelectedFlight(null);
        alert(`✅ Booking confirmed for ${passengerDetails.name}!`);
    };

    return (
        <div className="user-page">
            <div className="user-header">
                <h1>✈️ Welcome, {username}!</h1>
                <p>Book your flights and manage your bookings</p>
                <button onClick={onLogout} className="logout-btn">
                    🚪 Logout
                </button>
            </div>

            {!showBookingForm ? (
                <>
                    {/* Available Flights for Booking */}
                    <div className="flights-container">
                        <h2>🎯 Book Your Flight</h2>
                        <div className="flights-grid">
                            {flights.map(flight => (
                                <div key={flight.id} className="flight-card user">
                                    <div className="flight-header">
                                        <h3 className="airline">{flight.airline}</h3>
                                        <span className="price">₹{flight.price.toLocaleString()}</span>
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
                                        <button 
                                            className="book-btn enabled"
                                            onClick={() => handleBookFlight(flight)}
                                        >
                                            🎫 Book Now
                                        </button>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>

                    {/* My Bookings Section */}
                    <div className="bookings-container">
                        <h2>📋 My Bookings</h2>
                        {bookings.length === 0 ? (
                            <div className="no-bookings">
                                <p>No bookings yet. Book your first flight above!</p>
                            </div>
                        ) : (
                            <div className="bookings-list">
                                {bookings.map(booking => (
                                    <div key={booking.id} className="booking-card">
                                        <div className="booking-header">
                                            <h4>Booking #{booking.id}</h4>
                                            <span className="status">{booking.status}</span>
                                        </div>
                                        <div className="booking-details">
                                            <p><strong>Flight:</strong> {booking.flight.airline}</p>
                                            <p><strong>Route:</strong> {booking.flight.from} → {booking.flight.to}</p>
                                            <p><strong>Passenger:</strong> {booking.passenger.name}</p>
                                            <p><strong>Date:</strong> {booking.bookingDate}</p>
                                            <p><strong>Amount:</strong> ₹{booking.flight.price.toLocaleString()}</p>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        )}
                    </div>
                </>
            ) : (
                <BookingForm 
                    flight={selectedFlight}
                    onConfirm={handleConfirmBooking}
                    onCancel={() => setShowBookingForm(false)}
                />
            )}
        </div>
    );
}

// Booking Form Component
function BookingForm({ flight, onConfirm, onCancel }) {
    const [passengerName, setPassengerName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        if (passengerName && email && phone) {
            onConfirm({
                name: passengerName,
                email: email,
                phone: phone
            });
        } else {
            alert('Please fill all fields');
        }
    };

    return (
        <div className="booking-form-container">
            <h2>🎫 Complete Your Booking</h2>
            
            <div className="selected-flight">
                <h3>Selected Flight:</h3>
                <div className="flight-summary">
                    <p><strong>{flight.airline}</strong></p>
                    <p>{flight.from} → {flight.to}</p>
                    <p>{flight.departure} - {flight.arrival}</p>
                    <p className="price">₹{flight.price.toLocaleString()}</p>
                </div>
            </div>

            <form onSubmit={handleSubmit} className="booking-form">
                <h3>Passenger Details:</h3>
                
                <div className="form-group">
                    <label>Full Name:</label>
                    <input
                        type="text"
                        value={passengerName}
                        onChange={(e) => setPassengerName(e.target.value)}
                        placeholder="Enter passenger name"
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        placeholder="Enter email address"
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Phone Number:</label>
                    <input
                        type="tel"
                        value={phone}
                        onChange={(e) => setPhone(e.target.value)}
                        placeholder="Enter phone number"
                        required
                    />
                </div>

                <div className="form-buttons">
                    <button type="submit" className="confirm-btn">
                        ✅ Confirm Booking
                    </button>
                    <button type="button" onClick={onCancel} className="cancel-btn">
                        ❌ Cancel
                    </button>
                </div>
            </form>
        </div>
    );
}

export default UserPage;
