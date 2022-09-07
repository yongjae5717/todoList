package project.todoList.after.repository;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderTodoListSearch {
    private String memberName;
    private LocalDateTime orderDate;
}
