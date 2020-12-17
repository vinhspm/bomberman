package main.java.utils;

import java.io.Serializable;

public class GridPosition implements Serializable {
    static final double EPS = (double) 1e-2;

    private double x;
    private double y;

    public GridPosition(double x, double y) {
        this.x = x;
        this.y = y;

        if (isLatticePoint()) {
            this.x = Math.round(this.x);
            this.y = Math.round(this.y);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GridPosition)) {
            return false;
        }

        GridPosition v = (GridPosition)obj;
        return Math.abs(x - v.x) <= EPS && Math.abs(y - v.y) <= EPS;
    }

    @Override
    public int hashCode() {
        return (int)(x + y) % 100003;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public GridPosition step(Direction dir, double speed) {
        GridPosition newPosition;

        switch (dir) {
            case WEST:
                newPosition = new GridPosition(this.getX() - speed, this.getY());
                if (Math.ceil(newPosition.getX()) < x) {
                    newPosition.setX(Math.ceil(newPosition.getX()));
                }
                break;
            case NORTH:
                newPosition = new GridPosition(this.getX(), this.getY() - speed);
                if (Math.ceil(newPosition.getY()) < y) {
                    newPosition.setY(Math.ceil(newPosition.getY()));
                }
                break;
            case EAST:
                newPosition = new GridPosition(this.getX() + speed, this.getY());
                if (Math.floor(newPosition.getX()) > x) {
                    newPosition.setX(Math.floor(newPosition.getX()));
                }
                break;
            case SOUTH:
                newPosition = new GridPosition(this.getX(), this.getY() + speed);
                if (Math.floor(newPosition.getY()) > y) {
                    newPosition.setY(Math.floor(newPosition.getY()));
                }
                break;
            case STOP:
                newPosition = new GridPosition(this.getX(), this.getY());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dir);
        }

        return newPosition;
    }

    public double distance(GridPosition p) {
        return (double) Math.sqrt((x - p.getX()) * (x - p.getX()) + (y - p.getY()) * (y - p.getY()));
    }

    public boolean isLatticePoint() {
        return Math.abs(x - Math.round(x)) <= EPS && Math.abs(y - Math.round(y)) <= EPS;
    }

    @Override
    public String toString() {
        return "GridPosition{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
