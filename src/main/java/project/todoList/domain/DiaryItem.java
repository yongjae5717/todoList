package project.todoList.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class DiaryItem {
    @Id
    @Column(name = "diary_item_id", nullable = false)
    private Long id;

    private String content;

    private void change_content(String changing_content){
        this.content = changing_content;
    }
}
