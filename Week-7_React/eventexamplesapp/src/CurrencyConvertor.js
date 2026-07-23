import React, { useState } from 'react';

function CurrencyConvertor() {
    const [rupees, setRupees] = useState('');
    const [euros, setEuros] = useState('');
    const [conversionRate] = useState(0.011); // 1 INR = 0.011 EUR (approximate)

    // Handle the Submit event for conversion
    const handleSubmit = (e) => {
        e.preventDefault();
        if (rupees && !isNaN(rupees)) {
            const convertedAmount = (parseFloat(rupees) * conversionRate).toFixed(2);
            setEuros(convertedAmount);
        } else {
            alert('Please enter a valid amount in Rupees');
        }
    };

    // Handle input change
    const handleInputChange = (e) => {
        setRupees(e.target.value);
        setEuros(''); // Clear euros when input changes
    };

    // Reset function
    const handleReset = () => {
        setRupees('');
        setEuros('');
    };

    return (
        <div className="currency-converter">
            <h2>💱 Currency Converter (INR to EUR)</h2>
            <div className="converter-container">
                <form onSubmit={handleSubmit} className="converter-form">
                    <div className="input-group">
                        <label htmlFor="rupees">
                            <span className="currency-symbol">₹</span>
                            Enter Amount in Indian Rupees:
                        </label>
                        <input
                            type="number"
                            id="rupees"
                            value={rupees}
                            onChange={handleInputChange}
                            placeholder="Enter amount in INR"
                            className="currency-input"
                            step="0.01"
                            min="0"
                        />
                    </div>

                    <div className="button-group">
                        <button 
                            type="submit" 
                            className="convert-btn"
                            disabled={!rupees}
                        >
                            Convert to EUR
                        </button>
                        <button 
                            type="button" 
                            onClick={handleReset}
                            className="reset-btn"
                        >
                            Reset
                        </button>
                    </div>
                </form>

                {euros && (
                    <div className="result-container">
                        <h3>Conversion Result:</h3>
                        <div className="conversion-display">
                            <span className="from-currency">
                                ₹{parseFloat(rupees).toLocaleString('en-IN')}
                            </span>
                            <span className="arrow">→</span>
                            <span className="to-currency">
                                €{euros}
                            </span>
                        </div>
                        <p className="rate-info">
                            Exchange Rate: 1 INR = €{conversionRate}
                        </p>
                    </div>
                )}
            </div>
        </div>
    );
}

export default CurrencyConvertor;
