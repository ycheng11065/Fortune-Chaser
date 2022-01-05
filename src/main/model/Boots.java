package model;

import manager.ImageInventory;

public class Boots extends Consumables{

    public Boots(int x, int y) {
        super(x, y);
        this.image = ImageInventory.getBoots();
        this.name = "Boots";
    }


}
