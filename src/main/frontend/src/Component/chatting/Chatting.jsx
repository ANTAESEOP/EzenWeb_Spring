import React , { useState , useEffect , useRef } from 'react';

export default function Chatting ( props ) {
    // * 서버소켓과 연결 여부 저장하는 메모리
    const [ socketConn , setSockConn ] = useState( false )
    // * 서버소켓으로부터 들어온 메세지를 저장하는 메모리
    const [ msgList , setMsgList] = useState( [ ] );
    // * 클라이언트 소켓
    let ws = useRef(null); // 재 랜더링 될 때마다 컴포넌트 재 실행
    // * 컴포넌트 mount 될 때 서버소켓 연결 , unmount 될 때 서버소켓 닫기
    // useEffect( () => {} , [] ); useEffect( 함수명 , [] );
    useEffect( ()=>{
        if( !ws.current ){
            // 클라이언트 소켓 생성
            ws.current = new WebSocket('ws://localhost:8080/chat')
            // 1. 클라이언트 소켓이 open 될 때
            ws.current.onopen = () => { setSockConn( true ) }
            // 2. 클라이언트 소켓이 close 될 때
            ws.current.onclose = (e) => { console.log( ' 닫기 : ' + e ) }
            // 3. 클라이언트 소켓이 error 될 때
            ws.current.onerror = (e) => { console.log( ' 에러 : ' + e ) }
            // 4. 서버소켓으로부터 message 받았을 때
            ws.current.onmessage = (e) => {
                let data = JSON.parse( e.data );
                setMsgList( ( prevMsgList ) => [ ...prevMsgList , data ] ) ;
            }
        }
    } , [] );



    const onMsg = () => {

        let msg = document.querySelector('.msgInput').value     // 1. 입력받은 데이터 가져오기
        ws.current.send( JSON.stringify( { message : msg } ) )   // 2. 웹 소켓 이용한 send [ JSON 형식으로 보내기 ]
        document.querySelector('.msgInput').value = ''          // 3. 입력상자 초기화
    }

    return(
        <div>
            접속 상태 : <span> { socketConn } </span>
            <div>
                채팅입력 : <input type="text" className="msgInput" />
                <button type="button" onClick={ onMsg } > 전송 </button>
            </div>

            <div>
                <h6> 채팅창 </h6>
                {
                    msgList.map( ( msg ) => {
                        return <div> { JSON.stringify( msg ) } </div>
                    })
                }
            </div>
        </div>
    )
}