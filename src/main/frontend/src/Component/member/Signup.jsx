import React from 'react'; // 필수 ( React 사용하겠다 )
import styles from './Signup.css'; // css 파일 import
import axios from 'axios' // axios 사용

    // class-> className
    // onclick -> onClick 변경
    // 태그 닫기 /tag 명
    // 함수 호출 -> { } jsx 표현식

function Signup(props) {
    // 1. setmember 이벤트 함수 정의
    const setmember = () => {
    alert( "클릭" )
        let info = {
            memail : document.querySelector('.memail').value ,
            mpassword : document.querySelector('.mpassword').value ,
            mphone : document.querySelector('.mphone').value
        }
        // 확인
        console.log( info )
        // @CrossOrigin(origins = "http://localhost:3000")
        axios // 3. axios 비동기통신 이용한 서버[spring] 통신
            .post( "http://localhost:8080/member/setmember" , info ) // 요청
            .then( res => { alert( res.data ) } ) // 응답


    }
    // 2. 인증코드 요청 함수
    const getauth = () => { alert("클릭")}
    // 3. 타이머 함수
    const settimer = () => { alert("클릭")}
    // 4. 인증 버튼 확인 함수
    const authcode = () => { alert("클릭")}


    return(
        <div>
            <h3> 회원가입 </h3>
            <div>
                이메일 : <input type="text" className="memail" />
                <button type="button" onClick={ getauth } className="getauthbtn"> 인증코드 받기 </button> <br/>
                <div className="authbox">
                    <input type="text" className="authinput" />
                    <button type="button" className="authbtn" onClick={ authcode }>인증</button>
                    <span className="timerbox"></span>
                </div>
            </div>
            핸드폰 : <input type="text" className="mphone" /> <br/>
            비밀번호 : <input type="text" className="mpassword" /> <br/>
            <button type="button" onClick={ setmember }> 가입하기 </button>
        </div>
    );
}

export default Signup

/*
    class -> className 변경
    onclick -> onClick 변경
    함수호출 -> { } jsx 표현식
    태그 닫기 /tag명
*/

// 비동기 통신 [ ajax vs fetch[ react 내장 ] vs axios [ react 별도 설치 ] ]
    // axios 설치방법
        // npm : 라이브러리 별도/관리 [ node.js ]

 // axios 비동기통신 함수
            // axios.MethodType( "통신 URL " , 전송할 data )
                // axios.post( ) , axios.get( ) , axios.put( ) .axios.delete( )