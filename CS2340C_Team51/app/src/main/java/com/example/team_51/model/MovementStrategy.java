package com.example.team_51.model;

public interface MovementStrategy {
    abstract boolean moveLeft(); // take in keyboard press?
    abstract boolean moveRight();
    abstract boolean moveUp();
    abstract boolean moveDown();
}
