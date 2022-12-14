import React , { useState , useEffect } from 'react';
import axios from 'axios';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { useParams } from 'react-router-dom';

let bcontent = '';
export default function BoardUpdate( props ) {
    const params = useParams(); // usePparams() 훅 : 경로 [URL] 상의 매개변수 가져올때

    const [ board , setBoard ] = useState( { } ); // 게시물 메모리

    useEffect(
        () => axios.get( "/board/getboard" , { params : { bno:params.bno } } )
       .then( res => { setBoard( res.data ) } )
    , [] )

    const upboard = () => { // 서버로부터 수정된 정보를 이용한 게시물 수정 요청
        // 수정할 게시물번호 , 수정할 내용들 [ 제목 , 내용 , 첨부파일 ]
        let boardform = document.querySelector('.boardform')
        let formdata = new FormData( boardform );
        formdata.set( "bno" , board.bno );
        formdata.set( "bcontent" , bcontent );

        axios.put("/board/updateboard" , formdata , { headers : { "Content-Type" : "multipart/form-data" } } )
            .then( res => {
                    if( res.data == true ){ alert("게시물 수정 성공"); window.location.href='/board/list'; }
                    else{ alert("게시물 수정 실패") }
                })
            .catch( err => { console.log( err ) } )
    }
    return(
            <div>
               <h1> 글 수정 페이지</h1>
               <form className="boardform">
                    제목 : <input type ="text" Name="btitle" value={ board.btitle } /> <br/>
                    <CKEditor
                        editor={ ClassicEditor }
                        data= { board.bcontent }
                        onChange={ ( event, editor ) => { const data = editor.getData(); bcontent = data } }
                    />
                    첨부파일 : <input type ="file" Name="bfile" /> <br/>
                    <button type="button" onClick={ upboard }> 수정 </button>
                </form>
            </div> // 전체 div
        );
}



