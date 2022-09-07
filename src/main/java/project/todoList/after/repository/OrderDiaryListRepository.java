package project.todoList.after.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import project.todoList.after.domain.Diary.OrderDiaryList;
import project.todoList.after.domain.Member.Member;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDiaryListRepository {
    private final EntityManager em;

    public void save(OrderDiaryList order){
        em.persist(order);
    }

    public OrderDiaryList findOne(Long id){
        return em.find(OrderDiaryList.class, id);
    }

    public List<OrderDiaryList> findAll(OrderDiaryListSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderDiaryList> cq = cb.createQuery(OrderDiaryList.class);
        Root<OrderDiaryList> o = cq.from(OrderDiaryList.class);
        Join<OrderDiaryList, Member> m = o.join("member", JoinType.INNER); //회원과 조인
        List<Predicate> criteria = new ArrayList<>();
        //주문 상태 검색
        if (orderSearch.getOrderDate() != null) {
            Predicate orderDate = cb.equal(o.get(""),
                    orderSearch.getOrderDate());
            criteria.add(orderDate);
        }
        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name =
                    cb.like(m.<String>get("name"), "%" +
                            orderSearch.getMemberName() + "%");
            criteria.add(name);
        }
        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<OrderDiaryList> query = em.createQuery(cq).setMaxResults(1000); //최대 1000건
        return query.getResultList();
    }
}
