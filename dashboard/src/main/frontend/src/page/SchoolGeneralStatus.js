/*global kakao*/

import React, {useEffect, useState} from "react";
import axios from 'axios';
import {
    map_Ansan,
    map_Anseon,
    map_AnYan,
    map_Bucheon,
    map_Dongducheon,
    map_Gapyeon,
    map_Gimpo,
    map_Goyang,
    map_Gunpo,
    map_Guri,
    map_Gwacheon,
    map_Gwangju,
    map_Gwangmyeong,
    map_Hanam,
    map_Hwaseon,
    map_Icheon,
    map_Namyangju,
    map_Osan,
    map_Paju,
    map_Pocheon,
    map_Pyeongtaek,
    map_SeongNam,
    map_Siheung,
    map_Suwon,
    map_Uijeonbu,
    map_Uiwang,
    map_Yangju,
    map_Yangpyeon,
    map_Yeoju,
    map_Yeoncheon,
    map_Yongin
} from "./LatAndLong/AreaLocal";
import {
    eduMap_Ansan,
    eduMap_Anseon,
    eduMap_AnYan_Gwacheon,
    eduMap_Bucheon,
    eduMap_Dongducheon_Yangju,
    eduMap_Gapyeon,
    eduMap_Gimpo,
    eduMap_Goyang,
    eduMap_Gunpo_Uiwang,
    eduMap_Guri_Namyangju,
    eduMap_Gwangju_Hanam,
    eduMap_Gwangmyeong,
    eduMap_Hwaseon_Osan,
    eduMap_Icheon,
    eduMap_Paju,
    eduMap_Pocheon,
    eduMap_Pyeongtaek,
    eduMap_SeongNam,
    eduMap_Siheung,
    eduMap_Suwon,
    eduMap_Uijeonbu,
    eduMap_Yangpyeon,
    eduMap_Yeoju,
    eduMap_Yeoncheon,
    eduMap_Yongin
} from "./LatAndLong/AreaEdu";
import "../css/front.css"

import {CustomOverlayMap, Map, Polygon} from "react-kakao-maps-sdk";

export default function SchoolGeneralStatus() {

    // console.log("렌더링")

    // 학교별
    // const [schoolType, setSchoolType] = useState([]);

    // 행정구역 / 교육청 별 위도경도 배열
    const [areasPoly, setAreasPoly] = useState([]);

    // 표기할 학교의 타입을 지정함 ( k:유치원 / e:초등 / m:중등 / h:고등 )
    const [type, setType] = useState("kemh");

    // 행정구역별 전체 학교수를 가져옴 ( opacity 조절용 )
    const [dtoList, setDtoList] = useState();

    // 전체 학교수 max ( opacity 조절용 )
    const [totalCount, setTotalCount] = useState();

    // Map Hover 지역명 나타내기
    const [mousePosition, setMousePosition] = useState({
        lat: 0,
        lng: 0,
    })

    // 행정구역 지역청 구분
    const [areaType, setAreaType] = useState("행정구역");

    const toggleArea = () => {
        if (areaType == "지역청") {
            setAreaType("행정구역");
        } else {
            setAreaType("지역청");
        }
    }

    const [reactAreaName, setReactAreaName] = useState("");

    // 지역별 클릭 이벤트
    const HandleAreaClick = (areaName) => {
        console.log("지역명 : " + areaName)
        // searchPlace(areaName);
        setReactAreaName(areaName);
    }

    // 지도 지역을 클릭했을 때 어느 지역인지 Controller에서 가져옴
    // const searchPlace = (areaName) => {
    //     axios.get('/gyeonggi/schoolTypeCount/' + areaName)
    //         .then(response => setSchoolType(response?.data))
    //         .catch(error => console.log(error));
    // }

    /* for Opacity  */
    const placeCount = (name) => {
        if (!dtoList || dtoList?.length < 1) {
            return;
        }
        let a = dtoList?.filter(data => data?.name === name).map((data) => {
            return data.total_cnt;
        });
        return a[0] / totalCount;
    }
    /*----------------*/

    // 학교 타입지정
    const typeChange = () => {
        const query = 'input[name="schoolType"]:checked';
        const selectEls = document.querySelectorAll(query);
        let result = '';
        selectEls.forEach((el => {
            result += el.value + '';
        }))

        if (result == '') {

        }

        setType(result);
    }

    // dtoList 바뀔 때 보이는 맵도 다르게하기위함
    useEffect(() => {
        if (areaType == "행정구역") {
            setAreasPoly([
                {
                    name: "연천군",
                    isMouseOver: false,
                    path: map_Yeoncheon
                },
                {
                    name: "포천시",
                    isMouseOver: false,
                    path: map_Pocheon
                },
                {
                    name: "가평군",
                    isMouseOver: false,
                    path: map_Gapyeon
                },
                {
                    name: "양평군",
                    isMouseOver: false,
                    path: map_Yangpyeon
                },
                {
                    name: "여주시",
                    isMouseOver: false,
                    path: map_Yeoju
                },
                {
                    name: "이천시",
                    isMouseOver: false,
                    path: map_Icheon
                },
                {
                    name: "용인시",
                    isMouseOver: false,
                    path: map_Yongin
                },
                {
                    name: "안성시",
                    isMouseOver: false,
                    path: map_Anseon
                },
                {
                    name: "평택시",
                    isMouseOver: false,
                    path: map_Pyeongtaek
                },
                {
                    name: "화성시",
                    isMouseOver: false,
                    path: map_Hwaseon
                },
                {
                    name: "안산시",
                    isMouseOver: false,
                    path: map_Ansan
                },
                {
                    name: "안양시",
                    isMouseOver: false,
                    path: map_AnYan
                },
                {
                    name: "군포시",
                    isMouseOver: false,
                    path: map_Gunpo
                },
                {
                    name: "과천시",
                    isMouseOver: false,
                    path: map_Gwacheon
                },
                {
                    name: "의왕시",
                    isMouseOver: false,
                    path: map_Uiwang
                },
                {
                    name: "수원시",
                    isMouseOver: false,
                    path: map_Suwon
                },
                {
                    name: "구리시",
                    isMouseOver: false,
                    path: map_Guri
                },
                {
                    name: "성남시",
                    isMouseOver: false,
                    path: map_SeongNam
                },
                {
                    name: "광주시",
                    isMouseOver: false,
                    path: map_Gwangju
                },
                {
                    name: "하남시",
                    isMouseOver: false,
                    path: map_Hanam
                },
                {
                    name: "광명시",
                    isMouseOver: false,
                    path: map_Gwangmyeong
                },
                {
                    name: "부천시",
                    isMouseOver: false,
                    path: map_Bucheon
                },
                {
                    name: "시흥시",
                    isMouseOver: false,
                    path: map_Siheung
                },
                {
                    name: "오산시",
                    isMouseOver: false,
                    path: map_Osan
                },
                {
                    name: "동두천시",
                    isMouseOver: false,
                    path: map_Dongducheon
                },
                {
                    name: "파주시",
                    isMouseOver: false,
                    path: map_Paju
                },
                {
                    name: "양주시",
                    isMouseOver: false,
                    path: map_Yangju
                },
                {
                    name: "김포시",
                    isMouseOver: false,
                    path: map_Gimpo
                },
                {
                    name: "고양시",
                    isMouseOver: false,
                    path: map_Goyang
                },
                {
                    name: "의정부시",
                    isMouseOver: false,
                    path: map_Uijeonbu
                },
                {
                    name: "남양주시",
                    isMouseOver: false,
                    path: map_Namyangju
                }
            ])
        } else if (areaType == "지역청") {
            setAreasPoly([
                {
                    name: "연천군",
                    isMouseOver: false,
                    path: eduMap_Yeoncheon
                },
                {
                    name: "포천시",
                    isMouseOver: false,
                    path: eduMap_Pocheon
                },
                {
                    name: "가평군",
                    isMouseOver: false,
                    path: eduMap_Gapyeon
                },
                {
                    name: "양평군",
                    isMouseOver: false,
                    path: eduMap_Yangpyeon
                },
                {
                    name: "여주시",
                    isMouseOver: false,
                    path: eduMap_Yeoju
                },
                {
                    name: "이천시",
                    isMouseOver: false,
                    path: eduMap_Icheon
                },
                {
                    name: "용인시",
                    isMouseOver: false,
                    path: eduMap_Yongin
                },
                {
                    name: "안성시",
                    isMouseOver: false,
                    path: eduMap_Anseon
                },
                {
                    name: "평택시",
                    isMouseOver: false,
                    path: eduMap_Pyeongtaek
                },
                {
                    name: "화성시-오산시",
                    isMouseOver: false,
                    path: eduMap_Hwaseon_Osan
                },
                {
                    name: "안산시",
                    isMouseOver: false,
                    path: eduMap_Ansan
                },
                {
                    name: "과천시-안양시",
                    isMouseOver: false,
                    path: eduMap_AnYan_Gwacheon
                },
                {
                    name: "군포시-의왕시",
                    isMouseOver: false,
                    path: eduMap_Gunpo_Uiwang
                },
                {
                    name: "수원시",
                    isMouseOver: false,
                    path: eduMap_Suwon
                },
                {
                    name: "남양주시-구리시",
                    isMouseOver: false,
                    path: eduMap_Guri_Namyangju
                },
                {
                    name: "성남시",
                    isMouseOver: false,
                    path: eduMap_SeongNam
                },
                {
                    name: "광주시-하남시",
                    isMouseOver: false,
                    path: eduMap_Gwangju_Hanam
                },
                {
                    name: "광명시",
                    isMouseOver: false,
                    path: eduMap_Gwangmyeong
                },
                {
                    name: "부천시",
                    isMouseOver: false,
                    path: eduMap_Bucheon
                },
                {
                    name: "시흥시",
                    isMouseOver: false,
                    path: eduMap_Siheung
                },
                {
                    name: "양주시-동두천시",
                    isMouseOver: false,
                    path: eduMap_Dongducheon_Yangju
                },
                {
                    name: "파주시",
                    isMouseOver: false,
                    path: eduMap_Paju
                },
                {
                    name: "김포시",
                    isMouseOver: false,
                    path: eduMap_Gimpo
                },
                {
                    name: "고양시",
                    isMouseOver: false,
                    path: eduMap_Goyang
                },
                {
                    name: "의정부시",
                    isMouseOver: false,
                    path: eduMap_Uijeonbu
                },
            ])
        }
    }, [dtoList])

    useEffect(() => {

        if (areaType == "행정구역") {
            axios.all([axios.get("/gyeonggi/getLocalSchoolTotalCountList/" + type), axios.get('/gyeonggi/getLocalMaxTotal/' + type)])
                .then(axios.spread((axios_dtoList, axios_totalCount) => {
                    setDtoList(axios_dtoList.data);
                    setTotalCount(axios_totalCount.data);

                }))

        } else if (areaType == "지역청") {
            axios.all([axios.get("/gyeonggi/getEduSchoolTotalCountList/" + type), axios.get('/gyeonggi/getEduMaxTotal/' + type)])
                .then(axios.spread((axios_dtoList, axios_totalCount) => {
                    setDtoList(axios_dtoList.data);
                    setTotalCount(axios_totalCount.data);
                }))
        }

    }, [type, areaType])

    // GIS 그림
    useEffect(() => {
        const tileset = new kakao.maps.Tileset({
            width: 256,
            height: 256,
            getTile: () => {
                const dom = document.createElement('div');
                // GIS맵  배경이미지
                dom.style.background = "rgb(244,244,244)";
                return dom;
            },
        })
        kakao.maps.Tileset.add("TILE_NUMBER", tileset)

        // iframe
        const testFunction = (e) => {
            console.log("리스너 : ");
            console.log(e.data);
        }
        window.addEventListener("message", testFunction, false);

    }, [])

    return (

        <div>

            <div className="content">
                <div className="header">
                    <div className="left">
                        <h2><strong>{areaType}</strong></h2>
                        <div className="tabs" onClick={() => toggleArea()}>
                            <a href="#" className={(areaType == "행정구역") ? "active" : ""}>행정구역</a>
                            <a href="#" className={(areaType == "지역청") ? "active" : ""}>지역청</a>
                        </div>
                    </div>
                </div>
            </div>

            <div className="check-box" style={{zIndex: 1, paddingBottom: "30px"}} onChange={typeChange}>
                <div><label><input type="checkbox" defaultChecked={true} className="checkbox1 green" name="schoolType"
                                   value="k"/>
                    <p><em></em><span>유치원</span></p></label></div>
                <div><label><input type="checkbox" defaultChecked={true} className="checkbox1 yellow" name="schoolType"
                                   value="e"/>
                    <p><em></em><span>초등학교</span></p></label></div>
                <div><label><input type="checkbox" defaultChecked={true} className="checkbox1 red" name="schoolType"
                                   value="m"/>
                    <p><em></em><span>중학교</span></p></label></div>
                <div><label><input type="checkbox" defaultChecked={true} className="checkbox1 pink" name="schoolType"
                                   value="h"/>
                    <p><em></em><span>고등학교</span></p></label></div>

            </div>

            {/* 행정구역별 */}
            <div className="map-box1">

                <Map // 지도를 표시할 Container
                    center={{
                        lat: 37.56344698078499,
                        lng: 127.14015019063882,
                    }}
                    style={{
                        left: "5px",
                        width: "540px",
                        height: "495px",
                    }}
                    draggable={false}
                    zoomable={false}
                    disableDoubleClickZoom={true}
                    disableDoubleClick={true}

                    level={11.3} // 지도의 확대 레벨

                    onTileLoaded={map => map.addOverlayMapTypeId(kakao.maps.MapTypeId["TILE_NUMBER"])}

                >

                    {areasPoly.map((area, index) => (<Polygon
                            key={`area-${area.name}`}
                            path={area.path}
                            strokeWeight={2}
                            strokeColor={"#ffffff"}
                            strokeOpacity={0.8}
                            fillColor={area.isMouseover ? "#f5bb2d" : "rgb(118,156,225)"}
                            // fillOpacity={area.isMouseOver ? 1 : 0.2}
                            fillOpacity={area.isMouseOver ? 1 : placeCount(area?.name)}
                            onMousemove={(_map, mouseEvent) =>
                                setMousePosition({
                                    lat: mouseEvent.latLng.getLat(),
                                    lng: mouseEvent.latLng.getLng(),
                                })
                            }

                            onMouseover={() =>
                                setAreasPoly((prev) => [
                                    ...prev.filter((_, i) => i !== index),
                                    {
                                        ...prev[index],
                                        isMouseover: true,
                                    },
                                ])
                            }
                            onMouseout={() =>
                                setAreasPoly((prev) => [
                                    ...prev.filter((_, i) => i !== index),
                                    {
                                        ...prev[index],
                                        isMouseover: false,
                                    },
                                ])
                            }

                            onMousedown={() => HandleAreaClick(area.name)}
                            // setTextPlace(area.name);
                        />
                    ))}
                    {areasPoly.findIndex((v) => v.isMouseover) !== -1 && (
                        <CustomOverlayMap position={mousePosition}>
                            <div className="area"
                                 style={{
                                     position: "absolute",
                                     background: "#fff",
                                     border: "1px",
                                     borderColor: "#888",
                                     borderRadius: "3px",
                                     fontSize: "12px",
                                     top: "-5px",
                                     left: "15px",
                                     padding: "2px",
                                 }}
                            >{areasPoly.find((v) => v.isMouseover).name}</div>

                        </CustomOverlayMap>
                    )}
                </Map>

            </div>

        </div>

    );
}