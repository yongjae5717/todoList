package project.todoList.after.domain.TodoList;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TodoItem {
    @Id
    @Column(name = "todo_item_id", nullable = false)
    private Long id;
    private String content;
    private LocalDateTime updateDate;
    private LocalDateTime createDate;


    //==생성자==//
    public TodoItem() {
    }

    //==비지니스 로직 ==//
    //수정 로직
    public void changeContent(String newContent){
        this.content = newContent;
        this.updateDate = LocalDateTime.now();
    }
}
