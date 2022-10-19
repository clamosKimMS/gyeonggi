import './App.css';

import React from "react";
import SchoolTotal from "./page/SchoolTotal";
import Test from "./page/Test";
import ImageMapTest from "./page/ImageMapTest";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"


function App() {
    return (
        <Router>
            <Routes>

                <Route path={"/totalSchool"} element={<SchoolTotal />} />

                <Route path={"/test"} element={<Test />} />

                <Route path={"/mapTest"} element={ <ImageMapTest/>} />

            </Routes>
        </Router>
    );
}

export default App;
