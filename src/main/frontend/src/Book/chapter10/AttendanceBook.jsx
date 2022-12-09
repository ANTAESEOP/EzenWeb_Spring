// p.300 리스트와 키
import React from 'react';


const students = [
    { id : 1 , name:"Inje" } , { id : 2 , name:"Ahn" } ,
    { id : 3 , name:"Young"} , { id : 4 , name:"Kim" } ]

export default function AttendanceBook( props ){

    // jsx 표현식 { JS 코드 }
    return(
        <ul>
            {
                students.map( (s) => {
                    return <li key={s.id}> { s.name } </li>
            })}
        </ul>
    )
}