/*global kakao*/

import React, {useEffect, useState} from "react";
import {
  CustomOverlayMap,
  Map,
  MapMarker,
  MarkerClusterer
} from "react-kakao-maps-sdk";

export default function GIS_SocialWelfare() {

  // 행정구역 지역청 구분
  const [areaType, setAreaType] = useState("행정구역");

  // 체크박스 구분
  const [checkedMenu, setCheckedMenu] = useState("");

  // 구역 이름 배열
  const [areaName, setAreaName] = useState([]);

  // 선택한 구역 이름
  const [selectedAreaName, setSelectedAreaName] = useState("연천군");

  // 행정구역 지역청 구분 함수
  const toggleArea = () => {
    if (areaType == "지역청") {
      setAreaType("행정구역");
    } else {
      setAreaType("지역청");
    }
  }

  // 사회복지시설 동시체크 / 개별체크
  const [isAllChecked, setAllChecked] = useState(false);
  const [checkedState, setCheckedState] = useState(new Array(3).fill(false));

  // 지도위에 마커 그림
  const [info, setInfo] = useState()
  const [markers, setMarkers] = useState([])
  const [map, setMap] = useState()

  // 위도경도 배열 저장
  const [latLong, setLatLong] = useState([]);

  // DropDown 클릭한 지역 위도 경도 이동
  const [searchLatLong, setSearchLatLong] = useState({
    center : { lat: 37.16248629785744,  lng: 127.42286416497653 },
  });

  // 맵 확대 레벨 변환
  const [mapLevel, setMapLevel] = useState(11);

  // 체크메뉴 선택
  const checkedChange = () => {

    // console.log("checkedChange isAllChecked : " + isAllChecked)

    const query = 'input[name="checkedMenu"]:checked';
    const selectEls = document.querySelectorAll(query);
    let result = '';
    selectEls.forEach((el => {
      result += el.value + ',';
    }))
    setCheckedMenu(result);

    // console.log("result : " + result);

  }

  // 구역 선택
  const selectedButtonClick = (e) => {
    setSelectedAreaName(e.target.value);

    latLong.map((item) => {

      if (e.target.value == item.name) {
        setSearchLatLong({
          center : {
            lat: item.latitude,
            lng: item.longitude
          }
        })
      }

      setMapLevel(9);

    })

  };

  // 사회복지시설 클릭
  const handleAllCheck = () => {
    setAllChecked((prev) => !prev);
    let array = new Array(3).fill(!isAllChecked);
    setCheckedState(array);
  };

  // 사회복지시설_서브메뉴 클릭
  const handleMonoCheck = (position: number) => {
    const updatedCheckedState = checkedState.map((item, index) =>
        index === position ? !item : item
    );
    setCheckedState(updatedCheckedState);
    const checkedLength = updatedCheckedState.reduce((sum, currentState) => {
      if (currentState === true) {
        return sum + 1;
      }
      return sum;
    }, 0);
    setAllChecked(checkedLength === updatedCheckedState.length);
    // console.log("handleMonoCheck checkState : " + checkedState)
  };

  // 서브메뉴 클릭했을 때 result 값 달리하기 위함
  useEffect( () => {
    if (checkedState[0] == true || checkedState[1] == true || checkedState[2] == true || isAllChecked == false){
      const query = 'input[name="checkedMenu"]:checked';
      const selectEls = document.querySelectorAll(query);
      let result = '';
      selectEls.forEach((el => {
        result += el.value + ',';
      }))
      setCheckedMenu(result);
      // console.log("result2 : " + result);
    }
  }, [checkedState])

  // 체크된 메뉴 표기
  useEffect(() => {

    console.log(checkedMenu);

  }, [checkedMenu])

  // 지역명
  useEffect(() => {
    if (areaType == "행정구역") {
      setAreaName(
          ["연천군", "포천시", "가평군", "양평군", "여주시", "이천시", "용인시", "안성시", "평택시",
            "화성시", "안산시", "안양시", "군포시", "과천시", "의왕시", "수원시", "구리시", "성남시",
            "광주시", "하남시", "광명시", "부천시", "시흥시", "오산시", "동두천시", "파주시", "양주시",
            "김포시", "고양시", "의정부시", "남양주시"]
      )
    } else if (areaType == "지역청") {
      setAreaName(
          ["연천군", "포천시", "가평군", "양평군", "여주시", "이천시", "용인시", "안성시",
            "평택시", "안산시", "수원시", "성남시", "광명시", "부천시", "시흥시", "파주시", "김포시",
            "고양시", "의정부시", "화성시-오산시", "과천시-안양시", "군포시-의왕시", "남양주시-구리시",
            "광주시-하남시", "양주시-동두천시"]
      )
    }

    // 구역타입 바꿀 때 연천군으로 초기화
    setSelectedAreaName("연천군");

    if (areaType == "행정구역") {
      setLatLong([
        {
          name: "연천군",
          latitude : 38.096738,
          longitude : 127.074755
        },
        {
          name: "포천시",
          latitude : 37.894867,
          longitude : 127.2002404
        },
        {
          name: "가평군",
          latitude : 37.831508,
          longitude : 127.509541
        },
        {
          name: "양평군",
          latitude : 37.491791,
          longitude : 127.487597
        },
        {
          name: "여주시", // 부정확
          latitude : 37.334799,
          longitude : 127.647938
        },
        {
          name: "이천시",
          latitude : 37.272342,
          longitude : 127.435034
        },
        {
          name: "용인시",
          latitude : 37.2412522,
          longitude : 127.1774916
        },
        {
          name: "안성시",
          latitude : 37.0080546,
          longitude : 127.2797732
        },
        {
          name: "평택시",
          latitude : 36.9923537,
          longitude : 127.1126947
        },
        {
          name: "화성시",
          latitude : 37.199565,
          longitude : 126.831405
        },
        {
          name: "안산시",
          latitude : 37.3219123,
          longitude : 126.8308176
        },
        {
          name: "안양시",
          latitude : 37.3942905,
          longitude : 126.9567534
        },
        {
          name: "군포시",
          latitude : 37.361523,
          longitude : 126.935338
        },
        {
          name: "과천시",
          latitude : 37.4292013,
          longitude : 126.987675
        },
        {
          name: "의왕시",
          latitude : 37.3448869,
          longitude : 126.9682786
        },
        {
          name: "수원시",
          latitude : 37.263476,
          longitude : 127.028646
        },
        {
          name: "구리시",
          latitude : 37.594266,
          longitude : 127.129632
        },
        {
          name: "성남시",
          latitude : 37.4200267,
          longitude : 127.1267772
        },
        {
          name: "광주시",
          latitude : 37.4294306,
          longitude : 127.2550476
        },
        {
          name: "하남시",
          latitude : 37.5393014,
          longitude : 127.2148742
        },
        {
          name: "광명시",
          latitude : 37.4786176,
          longitude : 126.8646504
        },
        {
          name: "부천시",
          latitude : 37.5035917,
          longitude : 126.766
        },
        {
          name: "시흥시",
          latitude : 37.380177,
          longitude : 126.802934
        },
        {
          name: "오산시",
          latitude : 37.149887,
          longitude : 127.077462
        },
        {
          name: "동두천시",
          latitude : 37.903662,
          longitude : 127.060671
        },
        {
          name: "파주시",
          latitude : 37.760186,
          longitude : 126.779883
        },
        {
          name: "양주시",
          latitude : 37.785329,
          longitude : 127.045847
        },
        {
          name: "김포시",
          latitude : 37.61535,
          longitude : 126.715544
        },
        {
          name: "고양시",
          latitude : 37.6583981,
          longitude : 126.8319831
        },
        {
          name: "의정부시",
          latitude : 37.738083,
          longitude : 127.033753
        },
        {
          name: "남양주시",
          latitude : 37.635985,
          longitude : 127.216467
        }
      ])
    }
    else if (areaType == "지역청") {
      setLatLong([
        {
          name: "연천군",
          latitude : 38.096738,
          longitude : 127.074755
        },
        {
          name: "포천시",
          latitude : 37.894867,
          longitude : 127.2002404
        },
        {
          name: "가평군",
          latitude : 37.831508,
          longitude : 127.509541
        },
        {
          name: "양평군",
          latitude : 37.491791,
          longitude : 127.487597
        },
        {
          name: "여주시", // 부정확
          latitude : 37.334799,
          longitude : 127.647938
        },
        {
          name: "이천시",
          latitude : 37.272342,
          longitude : 127.435034
        },
        {
          name: "용인시",
          latitude : 37.2412522,
          longitude : 127.1774916
        },
        {
          name: "안성시",
          latitude : 37.0080546,
          longitude : 127.2797732
        },
        {
          name: "평택시",
          latitude : 36.9923537,
          longitude : 127.1126947
        },
        {
          name: "화성시-오산시",
          latitude : 37.199565,
          longitude : 126.831405
        },
        {
          name: "안산시",
          latitude : 37.3219123,
          longitude : 126.8308176
        },
        {
          name: "과천시-안양시",
          latitude : 37.4292013,
          longitude : 126.987675
        },
        {
          name: "군포시-의왕시",
          latitude : 37.361523,
          longitude : 126.935338
        },
        {
          name: "수원시",
          latitude : 37.263476,
          longitude : 127.028646
        },
        {
          name: "남양주시-구리시",
          latitude : 37.635985,
          longitude : 127.216467
        },
        {
          name: "성남시",
          latitude : 37.4200267,
          longitude : 127.1267772
        },
        {
          name: "광주시-하남시",
          latitude : 37.4294306,
          longitude : 127.2550476
        },
        {
          name: "광명시",
          latitude : 37.4786176,
          longitude : 126.8646504
        },
        {
          name: "부천시",
          latitude : 37.5035917,
          longitude : 126.766
        },
        {
          name: "시흥시",
          latitude : 37.380177,
          longitude : 126.802934
        },
        {
          name: "양주시-동두천시",
          latitude : 37.785329,
          longitude : 127.045847
        },
        {
          name: "파주시",
          latitude : 37.760186,
          longitude : 126.779883
        },
        {
          name: "김포시",
          latitude : 37.61535,
          longitude : 126.715544
        },
        {
          name: "고양시",
          latitude : 37.6583981,
          longitude : 126.8319831
        },
        {
          name: "의정부시",
          latitude : 37.738083,
          longitude : 127.033753
        },
      ])
    }


  }, [areaType])

  return (
      <div>

        {/*{console.log(searchLatLong)}*/}

        <div id="wrap">
          <div className="container">
            <aside>
              <h1>경기도 교육청</h1>
              <div className="choice-nav">
                <div className="tab" onClick={() => toggleArea()}>
                  <a href="#"
                     className={(areaType == "행정구역") ? "active" : ""}>행정구역</a>
                  <a href="#"
                     className={(areaType == "지역청") ? "active" : ""}>지역청</a>
                </div>
                <div className="dropdown">
                  <button
                      className="dropdown-toggle"
                      data-toggle="dropdown">
                    {selectedAreaName}
                  </button>
                  <div className="dropdown-menu">
                    <ul style={{overflow: "scroll", height: "300px"}}>

                      {areaName.map((area, index) => (
                          <li key={index}>
                            <button className="btn-choice"
                                    onClick={selectedButtonClick}
                                    value={area}> {area} </button>
                          </li>
                      ))}

                    </ul>
                  </div>
                </div>
              </div>
              <nav>
                <ul>
                  <li>
                    <a href="#" className="btn-dep1 flip">학교/교육기관</a>
                    <div className="dep2">
                      <div className="check-nav">
                        <ul>
                          <li><label><input type="checkbox"
                                            className="checkbox-nav"/><p>
                            <em></em><span>유치원</span></p></label></li>
                          <li><label><input type="checkbox"
                                            className="checkbox-nav"/><p>
                            <em></em><span>초등학교</span></p></label></li>
                          <li><label><input type="checkbox"
                                            className="checkbox-nav"/><p>
                            <em></em><span>중학교</span></p></label></li>
                          <li><label><input type="checkbox"
                                            className="checkbox-nav"/><p>
                            <em></em><span>고등학교</span></p></label></li>
                          <li><label><input type="checkbox"
                                            className="checkbox-nav"/><p>
                            <em></em><span>대학교</span></p></label></li>
                          <li><label><input type="checkbox"
                                            className="checkbox-nav"/><p>
                            <em></em><span>어린이집</span></p></label></li>
                          <li><label><input type="checkbox"
                                            className="checkbox-nav"/><p>
                            <em></em><span>학원교습소</span></p></label></li>
                          <li><label><input type="checkbox"
                                            className="checkbox-nav"/><p>
                            <em></em><span>평생교육기관</span></p></label></li>
                        </ul>
                      </div>
                    </div>
                  </li>

                  {/* 초기 펼쳐진거 접으려면 <li className="active" & style={{display : "block"}} 지우면 된다 */}
                  <li className="active">
                    <a href="#" className="btn-dep1 flip">지역복지</a>

                    <div className="dep2" style={{display: "block"}}>
                      <div className="check-nav" onChange={checkedChange}>
                        <ul>

                          <li><label><input type="checkbox"
                                            className="checkbox-nav"
                                            name="checkedMenu"
                                            value="childWelfare"/><p>
                            <em></em><span>아동복지시설</span></p></label></li>

                          <li><label><input type="checkbox"
                                            className="checkbox-nav"
                                            name="checkedMenu"
                                            value="healthyLife"/><p>
                            <em></em><span>건강생활지원센터</span></p></label></li>

                          <li className="active">
                            <label><input type="checkbox"
                                          className="checkbox-nav"
                                          checked={isAllChecked}
                                          onChange={() => handleAllCheck()}/><p>
                              <em></em><span>사회복지시설</span></p></label>
                            <a href="#" className="btn-dep3 flip"></a>
                            <ul className="dep3" style={{display: "block"}}>

                              <li><label><input type="checkbox"
                                                className="checkbox-nav"
                                                name="checkedMenu"
                                                value="youthWelfare"
                                                checked={checkedState[0]}
                                                onChange={() => handleMonoCheck(
                                                    0)}/><p>
                                <em></em><span>청소년복지시설</span></p></label></li>

                              <li><label><input type="checkbox"
                                                className="checkbox-nav"
                                                name="checkedMenu"
                                                value="mentalWelfare"
                                                checked={checkedState[1]}
                                                onChange={() => handleMonoCheck(
                                                    1)}/><p>
                                <em></em><span>정신보건</span></p></label></li>

                              <li><label><input type="checkbox"
                                                className="checkbox-nav"
                                                name="checkedMenu"
                                                value="singleParent"
                                                checked={checkedState[2]}
                                                onChange={() => handleMonoCheck(
                                                    2)}/><p>
                                <em></em><span>한부모가족</span></p></label></li>

                            </ul>
                          </li>
                          <li><label><input type="checkbox"
                                            className="checkbox-nav"
                                            name="checkedMenu"
                                            value="publicLibrary"/><p>
                            <em></em><span>공공도서관</span></p></label></li>

                          <li><label><input type="checkbox"
                                            className="checkbox-nav"
                                            name="checkedMenu"
                                            value="sportFacilities"/><p>
                            <em></em><span>체육시설</span></p></label></li>

                          <li><label><input type="checkbox"
                                            className="checkbox-nav"
                                            name="checkedMenu"
                                            value="socialEnterprise"/><p>
                            <em></em><span>사회적기업</span></p></label></li>
                        </ul>
                      </div>
                    </div>

                  </li>
                  <li>
                    <a href="#" className="btn-dep1 flip">체험/문화/관광</a>
                    <div className="dep2">
                      <div className="check-nav">
                        <ul>
                          <li><label><input type="checkbox"
                                            className="checkbox-nav"/><p>
                            <em></em><span>8대 체험학습</span></p></label></li>
                          <li><label><input type="checkbox"
                                            className="checkbox-nav"/><p>
                            <em></em><span>문화시설</span></p></label></li>
                          <li><label><input type="checkbox"
                                            className="checkbox-nav"/><p>
                            <em></em><span>문화축제</span></p></label></li>
                          <li>
                            <label><input type="checkbox"
                                          className="checkbox-nav"/><p>
                              <em></em><span>관광지정보</span></p></label>
                            <ul className="dep3">
                              <li><label><input type="checkbox"
                                                className="checkbox-nav"/><p>
                                <em></em><span>테마</span></p></label></li>
                              <li><label><input type="checkbox"
                                                className="checkbox-nav"/><p>
                                <em></em><span>체험</span></p></label></li>
                              <li><label><input type="checkbox"
                                                className="checkbox-nav"/><p>
                                <em></em><span>자연</span></p></label></li>
                              <li><label><input type="checkbox"
                                                className="checkbox-nav"/><p>
                                <em></em><span>역사</span></p></label></li>
                            </ul>
                          </li>
                          <li><label><input type="checkbox"
                                            className="checkbox-nav"/><p>
                            <em></em><span>향토문화유적</span></p></label></li>
                        </ul>
                      </div>
                    </div>
                  </li>
                  <li>
                    <a href="#" className="btn-dep1 flip">교통</a>
                    <div className="dep2">
                      <div className="check-nav">
                        <ul>
                          <li><label><input type="checkbox"
                                            className="checkbox-nav"/><p>
                            <em></em><span>지하철역정보</span></p></label></li>
                          <li><label><input type="checkbox"
                                            className="checkbox-nav"/><p>
                            <em></em><span>버스정보</span></p></label></li>
                        </ul>
                      </div>
                    </div>
                  </li>
                </ul>
              </nav>

            </aside>
            {/* 좌측 메뉴 */}

            <div className="content">
              <div className="header">
                <div className="left">
                  <div className="path">
                    <span className="home"></span>
                    <span>시설현황</span>
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
                <div className="wrap4">
                  <div className="left">
                    <div className="map-box2">

                      {/* 지도 영역 */}
                      <Map
                          /*center={{
                            // 지도의 중심좌표
                            // 위도경도 (3차원)
                            lat: 37.16248629785744,
                            lng: 127.42286416497653,

                            // 투영좌표계 (2차원)
                            // x:371488.59014800000,
                            // y:185884.42428200000,

                          }}*/
                          center={searchLatLong.center}
                          isPanto={false}
                          style={{
                            // 지도의 크기
                            width: "100%",
                            height: "100vh",

                          }}
                          className={"map-view"}
                          level={mapLevel} // 지도의 확대 레벨
                          // maxLevel={11}
                          onCreate={setMap}
                          id={"kakaoMap"}
                      >

                        {/*<MarkerClusterer
                            averageCenter={true}
                            minLevel={10}
                        >

                           맵 마커
                          {markers.map((marker) => (
                              <MapMarker
                                  key={`marker-${marker.content}-${marker.position.lat},${marker.position.lng}`}
                                  position={marker.position}
                                  onClick={() => setInfo(marker)}
                              >
                                클릭한 마커
                                {info && info.content === marker.content && (
                                <div style={{color: "#00abff"}}>{marker.content}</div>
                            )}

                              </MapMarker>
                          ))}
                        </MarkerClusterer>*/}

                        {markers.map((marker) => (
                            <CustomOverlayMap
                                position={{
                                  lat: marker.position.lat,
                                  lng: marker.position.lng
                                }}
                                yAnchor={1}
                            >
                              <div className="customoverlay">
                                <a
                                    // href="https://map.kakao.com/link/map/11394059"
                                    target="_blank"
                                    rel="noreferrer"
                                >
                                  <span
                                      className="title">{marker.content}</span>
                                </a>
                              </div>

                            </CustomOverlayMap>
                        ))}
                      </Map>
                    </div>
                  </div>
                  <div className="right">
                    <div className="white-box">
                      <div className="before-content">지도에서 보시고자하는 메뉴를 선택하면<br/>해당
                        목록이 표시됩니다.
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
