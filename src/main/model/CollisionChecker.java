package model;

public class CollisionChecker {
    private MainGame game;

    public CollisionChecker(MainGame game) {
        this.game = game;
    }

    public void checkCollision (Player player) {
        int leftWorldX = player.getWorldX() + player.getSolidArea().x;
        int rightWorldX = player.getWorldX() + player.getSolidArea().x + player.getSolidArea().width;
        int topWorldY = player.getWorldY() + player.getSolidArea().y;
        int bottomWorldY = player.getWorldY() + player.getSolidArea().y + player.getSolidArea().height;

        int leftCol = leftWorldX/game.TILE_SIZE;
        int rightCol = rightWorldX/game.TILE_SIZE;
        int topRow = topWorldY/game.TILE_SIZE;
        int botRow = bottomWorldY/game.TILE_SIZE;

        int tileNum1;
        int tileNum2;

        switch (player.getDir()) {
            case "up":
                topRow = (topWorldY - player.getSpeed()) / game.TILE_SIZE;
                tileNum1 = game.getWr().getTileNum(leftCol, topRow);
                tileNum2 = game.getWr().getTileNum(rightCol, topRow);
                if (game.getWr().getTile(tileNum1).getCollision() || game.getWr().getTile(tileNum2).getCollision()) {
                    player.setCollisonOn(true);
                }
                break;

            case "down":
                botRow = (bottomWorldY + player.getSpeed()) / game.TILE_SIZE;
                tileNum1 = game.getWr().getTileNum(leftCol, botRow);
                tileNum2 = game.getWr().getTileNum(rightCol, botRow);
                if (game.getWr().getTile(tileNum1).getCollision() || game.getWr().getTile(tileNum2).getCollision()) {
                    player.setCollisonOn(true);
                }
                break;

            case "left":
                leftCol = (leftWorldX - player.getSpeed()) / game.TILE_SIZE;
                tileNum1 = game.getWr().getTileNum(leftCol, topRow);
                tileNum2 = game.getWr().getTileNum(leftCol, botRow);
                if (game.getWr().getTile(tileNum1).getCollision() || game.getWr().getTile(tileNum2).getCollision()) {
                    player.setCollisonOn(true);
                }
                break;

            case "right":
                rightCol = (rightWorldX + player.getSpeed()) / game.TILE_SIZE;
                tileNum1 = game.getWr().getTileNum(rightCol, topRow);
                tileNum2 = game.getWr().getTileNum(rightCol, botRow);
                if (game.getWr().getTile(tileNum1).getCollision() || game.getWr().getTile(tileNum2).getCollision()) {
                    player.setCollisonOn(true);
                }
                break;
        }
    }
}
