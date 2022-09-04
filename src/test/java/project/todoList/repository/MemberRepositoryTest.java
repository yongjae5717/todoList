package project.todoList.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import project.todoList.domain.Member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    void saveAndFind() {
        //given
        Member member1 = new Member("userA", "member1@naver.com");
        Member member2 = new Member("userB", "member2@naver.com");


        //when
        Long savedUserId = memberRepository.save(member1);
        Member savedMember = memberRepository.find(savedUserId);

        //then
        assertThat(savedMember).isEqualTo(member1);

    }
}