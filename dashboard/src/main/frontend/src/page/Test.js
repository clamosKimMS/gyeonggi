import React, {useEffect, useState} from "react";
import {Line, Bar} from "react-chartjs-2";
import {registerables, Chart} from "chart.js";
import axios from 'axios';

export default function Test() {

    const [schoolType, setSchoolType] = useState([]);

    useEffect( () => {
        axios.get('/gyeonggi/schoolTypeCount')
            .then(response => setSchoolType(response?.data))
            .catch(error => console.log(error));
    }, [])

    Chart.register(...registerables);
    const data = {
        labels: ['2013', '2014', '2015', '2016', '2017', '2018', '2019', '2020', '2021', '2022' ],
        datasets: [
            {
                type: 'line',
                label: '유치원',
                backgroundColor: 'rgb(116,233,186)',
                data: [8000, 14000, 12813, 13087, 8640, 7208, 5407, 4809, 8102, 7021],
                borderColor: 'rgb(116,233,186)',
                borderWidth: 2,
            },
            {
                type: 'line',
                label: '초등학교',
                backgroundColor: 'rgb(253,219,134)',
                data: [16000, 18000, 12813, 14087, 13640, 12208, 10407, 12809, 14102, 13021],
                borderColor: 'rgb(253,219,134)',
                borderWidth: 2,
            },
            {
                type: 'line',
                label: '중학교',
                backgroundColor: 'rgb(253,133,133)',
                data: [14580, 11800, 13813, 13887, 12640, 14208, 12407, 14809, 13102, 15021],
                borderColor: 'rgb(253,133,133)',
                borderWidth: 2,
            },
            {
                type: 'line',
                label: '고등학교',
                backgroundColor: 'rgb(204,138,253)',
                data: [3000, 2056, 4523, 2803, 3085, 1905, 2013, 3809, 2102, 3021],
                borderColor: 'rgb(204,138,253)',
                borderWidth: 2,
            },
            {
                type: 'bar',
                label: '학교수',
                backgroundColor: 'rgb(233,233,233)',
                data: [20137, 20336, 20540, 20729, 20835, 20938, 20967, 20809, 20772, 20696],
                borderWidth: 2,
            },
        ],
    };

    // useEffect(()=> {
    //     const division = (schoolType, n개씩) => {
    //         const length = 배열.length;
    //         const divide = Math.floor(length / n개씩) + (Math.floor( length % n개씩 ) > 0 ? 1 : 0);
    //         const newArray = [];
    //
    //         for (let i = 0; i <= divide; i++) {
    //             // 배열 0부터 n개씩 잘라 새 배열에 넣기
    //             newArray.push(배열.splice(0, n개씩));
    //         }
    //
    //         return newArray;
    //     }
    // },[schoolType])

    return (
        <div>

            <h1>this is TestPage</h1>
            {console.log("학교 수 : " + schoolType)}

            <Line type="line" data={data}/>

        </div>
    );

}