package project.todoList.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public Member(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }
}
