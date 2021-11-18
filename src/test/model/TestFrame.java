package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TestFrame {
     Frame map;

    @BeforeEach
    public void runBefore() {
        map = new Frame();
    }

    @Test
    public void keyPressedTest() {
        map.keyPressed(KeyEvent.VK_W);
//        assertEquals(map.get);
    }

    @Test
    public void setMsgTest() {

    }


}
