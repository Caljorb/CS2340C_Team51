package com.example.team_51.model.enemies;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.team_51.model.Circle;
import com.example.team_51.model.MoveBall;
import com.example.team_51.model.MoveSubscriber;
import com.example.team_51.model.MovementStrategy;
import com.example.team_51.model.Sprite;
import com.example.team_51.model.map.Tilemap;
import com.example.team_51.viewmodels.GameDisplay;
import com.example.team_51.viewmodels.SpriteSheet;

public interface Enemy {
    void spawn();
}
