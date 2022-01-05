package model;

import ui.GamePanel;

import java.util.ArrayList;

public class ObjectSetter {
    private MainGame game;
    private ArrayList<Consumables> consumables;

    public ObjectSetter(MainGame game) {
        this.game = game;
        consumables = new ArrayList<Consumables>();
    }

    public void setObject() {
        consumables.add(new Treasure(18  * MainGame.TILE_SIZE, 46 * MainGame.TILE_SIZE));
        consumables.add(new Treasure(7 * MainGame.TILE_SIZE, 44 * MainGame.TILE_SIZE));
        consumables.add(new Treasure(33 * MainGame.TILE_SIZE, 3 * MainGame.TILE_SIZE));
        consumables.add(new Boots(5 * MainGame.TILE_SIZE, 5 * MainGame.TILE_SIZE));
        game.setObjs(consumables);
    }
}
