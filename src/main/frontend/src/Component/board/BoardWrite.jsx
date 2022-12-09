import React , { useState , useEffect } from 'react';
import axios from 'axios';

let bcno = 0; // 선택한 카테고리 번호 [ 전역변수 ]

export default function BoardWrite( props ){

    const [ categoryList , setCategoryList ] = useState( [] ) // 서버로부터 가져온 카테고리 리스트
    const [ category , setCategory ] = useState(""); // 입력받은 카테고리 명
    /*const [ board , SetBoard ] = useState(""); // 입력받은 글*/

    // 1. 모든 카테고리 가져오기 함수 [ 실해조건 : 페이지가 렌더링 되었을 때 ]
    const getbcategory = () => {
        axios
            .get("/board/bcategorylist")
            .then( res => { setCategoryList( res.data ); console.log( res )  } )
            .catch( err => { console.log( err); } )
    }
    useEffect( getbcategory , [] ); // 페이지가 mount , unmount

    // 2. 입력된 카테고리 등록 함수 [ 카테고리 등록 버튼 눌렀을 때 ]
    const setbcategory = () => {
        if( category == '') { alert( "Please input a category" ); return }
            axios
                .post("/board/setbcategory" , { bcname : category } )
                .then( res => {
                    if( res.data == true ) { alert(' 카테고리 등록 성공' ); }
                    else{ alert( '카테고리 등록 실패' ); }
                })
                .catch( err => { alert( err ) } )
    }

    // 3. 입력받은 게시물 등록 함수 [ 실행조건 : 글쓰기 등록 버튼 눌렀을때 ]
    const setboard = () => {
        if( bcno == 0 ){ alert('카테고리를 선택해주세요'); return; }

        let boardform = document.querySelector('.boardform')
        let formdata = new FormData( boardform );
        formdata.set( "bcno" , bcno );       // 폼 데이터의 카테고리 번호 추가

        axios                                  // headers : { "Content-Type" : 'multipart/form-data' } = 파일 전송을 위해 사용
            .post("/board/setboard" , formdata , { headers : { "Content-Type" : 'multipart/form-data' } } )
            .then( res => {
                    console.log( res.data )
                    if( res.data == true ){ alert("게시물 작성 성공"); }
                    else{ alert("게시물 작성 실패") }
                })
            .catch( err => { console.log( err ) } )
    }
    return(
        <div>
           <h1> 글 쓰기 페이지</h1>
           <input type="text" value={ category } onChange={ ( e )=> { setCategory( e.target.value ) } } />
                <button type="button" onClick={ setbcategory }> 카테고리 추가 </button>

                <div className="bcategorybox">
                    {
                        categoryList.map( ( c ) => {
                            return (
                                <button
                                    key = { c.bcno }
                                    type="button"
                                    onClick={ () => { bcno = c.bcno; alert( bcno ) } } >
                                    { c.bcname }
                                </button>
                            )
                        })
                    }
                </div>

                <div>
                    <form className="boardform">
                        제목 : <input type ="text" Name="btitle" /> <br/>
                        내용 : <input type ="text" Name="bcontent" /> <br/>
                        첨부파일 : <input type ="file" Name="bfile" /> <br/>
                        <button type="button" onClick={ setboard }> 등록 </button>
                    </form>
                </div>
        </div>
    );
}
