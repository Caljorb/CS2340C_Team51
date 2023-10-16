package com.example.team_51.model;

import android.view.KeyEvent;

public interface MovementStrategy {
    abstract boolean moveOnPress(int keyCode, KeyEvent keyEvent); // use update pos method in player
    // use switch, take in keyCode, use case KeyEvent.KEYCODE_DPAD_XXXX
    // LEFT, RIGHT, UP, DOWN, add to player X or Y
    // at end, call check collision
    // can also call checkOutOfBounds
    // return true
}
