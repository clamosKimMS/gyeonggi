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

export default function React_SchoolAfterCare() {

  // 행정구역 / 교육청 별 위도경도 배열
  const [areasPoly, setAreasPoly] = useState([]);

  // 표기할 학교의 타입을 지정함 ( e:초등 / m:중등 / h:고등 )
  const [type, setType] = useState("emh");

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
    console.log("react -> html 전송 : " + areaName)
    window.parent.postMessage(areaName, "*");

    setReactAreaName(areaName);
  }

  /* for Opacity  */
  const placeCount = (name) => {
    if (!dtoList || dtoList?.length < 1) {
      return;
    }
    let a = dtoList?.filter(data => data?.schl_stat_regional_area === name).map((data) => {
      return data.aftr_schl_care_oprtn_yn_count;
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
      axios.all([axios.get("/gyeonggi/getLocalStudentMaxList/" + type), axios.get('/gyeonggi/getLocalStudentMaxTotal/' + type)])
      .then(axios.spread((axios_dtoList, axios_totalCount) => {
        setDtoList(axios_dtoList.data);
        setTotalCount(axios_totalCount.data);

      }))

    } else if (areaType == "지역청") {
      axios.all([axios.get("/gyeonggi/getEduStudentMaxList/" + type), axios.get('/gyeonggi/getEduStudentMaxTotal/' + type)])
      .then(axios.spread((axios_dtoList, axios_totalCount) => {
        setDtoList(axios_dtoList.data);
        setTotalCount(axios_totalCount.data);
      }))
    }

  }, [type, areaType])

  return (
      <div>

      </div>
  )
}