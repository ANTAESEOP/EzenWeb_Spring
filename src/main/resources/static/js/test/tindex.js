function tsetboard(){

    let data = {
        tbtitle : document.querySelector('.tbtitle').value,
        tbcontent : document.querySelector('.tbcontent').value,
        tbuser : document.querySelector('.tbuser').value
    }

    $.ajax({
        url: '/tboard/tsetboard' ,
        type: 'POST' ,
        data : JSON.stringify(data) ,
        contentType : "application/json" ,
        success: function(re) { alert( re ); }
    })
}

function tsetcategory(){

    let data = {}

    $.ajax({
        url: '/tboard/tsetboard' ,
        type: 'POST' ,
        data : JSON.stringify(data) ,
        contentType : "application/json" ,
        success: function(re) { alert( re ); }
    })
}

function tblist(){
    $.ajax({
    url : '/tboard/tsetboard'


    })

}



