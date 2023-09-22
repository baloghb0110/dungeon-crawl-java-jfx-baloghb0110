package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Cica;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.data.artifacts.Key;
import com.codecool.dungeoncrawl.data.artifacts.Shield;
import com.codecool.dungeoncrawl.data.customitems.Life;
import com.codecool.dungeoncrawl.data.customitems.Portal;
import com.codecool.dungeoncrawl.data.artifacts.Sword;
import com.codecool.dungeoncrawl.data.specialities.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String mapName) {
        InputStream is = MapLoader.class.getResourceAsStream(mapName);
        assert is != null;
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            new Cica(cell);
                            break;
                        case 'f':
                            cell.setType(CellType.WALL);
                            new Fire(cell);
                            break;
                        case 'l':
                            cell.setType(CellType.FLOOR);
                            new Life(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'd':
                            cell.setType(CellType.FLOOR);
                            new Shield(cell);
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            new Portal(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new Water(cell);
                            break;
                        case 'W':
                            cell.setType(CellType.WALL);
                            new WaterGate(cell);
                            break;
                        case 'g':
                            cell.setType(CellType.WALL);
                            new Gate(cell);
                            break;
                        case '1':
                            cell.setType(CellType.WALL);
                            new WaterWall(cell);
                            break;
                        case '2':
                            cell.setType(CellType.WALL);
                            new WaterSwitcher(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
