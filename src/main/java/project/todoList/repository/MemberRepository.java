package project.todoList.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.todoList.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // 멤버 저장
    public void save(Member member){
        em.persist(member);
    }

    // 멤버 찾기
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    //전체 멤버 리스트 반환
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    //이름에 의한 회원 조회기능
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.userName = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
