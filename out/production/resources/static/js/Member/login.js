function getmember(){
    let info = {
        memail : document.querySelector('.memail').value ,
        mpassword : document.querySelector('.mpassword').value
    }
    $.ajax({
        url : "/member/getmember",
        type : "post",
        data : JSON.stringify(info) ,
        contentType : "application/json",
        success : function(re) {
            if(re == '1'){
                location.href = "/index"
            } else { alert('로그인에 실패했습니다.')}
        }
    })
}