    // jsx : 리액트 확장 표현식 파일 [ html + js = jsx ]
    // 컴포넌트 단위 애플리케이션 제작
    // SPA : 실글 페이지 애플리케이션 [ 페이지는 하나다 ]
        // 라우터 라이브러리 : 가상 URL :
    // 컴포넌트 만들기 준비물
        // 1. 첫글자는 대문자 [ 컴포넌트명 == 파일명 ]
        // 2. 리액트 [ 프레임워크가 아니다 ] 라이브러리 집합소 [ import 많다 ]
            // 1. import React from 'react';
            // 2. function 컴포넌트명() { return ( 렌더링할 코드 ); }
            // 3. export default 컴포넌트명;
                // 2,3 : export default function 컴포넌트명() { return ( 렌더링할 코드 ) ; }

// 1.
import React from 'react';
import Header from './Header';
import Footer from './Footer';      // i = install
import Signup from './member/Signup'
// 라우터 설치 [ 터미널 ] : npm i react-router-dom vs npm install react-router-dom
// import { 컴포넌트명 } from 'react-router-dom'; v6
import { BrowserRouter , Routes , Route , Link , Router } from 'react-router-dom';
    // BrowserRouter :
    //  vs HashRouter :
    // Routes : Route 목록 / 리스트
    // Route : 가상 URL 만들기 --> 해당 url 에 따른 컴포넌트 렌더링 [ SPA ]
    // Link : <---> a 태그 : 하이퍼링크
        // Link to="Route URL"
    // Router :
export default function Index( props ) {
    return (
        <div>
            <BrowserRouter>
                <Header/>
                    <Routes>
                        <Route path="/" />
                        <Route path="/member/signup" element={ <Signup/> } />
                    </Routes>
                <Footer/>
            </BrowserRouter>
        </div>
    );
}