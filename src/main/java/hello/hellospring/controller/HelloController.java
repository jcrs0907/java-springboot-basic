package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//Controller라는 어노테이션을 적어주어야 한다.
@Controller
public class HelloController {
    //정적 콘텐츠
    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!!");
        //resourses에 있는 hello를 찾아서 렌더를 해라
        return "hello";
    }

    //템플릿 엔진
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        //모델에 담아서 name을 보냄
        model.addAttribute("name",name);
        //템플릿 엔진을 사용해서 웹 브라우저에게 넘겨줄 때는 html으로 변환해서 넘긴다.
        return "hello-templete";
    }

    //API
    @GetMapping("hello-string")
    //ResponseBody를 사용하면 http body에 문자 내용을 직접 반환한다.
    //viewResolver대신 HttpMessageConverter가 동작을 한다.
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        //html파일이 열리는게 아니라 데이터 그대로 보여준다.
        //객체를 데이터 형식에 따라 StringConverter, JsonConverter를 자동 실행한다.
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello HelloApi(@RequestParam("name") String name){
        //줄 끝에 자동 세미콜론: command + shift + enter
        Hello hello = new Hello();
        //parameter로 넘어온 name을 넘긴다.
        hello.setName(name);
        //return값으로 객체를 넘김
        //결과를 Json 형태로 보여준다.
        return hello;

    }

    static class Hello{
        private String name;
        //getter setter: 자바 빈 규약, 프로퍼티 접근 방식이라고도 한다.
        //단축키: command + n
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
