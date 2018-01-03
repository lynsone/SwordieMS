package util;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(){
        x = 0;
        y = 0;
    }

    @Override
    public String toString() {
        return String.format("x: %d, y: %d", getX(), getY());
    }

    public Position deepCopy() {
        return new Position(getX(), getY());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
