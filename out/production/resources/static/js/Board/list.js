// 전역변수
let bcno = 0; // 선택된 카테고리 / 기본값 0 : 전체보기
// 1. 게시물 출력 [ 페이지 열렸을때 , 카테고리 버튼 클릭했을때 ]
getlist()
function getlist(){
    $.ajax({
        url: "/board/boardlist",
        type : "get" ,
        data : { "bcno" : bcno },
        success: function(list) {
        console.log(list)
        let html = '<tr> <th> 번호 </th> <th> 제목 </th> <th> 작성자 </th> <th> 첨부파일 </th> <th> 조회수 </th> </tr>';
            list.forEach ( ( b ) => {
                html +=
                    '<tr> <td> <a href="/board/view?bno='+b.bno+'" onclick="getboard('+b.bno+') ; bviewup('+b.bno+')" </a> '+b.bno+' </td> <td> '+b.btitle+' </td> <td> '+b.memail+' </td> <td> '+b.bfilename+' </td> <td> '+b.bview+' </td> </tr>';
                })
                document.querySelector(".btable").innerHTML = html;
            }
    })
}

function getboard(bno){
    // 1. 클릭한 게시물번호 저장
    sessionStorage.setItem("bno" , bno );
    // 2. 페이지 전환
    location.href="/board/view";
}
getloginMno()
function getloginMno (){
    $.ajax({
        url : "/member/getloginMno",
        type : "get",
        success : function(re) {
            let headerbox = '';
            if( re == "0" ){
                headerbox+= ''; // 로그인 안하면
            }
            else{
                headerbox +=
                            ' <a href="/board/write">Write</a> '
            }
            document.querySelector(".headerbox").innerHTML = headerbox;
        }
    })
}
// 3. 모든 카테고리 출력
bcategorylist()
function bcategorylist(){
    $.ajax({
        url: "/board/bcategorylist",
        type : "get",
        success: function(re) {
            console.log(re)
            let html = '<button type="button" onclick="bcnochange(0)"> 전체보기 </button>';
            re.forEach( c =>{
                html += '<button type="button" onclick="bcnochange('+c.bcno+')"> '+c.bcname+'</button>';
            })
            document.querySelector(".bcategorybox").innerHTML = html;
        }
    })
}
// 4. 카테고리를 선택했을때 선택된 카테고리 번호 변경
function bcnochange ( cno ) { bcno = cno; alert( bcno ); getlist()}

function bviewup(bno){
    $.ajax({
        url: '/board/bviewup',
        type: 'PUT',
        success : function(re) {
            if(re == true){
            alert('조회수 증가 성공')}
        }
    })
alert('z')
}

