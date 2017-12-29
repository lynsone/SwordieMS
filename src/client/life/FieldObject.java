package client.life;

import util.Point;

public class FieldObject {
    private Point position;
    private int objectId;

    public FieldObject(int objectId) {
        this.objectId = objectId;
        this.position = new Point(0, 0);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
}
