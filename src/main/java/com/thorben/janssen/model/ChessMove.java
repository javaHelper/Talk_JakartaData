package com.thorben.janssen.model;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
public class ChessMove {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int moveNumber;

    private MoveColor color;

    private String move;

    @ManyToOne
    private ChessGame game;

    @Version
    private int version;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }

    public MoveColor getColor() { return color; }

    public void setColor(MoveColor color) { this.color = color; }

    public ChessGame getGame() {
        return game;
    }

    public void setGame(ChessGame game) {
        this.game = game;
    }

    public int getVersion() {
        return version;
    }
}
