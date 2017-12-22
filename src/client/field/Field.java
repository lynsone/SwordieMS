package client.field;

import java.awt.*;

/**
 * Created on 12/14/2017.
 */
public class Field {
    private Rectangle rect;
    private int id;

    public Field(int fieldID) {
        this.id = fieldID;
        this.rect = new Rectangle(800,600);
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public int getWidth() {
        return (int) getRect().getWidth();
    }

    public int getHeight() {
        return (int) getRect().getHeight();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
