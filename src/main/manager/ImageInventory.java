package manager;

import java.awt.image.BufferedImage;

public class ImageInventory {
    private static final int width = 30;
    private static final int height = 30;

    private static BufferedImage[] player_down_still;
    private static BufferedImage[] player_right_still;
    private static BufferedImage[] player_left_still;
    private static BufferedImage[] player_up_still;

    private static BufferedImage[] player_down;
    private static BufferedImage[] player_up;
    private static BufferedImage[] player_left;
    private static BufferedImage[] player_right;

    private static BufferedImage[] npcGuide_down_still;
    private static BufferedImage[] npcGuide_right_still;
    private static BufferedImage[] npcGuide_left_still;
    private static BufferedImage[] npcGuide_up_still;

    private static BufferedImage[] npcGuide_down;
    private static BufferedImage[] npcGuide_up;
    private static BufferedImage[] npcGuide_left;
    private static BufferedImage[] npcGuide_right;

    private static BufferedImage lightGrass;
    private static BufferedImage darkGrass;
    private static BufferedImage sand;
    private static BufferedImage wall;
    private static BufferedImage rock;
    private static BufferedImage tree;
    private static BufferedImage treeBunch;
    private static BufferedImage water;
    private static BufferedImage cookie;
    private static BufferedImage boots;

    private static BufferedImage grass1;
    private static BufferedImage grass2;
    private static BufferedImage grass3;
    private static BufferedImage grass4;
    private static BufferedImage grass5;
    private static BufferedImage grass6;
    private static BufferedImage grass7;
    private static BufferedImage grass8;
    private static BufferedImage grass9;
    private static BufferedImage grass10;
    private static BufferedImage grass11;
    private static BufferedImage grass12;
    private static BufferedImage grass13;
    private static BufferedImage grass14;
    private static BufferedImage grass15;


    public static void init() {
        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("/sprites/tilegame.png"));
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/sprites/forest.png"));
        SpriteSheet sheet3 = new SpriteSheet(ImageLoader.loadImage("/sprites/water.png"));
        SpriteSheet sheet4 = new SpriteSheet(ImageLoader.loadImage("/sprites/fortuneCookieGreen.png"));
        SpriteSheet sheet5 = new SpriteSheet(ImageLoader.loadImage("/sprites/boots.png"));

        SpriteSheet grassSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/grassTiles.png"));
        SpriteSheet treeSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/treeSprite.png"));

        player_down_still = new BufferedImage[1];
        player_up_still = new BufferedImage[1];
        player_right_still = new BufferedImage[1];
        player_left_still = new BufferedImage[1];

        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_right = new BufferedImage[2];
        player_left = new BufferedImage[2];

        npcGuide_down_still = new BufferedImage[1];
        npcGuide_up_still = new BufferedImage[1];
        npcGuide_right_still = new BufferedImage[1];
        npcGuide_left_still = new BufferedImage[1];

        npcGuide_down = new BufferedImage[2];
        npcGuide_up = new BufferedImage[2];
        npcGuide_right = new BufferedImage[2];
        npcGuide_left = new BufferedImage[2];

        //NPC guy
        player_down_still[0] = sheet1.crop(3, 256, width, 32);
        player_right_still[0] = sheet1.crop(3, 320, width, 32);
        player_left_still[0] = sheet1.crop(3, 288, width, 32);
        player_up_still[0] = sheet1.crop(3, 352, width, 32);

        player_down[0] = sheet1.crop(35, 256, width, 32);
        player_down[1] = sheet1.crop(67, 256, width, 32);

        player_up[0] = sheet1.crop(37, 352, width, 32);
        player_up[1] = sheet1.crop(69, 352, width, 32);

        player_left[0] = sheet1.crop(35, 288, width, 32);
        player_left[1] = sheet1.crop(67, 288, width, 32);

        player_right[0] = sheet1.crop(35, 320, width, 32);
        player_right[1] = sheet1.crop(65, 320, width, 32);

        // NPC girl
        npcGuide_down_still[0] = sheet1.crop(94, 256, width, 32);
        npcGuide_up_still[0] = sheet1.crop(94, 352, width, 32);
        npcGuide_right_still[0] = sheet1.crop(94, 320, width, 32);
        npcGuide_left_still[0] = sheet1.crop(94, 288, width, 32);

        npcGuide_down[0] = sheet1.crop(126, 256, width, 32);
        npcGuide_down[1] = sheet1.crop(158, 256, width, 32);

        npcGuide_up[0] = sheet1.crop(126, 352, width, 32);
        npcGuide_up[1] = sheet1.crop(158, 352, width, 32);

        npcGuide_left[0] = sheet1.crop(126, 288, width, 32);
        npcGuide_left[1] = sheet1.crop(158, 288, width, 32);

        npcGuide_right[0] = sheet1.crop(126, 320, width, 32);
        npcGuide_right[1] = sheet1.crop(158, 320, width, 32);

        lightGrass = grassSheet.crop(96, 0, 32, 32);
        darkGrass = grassSheet.crop(32, 0, 32, 32);

        sand = sheet1.crop(457, 100, 50, 50);
        wall = sheet1.crop(354, 2, width, height);

//        rock = sheet2.crop(0, 16, 16, 16);
        rock = grassSheet.crop(64, 32, 32, 32);
//        tree = sheet2.crop(8, 0, 16, 16);
        tree = treeSheet.crop(160, 0, 32, 32);
        treeBunch = sheet2.crop(24, 0, 16, 16);
        water = sheet3.crop(50, 108, 35, 35);
        cookie = sheet4.crop(300, 10, 250, 250);
        boots = sheet5.crop(0, 0, 1184, 1184);

        grass1 = grassSheet.crop(0, 0, 32, 32);
        grass2 = grassSheet.crop(32, 0, 32, 32);
        grass3 = grassSheet.crop(64, 0, 32, 32);
        grass4 = grassSheet.crop(96, 0, 32, 32);
        grass5 = grassSheet.crop(128, 0, 32, 32);
        grass6 = grassSheet.crop(0, 32, 32, 32);
        grass7 = grassSheet.crop(32, 32, 32, 32);


        grass8 = grassSheet.crop(64, 32, 32, 32);
        grass9 = grassSheet.crop(96, 32, 32, 32);
        grass10 = grassSheet.crop(128, 32, 32, 32);
        grass11 = grassSheet.crop(0, 64, 32, 32);
        grass12 = grassSheet.crop(32, 64, 32, 32);
        grass13 = grassSheet.crop(64, 64, 32, 32);
        grass14 = grassSheet.crop(96, 64, 32, 32);
        grass15 = grassSheet.crop(128, 64, 32, 32);

    }

    public static BufferedImage[] getPlayer_down_still() {
        return player_down_still;
    }

    public static BufferedImage[] getPlayer_right_still() {
        return player_right_still;
    }

    public static BufferedImage[] getPlayer_left_still() {
        return player_left_still;
    }

    public static BufferedImage[] getPlayer_up_still() {
        return player_up_still;
    }

    public static BufferedImage[] getPlayerDown() {
        return player_down;
    }

    public static BufferedImage[] getPlayerUp() {
        return player_up;
    }

    public static BufferedImage[] getPlayerLeft() {
        return player_left;
    }

    public static BufferedImage[] getPlayerRight() {
        return player_right;
    }

    public static BufferedImage getLightGrass() {
        return lightGrass;
    }

    public static BufferedImage getSand() {
        return sand;
    }

    public static BufferedImage getWall() {
        return wall;
    }

    public static BufferedImage getRock() {
        return rock;
    }

    public static BufferedImage getTree() {
        return tree;
    }

    public static BufferedImage getWater() {
        return water;
    }

    public static BufferedImage getDarkGrass() {
        return darkGrass;
    }

    public static BufferedImage getCookie() {
        return cookie;
    }

    public static BufferedImage getTreeBunch() {
        return treeBunch;
    }

    public static BufferedImage getBoots() {
        return boots;
    }

    public static BufferedImage getGrass1() {
        return grass1;
    }

    public static BufferedImage getGrass2() {
        return grass2;
    }

    public static BufferedImage getGrass3() {
        return grass3;
    }

    public static BufferedImage getGrass4() {
        return grass4;
    }

    public static BufferedImage getGrass5() {
        return grass5;
    }

    public static BufferedImage getGrass6() {
        return grass6;
    }

    public static BufferedImage getGrass7() {
        return grass7;
    }

    public static BufferedImage getGrass8() {
        return grass8;
    }

    public static BufferedImage getGrass9() {
        return grass9;
    }

    public static BufferedImage getGrass10() {
        return grass10;
    }

    public static BufferedImage getGrass11() {
        return grass11;
    }

    public static BufferedImage getGrass12() {
        return grass12;
    }

    public static BufferedImage getGrass13() {
        return grass13;
    }

    public static BufferedImage getGrass14() {
        return grass14;
    }

    public static BufferedImage getGrass15() {
        return grass15;
    }

    public static BufferedImage[] getNpcGuide_down_still() {
        return npcGuide_down_still;
    }

    public static BufferedImage[] getNpcGuide_right_still() {
        return npcGuide_right_still;
    }

    public static BufferedImage[] getNpcGuide_left_still() {
        return npcGuide_left_still;
    }

    public static BufferedImage[] getNpcGuide_up_still() {
        return npcGuide_up_still;
    }

    public static BufferedImage[] getNpcGuide_down() {
        return npcGuide_down;
    }

    public static BufferedImage[] getNpcGuide_up() {
        return npcGuide_up;
    }

    public static BufferedImage[] getNpcGuide_left() {
        return npcGuide_left;
    }

    public static BufferedImage[] getNpcGuide_right() {
        return npcGuide_right;
    }
}
