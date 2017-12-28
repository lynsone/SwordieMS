package client.field;

import util.Point;

import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 12/14/2017.
 */
public class Field {
    private Rectangle rect;
    private int id;
    private long uniqueId;
    private Set<Portal> portals;
    private Set<Foothold> footholds;

    public Field(int fieldID, long uniqueId) {
        this.id = fieldID;
        this.uniqueId = uniqueId;
        this.rect = new Rectangle(800,600);
        this.portals = new HashSet<>();
        this.footholds = new HashSet<>();
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

    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Set<Portal> getPortals() {
        return portals;
    }

    public void setPortals(Set<Portal> portals) {
        this.portals = portals;
    }

    public void addPortal(Portal portal) {
        getPortals().add(portal);
    }

    public Portal getPortalByName(String name) {
        return getPortals().stream().filter(portal -> portal.getName().equals(name)).findAny().orElse(null);
    }

    public Foothold findFootHoldBelow(Point pos) {
        Set<Foothold> footholds = getFootholds().stream().filter(fh -> fh.getX1() <= pos.getX() && fh.getX2() >= pos.getX()).collect(Collectors.toSet());
        Foothold res = null;
        int lastY = Integer.MIN_VALUE;
        for(Foothold fh : footholds) {
            if(res == null) {
                res = fh;
            } else {
                // interpolate between the two foothold ends for the y value below pos.x
                int x1 = fh.getX1();
                int x2 = fh.getX2() - x1;
                int x = pos.getX() - x1;
                double perc = (double) x / (double) x2;
                System.out.println("Percentage: " + perc);
                int y = (int) (fh.getY1() + (perc * (fh.getY1() - fh.getY2())));
                if(y > lastY && y <= pos.getY()) {
                    res = fh;
                    lastY = y;
                }
            }
        }
        return res;
    }

    public Set<Foothold> getFootholds() {
        return footholds;
    }

    public void setFootholds(Set<Foothold> footholds) {
        this.footholds = footholds;
    }

    public void addFoothold(Foothold foothold) {
        getFootholds().add(foothold);
    }
}
