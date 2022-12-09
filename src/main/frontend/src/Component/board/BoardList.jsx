import React , { useState , useEffect } from 'react';
import axios from 'axios';


export default function BoardList(){

    // 1. 메모리
        // 1. 게시물 리스트 state
    const [ boardList , setBoardList ] = useState( [] ) // 서버로부터 가져온 글 리스트

    const getboard = () => {
        axios
            .get("/board/boardlist" , { params: { bcno : 0 , page : 1 , key : "" , keyword : "" } } )
            .then( res => { setBoardList( res.data ); console.log( res ); } )
            .catch( err => { console.log( err ); } )
    }
    useEffect( getboard , [] ); // 페이지 실행될떄 ( mount 될 떄 )

    return(
        <div>
            <a href="/board/write"> 글 쓰기 [ 로그인 했을때만 표시 ] </a>
            <h1> 글 목록 페이지 </h1>
            <div className="bcategorybox"></div>

            <table class="btable">

            </table>
        </div>
    );
}