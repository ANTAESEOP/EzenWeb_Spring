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
        success : function(re) { alert(re) }
    })
}