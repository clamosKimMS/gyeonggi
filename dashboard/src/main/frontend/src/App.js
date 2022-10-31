import './App.css';

import React from "react";
import SchoolGeneralStatus from "./page/SchoolGeneralStatus";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import MultiCulturalFamilyStudent from "./page/MultiCulturalFamilyStudent";


function App() {
    return (
        <Router>
            <Routes>

                <Route path={"/schoolGeneralStatus"} element={ <SchoolGeneralStatus/>} />
                <Route path={"/MultiCulturalFamilyStudent"} element={ <MultiCulturalFamilyStudent/> } />

                {/*Chart.js 및 퍼블리싱된 페이지 Test 용*/}
                {/*<Route path={"/totalSchool"} element={<SchoolTotal />} />*/}
                {/*<Route path={"/test"} element={<Test />} />*/}


            </Routes>
        </Router>
    );
}

export default App;
