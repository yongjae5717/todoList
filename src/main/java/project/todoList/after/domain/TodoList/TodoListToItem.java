package project.todoList.after.domain.TodoList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoListToItem {

    @Id
    @Column(name = "todo_list_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "todo_item_id")
    private project.todoList.after.domain.TodoList.TodoItem todoItem;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private OrderTodoList order;

    private String todoName;

    private String todoContent;

    //==생성 메서드==//
    public static TodoListToItem createOrderTodoList(TodoItem todoItem, String name, String content){
        TodoListToItem todoListToItem = new TodoListToItem();
        todoListToItem.setTodoItem(todoItem);
        todoListToItem.setTodoName(name);
        todoListToItem.setTodoContent(content);

        return todoListToItem;
    }

    //==비지니스 로직 추가==//
    public void changeContent(String name, String content) {
        getTodoItem().change(name, content);
    }

}
