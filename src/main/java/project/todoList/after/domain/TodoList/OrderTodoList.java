package project.todoList.after.domain.TodoList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.todoList.after.domain.Member.Member;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<project.todoList.after.domain.TodoList.TodoListToItem> todoListToItems = new ArrayList<>();

    private LocalDateTime orderDate;

    //==연관관계 편의 메서드==//
    public void setMember(Member member){
        this.member = member;
        member.getOrderTodoLists().add(this);
    }

    public void addOrderTodoItem(TodoListToItem todoListToItem){
        todoListToItems.add(todoListToItem);
        todoListToItem.setOrder(this);
    }

    //==생성 메서드==//

    public static OrderTodoList createTodoOrder(Member member, project.todoList.after.domain.TodoList.TodoListToItem... todoListToItems){
        //order생성
        OrderTodoList order = new OrderTodoList();
        //order세팅
        order.setMember(member);
        for(project.todoList.after.domain.TodoList.TodoListToItem todoListToItem: todoListToItems){
            order.addOrderTodoItem(todoListToItem);
        }
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

}