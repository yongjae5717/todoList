package project.todoList.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

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

    //==비지니스 로직 추가==//
    private void check(){
        status = TodoStatus.SUCCESS;
    }

    private void change_content(String changing_content){
        this.content = changing_content;
    }

}
