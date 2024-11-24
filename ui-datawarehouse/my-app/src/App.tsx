import React from 'react';
import './App.css';
import GoldPriceChart from './GoldPriceChart';
import 'bootstrap/dist/css/bootstrap.min.css';
const App: React.FC = () => {
  return (
      <div className="App">
        <GoldPriceChart />
      </div>
  );
};

export default App;
