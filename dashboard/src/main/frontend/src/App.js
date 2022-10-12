import './App.css';

import React from "react";
import SchoolTotal from "./page/SchoolTotal";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"


function App() {
    return (
        <Router>
            <Routes>

                <Route path={"/"} element={<SchoolTotal />} />

            </Routes>
        </Router>
    );
}

export default App;
