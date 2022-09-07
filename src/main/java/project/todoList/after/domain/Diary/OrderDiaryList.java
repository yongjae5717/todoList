package project.todoList.after.domain.Diary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import project.todoList.after.domain.Member.Member;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "orderDiaryLists")
@Getter
@Setter
public class OrderDiaryList {
    @Id
    @Column(name = "order_diary_list_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_item_id")
    private DiaryItem diaryItem;

    @JsonIgnore
    @OneToMany(mappedBy = "orderDiaryList", cascade = CascadeType.ALL)
    private List<DiaryListToItem> diaryListToItems = new ArrayList<>();

    private LocalDateTime orderDate;


    //==연관관계 편의 메서드==//
    public void setMember(Member member){
        this.member = member;
        member.getOrderDiaryLists().add(this);
    }

    public void addOrderDiaryItem(DiaryListToItem diaryListToItem){
        diaryListToItems.add(diaryListToItem);
        diaryListToItem.setOrderDiaryList(this);
    }


    //==생성 메서드==//
    public static OrderDiaryList createDiaryOrder(Member member, DiaryListToItem... diaryListToItems){
        //order생성
        OrderDiaryList order = new OrderDiaryList();
        //order세팅
        order.setMember(member);
        for(DiaryListToItem diaryListToItem: diaryListToItems){
            order.addOrderDiaryItem(diaryListToItem);
        }
        order.setOrderDate(LocalDateTime.now());
        return order;
    }
}
