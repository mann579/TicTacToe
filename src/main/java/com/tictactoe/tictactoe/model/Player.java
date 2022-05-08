package com.tictactoe.tictactoe.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "players")
public class Player {

    public Player() {
        this.name = "Player";
    }

    public Player(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private final String name;
}

