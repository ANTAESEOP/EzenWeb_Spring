
// ----------카테고리 기본값 (전역변수 ) ---------- //
let bcno = 2;

/*function setboard(){ // [ 일반 전송 ]

    let data = {
        btitle : document.querySelector('.btitle').value ,
        bcontent : document.querySelector('.bcontent').value ,
        bfile : document.querySelector('.bfile').value ,
        bcno : bcno
    }

    $.ajax({ // http 사용하는 jquery 비동기통신 함수 [ 기본값 : contentType : text/html ]
        url : "/board/setboard",
        type : "post",
        data : JSON.stringify(data) ,
        contentType : "application/json",
        success : function(re) {
            if(re == true){
                alert('글 작성 성공');
                location.href = "/board/list";
            }
            else { alert('글 작성 실패') }
         }
    })
}*/

function setboard(){ // [ 첨부파일 전송 ]

    let boardform = document.querySelector('.boardform')
    let formdata = new FormData(boardform);
    formdata.set("bcno" , bcno) // 폼 데이터에 데이터 추가
                // "name" , data
    $.ajax({ // http 사용하는 jquery 비동기통신 함수 [ 기본값 : contentType : text/html ]
        url : "/board/setboard",
        type : "post",              // Multpart 전송 방법 1 ( 첨부파일 )
        data : formdata,
        // content-type : multpart/formed-data <-- 파일 첨부
        contentType : false,        // Multpart 전송 방법 2 ( 첨부파일 )
        processData : false,        // Multpart 전송 방법 3 ( 첨부파일 )
        success : function(re) {
            if(re == true){
                alert('글 작성 성공');
                location.href = "/board/list";
            }
            else { alert('글 작성 실패') }
         }
    })
}

// 2. 게시물 카테고리 등록 메소드
function setbcategory(){
    let data = { bcname : document.querySelector('.bcname').value }
    $.ajax({
        url : "/board/setbcategory",
        type : "post",
        data : JSON.stringify(data) ,
        contentType : "application/json",
        success : function(re) {
            if( re == true){
                alert('카테고리 등록 성공')
                bcategorylist()
            } else {
                alert('카테고리 등록 실패' )}
        }
    })
}

// 3. 모든 카테고리 출력
bcategorylist()
function bcategorylist(){
    $.ajax({
        url: "/board/bcategorylist",
        success: function(re) {
            console.log(re)
            let html = "";
            re.forEach( c =>{
                html += '<button type="button" onclick="bcnochange('+c.bcno+')"> '+c.bcname+'</button>';
            })
            document.querySelector(".bcategorylistbox").innerHTML = html;
        }
    })
}
// 4. 카테고리를 선택했을때 선택된 카테고리 번호 변경
function bcnochange ( cno ) { bcno = cno; alert(bcno+"의 카테고리 선택"); }