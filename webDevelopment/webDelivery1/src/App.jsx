import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import NaviList from './components/naviList'
import Home from './pages/Home'
import About from './pages/About'
import Dashboard from './pages/Dashboard'
import Default from './pages/Default'
import MiH2 from './pages/creditCardValidation'
import WordList from './pages/wordList'
import AlbumList from './pages/apiFetch'



function App() {
  const [count, setCount] = useState(0)

  return (
    <div className='bg-[#F4EDD3] min-h-screen'>
      <Router>
      <div>
        <NaviList/>
        <div className='flex justify-center  h-screen py-16'>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/WordList" element={<WordList />} />
          <Route path="/albumList" element={<AlbumList />} />
          <Route path="/MiH2" element={<MiH2 />} />
          <Route path="*" element={<Default />} />

        </Routes>
        </div>
      </div>
      </Router> 

      </div>
  )
}

export default App
