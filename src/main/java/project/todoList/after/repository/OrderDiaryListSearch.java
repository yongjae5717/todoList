package project.todoList.after.repository;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDiaryListSearch {
    private String memberName; //회원 이름
    private LocalDateTime orderDate; //주문 시간
}
