package com.Ezenweb.controller.test;


import com.Ezenweb.domain.Dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

@RestController
@RequestMapping("api/v1/get-api") // 요청 URL 정의하기
public class GetController {

    // 1. p.57
    @RequestMapping(value = "/hello", method = RequestMethod.GET) // GET 요청
    public String getHello(){ // 함수명 [ 아무거나 / 중복 X ]
        return "해당 메소드로 들어왔다."; // repsonse 응답
        // http://localhost:8080/api/v1/get-api/hello
    }
        // @RequestMapping
            // 1. GetMapping
                // @RequestMapping(value = "/hello", method = RequestMethod.GET) // GET 요청
                // @GetMapping("/hello")
            // 2. PostMapping
            // 3. PutMapping
            // 4. DeleteMapping
    // 2. p.58
    @GetMapping("/name")
    public String getName(){
        return "Falture";
    }
    // 3. p.59      주의 : 경로상의 변수명 [ {variable} ] 과 @PathVariable 매개변수 [ variable ]
    @GetMapping("/variable1/{variable}") // 경로 상의 변수 [ 값 ]
    public String getVariable( @PathVariable String variable ){
        return variable;
        // http://localhost:8080/api/v1/get-api/variable1/TEST
    }

    // 4. p.60
    @GetMapping("/variable2/{variable}")
    public String getVariable2( @PathVariable( value = "variable" )String test ){
        return test;
        //
    }

    // [ 변수 1개 ]
    //  @PathVariable [ / ] : http://localhost:8080/api/v1/get-api/variable2/TEST
    // VS
    // [ 변수 여러개 => 키:값 ]
    //  @RequestParam [ ? ] : http://localhost:8080/api/v1/get-api/variable3?variable=하하

    // 4-2 [ 비교 ]
    @GetMapping("/variable3")
    public String getVariable3( @RequestParam ( value = "variable" )String variable ){
        return variable;
    }

    // 5. p.61
    @GetMapping("/request1") // UTRL ?변수명 = 값 & 변수명2 = 값2 & 변수명3 = 값3
    public String getRequestParam1(
            @RequestParam String name , @RequestParam String email , @RequestParam String organiztion){
        return name+ " " + email + " " + organiztion;
    }
    // http://localhost:8080/api/v1/get-api/request1?name=asd&email=123&organiztion=123123

    // 6. p.62
    @GetMapping("/rquest2")
    public String getRequestParam2(@RequestParam Map<String , String > param){
        return param.toString();
    }
    // http://localhost:8080/api/v1/get-api/rquest2?name=test&email=test@naver.com&organiztion=123123
    /*
        JAVA 컬렉션 프레임워크
            1. list : 인덱스 [ 중복가능 ] , set : 인덱스 X [ 중복 불가능 ] , MAP : 인덱스 X [ 키 : 값 ] 엔트리 사용
        JS
            1. JSON
     */

    // 7. p.66
    @GetMapping("/request3")
    public String getRequestParam3(MemberDto memberDto){
        return memberDto.toString();
    }








}
