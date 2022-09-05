package project.todoList.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class DiaryItem {
    @Id
    @Column(name = "diary_item_id", nullable = false)
    private Long id;

    private String content;
    private LocalDateTime updateDate;

    private LocalDateTime createDate;

    //==비지니스 로직 추가==//
    public void changeContent(String changing_content){
        this.content = changing_content;
        this.updateDate = LocalDateTime.now();
    }
}