getloginMno()
function getloginMno (){
    $.ajax({
        url : "/member/getloginMno",
        type : "get",
        success : function(re) {
            alert(re)
            let headerbox = '';
            if( re == "" ){
                headerbox+=
                        ' <a href="/member/signup"><button type="button" class="btn btn-primary"> 회원가입 </button></a>'+
                        ' <a href="/member/login"><button type="button" class="btn btn-primary"> 로그인 </button></a>'
            }
            else{
                headerbox +=
                        ' <a href="/member/logout"> <button type="button" class="btn btn-primary"> 로그아웃 </button> </a>'+
                        ' <a href="/member/findpassword"><button type="button" class="btn btn-primary"> 비밀번호 찾기 </button> </a>'+
                        ' <a href="/member/update"><button type="button" class="btn btn-primary"> 비밀번호 수정 </button> </a>'+
                        ' <a href="/member/delete"><button type="button" class="btn btn-primary"> 회원탈퇴 </button> </a>'
            }
            document.querySelector(".headerbox").innerHTML = headerbox;
        }
    })
}

/*function logout(){
    $.ajax({
        url : "/member/logout",
        type : "get",
        success : function(re) {window.location.reload()}
    })
}*/
list() // list 함수 실행
// 회원 목록
function list(){
    $.ajax({
        url : "/member/list",
        type : "get",
        success : function(re) {
            let html = '<tr> <th> 번호 </th> <th> 이메일 </th> <th> 비밀번호 </th> <th> 전화번호 </th> </tr>';
            re.forEach( (m)=>{
            html += '<tr> <td> '+m.mno+'</td> <td> '+m.memail+' </td> <td> '+m.mpassword+' </td> <td> '+m.mphone+' </td> </tr>';
            })
            document.querySelector(".mtable").innerHTML = html;
        }
    })
}