package project.todoList.repository;

import org.springframework.stereotype.Repository;
import project.todoList.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // 멤버 저장
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    // 멤버 찾기
    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
