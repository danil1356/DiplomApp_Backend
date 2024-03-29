package com.example.testsapp.data.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

@Entity
@Table(name = "tests")
@Getter
@Setter
@ToString
public class Tests extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "execution_time",nullable = false)
    private Time execution_time;

    @Column(name = "author", nullable = false)
    private String author;

    @ManyToMany(mappedBy = "testsSet", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Users> usersAccess;
//    @PrePersist
//    public void addUser(){
//        usersAccess.forEach(user -> user.getTestsSet().add(this));
//    }
    @PreRemove
    public void removeUsers(){
        usersAccess.forEach(user -> user.getTestsSet().remove(this));
    }


    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false, referencedColumnName = "id")
    private Themes theme;

    @ManyToOne
    @JoinColumn(name = "subthemes_id", nullable = false, referencedColumnName = "id")
    private SubTheme subTheme;

    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "test")
    private Set<Questions> questionsSet;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "test")
    private Set<Statistics> statisticsSet;

    public Tests(Long id) {
        super(id);
    }

    public Tests() {
    }

    public Tests(Long id, String name, Time execution_time, String author, Themes theme, SubTheme subTheme) {
        super(id);
        this.name = name;
        this.execution_time = execution_time;
        this.author = author;
        this.theme = theme;
        this.subTheme = subTheme;
    }
}
