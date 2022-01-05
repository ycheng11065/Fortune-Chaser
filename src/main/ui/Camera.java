//package ui;
//
//import manager.Handler;
//import manager.Tile;
//import model.Player;
//
//public class Camera {
//    private Handler handler;
//    private int xoffSet;
//    private int yoffSet;
//
//    public Camera(Handler handler, int xoffset, int yoffset) {
//        this.handler = handler;
//        this.xoffSet = xoffset;
//        this.yoffSet = yoffset;
//    }
//
//    public void checkSpace() {
//        if (xoffSet < 0) {
//            xoffSet = 0;
//        } else if (xoffSet > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
//            xoffSet = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
//        }
//
//        if (yoffSet < 0) {
//            yoffSet = 0;
//        } else if (yoffSet > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
//            yoffSet = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
//        }
//
//    }
//
//    public void center(Player player) {
//        xoffSet = player.getXcoord() - handler.getWidth() / 2 + player.SIZEX / 2;
//        yoffSet = player.getYcoord() - handler.getHeight() / 2 + player.SIZEY / 2;
//        checkSpace();
//    }
//
//    public void move(int x, int y) {
//        xoffSet += x;
//        yoffSet += y;
//    }
//
//    public int getXoffSet() {
//        return xoffSet;
//    }
//
//    public int getYoffSet() {
//        return yoffSet;
//    }
//
//    public void setXoffSet(int xoffSet) {
//        this.xoffSet = xoffSet;
//    }
//
//    public void setYoffSet(int yoffSet) {
//        this.yoffSet = yoffSet;
//    }
//}
