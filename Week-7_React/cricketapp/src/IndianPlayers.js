import React from 'react';

function IndianPlayers() {
    // Team players for destructuring
    const teamPlayers = [
        "Virat Kohli", "Rohit Sharma", "KL Rahul", "Hardik Pandya", 
        "Rishabh Pant", "Ravindra Jadeja", "Jasprit Bumrah", "Mohammed Shami",
        "Yuzvendra Chahal", "Bhuvneshwar Kumar", "Shikhar Dhawan"
    ];

    // Destructuring to get odd and even positioned players
    const [first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh] = teamPlayers;
    
    // Odd positioned players (1st, 3rd, 5th, etc.)
    const oddTeamPlayers = [first, third, fifth, seventh, ninth, eleventh];
    
    // Even positioned players (2nd, 4th, 6th, etc.)
    const evenTeamPlayers = [second, fourth, sixth, eighth, tenth];

    // T20 players array
    const T20players = ["Virat Kohli", "Rohit Sharma", "KL Rahul", "Hardik Pandya", "Rishabh Pant"];
    
    // Ranji Trophy players array
    const RanjiTrophyPlayers = ["Prithvi Shaw", "Devdutt Padikkal", "Sarfaraz Khan", "Ishan Kishan", "Washington Sundar"];
    
    // Merge the two arrays using ES6 spread operator
    const mergedPlayers = [...T20players, ...RanjiTrophyPlayers];

    return (
        <div style={{ padding: '20px', backgroundColor: '#fff8dc', margin: '10px', borderRadius: '8px' }}>
            <h2 style={{ color: '#2c3e50', textAlign: 'center' }}>🇮🇳 Indian Players</h2>
            
            <div style={{ marginBottom: '30px' }}>
                <h3 style={{ color: '#e67e22' }}>Odd Team Players (1st, 3rd, 5th positions...):</h3>
                <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px' }}>
                    {oddTeamPlayers.map((player, index) => (
                        <div key={index} style={{ 
                            backgroundColor: '#fff', 
                            padding: '10px 15px', 
                            borderRadius: '20px', 
                            border: '2px solid #f39c12',
                            fontWeight: 'bold',
                            color: '#d35400'
                        }}>
                            {player}
                        </div>
                    ))}
                </div>
            </div>

            <div style={{ marginBottom: '30px' }}>
                <h3 style={{ color: '#8e44ad' }}>Even Team Players (2nd, 4th, 6th positions...):</h3>
                <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px' }}>
                    {evenTeamPlayers.map((player, index) => (
                        <div key={index} style={{ 
                            backgroundColor: '#fff', 
                            padding: '10px 15px', 
                            borderRadius: '20px', 
                            border: '2px solid #9b59b6',
                            fontWeight: 'bold',
                            color: '#6c3483'
                        }}>
                            {player}
                        </div>
                    ))}
                </div>
            </div>

            <div>
                <h3 style={{ color: '#16a085' }}>Merged Players (T20 + Ranji Trophy):</h3>
                <div style={{ marginBottom: '15px' }}>
                    <h4 style={{ color: '#2980b9' }}>T20 Players:</h4>
                    <div style={{ display: 'flex', flexWrap: 'wrap', gap: '8px', marginBottom: '10px' }}>
                        {T20players.map((player, index) => (
                            <span key={index} style={{ 
                                backgroundColor: '#3498db', 
                                color: 'white',
                                padding: '5px 10px', 
                                borderRadius: '15px',
                                fontSize: '14px'
                            }}>
                                {player}
                            </span>
                        ))}
                    </div>
                    
                    <h4 style={{ color: '#27ae60' }}>Ranji Trophy Players:</h4>
                    <div style={{ display: 'flex', flexWrap: 'wrap', gap: '8px', marginBottom: '15px' }}>
                        {RanjiTrophyPlayers.map((player, index) => (
                            <span key={index} style={{ 
                                backgroundColor: '#2ecc71', 
                                color: 'white',
                                padding: '5px 10px', 
                                borderRadius: '15px',
                                fontSize: '14px'
                            }}>
                                {player}
                            </span>
                        ))}
                    </div>
                </div>
                
                <h4 style={{ color: '#c0392b' }}>All Merged Players:</h4>
                <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(150px, 1fr))', gap: '10px' }}>
                    {mergedPlayers.map((player, index) => (
                        <div key={index} style={{ 
                            backgroundColor: index < T20players.length ? '#ebf3fd' : '#eafaf1', 
                            padding: '10px', 
                            borderRadius: '5px', 
                            border: index < T20players.length ? '1px solid #3498db' : '1px solid #2ecc71',
                            textAlign: 'center',
                            fontWeight: 'bold'
                        }}>
                            {player}
                            <br />
                            <small style={{ color: '#7f8c8d' }}>
                                {index < T20players.length ? 'T20' : 'Ranji'}
                            </small>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default IndianPlayers;
