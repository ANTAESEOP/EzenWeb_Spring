// 1. 세선 스토리지 호출
let bno = sessionStorage.getItem( "bno" );
// 2. 수정 전의 게시물 정보 호출
getboard()
function getboard(){
    $.ajax({
        url : "/board/getboard" ,
        type: "GET" ,
        data : { "bno" : bno } ,
        success: function(re) { console.log(re)}
    })
}
// 3. 수정버튼 클릭시 호출 되는 메소드
function updateboard() {
    let data = {
        btitle : document.querySelector('.btitle').value , // 수정할 제목
        bcontent : document.querySelector('.bcontent').value , // 수정할 내용
        bfile : document.querySelector('.bfile').value , // 수정할 첨부파일
        bno : bno // 수정할 게시물 번호
    }
    $.ajax({
        url : "/board/updateboard",
        type : "put",
        data : JSON.stringify(data) ,
        contentType : "application/json",
        success : function(re) {
            if(re == true){
                alert('글 수정 성공');
                location.href = "/board/view";
            }
            else { alert('글 수정 실패') }
         }
    })
}