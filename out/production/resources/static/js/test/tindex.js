let tbcno = 3;
tblist()

function tsetboard(){ // 방명록 등록
    let data = {
        tbtitle : document.querySelector('.tbtitle').value,
        tbcontent : document.querySelector('.tbcontent').value,
        tbuser : document.querySelector('.tbuser').value ,
        tbcno : tbcno
    }
    $.ajax({
        url: '/tboard/tsetboard' ,
        type: 'POST' ,
        data : JSON.stringify(data) ,
        contentType : "application/json" ,
        success: function(re) { alert( re ); tblist(); }
    })
}

function tsetcategory(){ // 카테고리 등록
    let data = { tbcname : document.querySelector('.tbcname').value }
    $.ajax({
        url: "/tboard/tsetcategory" ,
        type: "post" ,
        data : JSON.stringify(data) ,
        contentType : "application/json" ,
        success: function(re) { alert( re ); tbclist(); }
    })
}

tbclist()
function tbclist(){ // 카테고리 호출
    $.ajax({
        url: '/tboard/tbclist' ,
        success: function(re) {
            console.log(re)
            let html = ' <button type="button" onclick="tbcnochange(0)"> 전체보기 </button>'
            re.forEach ( c => {
                html += '<button type="button" onclick="tbcnochange('+c.tbcno+')"> '+c.tbcname+'</button>';
            })
            document.querySelector(".tbclistbox").innerHTML = html;
        }
    })
}

// 4. 카테고리를 선택했을때 선택된 카테고리 번호 변경
function tbcnochange( tcno ){ tbcno = tcno; alert(tbcno+' 변경 완료 '); tblist() }

function tblist(){
    $.ajax({
        url: '/tboard/tblist' ,
        type : "get" ,
        data : { "tbcno" : tbcno },
        success : function(re){
            let html = '<tr> <th> 번호 </th> <th> 작성자 </th> <th> 내용 </th> </tr>';
            re.forEach( ( b ) => {
            html +=
                '<tr> <td onclick="tgetboard('+b.tbno+')"> '+b.tbno+' </td> <td> '+b.tbcontent+' </td> <td> '+b.tbuser+' </td> <td> <button type="button" onclick="bdelete()"> 삭제하기 </button> </td> <td> <button type="button" onclick="bupdatemodal('+b.tbno+')"> 수정하기 </button> </td> </tr>'
            })
            document.querySelector(".ttable").innerHTML = html;
        }
    })
}

let tbno = sessionStorage.getItem('tbno');

function bdelete(){
    $.ajax({
        url: '/tboard/bdelete' ,
        type : "DELETE" ,
        data : { "tbno" : tbno } ,
        success: function(re) { alert ( re ) }
    })
}

function tgetboard(tbno){ // 누른 게시물 저장
    sessionStorage.setItem("tbno" , tbno );
    console.log(sessionStorage.getItem("tbno"))
}

function bupdatemodal(){ // 수정 innerHTML
    let html = '';
    html +=
    '<from>' +
    ' 수정 내용 : <input type ="text" class="tbcontent"> <button type="button" onclick="bupdate()"> 수정 </button>'+
    '</from>'
    document.querySelector(".updatemodal").innerHTML = html;
}

function bupdate(){
    let data = {
        tbcontent : document.querySelector('.tbcontent').value
    }
    $.ajax({
        url: '/tboard/bupdate' ,
        type : "PUT" ,
        data : JSON.stringify(data) ,
        contentType : "application/json",
        success: function(re) {
            if(re == true){
            alert( '글 수정 성공' )
            } else { alert ( ' 글 수정 실패 ' )}
        }
    })
}
