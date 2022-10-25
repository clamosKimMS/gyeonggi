/*global kakao*/

import React, {useEffect, useRef, useState} from "react";
import axios from 'axios';
import {
    map_Ansan, map_Anseon, map_AnYan, map_Bucheon, map_Dongducheon, map_Gapyeon, map_Gimpo, map_Goyang,
    map_Gunpo, map_Guri, map_Gwacheon, map_Gwangju, map_Gwangmyeong, map_Hanam, map_Hwaseon, map_Icheon, map_Namyangju,
    map_Osan, map_Paju, map_Pocheon, map_Pyeongtaek, map_SeongNam, map_Siheung, map_Suwon, map_Uijeonbu,
    map_Uiwang, map_Yangju, map_Yangpyeon, map_Yeoju, map_Yeoncheon, map_Yongin
} from "./latitude";

import {CustomOverlayMap, Map, Polygon, MapInfoWindow} from "react-kakao-maps-sdk";

export default function ImageMapTest() {

    // 학교별
    const [schoolType, setSchoolType] = useState([]);

    // 지역별 위도경도 배열
    const [areas, setAreas] = useState([
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

    // 지역별 클릭 이벤트
    const HandleAreaClick = (areaName) => {
        console.log("핸들러 : " + areaName)
        searchPlace(areaName);
    }

    // 지도 지역을 클릭했을 때 어느 지역인지 Controller에서 가져옴
    const searchPlace = (areaName) => {
        axios.get('/gyeonggi/schoolTypeCount/' + areaName)
            .then(response => setSchoolType(response.data))
            .catch(error => console.log(error));
    }

    /* for Opacity  */
    const placeCount = (name) => {
        // console.log(dtoList);
        if (!dtoList || dtoList?.length < 1) {
            return;
        }
        let a = dtoList?.filter(data => data.name === name).map((data) => {
            return data.total_cnt;
        });
        return a[0] / totalCount;
        // return [datasets.name]
        // return 0.2;
    }

    const totalDtoList = () => {
        axios.get('/gyeonggi/getSchoolTotalCountList/' + type)
            .then(response => setDtoList(response.data) )
            .catch(error => console.log(error));
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
        setType(result);
    }





    useEffect(() => {
        axios.get('/gyeonggi/getMaxTotal')
            .then(response => setTotalCount(response.data))
            .catch(error => console.log(error));
    }, [totalCount]);

    useEffect(() => {
        totalDtoList();
    }, [type])

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
    }, [])


    return (

        <div>

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
                    {areas.map((area, index) => (<Polygon
                            key={`area-${area.name}`}
                            path={area.path}
                            strokeWeight={2}
                            strokeColor={"#ffffff"}
                            strokeOpacity={0.8}
                            fillColor={area.isMouseover ? "#f5bb2d" : "rgb(118,156,225)"}
                            // fillOpacity={area.isMouseOver ? 1 : 0.2}
                            fillOpacity={area.isMouseOver ? 1 : placeCount(area.name)}
                            onMousemove={(_map, mouseEvent) =>
                                setMousePosition({
                                    lat: mouseEvent.latLng.getLat(),
                                    lng: mouseEvent.latLng.getLng(),
                                })
                            }

                            onMouseover={() =>
                                setAreas((prev) => [
                                    ...prev.filter((_, i) => i !== index),
                                    {
                                        ...prev[index],
                                        isMouseover: true,
                                    },
                                ])
                            }
                            onMouseout={() =>
                                setAreas((prev) => [
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


                    {areas.findIndex((v) => v.isMouseover) !== -1 && (

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
                            >{areas.find((v) => v.isMouseover).name}</div>

                        </CustomOverlayMap>

                    )}

                </Map>

            </div>
            {/*{console.log(schoolType)}*/}
            {/*{console.log(totalCount)}*/}
            {/*{console.log(type)}*/}
            {/*{console.log(temp)}*/}

        </div>

    );
}