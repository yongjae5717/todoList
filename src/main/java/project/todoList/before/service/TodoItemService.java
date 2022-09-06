package project.todoList.before.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.todoList.before.domain.TodoItem;
import project.todoList.before.repository.TodoItemRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoItemService {
    private final TodoItemRepository todoItemRepository;

    @Transactional //쓰기모드
    // 아이템 저장하기
    public void saveItem(TodoItem item){
        todoItemRepository.save(item);
    }

    //전체 아이템 찾기
    public List<TodoItem> findItems(){
        return todoItemRepository.findAll();
    }

    //한가지 아이템 찾기
    public TodoItem findOne(Long itemId){
        return todoItemRepository.findOne(itemId);
    }
}
