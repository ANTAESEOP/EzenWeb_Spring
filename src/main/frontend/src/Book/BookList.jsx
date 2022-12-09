import React from 'react';
import Header from '../Component/Header';
import Home from '../Component/Home';
import Footer from '../Component/Footer';
import Styles from '../css/header.css'; // src->css->header.css
import logo from '../img/logo.png'

import Book from './chapter3/Book';
import Clock from './chapter4/Clock';
import CommentList from './chapter5/CommentList';
import NotificationList from './chapter6/NotificationList';
import Counter from './chapter7/Ex1_Hook';
import Accommodate from './chapter7/Accommodate';
import ConfirmButton from './chapter8/ConfirmButton';
import LandingPage from './chapter9/LandingPage';
import Toolbar from './chapter9/Toolbar';

import Login from '../Component/member/Login';

import { HashRouter , BrowserRouter , Routes , Route , Link , Router } from 'react-router-dom';

export default function BookList( props ) {
    return (
        <div className="webbox">
                <li> <Link to="/c3/Library"> 챕3 </Link> </li>
                <li> <Link to="/c4/clock"> 챕4 </Link> </li>
                <li> <Link to="/c5/commentlist"> 챕5 </Link> </li>
                <li> <Link to="/c6/notificationlist"> 챕6 </Link> </li>
                <li> <Link to="/c7/accommodate"> 챕7 </Link> </li>
                <li> <Link to="/c8/confirmbutton"> 챕8 </Link> </li>
                <li> <Link to="/c9/landingpage"> 챕9 </Link> </li>
                <li> <Link to="/c10/attendancebook"> 챕10 </Link> </li>
                <li> <Link to="/c11/ex1form"> 챕11-1 </Link> </li>
                <li> <Link to="/c11/ex1signup"> 챕11-2 </Link> </li>
        </div>
    );
}