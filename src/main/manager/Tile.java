package manager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Tile {

    private BufferedImage image;
    private boolean collision;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        collision = false;
    }

    public void setCollection(ArrayList<BufferedImage> images) {
        int min = 0;
        int max = images.size() - 1;

        int ranint = new Random().nextInt(max - min + 1) + min;
        this.image = images.get(ranint);
        collision = false;
    }


    public void setSolid() {
        collision = true;
    }

    public boolean getCollision() {
        return collision;
    }
}
