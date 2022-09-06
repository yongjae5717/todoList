package project.todoList.before.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.todoList.before.domain.TodoItem;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoItemRepository {

    private final EntityManager em;

    // 아이템 저장
    public void save(TodoItem todoItem){
        if(todoItem.getId() == null){
            em.persist(todoItem);
        } else{
            em.merge(todoItem); //update와 비슷하다.
        }
    }

    // 아이템 1개 조회
    public TodoItem findOne(Long id){
        return em.find(TodoItem.class, id);
    }

    // 전체 아이템 조회
    public List<TodoItem> findAll(){
        return em.createQuery("select i from TodoItem i", TodoItem.class)
                .getResultList();
    }

}
