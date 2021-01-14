public abstract class Entity {
    private Coordinates coordinates;

    public Entity(int x, int y) {
        this.coordinates = new Coordinates(x, y);
    }

    public Coordinates coordinates() {
        return this.coordinates;
    }

    public int getX() {
        return this.coordinates.getX();
    }

    public int getY() {
        return this.coordinates.getY();
    }

    public void setX(int x) {
        this.coordinates.setX(x);
    }

    public void setY(int y) {
        this.coordinates.setY(y);
    }

    public void move(Character direction) {
        if(direction == 'w')
            this.moveUp();
        else if(direction == 'a')
            this.moveLeft();
        else if(direction == 's')
            this.moveDown();
        else if(direction == 'd')
            this.moveRight();
    }

    private void moveUp() {
        this.setY(this.getY() - 1);
    }

    private void moveDown() {
        this.setY(this.getY() + 1);
    }

    private void moveLeft() {
        this.setX(this.getX() - 1);
    }

    private void moveRight() {
        this.setX(this.getX() + 1);
    }
}