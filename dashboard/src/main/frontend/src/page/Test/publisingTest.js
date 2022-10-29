/*global kakao*/

import React from "react";
import '../css/front.css'
import {useEffect, useState} from "react";
import {
    map_Ansan, map_Anseon, map_AnYan, map_Bucheon, map_Dongducheon, map_Gapyeon, map_Gimpo, map_Goyang,
    map_Gunpo, map_Guri, map_Gwacheon, map_Gwangju, map_Gwangmyeong, map_Hanam, map_Hwaseon, map_Icheon, map_Namyangju,
    map_Osan, map_Paju, map_Pocheon, map_Pyeongtaek, map_SeongNam, map_Siheung, map_Suwon, map_Uijeonbu,
    map_Uiwang, map_Yangju, map_Yangpyeon, map_Yeoju, map_Yeoncheon, map_Yongin
} from "./LatAndLong/AreaLocal";
import {CustomOverlayMap, Map, Polygon, MapInfoWindow} from "react-kakao-maps-sdk";

export default function Test() {
    const [textPlace, setTextPlace] = useState('');

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

    // Map Hover 지역명 나타내기
    const [mousePosition, setMousePosition] = useState({
        lat: 0,
        lng: 0,
    })

    const handleAreaClick = (areaName) => {
        setTextPlace(areaName);
    }
    useEffect(() => {
        const tileset = new kakao.maps.Tileset({
            width: 256,
            height: 256,
            getTile: (x, y, z) => {
                const div = document.createElement('div');

                // GIS맵  배경이미지
                div.style.background = "rgb(244,244,244)";
                return div;
            }
        })
        kakao.maps.Tileset.add("TILE_NUMBER", tileset)
    }, [])

    return (
        <div>

            <div id="wrap">
                <div className="container">
                    <aside>
                        <h1>경기도 교육청</h1>
                        <nav>
                            <ul>
                                <li>
                                    <a href="#" className="btn-dep1">전체현황</a>
                                </li>
                                <li className="active">
                                    <a href="#" className="btn-dep1 flip">학교일반현황</a>
                                    <ul className="dep2" style={{display: "block"}}>
                                        <li className="active">
                                            <a href="#" className="btn-dep2 flip">학교 현황</a>
                                            <ul className="dep3" style={{display: "block"}}>
                                                <li className="active"><a href="#" className="btn-dep3">연도별</a></li>
                                                <li><a href="#" className="btn-dep3">지역별</a></li>
                                            </ul>
                                        </li>
                                        <li>
                                            <a href="#" className="btn-dep2 flip">학생 현황</a>
                                            <ul className="dep3">
                                                <li><a href="#" className="btn-dep3">연도별</a></li>
                                                <li><a href="#" className="btn-dep3">지역별</a></li>
                                            </ul>
                                        </li>
                                        <li>
                                            <a href="#" className="btn-dep2 flip">교원 현황</a>
                                            <ul className="dep3">
                                                <li><a href="#" className="btn-dep3">연도별</a></li>
                                                <li><a href="#" className="btn-dep3">지역별</a></li>
                                            </ul>
                                        </li>
                                        <li>
                                            <a href="#" className="btn-dep2 flip">급식운영</a>
                                            <ul className="dep3">
                                                <li><a href="#" className="btn-dep3">연도별</a></li>
                                                <li><a href="#" className="btn-dep3">지역별</a></li>
                                            </ul>
                                        </li>
                                        <li>
                                            <a href="#" className="btn-dep2 flip">방과 후 학교 운영</a>
                                            <ul className="dep3">
                                                <li><a href="#" className="btn-dep3">연도별</a></li>
                                                <li><a href="#" className="btn-dep3">지역별</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#" className="btn-dep1 flip">지역일반현황</a>
                                    <ul className="dep2">
                                        <li><a href="#" className="btn-dep2">지역내인구</a></li>
                                        <li><a href="#" className="btn-dep2">다문화인구</a></li>
                                        <li><a href="#" className="btn-dep2">기초수급자</a></li>
                                        <li><a href="#" className="btn-dep2">지역재정</a></li>
                                    </ul>
                                </li>
                                <li className="line">
                                    <a href="#" className="btn-dep1 ">시설현황</a>
                                </li>
                                <li>
                                    <a href="#" className="btn-dep1 flip">다문화가정 학생</a>
                                    <ul className="dep2">
                                        <li><a href="#" className="btn-dep2 ">학생현황(시계열)</a></li>
                                        <li><a href="#" className="btn-dep2">학생현황(연도별)</a></li>
                                        <li><a href="#" className="btn-dep2">학생수 증감현황</a></li>
                                        <li><a href="#" className="btn-dep2">학생 학업중단 현황(연도별)</a></li>
                                        <li><a href="#" className="btn-dep2">다문화 가족 지원센터 현황</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#" className="btn-dep1 flip">학교폭력대책</a>
                                    <ul className="dep2">
                                        <li><a href="#" className="btn-dep2">2뎁스메뉴</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                        <div className="menu">
                            <a href="#" className="btn-d1">설프분석</a>
                            <div className="box dropdown1">
                                <a href="#" className="btn-d1">이용안내</a>
                                <ul>
                                    <li><a href="#">2뎁스</a></li>
                                    <li><a href="#">2뎁스</a></li>
                                    <li><a href="#">2뎁스</a></li>
                                    <li><a href="#">2뎁스</a></li>
                                </ul>
                            </div>
                        </div>
                    </aside>
                    {/* 좌측 메뉴 */}

                    <div className="content">
                        <div className="header">
                            <div className="left">
                                <h2><strong>경기도 전체</strong></h2>
                                <div className="tabs">
                                    <a href="#" className="active">행정구역</a>
                                    <a href="#">지역청</a>
                                </div>

                            </div>
                            <div className="right">
                                <div className="welcome">
                                    <div className="user"><i></i><span>김회계</span></div>
                                    <a href="#">로그아웃</a>
                                </div>
                            </div>
                        </div>
                        <div className="body">
                            <div className="wrap2">
                                <div className="left">
                                    <div className="map-wrap1">
                                        <div className="title-header">
                                            <div className="btns">
                                                <a href="#" className="btn-down1"></a>
                                                <a href="#" className="btn-down2"></a>
                                            </div>
                                        </div>
                                        <div className="map-box1">
                                            {/*<img src="img/common/map1.png"/>*/}

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
                                                onCreate={map => map.addOverlayMapTypeId(kakao.maps.MapTypeId["TILE_NUMBER"])}
                                                onMouseMove={(_map, mouseEvent) =>
                                                    setMousePosition({
                                                        lat: mouseEvent.latLng.getLat(),
                                                        lng: mouseEvent.latLng.getLng(),
                                                    })
                                                }
                                            >

                                                {areas.map((area, index) => (
                                                    <Polygon
                                                        key={`area-${area.name}`}
                                                        path={area.path}
                                                        strokeWeight={2}
                                                        strokeColor={"#ffffff"}
                                                        strokeOpacity={0.8}
                                                        fillColor={area.isMouseover ? "#f5bb2d" : "rgb(118,156,225)"}
                                                        fillOpacity={area.isMouseOver ? 1 : 0.2}
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

                                                        onMousedown={() => handleAreaClick(area.name)}
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

                                                {console.log(textPlace)}


                                            </Map>

                                            <div className="check-box" style={{zIndex:1, paddingBottom:"30px"}}> {/* checked 있었음 */}
                                                {/*<div><label><input type="checkbox" checked className="checkbox1 green"/>
                                                    <p><em></em><span>유치원</span></p></label></div>*/}
                                                <div><label><input type="checkbox" defaultChecked={true} className="checkbox1 green"/>
                                                    <p><em></em><span>유치원</span></p></label></div>
                                                <div><label><input type="checkbox" defaultChecked={true} className="checkbox1 yellow"/>
                                                    <p><em></em><span>초등학교</span></p></label></div>
                                                <div><label><input type="checkbox" defaultChecked={true} className="checkbox1 red"/>
                                                    <p><em></em><span>중학교</span></p></label></div>
                                                <div><label><input type="checkbox" defaultChecked={true} className="checkbox1 pink"/>
                                                    <p><em></em><span>고등학교</span></p></label></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="white-box box2">
                                        <div className="title-header">
                                            <h3>
                                                <strong>학교급별 학교수</strong>
                                                <small>출처: 경기데이터드림</small>
                                            </h3>
                                            <div className="btns">
                                                <a href="#" className="btn-down1"></a>
                                                <a href="#" className="btn-down2"></a>
                                            </div>
                                        </div>
                                        <div className="table1 type1">
                                            <iframe title="viz 테이블 데이터" style={{width:'100%',height:'100vh'}} src={`http://hsviz.clamos.io/sheet/6346360f3f7bf50000abb443?apikey=084ecb00-cac0-433b-bd2f-d79fb894e93a&table[지역명]=${textPlace}`}></iframe>
                                        </div>
                                    </div>

                                </div>


                                <div className="right2">
                                    <div className="box5">
                                        <div className="white-box">
                                            <div className="title-header ">
                                                <h3>
                                                    <strong>전체 학교수</strong>
                                                    <small>출처: 경기데이터드림</small>
                                                </h3>
                                                <div className="btns">
                                                    <a href="#" className="btn-down1"></a>
                                                    <a href="#" className="btn-down2"></a>
                                                </div>
                                            </div>
                                            <div className="graph-box pdb0">
                                                <div className="graph"><img src="img/common/graph7.png"/></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="box6">
                                        <div className="white-box">
                                            <div className="title-header ">
                                                <h3>
                                                    <strong>학교급별 학급수</strong>
                                                    <small>출처: 경기데이터드림</small>
                                                </h3>
                                                <div className="btns">
                                                    <a href="#" className="btn-down1"></a>
                                                    <a href="#" className="btn-down2"></a>
                                                </div>
                                            </div>
                                            <div className="graph-box pdb0">
                                                <div className="graph"><img src="img/common/graph8.png"/></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="box7">
                                        <div className="white-box">
                                            <div className="title-header type2">
                                                <h3>
                                                    <strong>학교급별 특수학급수 &amp; 특수학급비율</strong>
                                                    <small>출처: 경기데이터드림</small>
                                                </h3>
                                                <div className="btns">
                                                    <a href="#" className="btn-down1"></a>
                                                    <a href="#" className="btn-down2"></a>
                                                </div>
                                            </div>
                                            <div className="graph-box pdb0">
                                                <div className="graph"><img src="img/common/graph9.png"/></div>
                                            </div>
                                        </div>
                                    </div>


                                </div>


                                <div className="right3">
                                    <div className="box8">
                                        <div className="white-box">
                                            <div className="title-header ">
                                                <h3>
                                                    <strong>학급당 학생수</strong>
                                                    <small>출처: 경기데이터드림</small>
                                                </h3>
                                                <div className="btns">
                                                    <a href="#" className="btn-down1"></a>
                                                    <a href="#" className="btn-down2"></a>
                                                </div>
                                            </div>
                                            <div className="graph-box pdb0">
                                                <div className="graph"><img src="img/common/graph10.png"/></div>
                                                {/*<div className="layer1" style={{left:"100px", top:"100px"}}>
                                                    <div className="tit">2021</div>
                                                    <div className="info">
                                                        <dl>
                                                            <dt>유치원</dt>
                                                            <dd>562</dd>
                                                        </dl>
                                                        <dl>
                                                            <dt>초등학교</dt>
                                                            <dd>324</dd>
                                                        </dl>
                                                        <dl>
                                                            <dt>중학교</dt>
                                                            <dd>126</dd>
                                                        </dl>
                                                        <dl>
                                                            <dt>고등학교</dt>
                                                            <dd>135</dd>
                                                        </dl>
                                                    </div>
                                                </div>*/}

                                            </div>
                                        </div>
                                    </div>
                                    <div className="box9">
                                        <div className="white-box">
                                            <div className="title-header ">
                                                <h3>
                                                    <strong>폐교 현황</strong>
                                                    <small>출처: 경기데이터드림</small>
                                                </h3>
                                                <div className="btns">
                                                    <a href="#" className="btn-down1"></a>
                                                    <a href="#" className="btn-down2"></a>
                                                </div>
                                            </div>
                                            <div className="graph-box pdb0">
                                                <div className="graph"><img src="img/common/graph11.png"/></div>
                                            </div>
                                        </div>
                                    </div>


                                </div>


                            </div>
                        </div>

                    </div>

                    {/* 우측 영역 */}

                </div>
            </div>

        </div>
    );
}