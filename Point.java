public class Point {
    private Object occupant;
    private Coordinates coordinates;

    public Point(int x, int y, Object occupant) {
        this.coordinates = new Coordinates(x, y);
        this.occupant = occupant;
    }

    public Coordinates coordinates() {
        return this.coordinates;
    }

    public Object occupant() {
        return this.occupant;
    }

    public void setOccupant(Object occupant) {
        this.occupant = occupant;
    }

    public String toString() {
        if(this.occupant instanceof Player)
            return "@";
        else if(this.occupant instanceof Vampire)
            return "v";
        else
            return "·";
    }
}