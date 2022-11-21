
// ----------카테고리 기본값 (전역변수 ) ---------- //
let bcno = 2;

function setboard(){

    let data = {
        btitle : document.querySelector('.btitle').value ,
        bcontent : document.querySelector('.bcontent').value ,
        bfile : document.querySelector('.bfile').value ,
        bcno : bcno
    }

    $.ajax({
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