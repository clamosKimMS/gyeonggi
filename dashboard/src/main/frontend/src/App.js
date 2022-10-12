import './App.css';

import React from "react";
import SchoolTotal from "./page/SchoolTotal";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"


function App() {
    return (
        <Router>
            <Routes>

                <Route path={"/totalSchool"} element={<SchoolTotal />} />

            </Routes>
        </Router>
    );
}

export default App;
