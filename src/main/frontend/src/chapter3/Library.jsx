import React from 'react';
import Book from "./Book";

function Library( props ) {
    return (
        <div>
            <Book name="처음 만난 파이썬" numOfPage={123} />
            <Book name="처음 만난 AWS" numOfPage={456} />
            <Book name="처음 만난 React" numOfPage={789} />
        </div>
    ); // return End
} // function End

// 함수 내보내기
export default Library;