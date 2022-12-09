// p.249
// 1. 컴포넌트 첫글자 대문자 ( * 안 읽힘 !! )
// 2. 클래스 컴포넌트 / 함수 컴포넌트
// 3. this.필드명 : 현재클래스의 맴버 [ 필드 , 함수 , 생성자 ]
import React from 'react';

class Ex1_Event extends React.Component {

    // ----------------------- 1 ----------------------- //
    // 1. 생성자
    constructor(props) {
        super(props);
        this.state = { isToggleOn : true }; // 메모리 관리
        // callback에서 'this' 를 사용하기 위에서는 바인딩을 필수적으로 해줘야 함.
        this.handleClick = this.handleClick.bind(this);
    }

    // 2. 이벤트 함수
    handleClick(){  // prevState : state 값
        this.setState( prevState => ({
            isToggleOn : !prevState.isToggleOn
        }));
    }
    // ----------------------- 2 ----------------------- //
    handleClick = () => {
        this.setState( prevState => ({
            isToggleOn : !prevState.isToggleOn
        }));
    }
    // ----------------------- 3 ----------------------- //
    handleClick(){
        this.setState( prevState => ({
            isToggleOn : !prevState.isToggleOn
        }));
    }


    // 2. 렌더링 함수
    render() {
        return (
            <button onClick={ this.handleClick }>
                { this.state.isToggleOn ? '켜짐' : '꺼짐' }
            </button>
        );
    }
}
export default Ex1_Event

