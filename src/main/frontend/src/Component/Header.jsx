// 1.
import React from 'react'; // 컴포넌트 호출
import Styles from '../css/header.css'; // src->css->header.css
import logo from '../img/logo.png';
import { BrowserRouter , Routes , Route , Link , Router } from 'react-router-dom';
import axios from 'axios' // react 비동기 통신 라이브러리 호출
// 2.
export default function Header( props ) {

    // 1. 서버와 통신 [ axios ]
    axios.get('http://localhost:8080/member/getloginMno').then(res => {alert('서버 통신') } );
    // axios.type( 'URL' ).then( res => { 응답 } )
    /*
        $.ajax({
            url: '/member/getloginMno',
            type : "get" ,
            success : function(re)

        })


    */
    return (
        <div>
            <div className="header">
                <div className="header_logo">
                    <Link to="/"> <img className="logo" src={logo} /> </Link>
                </div>
                <ul className="top_menu">
                    <li> <Link to="/member/signup">  회원가입 </Link> </li>
                    <li> <Link to="/member/Login">  로그인 </Link> </li>
                    <li> <Link to="/board/List"> 자유게시판 </Link> </li>
                    <li> <a href="/member/logout" > 로그아웃 </a> </li>
                </ul>
            </div>
        </div>
    );
}