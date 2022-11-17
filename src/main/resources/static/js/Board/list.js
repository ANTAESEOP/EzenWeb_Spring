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
        let html = '<tr> <th> 번호 </th> <th> 제목 </th> <th> 내용 </th> <th> 첨부파일 </th> </tr>';
            re.forEach ( ( b ) => {
                html +=
                    '<tr> <td> '+b.bno+'</td> <td> '+b.btitle+' </td> <td> '+b.bcontent+' </td> <td> '+b.bfile+' </td>  </tr>';
                })
                document.querySelector(".btable").innerHTML = html;
            }
    })
}