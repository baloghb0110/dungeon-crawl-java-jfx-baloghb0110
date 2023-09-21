package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(31, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("cica", new Tile(30, 7));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("sword", new Tile(1, 28));
        tileMap.put("portal", new Tile(10, 10));
        tileMap.put("fire", new Tile(15, 10));
        tileMap.put("water", new Tile(12, 4));
        tileMap.put("waterGateClosed", new Tile(0, 9));
        tileMap.put("waterGateOpen", new Tile(2, 9));
        tileMap.put("waterWall", new Tile(2, 11));
        tileMap.put("runningWater", new Tile(8, 4));
        tileMap.put("waterSwitchOn", new Tile(4, 10));
        tileMap.put("waterSwitchOff", new Tile(3, 10));
        tileMap.put("gateOff", new Tile(9, 11));
        tileMap.put("gateOn", new Tile(8, 10));
        tileMap.put("gate", new Tile(9, 11));
        tileMap.put("life", new Tile(23, 23));
        tileMap.put("shield", new Tile(5, 24));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {

        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
