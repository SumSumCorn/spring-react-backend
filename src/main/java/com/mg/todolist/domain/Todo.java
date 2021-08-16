package com.mg.todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean completed;

    @Column(nullable = false)
    private String todoName;


    public void init(String name) {
        setTodoName(name);
        setCompleted(false);
    }

}
