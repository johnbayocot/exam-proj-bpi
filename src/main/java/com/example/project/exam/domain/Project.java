package com.example.project.exam.domain;



import lombok.Data;

import jakarta.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Basic
    private Long id;
    @Basic
    private String projectName;
    @Basic
    private String projectDesc;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
