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

    private String name;

    private LocalDateTime updateDate;

    private LocalDateTime createDate;

    private String content;

    //==생성자==//
    public TodoItem() {
    }

    public TodoItem(String name, LocalDateTime updateDate, LocalDateTime createDate, String content) {
        this.name = name;
        this.updateDate = updateDate;
        this.createDate = createDate;
        this.content = content;
    }

    //==비지니스 로직 추가==//


    //==비지니스 로직 ==//
    //수정 로직
    public void change(String newName, String newContent){
        this.name = newName;
        this.content = newContent;
        this.updateDate = LocalDateTime.now();
    }
}
