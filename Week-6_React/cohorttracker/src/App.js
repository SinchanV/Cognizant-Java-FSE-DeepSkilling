import logo from './logo.svg';
import './App.css';
import { CohortsData} from './Cohort'
import CohortDetails from './CohortDetails'; 

function App() {
 
  return (
  <div style={{padding: '20px'}}>
    <h1 style={{textAlign: 'center', color: '#333', marginBottom: '30px'}}>Cohort Details Dashboard</h1>
    <div style={{display: 'flex', flexWrap: 'wrap', justifyContent: 'center'}}>
      {CohortsData.map((cohort, index) => <CohortDetails key={index} cohort={cohort}/>)}
    </div>
  </div>
  );
}

export default App;
