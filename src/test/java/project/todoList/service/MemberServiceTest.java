package project.todoList.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import project.todoList.domain.Member;
import project.todoList.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
//    @Autowired
//    EntityManager em;

    @Test
//    @Rollback(value = false) //Transactional으로 인한 Rollback True
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setUserName("Kim");

        //when
        Long savedId = memberService.join(member);

        //then
//        em.flush(); // 영속성 콘텍스트 멤버 객체를 변경 내용을 쿼리로 볼 수 있다.
        //같은 회원인지 확인
        assertEquals(member, memberRepository.findOne(savedId));
    }

    //Try-catch사용을 줄일 수 있음
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setUserName("Kim");
        Member member2 = new Member();
        member2.setUserName("Kim");
        //when
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야하는 상황

        //then
        fail("예외가 발생해야 합니다.");
    }
}