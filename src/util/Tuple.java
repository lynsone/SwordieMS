package util;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created on 1/1/2018.
 */
public class Tuple<L, R> {
    private L left;
    private R right;

    public Tuple(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    public R getRight() {
        return right;
    }

    public void setRight(R right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Tuple)) {
            return false;
        }
        Tuple tuple = (Tuple) obj;
        return tuple.getLeft().equals(left) && tuple.getRight().equals(right);
    }
}
