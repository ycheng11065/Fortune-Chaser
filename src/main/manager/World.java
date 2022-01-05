package manager;

import model.MainGame;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class World {
    private MainGame game;
    private int[][] mapTileNum;
    private Tile[] tileImage;

    public World(MainGame game, String path) {
        this.game = game;
        tileImage = new Tile[200];
        mapTileNum = new int[game.MAXWORLDCOL][game.MAXWORLDROW];
        getTileImage();
        loadWorld(path);
    }

    public void getTileImage() {
        Tile tile1 = new Tile();
        tile1.setImage(ImageInventory.getLightGrass());
        tileImage[0] = tile1;

        Tile tile2 = new Tile();
        tile2.setImage(ImageInventory.getSand());
        tileImage[1] = tile2;

        Tile tile3 = new Tile();
        tile3.setImage(ImageInventory.getWall());
        tile3.setSolid();
        tileImage[2] = tile3;

        Tile tile4 = new Tile();
        tile4.setImage(ImageInventory.getRock());
        tile4.setSolid();
        tileImage[3] = tile4;

        Tile tile5 = new Tile();
        tile5.setImage(ImageInventory.getWater());
        tile5.setSolid();
        tileImage[4] = tile5;

        Tile tile6 = new Tile();
        tile6.setImage(ImageInventory.getTree());
        tile6.setSolid();
        tileImage[5] = tile6;

        Tile tile7 = new Tile();
        tile7.setImage(ImageInventory.getDarkGrass());
        tileImage[6] = tile7;

        Tile tile8 = new Tile();
        tile8.setImage(ImageInventory.getTreeBunch());
        tileImage[7] = tile8;


        // Grass Tiles
        Tile tile9 = new Tile();
        tile9.setImage(ImageInventory.getGrass1());
        tileImage[8] = tile9;

        Tile tile11 = new Tile();
        tile11.setImage(ImageInventory.getGrass3());
        tileImage[9] = tile11;

        Tile tile12 = new Tile();
        tile12.setImage(ImageInventory.getGrass4());
        tileImage[10] = tile12;

        Tile tile13 = new Tile();
        tile13.setImage(ImageInventory.getGrass5());
        tileImage[11] = tile13;

        Tile tile14 = new Tile();
        tile14.setImage(ImageInventory.getGrass6());
        tileImage[12] = tile14;

        Tile tile15 = new Tile();
        tile15.setImage(ImageInventory.getGrass5());
        tileImage[13] = tile13;

        Tile tile16 = new Tile();
        tile15.setImage(ImageInventory.getGrass5());
        tileImage[14] = tile13;

        Tile tile17 = new Tile();
        tile15.setImage(ImageInventory.getGrass5());
        tileImage[15] = tile13;

    }

    private void loadWorld(String path) {

        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < game.MAXWORLDCOL && row < game.MAXWORLDROW) {
                String line = br.readLine();  // Read single line of the txt file

                while (col < game.MAXWORLDCOL) {
                    String numbers[]  = line.split(" ");  // Split string at each space then store in array

                    int num = Integer.parseInt(numbers[col]); // Change all the string into int using column as index

                    mapTileNum[col][row] = num; // Store extracted num in 2d array
                    col++;
                }
                if (col == game.MAXWORLDCOL) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: Fills screen with tile based on col/row position
    public void render(Graphics g) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < game.MAXWORLDCOL && worldRow < game.MAXWORLDROW) {

            int tileNum = mapTileNum[worldCol][worldRow]; // Extract tile num store in the 2d array

            int worldX = worldCol * game.TILE_SIZE;  // x coord on map
            int worldY = worldRow * game.TILE_SIZE;  // y coord on map
            int screenX = worldX - game.getPlayer().getWorldX() + game.getScreenx();
            int screenY = worldY - game.getPlayer().getWorldY() + game.getScreeny();

            // Create boundary within player screen
            if (worldX + game.TILE_SIZE > game.getPlayer().getWorldX() - game.getScreenx() &&
                    worldX - game.TILE_SIZE < game.getPlayer().getWorldX() + game.getScreenx() &&
                    worldY + game.TILE_SIZE > game.getPlayer().getWorldY() - game.getScreeny() &&
                    worldY - game.TILE_SIZE < game.getPlayer().getWorldY() + game.getScreeny()) {

                g.drawImage(tileImage[tileNum].getImage(), screenX
                        , screenY, game.TILE_SIZE, game.TILE_SIZE, null);
            }

            worldCol++;
            if (worldCol == game.MAXWORLDCOL) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public int getTileNum(int col, int row) {
        return mapTileNum[col][row];
    }

    public Tile getTile(int tileNum) {
        return tileImage[tileNum];
    }
}
