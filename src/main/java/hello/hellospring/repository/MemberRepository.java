package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//회원 객체를 저장하는 저장소
public interface MemberRepository {
    Member save(Member member);
    //Optional은 null값이 반환될 때 Optional이라는 걸로 감싸서 반환을 해준다.
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();



}
