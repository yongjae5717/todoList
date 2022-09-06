package project.todoList.before.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderTodoList {
    @Id
    @Column(name = "order_todo_list_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "todo_item_id")
    private TodoItem todoItem;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private String todoName;

    private String todoContent;

    //==생성 메서드==//
    public static OrderTodoList createOrderTodoList(TodoItem todoItem, String name, String content){
        OrderTodoList orderTodoList = new OrderTodoList();
        orderTodoList.setTodoItem(todoItem);
        orderTodoList.setTodoName(name);
        orderTodoList.setTodoContent(content);

        return orderTodoList;
    }

    //==비지니스 로직 추가==//
    public void changeContent(String name, String content) {
        getTodoItem().change(name, content);
    }

}
