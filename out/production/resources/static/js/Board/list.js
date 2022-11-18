getloginMno()
function getloginMno (){
    $.ajax({
        url : "/member/getloginMno",
        type : "get",
        success : function(re) {
            alert(re)
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

getlist()
function getlist(){
    $.ajax({
        url: "/board/boardlist",
        type : "get" ,
        success: function(re) {
        let html = '<tr> <th> 번호 </th> <th> 제목 </th> <th> 작성자 </th> <th> 첨부파일 </th> <th> 조회수 </th> </tr>';
            re.forEach ( ( b ) => {
                html +=
                    '<tr> <td> <a href="/board/view?bno='+b.bno+'" onclick="getboard('+b.bno+')" </a> '+b.bno+' </td> <td> '+b.btitle+' </td> <td> '+b.memail+' </td> <td> '+b.bfile+' </td> <td> '+b.bview+' </td> </tr>';
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

