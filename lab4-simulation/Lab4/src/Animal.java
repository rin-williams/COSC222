public class Animal {
    int x;
    int y;
    String name;
    boolean hasMoved;

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}

class Wolf extends Animal {
    public Wolf(int x, int y, boolean hasMoved) {
        setX(x);
        setY(y);
        this.hasMoved = hasMoved;
        name = "WOLF";
    }
}

class Deer extends Animal {
    public Deer(int x, int y, boolean hasMoved) {
        setX(x);
        setY(y);
        this.hasMoved = hasMoved;
        name = "DEER";
    }

}

class Empty extends Animal {
    public Empty(int x, int y, boolean hasMoved) {
        setX(x);
        setY(y);
        this.hasMoved = hasMoved;
        name = "    ";
    }
}
