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

    let boardform = document.querySelector('.boardform')
    let formdata = new FormData(boardform);
    formdata.set("bno" , bno) // 수정할 게시물 번호

    $.ajax({
        url : "/board/updateboard",
        type : "put",
        data : formdata,
        contentType : false,        // Multpart 전송 방법 2 ( 첨부파일 )
        processData : false,        // Multpart 전송 방법 3 ( 첨부파일 )
        success : function(re) {
            if(re == true){
                alert('글 수정 성공');
                location.href = "/board/view";
            }
            else { alert('글 수정 실패') }
         }
    })
}