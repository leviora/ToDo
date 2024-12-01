package com.mazowiecka.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Projects")
@NoArgsConstructor
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long project_id;

    @Column(nullable = false, name = "project_name")
    private String project_name;

    @Column(name = "project_description")
    private String project_description;

    @Column(name = "completed", nullable = false)
    private boolean completed;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
