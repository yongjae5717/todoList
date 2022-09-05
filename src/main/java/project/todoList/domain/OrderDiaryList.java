package project.todoList.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDiaryList {
    @Id
    @Column(name = "order_diary_list_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_item_id")
    private DiaryItem diaryItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private String diaryContent;

    //==생성 메서드==//
    public static OrderDiaryList createOrderDiary(DiaryItem diaryItem, String content){
        OrderDiaryList orderDiaryList = new OrderDiaryList();
        orderDiaryList.setDiaryItem(diaryItem);
        orderDiaryList.setDiaryContent(content);

        return orderDiaryList;
    }

    //==비지니스 로직 추가==//
    public void changeContent(String content) {
        getDiaryItem().changeContent(content);
    }


}
