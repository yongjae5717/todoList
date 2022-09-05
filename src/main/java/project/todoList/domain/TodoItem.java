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

    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    private TodoStatus status; //주문상태 [SUCCESS, FAIL]

    private String content;

    //==비지니스 로직 추가==//
    private void check(){
        status = TodoStatus.SUCCESS;
    }


    //==비지니스 로직 ==//
    //수정 로직
    public void change(String newName, String newContent){
        this.name = newName;
        this.content = newContent;
        this.updateDate = LocalDateTime.now();
    }

}
