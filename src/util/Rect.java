package util;

import connection.OutPacket;

/**
 * Created on 1/5/2018.
 */
public class Rect {
    private int left, top, right, bottom;

    public Rect() {
    }

    public Rect(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public Rect(Position lt, Position rb) {
        this.left = lt.getX();
        this.top = lt.getY();
        this.right = rb.getX();
        this.bottom = rb.getY();
    }

    /**
     * Top left x coord
     * @return
     */
    public int getLeft() {
        return left;
    }

    /**
     * Top left x coord
     * @param left
     */
    public void setLeft(int left) {
        this.left = left;
    }

    /**
     * Top left y coord
     * @return
     */
    public int getTop() {
        return top;
    }

    /**
     * Top left y coord
     * @param top
     */
    public void setTop(int top) {
        this.top = top;
    }

    /**
     * Bottom right x coord
     * @return
     */
    public int getRight() {
        return right;
    }

    /**
     * Bottom right x coord
     * @param right
     */
    public void setRight(int right) {
        this.right = right;
    }

    /**
     * Bottom right y coord
     * @return
     */
    public int getBottom() {
        return bottom;
    }

    /**
     * Bottom right y coord
     * @param bottom
     */
    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getLeft());
        outPacket.encodeInt(getTop());
        outPacket.encodeInt(getRight());
        outPacket.encodeInt(getBottom());
    }
}
