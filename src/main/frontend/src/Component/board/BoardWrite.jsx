import React from 'react';
import axios from 'axios';

export default function BoardWrite( props ){

    const setbcategory = () => {
        let category = { bcname : document.querySelector('.bcname').value }
        axios
            .post("http://localhost:8080/board/setbcategory" , category )
            .then( (response) => {
                let result = response.data;
                if( result = '0' ){
                    alert(' 카테고리 등록 성공 ');
                } else {
                    alert(' 카테고리 등록 실패 ')
                }
            })
            .catch( err => { alert( err ) } )                              // 예외처리
    }

    const setboard = () => {
            let board = {
                    btitle : document.querySelector('.btitle').value ,
                    bcontent : document.querySelector('.bcontent').value ,
                    bfile :  document.querySelector('.bfile').value
            }

            axios
                .post("http://localhost:8080/board/setboard" , board )
                .then( (response) => {
                    let result = response.data;
                    if( result = '0' ){
                        alert(' 글 등록 성공 ');
                    } else {
                        alert(' 글 등록 실패 ')
                    }
                })
                .catch( err => { alert( err ) } )
    }

    return(
        <div>
           <h1> 글 쓰기 페이지</h1>
           <input type="text" className="bcname" />
                <button type="button" onClick={ setbcategory }> 카테고리 추가 </button>
                <div className="bcategorylistbox"></div>

                <form className="boardform">
                    제목 : <input type ="text" className="btitle" /> <br/>
                    내용 : <input type ="text" className="bcontent" /> <br/>
                    첨부파일 : <input type ="file" className="bfile" /> <br/>
                    <button type="button" onClick={ setboard }> 등록 </button>
                </form>
        </div>
    );
}