package project.todoList.after.domain.Member;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import project.todoList.before.domain.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id", nullable = false)
    private Long id;

    private String userName;
    private String userEmail;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
