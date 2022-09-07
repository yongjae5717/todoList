package project.todoList.after.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import project.todoList.after.domain.Diary.OrderDiaryList;
import project.todoList.after.domain.Member.Member;
import project.todoList.after.domain.TodoList.OrderTodoList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderTodoListRepository {
    private final EntityManager em;

    public void save(OrderTodoList order) {
        em.persist(order);
    }

    public OrderTodoList findOne(Long id) {
        return em.find(OrderTodoList.class, id);
    }

    public List<OrderTodoList> findAll(OrderTodoListSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderTodoList> cq = cb.createQuery(OrderTodoList.class);
        Root<OrderTodoList> o = cq.from(OrderTodoList.class);
        Join<OrderDiaryList, Member> m = o.join("member", JoinType.INNER);
        List<Predicate> criteria = new ArrayList<>();

        if (orderSearch.getOrderDate() != null) {
            Predicate orderDate = cb.equal(o.get(""),
                    orderSearch.getOrderDate());
            criteria.add(orderDate);
        }

        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name = cb.like(m.<String>get("name"), "%" +
                    orderSearch.getMemberName() + "%");

            criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<OrderTodoList> query = em.createQuery(cq).setMaxResults(1000); //최대 1000건
        return query.getResultList();
    }
}
