import React , { useState } from 'react'
import TemperatureInput from './TemperatureInput'

// 1. 매개변수 [ props ] 의 온도가 100 이상이면 렌더링 아니면 렌더링
function BoilingVerdict( props ) {
    if( props.celsius >= 100 ){ return <p> 물이 끓습니다. </p> }
    return <p> 물이 끓지 않습니다. </p>;
}

// 2. 매개변수 화씨 -> 섭씨 변환 하는 계산 함수
function toCelsius( fahrenheit ) {
    return(( fahrenheit -32 ) * 5 / 9 )
}
// 3. 매개변수 섭씨 -> 화씨 변환하는 계산 함수
function toFahrenheit( celsius ) {
    return( celsius * 9 ) / 5 + 32 ;
}
// 4.
function tryConvert( temperature , convert ){
    const input = parseFloat( temperature ) // 매개변수 temperature 실수형 형변환
    if( Number.isNaN( input ) ){ return ""; } // 만약에 매개변수가 없으면 공백 리턴

    const output = convert( input ) // 매개변수를 convert 함수에 대입
    const rounded = Math.round( output * 1000 ) / 1000; // 반올림
    return rounded.toString(); // 결과를 문자열로 반환
}

export default function Calculator( porps ){

    const [ temperature , setTemperature ] = useState("");
    const [ scale , setScale ] = useState("c");

    const handleCelsiusChange = ( t ) => { setTemperature(t); setScale("c"); }
    const handleFahrenheitChange = ( t ) => { setTemperature(t); setScale("f"); }

    const celsius = scale === "f" ? tryConvert( temperature , toCelsius ) : temperature;
    const fahrenheit = scale === "c" ? tryConvert( temperature , toFahrenheit ) : temperature;

    return(
        <div>
            <TemperatureInput
                scale="c"
                temperature={ celsius }
                onTemperatureChange={ handleCelsiusChange }
            />
            <TemperatureInput
                scale="f"
                temperature={ fahrenheit }
                onTemperatureChange={ handleFahrenheitChange }
            />
            <BoilingVerdict celsius={ parseFloat( celsius )} />
        </div>
    );
}