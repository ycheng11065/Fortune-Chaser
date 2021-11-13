package model;

public class Item {
    private int xcoord;
    private int ycoord;

    public Item(int x, int y) {
        xcoord = x;
        ycoord = y;
    }

    public int getX() {
        return xcoord;
    }

    public int gety() {
        return ycoord;
    }
}
