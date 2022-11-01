import './App.css';

import React from "react";
import SchoolGeneralStatus from "./page/SchoolGeneralStatus";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import MultiCulturalFamilyStudent from "./page/MultiCulturalFamilyStudent";
import React_SchoolGeneralStatus from "./page/React_SchoolGeneralStatus"
import React_TeacherStatus from "./page/React_TeacherStatus";

function App() {
    return (
        <Router>
            <Routes>

                <Route path={"/schoolGeneralStatus"} element={ <SchoolGeneralStatus/>} />
                <Route path={"/MultiCulturalFamilyStudent"} element={ <MultiCulturalFamilyStudent/> } />
                <Route path={"/ReactSchoolGeneralStatus"} element={ <React_SchoolGeneralStatus/> } />
                <Route path={"/ReactTeacherStatus"} element={ <React_TeacherStatus/>} />

                {/*Chart.js 및 퍼블리싱된 페이지 Test 용*/}
                {/*<Route path={"/totalSchool"} element={<SchoolTotal />} />*/}
                {/*<Route path={"/test"} element={<Test />} />*/}

            </Routes>
        </Router>
    );
}

export default App;
