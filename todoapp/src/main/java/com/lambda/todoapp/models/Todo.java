package com.lambda.todoapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The entity allowing interaction with the Todos table
 */
@Entity
@Table(name = "todos")
public class Todo extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    @Column(nullable = false)
    private String description;

    private boolean completed = false;

    private Date createddate;

    private String createdby;

    /**
     * Creates a join table joining Todos and Users
     * in a Many-To-One relation.
     */
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties(value = "todos")
    private User user;

    public Todo() {
    }

    public Todo(User user, String description) {
        createddate = new Date();
        createdby = user.getUsername();
        this.user = user;
        this.description = description;
    }

    public Todo(User user, String description, boolean completed) {
        createddate = new Date();
        this.user = user;
        this.description = description;
        this.completed = completed;
    }

    public long getTodoid() {
        return todoid;
    }

    public void setTodoid(long todoid) {
        this.todoid = todoid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return ft.format(createddate);
    }


    @Override
    public String toString() {
        return "Todo{" +
                "createdDate=" + getDate() +
                "todoid=" + todoid +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", user=" + user +
                '}';
    }
}
