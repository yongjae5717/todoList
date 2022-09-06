package project.todoList.before.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.todoList.before.domain.DiaryItem;
import project.todoList.before.repository.DiaryItemRepository;


import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryItemService {

    private final DiaryItemRepository diaryItemRepository;

    @Transactional //쓰기모드
    // 아이템 저장하기
    public void saveItem(DiaryItem item){
        diaryItemRepository.save(item);
    }

    //전체 아이템 찾기
    public List<DiaryItem> findItems(){
        return diaryItemRepository.findAll();
    }

    //한가지 아이템 찾기
    public DiaryItem findOne(Long itemId){
        return diaryItemRepository.findOne(itemId);
    }
}
