import './App.css';

import React from "react";
import SchoolTotal from "./page/SchoolTotal";
import Test from "./page/Test";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"


function App() {
    return (
        <Router>
            <Routes>

                <Route path={"/totalSchool"} element={<SchoolTotal />} />

                <Route path={"/test"} element={<Test />} />

            </Routes>
        </Router>
    );
}

export default App;
