package project.todoList.before.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Order{
    @Id
    @Column(name = "order_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDiaryList> orderDiaryLists = new ArrayList<OrderDiaryList>();

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderTodoList> orderTodoLists = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [DIARY, TODOLIST]

    //==연관관계 편의 메서드==//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderDiaryItem(OrderDiaryList orderDiaryList){
        orderDiaryLists.add(orderDiaryList);
        orderDiaryList.setOrder(this);
    }

    public void addOrderTodoItem(OrderTodoList orderTodoList){
        orderTodoLists.add(orderTodoList);
        orderTodoList.setOrder(this);
    }

    //==생성 메서드==//
    public static Order createDiaryOrder(Member member, OrderDiaryList... orderDiaryLists){
        //order생성
        Order order = new Order();
        //order세팅
        order.setMember(member);
        for(OrderDiaryList orderDiary: orderDiaryLists){
            order.addOrderDiaryItem(orderDiary);
        }
        order.setStatus(OrderStatus.DIARY);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public static Order createTodoOrder(Member member, OrderTodoList... orderTodoLists){
        //order생성
        Order order = new Order();
        //order세팅
        order.setMember(member);
        for(OrderTodoList orderTodoItem: orderTodoLists){
            order.addOrderTodoItem(orderTodoItem);
        }
        order.setStatus(OrderStatus.TODOLIST);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }



}
