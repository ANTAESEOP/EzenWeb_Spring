// 1. list.js에서 클릭된 게시물 번호 호출
let bno = sessionStorage.getItem( "bno" ) ;
// 2. 클릭된 게시물번호의 게시물정보를 호출하는 메소드
getboard()
function getboard(){
    $.ajax({
        url : "/board/getboard" ,
        type: "GET" ,
        data : { "bno" : bno } ,
        success: function(re) {
            console.log(re)
            document.querySelector('.bno').innerHTML = re.bno;
            document.querySelector('.bcontent').innerHTML = re.bcontent;
            document.querySelector('.memail').innerHTML = re.memail;
            let html = ' <a href="http://localhost:8080/board/filedownload?filename='+re.bfilename+'"> '+re.bfilename+' </a>';
            document.querySelector(".file").innerHTML = html;
            re.bview +1;
        }
    })
}

// 3. 삭제버튼 클릭시 호출되는 메소드
function deleteboard(){ // 글 삭제 함수
    $.ajax({
        url: "/board/deleteboard" ,
        type: "DELETE",
        data: { "bno" : bno } ,
        success: function(re) {
            if( re == true){
                location.href = "/board/list";
                alert('삭제 성공')
            } else {
                alert('삭제 실패')
            }
        }
    })
}

