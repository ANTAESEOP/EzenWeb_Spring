import React from 'react';
export default function BoardList(  ){
    return(
        <div>
            <a href="/board/write"> 글 쓰기 [ 로그인 했을때만 표시 ] </a>
            <h1> 글 목록 페이지 </h1>
            <div className="bcategorybox"></div>
            <table class="btable"></table>
        </div>
    );
}