import React, {useEffect, useState} from "react";

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
    setSelectedAreaName(e.target.value)
  }

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

  }, [areaType])

  return (
      <div>

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
                    <ul style={{overflow:"scroll", height: "300px"}}>

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
                                          className="checkbox-nav" checked={isAllChecked} onChange={() => handleAllCheck()}/><p>
                              <em></em><span>사회복지시설</span></p></label>
                            <a href="#" className="btn-dep3 flip"></a>
                            <ul className="dep3" style={{display: "block"}}>

                              <li><label><input type="checkbox"
                                                className="checkbox-nav"
                                                name="checkedMenu"
                                                value="youthWelfare" checked={checkedState[0]} onChange={() => handleMonoCheck(0)}/><p>
                                <em></em><span>청소년복지시설</span></p></label></li>

                              <li><label><input type="checkbox"
                                                className="checkbox-nav"
                                                name="checkedMenu"
                                                value="mentalWelfare" checked={checkedState[1]} onChange={() => handleMonoCheck(1)}/><p>
                                <em></em><span>정신보건</span></p></label></li>

                              <li><label><input type="checkbox"
                                                className="checkbox-nav"
                                                name="checkedMenu"
                                                value="singleParent" checked={checkedState[2]} onChange={() => handleMonoCheck(2)}/><p>
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
                      {/*<div className="map-area"><img
                          src="img/common/tmp_map.png"
                          style="width:100%; height: 100%; object-fit: cover;"
                          alt="임시 이미지"></div>

                      <div className="marker " style="left: 300px; top:300px;">
                        <i className="i1"></i>
                        <div className="layer">
                          <h4>수원시 초등학교</h4>
                          <div className="txt">
                            경기도 수원시 수원구 7<br>
                            전화번호: 032-256-4568<br>
                            <a href="#"
                               target="_blank">www.lssdfvsdagadfgd.com</a>
                          </div>
                        </div>
                      </div>
                      <div className="marker" style="left: 500px; top:200px;">
                        <i className="i2"></i>
                        <div className="layer">
                          <h4>수원시 초등학교</h4>
                          <div className="txt">
                            경기도 수원시 수원구 7<br>
                            전화번호: 032-256-4568<br>
                            <a href="#"
                               target="_blank">www.lssdfvsdagadfgd.com</a>
                          </div>
                        </div>
                      </div>
                      <div className="marker" style="left: 500px; top:400px;">
                        <i className="i2"></i>
                        <div className="layer">
                          <h4>수원시 초등학교</h4>
                          <div className="txt">
                            경기도 수원시 수원구 7<br>
                            전화번호: 032-256-4568<br>
                            <a href="#"
                               target="_blank">www.lssdfvsdagadfgd.com</a>
                          </div>
                        </div>
                      </div>
                      <div className="marker" style="left: 350px; top:500px;">
                        <i className="i3"></i>
                        <div className="layer">
                          <h4>수원시 초등학교</h4>
                          <div className="txt">
                            경기도 수원시 수원구 7<br>
                            전화번호: 032-256-4568<br>
                            <a href="#"
                               target="_blank">www.lssdfvsdagadfgd.com</a>
                          </div>
                        </div>
                      </div>*/}
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
