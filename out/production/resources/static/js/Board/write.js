function setboard(){

    let data = {
        btitle : document.querySelector('.btitle').value ,
        bcontent : document.querySelector('.bcontent').value ,
        bfile : document.querySelector('.bfile').value
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