import './App.css';

import React from "react";
import SchoolGeneralStatus from "./page/SchoolGeneralStatus";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom"
import React_SchoolGeneralStatus from "./page/React_SchoolGeneralStatus"
import React_TeacherStatus from "./page/React_TeacherStatus";
import React_StudentStatus from "./page/React_StudentStatus";
import React_MultiCulturalYearStatus from "./page/React_MultiCulturalYearStatus";
import React_MultiDropoutStatus from "./page/React_MultiDropoutStatus";
import React_MultiCultureTime from "./page/React_MultiCultureTime";
import React_SchoolAfterCare from "./page/React_SchoolAfterCare";
import GIS_SocialWelfare from "./page/GIS_SocialWelfare";

function App() {
    return (
        <Router>
            <Routes>

                <Route path={"/schoolGeneralStatus"} element={ <SchoolGeneralStatus/>} />

                <Route path={"/ReactMultiCulturalYearStatus"} element={ <React_MultiCulturalYearStatus/>} />
                <Route path={"/ReactSchoolGeneralStatus"} element={ <React_SchoolGeneralStatus/> } />
                <Route path={"/ReactTeacherStatus"} element={ <React_TeacherStatus/>} />
                <Route path={"/ReactStudentStatus"} element={ <React_StudentStatus/>} />
                <Route path={"/ReactMultiDropoutStatus"} element={<React_MultiDropoutStatus/>} />
                <Route path={"/ReactMultiCultureTime"} element={<React_MultiCultureTime/>} />
                <Route path={"/ReactSchoolAfterCare"} element={<React_SchoolAfterCare/>} />

                <Route path={"/GisSocialWelfare"} element={<GIS_SocialWelfare/>} />

                {/*Chart.js 및 퍼블리싱된 페이지 Test 용*/}
                {/*<Route path={"/totalSchool"} element={<SchoolTotal />} />*/}
                {/*<Route path={"/test"} element={<Test />} />*/}

            </Routes>
        </Router>
    );
}

export default App;
