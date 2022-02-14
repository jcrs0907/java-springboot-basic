package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//서비스는 비지니스에 의존적 설계
//테스트 클래스를 자동으로 만들어주는 단축키 cmd shift t
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    //memberRepository를 외부에서 넣어주도록 변경
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     */
    public Long join(Member member){
        //조건: 같은 이름이 있는 중복 회원을 받지 않음

        //결과가 null로 반환될 수 있는 경우 Optional 많이 감싸서 사용한다.
        //ifPresent 같은 메서드를 사용할 수 있다.
//        Optional<Member> result = memberRepository.findByName(member.getName());
//
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        //중복 회원 검증을 메서드로 생성한다.
        //command option m -> 메서드 생성
        validateDuplicateMember(member); //중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
