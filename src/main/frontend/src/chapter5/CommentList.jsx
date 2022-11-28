import React from 'react'; // 필수 ( React 사용하겠다 )
import Comment from './Comment'

// 1. 데이터 리스트 [ 서버 통신과 통신된 결과물 ]
const comments = [ // 댓글 3개 객체를 저장하는 리스트 객체
    { // 댓글1
        name: "이인제" ,
        comment : "안녕하세요 소플입니다.",
    },
    { // 댓글2
        name: "유재석",
        comment : "무한 ~ 도전 ~.",
    },
    { // 댓글3
        name: "강호동",
        comment : "리액트 어려운가요?",
    }
];


function CommentList ( props ){
    // map vs forEach
        // 리스트명.map ( ( 반복변수명 ) => { 실행문 } )

/*
    반복문 사용 X [ 직접 값을 대입했을 때 ]
    return(
        <div>
            <Comment name={"이인제"} comment={"안녕하세요 소플입니다."}>
            <Comment name={"유재석"} comment={"무한 ~ 도전 ~."}>
            <Comment name={"강호동"} comment={"리액트 어려운가요?"}>
        </div>
    );
*/

    return(
        <div>
            { comments.map( (c) => {
                return(
                    <Comment name={c.name} Comment comment={c.comment} />
                );
            })}
        </div>
    );
}
export default CommentList

