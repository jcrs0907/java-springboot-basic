package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
//클래스를 동작시키면 클래스 안에 있는 테스트케이스 전체를 확인할 수 있다.
class MemoryMemberRepositoryTest{

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트 코드를 미리 작성하는 경우가 있는데, 테스트 주도개발(TDD)이라고 한다.
    //메서드 테스트가 끝날때마다 repository를 지워주는 코드(콜백 메서드)
    //클래스 실행 시 메서드가 랜덤으로 실행되기 때문에 메서드가 실행될 때마다 공용으로 사용하는 데이터를 지워줘야 된다.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        //name이 저장이 잘 되었는지 id를 가지고 와본다.
        //optional에서 값을 꺼낼때는 get()을 이용할 수 있다.
        Member result = repository.findById(member.getId()).get();

        //결과
        //System.out.println("result =" +(result == member));

        //결과: Assertions.assertThat
        //assertThat(result).isEqualTo(member);

        //결과: Assertions.assertEquals
        //member와 result가 같지 않을 경우 run했을 때 오류가 뜸
        //Assertions.assertEquals(member, result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //rename: shift + fn + f6
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result= repository.findAll();

        //result의 크기가 2와 같다면
        assertThat(result.size()).isEqualTo(2);
    }
}
