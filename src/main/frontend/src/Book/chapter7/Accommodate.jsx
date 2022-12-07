// p.235
import React , { useState , useEffect } from 'react';
import useCounter from './useCounter' // 커스텀 훅 가져오기

const MAX_CAPACITY = 10; // 전역변수 ( 최대 수용 인원 )

// 1. 컴포넌트 선언
export default function Accommodate( props ) {
        // 1. 커스텀 훅 호출해서 [ 인원수 , 증가함수 , 감소함수 ] 배열 반환
        const [ count , increaseCount , decreaseCount ] = useCounter(0);
        // 2. 최대수용인원 여부 상태
        const [ isFull , setIsFull ] = useState( false );

        // 3.
            // 의존성 배열 없음 [ 생략 ]
            // 실행조건 : 1. mount , 2.update
        useEffect (()=>{
            console.log( "====================")
            console.log( "이펙트 훅 is called")
            console.log(`isFull: ${isFull}`);
        })

        // 3. 만약에 현재 인원이 최대 수용 인원보다 크거나 같으면 true 가 isFull 변수에 true 저장되고 아니면 false
            // 의존성배열 존재 [ count ]
            // 실행조건 : 의존성배열이 업데이트 될때
        useEffect(() => { setIsFull( count >= MAX_CAPACITY ) } , [count] )



    // 4. 렌더링 되는 구역
    return(
        <div style={{ padding : 16 }}>
            <p> 총 {count}명 수용했습니다. </p>
            <button onClick={increaseCount} disabled = {isFull}> 입장 </button>
            <button onClick={decreaseCount}> 퇴장 </button>
            { isFull && <p style={{ color : "red" }}>정원이 가득찼습니다. </p> }
        </div>
    );
}