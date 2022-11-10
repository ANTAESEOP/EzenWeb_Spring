function getMapping1() {
    $.ajax({
        url : "http://192.168.17.47:8080/api/v1/get-api/hello",
        type : "get",
        success : function(re) { alert ( re ) }
    })
}
function getMapping2() {
    $.ajax({
        url : "http://192.168.17.47:8080/api/v1/get-api/name",
        success : function(re) { alert ( re ) }
    })
}
function GetMapping3() {
    $.ajax({
        url : "http://192.168.17.47:8080/api/v1/get-api/variable1/테스트",
        success : function(re) { alert ( re ) }
    })
}
function GetMapping4() {
    $.ajax({
        url : "http://192.168.17.47:8080/api/v1/get-api/variable2/무야호",
        success : function(re) { alert ( re ) }
    })
}
function GetMapping5() {
    $.ajax({
        url : "http://192.168.17.47:8080/api/v1/get-api/variable3?variable=테스트으",
        success : function(re) { alert ( re ) }
    })
}
function GetMapping6() {
    $.ajax({
        url : "http://192.168.17.47:8080/api/v1/get-api/request1?name=asd&email=123&organiztion=123123",
        success : function(re) { alert ( re ) }
    })
}
function GetMapping7() {
    $.ajax({
        url : "http://192.168.17.47:8080/api/v1/get-api/rquest2?key1=value1&key2=value2&key3=value3",
        success : function(re) { alert ( re ) }
    })
}
function GetMapping8() {
    $.ajax({
        url : "http://192.168.17.47:8080/api/v1/get-api/request3?name=asd&email=123&organiztion=123123",
        success : function(re) { alert ( re ) }
    })
}

/*----------------------------------------------------------------*/

function PostMapping1() {
    $.ajax({
        url : "http://192.168.17.47:8080/api/v1/post-api/domain",
        type : "post",
        success : function(re) { alert ( re ) }
    })
}
function PostMapping2() {
    let member = { name : "유재석", email : "123@naver.com", organiztion : "qweqwe"}
    $.ajax({
        url : "http://192.168.17.47:8080/api/v1/post-api/member1",
        type : "post",
        data : JSON.stringify(member),
        contentType : "application/json",
        success : function(re) { alert ( re ) }
    })
}
function PostMapping3() {
    let member = { name : "유재석", email : "123@naver.com", organiztion : "qweqwe"}
    $.ajax({
        url : "http://192.168.17.47:8080/api/v1/post-api/member2",
        type : "post",
        data : JSON.stringify(member),
        contentType : "application/json",
        success : function(re) { alert ( re ) }
    })
}
/*----------------------------------------------------------------*/
function PutMapping1() {
    let member = { name : "유재석", email : "123@naver.com", organiztion : "qweqwe"}
    $.ajax({
        url : "http://192.168.17.47:8080/api/v1/put-api/member",
        type : "PUT",
        data : JSON.stringify(member),
        contentType : "application/json",
        success : function(re) { alert ( re ) }
    })
}
function PutMapping2() {
    let member = { name : "유재석", email : "123@naver.com", organiztion : "qweqwe"}
    $.ajax({
        url : "http://192.168.17.47:8080/api/v1/put-api/member1",
        type : "PUT",
        data : JSON.stringify(member),
        contentType : "application/json",
        success : function(re) { alert ( re ) }
    })
}
function PutMapping3() {
    let member = { name : "유재석", email : "123@naver.com", organiztion : "qweqwe"}
    $.ajax({
        url : "http://192.168.17.47:8080/api/v1/put-api/member2",
        type : "PUT",
        data : JSON.stringify(member),
        contentType : "application/json",
        success : function(re) {
            console.log(re)
            console.log(re.name)
        }
             // let json = JSON.parse(re); console.log(json) = parseing 생략
    })
}
    /*-------------------------------------------------------*/

function DeleteMapping1(){
   $.ajax({
       url : "http://192.168.17.47:8080/api/v1/delete-api/ㅋㅋㅋㅋ",
       type : "DELETE",
       success : function(re) { alert ( re ) }
   })
}
function DeleteMapping2(){
   $.ajax({
       url : "http://192.168.17.47:8080/api/v1/delete-api/request1?variable=이야후",
       type : "DELETE",
       success : function(re) { alert ( re ) }
   })
}

