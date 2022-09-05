package project.todoList.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.todoList.domain.DiaryItem;


import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DiaryItemRepository {
    private final EntityManager em;

    // 아이템 저장
    public void save(DiaryItem diaryItem){
        if(diaryItem.getId() == null){
            em.persist(diaryItem);
        } else{
            em.merge(diaryItem); //update와 비슷하다.
        }
    }

    // 아이템 1개 조회
    public DiaryItem findOne(Long id){
        return em.find(DiaryItem.class, id);
    }

    // 전체 아이템 조회
    public List<DiaryItem> findAll(){
        return em.createQuery("select i from DiaryItem i", DiaryItem.class)
                .getResultList();
    }

}
