package project.todoList.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity

public class TodoItem {
    @Id
    @Column(name = "todo_item_id", nullable = false)
    private Long id;

    private String name;

    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    private TodoStatus status; //주문상태 [SUCCESS, FAIL]

    private String content;


}
