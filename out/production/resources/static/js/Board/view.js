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
function filedownload(){
    $.ajax({
        url: "/board/filedownload",
        type : "get",
        data : { "filename" : filename },
        success : function(re) {
        alert(filename)
            let html = '';
            re.forEach( f => {
                html = '<a href="http://localhost:8080/board/filedownload?filename='+f.filename+'"> 첨부파일 </a>'
            })
            document.querySelector(".file").innerHTML = html;
        }
    })
}
