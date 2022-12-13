import React , { useState , useEffect } from 'react';
import axios from 'axios';
import Pagination from 'react-js-pagination'// npm i react-js-pagination


export default function BoardList(){

    // 1. 메모리
    const [ pageInfo , setPageInfo ] = useState({ bcno : 0 , page : 1 , key : "" , keyword : "" }) // 1. 검색 정보 객체 state
    const [ pageDto , setPageDto ] = useState( { list : [] } )                                 // 1. 게시물 리스트 state
    const [ bcategory , setBcategoryList ] = useState([])                                      // 1. 카테고리 리스트
    // -------------------------------- 1. 게시물 -------------------------------- //
    function getboardList(){ // 2. server : pageInfo 요청 =>  pageDto 응답 리스트를 가져오는 함수 [ 실행조건 : 1. 렌더링될때 2. 검색할때 3. 카테고리선택 4. 페이징선택 ---> 일반 함수화 ]
        axios
            .post("/board/boardlist" , pageInfo )
            .then( res => { setPageDto( res.data ); console.log( res.data ); } ) .catch( err => { console.log( err ); })
    }
    useEffect( getboardList , [ pageInfo ] ); // 3. 렌더링 될 때 그리고 *** pageInfo 변경될때 마다
                                // 의존성 배열 넣을때 SET 말고 변수명 넣어야함
    // ---------------------------------------------------------------------------------------- //
    // -------------------------------- 2. 카테고리 -------------------------------- //
    function getBcategory(){ // 카테고리 리스트 가져오기
        axios.get("/board/bcategorylist")
            .then( res => { setBcategoryList( res.data ); console.log( res.data ); } ) .catch( err => { console.log( err ); })
    }
    useEffect( getBcategory , [ ] ) // 3. [ 실행조건 : mount , unmount ]

    // 카테고리 버튼을 선택 했을 때
    const onCategory = ( bcno ) => {
        setPageInfo(
            {
                bcno : bcno ,
                page : 1 ,
                key : "" ,
                keyword : ""    }
        )
    }
    // ---------------------------------------------------------------------------------------- //
    // -------------------------------- 3. 페이징 -------------------------------- //
    const onPage = ( page ) => {
        setPageInfo(
            {   bcno : pageInfo.bcno ,           // 기존 카테고리
                page : page ,
                key : pageInfo.key ,             // 기존 검색 필드명
                keyword : pageInfo.keyword  }    // 기존 검색할 단어
        )
    }
    // ---------------------------------------------------------------------------------------- //
    // -------------------------------- 4. 검색 -------------------------------- //
    const onSearch = (  ) => {
        setPageInfo(
            {   bcno : pageInfo.bcno , // 카테고리 번호 [ 기존 그대로 : pageInfo.bcno ]
                page : 1 ,             // 검색시 첫 페이지부터 보여주기 [ 1 ]
                key : document.querySelector('.key').value ,    // 검색할 필드명
                keyword : document.querySelector('.keyword').value } // 검색할 단어
        )
    }
    // ---------------------------------------------------------------------------------------- //

    const loadView = ( bno ) => {
        window.location.href = '/board/view/'+bno
    }

    return(
        <div>
            <a href="/board/write"> 글 쓰기 [ 로그인 했을때만 표시 ] </a>
            <h1> 글 목록 페이지 </h1>

            <div className="bcategorybox">
                <button type="button" onClick={ ()=> onCategory( 0 ) }> 전체보기 </button>
                {
                    bcategory.map( (c)=> {
                        return (
                            <button type="button" onClick={ () => onCategory( c.bcno ) }> { c.bcname } </button>
                        )
                    })
                }

            </div>

            <table className="btable">
                {
                    pageDto.list.map( ( b ) => {
                        return (
                            <tr>
                                <td> { b.bno } </td>
                                <td onClick={ ()=> loadView( b.bno ) }> { b.btitle } </td>
                                <td> { b.memail } </td>
                                <td> { b.bcontent } </td>
                                <td> { b.bdate } </td>
                                <td> { b.bview } </td>
                            </tr>
                        )
                    })
                }
            </table>

            <Pagination
                activePage = { pageInfo.page } // 현재페이지
                itemsCountPerPage = { 3 } // 페이지당 표시할 게시물 수
                totalItemsCount = { pageDto.totalBoards } // 게시물 총 개수
                pageRangeDisplayed = { 5 } // 표시할 페이징 버튼 최대 개수
                onChange = { onPage } // 버튼 클릭할때마다 이벤트
            />

            <div className="searchBox">
                <select className="key">
                    <option value="btitle"> 제목 </option>
                    <option value="bcontent"> 내용 </option>

                </select>
                <input type="text" className="keyword"/>
                <button type="button" onClick={ onSearch }> 검색 </button>
            </div>

        </div>
    );
}