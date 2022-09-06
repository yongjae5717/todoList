package project.todoList.after.domain.Diary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryListToItem {
    @Id
    @Column(name = "diary_list_to_item_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_item_id")
    private DiaryItem diaryItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_diary_list")
    private OrderDiaryList orderDiaryList;

    private String diaryContent;

    //==생성 메서드==//
    public static DiaryListToItem createOrderDiary(DiaryItem diaryItem, String content){
        DiaryListToItem diaryListToItem = new DiaryListToItem();
        diaryListToItem.setDiaryItem(diaryItem);
        diaryListToItem.setDiaryContent(content);
        return diaryListToItem;
    }

    //==비지니스 로직 추가==//
    public void changeContent(String content) {
        getDiaryItem().changeContent(content);
    }

}
