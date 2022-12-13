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
import Home from './Home';
import Footer from './Footer';
import Signup from './member/Signup';
import Login from './member/Login';
import BoardList from './board/BoardList';
import BoardWrite from './board/BoardWrite';
import BookList from '../Book/BookList';
import BoardView from './board/BoardView';
//---------------------------------------------------------------
import Book from '../Book/chapter3/Book';
import Library from '../Book/chapter3/Library';
import Clock from '../Book/chapter4/Clock';
import CommentList from '../Book/chapter5/CommentList';
import NotificationList from '../Book/chapter6/NotificationList';
import Counter from '../Book/chapter7/Ex1_Hook';
import Accommodate from '../Book/chapter7/Accommodate';
import ConfirmButton from '../Book/chapter8/ConfirmButton';
import LandingPage from '../Book/chapter9/LandingPage';
import AttendanceBook from '../Book/chapter10/AttendanceBook'
import Ex1_Form from '../Book/chapter11/Ex1_Form';
import SignUp from '../Book/chapter11/SignUp';


// 라우터 설치 [ 터미널 ] : npm i react-router-dom vs npm install react-router-dom
// import { 컴포넌트명 } from 'react-router-dom'; v6
import { HashRouter , BrowserRouter , Routes , Route , Link , Router } from 'react-router-dom';
    // BrowserRouter : 가상 URL
    //  vs HashRouter :
    // Routes : Route 목록 / 리스트
    // Route : 가상 URL 만들기 --> 해당 url 에 따른 컴포넌트 렌더링 [ SPA ]
    // Link : <---> a 태그 : 하이퍼링크
        // Link to="Route URL"
    // Router :
export default function Index( props ) {
    return (
        <div className="webbox">
            <BrowserRouter>
                <Header/>
                    <Routes>
                        <Route path="/" element={ <Home/>} />
                        <Route path="/member/signup" element={ <Signup/> } />
                        <Route path="/member/login" element={ <Login/> } />
                        <Route path="/board/list" element={ <BoardList/> } />
                        <Route path="/board/write" element={ <BoardWrite/> } />
                        <Route path="/board/view/:bno" element={ <BoardView/> } />
                        <Route path="/book/list" element={ <BookList/> } />
                        <Route path="/c3/Library" element={ <Library/> } />
                        <Route path="/c4/clock" element={ <Clock/> } />
                        <Route path="/c5/commentlist" element={ <CommentList/> } />
                        <Route path="/c6/notificationlist" element={ <NotificationList/> } />
                        <Route path="/c7/accommodate" element={ <Accommodate/> } />
                        <Route path="/c8/confirmbutton" element={ <ConfirmButton/> } />
                        <Route path="/c9/landingpage" element={ <LandingPage/> } />
                        <Route path="/c10/attendancebook" element={ <AttendanceBook/> } />
                        <Route path="/c11/ex1form" element={ <Ex1_Form/> } />
                        <Route path="/c11/ex1signup" element={ <SignUp/> } />
                    </Routes>
                <Footer/>
            </BrowserRouter>
        </div>
    );
}