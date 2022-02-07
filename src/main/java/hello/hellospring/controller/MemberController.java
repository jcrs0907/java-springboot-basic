package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//Controller 어노테이션이 있으면 해당 객체를 생성해서 스프링에 넣어두고 관리를 한다.
@Controller
public class MemberController {

    //private final MemberService memberService = new MemberService();

    //필드 주입
    //스프링 뜰 때만 넣어주어서 바꿀 수 있는 방법이 없다.
    //@Autowired private MemberService memberService;

    //생성자 주입
    //memberService가 MemberController를 통해 들어옴
    //@Autowired
    //    public MemberController(MemberService memberService){
    //        this.memberService = memberService;
    //    }

    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService){
        this.memberService = memberService;
    }
    //조회할때 주로 Get
   @GetMapping("/members/new")
    public String createForm(){
        //templates 아래에서 찾음
        return "members/createMemberForm";
   }
    //값 넘길 때 주로 Post
   @PostMapping("/members/new")
   public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
   }

   @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

    //command e 가장 최근에 봤던 단축키


}
