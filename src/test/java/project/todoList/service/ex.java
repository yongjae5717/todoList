package project.todoList.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import project.todoList.after.domain.Member.Member;
import project.todoList.after.domain.TodoList.TodoItem;
import project.todoList.after.repository.MemberRepository;
import project.todoList.after.repository.TodoItemRepository;
import project.todoList.after.service.MemberService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ex {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Autowired
    TodoItemRepository todoItemRepository;

    @Test
    public void 테스트(){
        TodoItem todoItem = new TodoItem();

        //생성자
        todoItem.setContent(",....dfsof");
        todoItem.setUpdateDate(LocalDateTime.now());
        todoItem.setCreateDate(LocalDateTime.now());

        //테이블 생성
        todoItemRepository.save(todoItem);

        //비지니스 로직 (수정)
        todoItem.changeContent("변경내용");
    }
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