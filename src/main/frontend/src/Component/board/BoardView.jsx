import React , { useState , useEffect } from 'react'
import { useParams } from 'react-router-dom';
import axios from 'axios';
import styles from './Style.css'
export default function BoardView ( props ) {
    const params = useParams(); // usePparams() 훅 : 경로 [URL] 상의 매개변수 가져올때

    const [ board , setBoard ] = useState( { } ); // 게시물 메모리
    useEffect( // 1. 서버로 부터 해당 게시물 번호의 게시물 정보 -> useState[ board ] 요청
       () => axios.get( "/board/getboard" , { params : { bno : params.bno } } )
       .then( res => { setBoard( res.data ) } )
    , [] )

    const [ login , setLogin ] = useState( { } ); // 로그인 정보 메모리
    useEffect( // 2. 서버로 부터 해당 로그인 된 회원의 아이디 [ MemberService : getloginMno()
        ()=>axios.get( "/member/getloginMno").then( res => { setLogin( res.data ); } )
    , [] )

    const onDelete = () => { // 3. 서버로 부터 해당 게시물 번호를 이용한 삭제 요청
        axios.delete("/board/deleteboard" , { params : { bno : params.bno } } )
        .then( res => { alert('게시물 삭제 성공'); window.location.href='/board/list'; } )
    }

    const getUpdate = () => { window.location.href='/board/update/'+params.bno }


    return(
        <div>
            <div>{ board.btitle }</div>
            <div dangerouslySetInnerHTML={{__html:board.bcontent }} ></div>
            { board.bfilename != '' && <a href={"/board/filedownload?filename="+ board.bfilename} > { board.bfilename }</a> }
            <div>
                { login == board.memail && <button type="button" onClick={ onDelete }> 삭제 </button>  }
                { login == board.memail && <button type="button" onClick={ getUpdate }> 수정 </button>  }
            </div>
        </div>
    )
}