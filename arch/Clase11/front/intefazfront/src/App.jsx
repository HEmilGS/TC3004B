import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ProductoList from './Components/ProductoList';
import NavBar from './Components/NavBar';

function Home() {
  return <h2>Hola Mundo</h2>;
}

function App() {
  return (
    <Router>
      <NavBar />
      <div style={{ marginTop: 64 }}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<ProductoList />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
