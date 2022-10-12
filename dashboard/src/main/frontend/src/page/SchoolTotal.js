import React from "react";
import '../css/front.css'

export default function SchoolTotal() {

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
                                    <ul className="dep2" style={{display:"block"}}>
                                        <li className="active">
                                            <a href="#" className="btn-dep2 flip">학교 현황</a>
                                            <ul className="dep3" style={{display:"block"}}>
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
                                            <div className="in-map">
                                                <img src="img/common/map1.png"/>
                                            </div>
                                            <div className="check-box"> {/* checked 있었음음 */ }
                                                {/*<div><label><input type="checkbox" checked className="checkbox1 green"/>
                                                    <p><em></em><span>유치원</span></p></label></div>*/}
                                                <div><label><input type="checkbox" className="checkbox1 green"/>
                                                    <p><em></em><span>유치원</span></p></label></div>
                                                <div><label><input type="checkbox" className="checkbox1 yellow"/>
                                                    <p><em></em><span>초등학교</span></p></label></div>
                                                <div><label><input type="checkbox" className="checkbox1 red"/>
                                                    <p><em></em><span>중학교</span></p></label></div>
                                                <div><label><input type="checkbox" className="checkbox1 pink"/>
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
                                            <table style={{width:"700px"}}>
                                                <thead>
                                                <tr>
                                                    <th>구분</th>
                                                    <th>2022</th>
                                                    <th>2021</th>
                                                    <th>2020</th>
                                                    <th>2019</th>
                                                    <th>2018</th>
                                                    <th>2017</th>
                                                    <th>2016</th>
                                                    <th>2015</th>
                                                    <th>2014</th>
                                                    <th>2013</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>김포</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                </tr>
                                                <tr>
                                                    <td>오산</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                </tr>
                                                <tr>
                                                    <td>수원</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                </tr>
                                                <tr>
                                                    <td>파주</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                </tr>
                                                <tr>
                                                    <td>광주</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                </tr>
                                                <tr>
                                                    <td>부천</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                </tr>
                                                <tr>
                                                    <td>김포</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                </tr>
                                                <tr>
                                                    <td>오산</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                </tr>
                                                <tr>
                                                    <td>수원</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                </tr>
                                                <tr>
                                                    <td>파주</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                </tr>
                                                <tr>
                                                    <td>광주</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                </tr>
                                                <tr>
                                                    <td>부천</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                    <td>5732</td>
                                                    <td>456</td>
                                                </tr>
                                                </tbody>
                                            </table>
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