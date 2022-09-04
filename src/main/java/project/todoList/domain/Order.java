package project.todoList.domain;


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
public class Order {
    @Id
    @Column(name = "order_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderDiaryList> orderDiaryLists = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderTodoList> orderTodoLists = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [DIARY, TODOLIST]

}
