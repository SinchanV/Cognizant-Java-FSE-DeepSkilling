import React from 'react';

function ListofPlayers() {
    // Array with 11 players and their scores using ES6 map feature
    const players = [
        { name: "Virat Kohli", score: 85 },
        { name: "Rohit Sharma", score: 92 },
        { name: "KL Rahul", score: 65 },
        { name: "Hardik Pandya", score: 78 },
        { name: "Rishabh Pant", score: 55 },
        { name: "Ravindra Jadeja", score: 45 },
        { name: "Jasprit Bumrah", score: 25 },
        { name: "Mohammed Shami", score: 30 },
        { name: "Yuzvendra Chahal", score: 15 },
        { name: "Bhuvneshwar Kumar", score: 40 },
        { name: "Shikhar Dhawan", score: 88 }
    ];

    // Filter players with scores below 70 using arrow functions
    const lowScorePlayers = players.filter(player => player.score < 70);

    return (
        <div style={{ padding: '20px', backgroundColor: '#f0f8ff', margin: '10px', borderRadius: '8px' }}>
            <h2 style={{ color: '#2c3e50', textAlign: 'center' }}>🏏 List of Players</h2>
            
            <div style={{ marginBottom: '30px' }}>
                <h3 style={{ color: '#27ae60' }}>All Players:</h3>
                <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))', gap: '10px' }}>
                    {players.map((player, index) => (
                        <div key={index} style={{ 
                            backgroundColor: '#fff', 
                            padding: '10px', 
                            borderRadius: '5px', 
                            border: '1px solid #ddd',
                            textAlign: 'center'
                        }}>
                            <strong>{player.name}</strong><br />
                            Score: {player.score}
                        </div>
                    ))}
                </div>
            </div>

            <div>
                <h3 style={{ color: '#e74c3c' }}>Players with Scores Below 70:</h3>
                <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))', gap: '10px' }}>
                    {lowScorePlayers.map((player, index) => (
                        <div key={index} style={{ 
                            backgroundColor: '#ffe6e6', 
                            padding: '10px', 
                            borderRadius: '5px', 
                            border: '1px solid #ff9999',
                            textAlign: 'center'
                        }}>
                            <strong>{player.name}</strong><br />
                            Score: {player.score}
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default ListofPlayers;
