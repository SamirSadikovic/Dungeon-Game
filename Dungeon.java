import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.HashSet;

public class Dungeon {
    private int length;
    private int height;
    private int moves;
    private Point points[][];
    private List<Vampire> vampires;
    private boolean vampiresMove;
    private Player player;
    private Scanner reader;

    public Dungeon(int length, int height, int numberOfVampires, int moves, boolean vampiresMove) {
        this.length = length;
        this.height = height;
        this.moves = moves;
        this.vampiresMove = vampiresMove;
        this.reader = new Scanner(System.in);
        this.player = new Player();
        this.points = new Point[height][length];
        this.vampires = new ArrayList<Vampire>();
        this.initializeDungeon();
        this.addVampires(numberOfVampires);
    }

    private void initializeDungeon() {
        for(int row = 0; row < this.points.length; row++)
            for(int col = 0; col < this.points[row].length; col++)
                this.points[row][col] = new Point(col, row, new String());
    }

    private void addVampires(int numberOfVampires) {
        for(int i = 0; i < numberOfVampires; i++){
            Random r = new Random();

            int col = r.nextInt(this.length);
            int row = r.nextInt(this.height);

            while(this.points[row][col].occupant() instanceof Entity){
                col = r.nextInt(this.length);
                row = r.nextInt(this.height);
            }

            this.vampires.add(new Vampire(col, row));
        }     
    }

    public void run() {

        while(this.checkState()){
            this.updateUI();
            this.displayDungeon();
            String input = this.reader.nextLine();
            this.execute(input);
        }

    }

    private boolean checkState() {
        if(this.vampires.isEmpty()){
            System.out.println("YOU WIN!");
            return false;
        }
        else if(this.moves == 0){
            System.out.println("YOU LOSE!");
            return false;
        }
        else
            return true;
    }

    private void updateUI() {
        for(int row = 0; row < this.points.length; row++)
            columnLoop:
            for(int col = 0; col < this.points[row].length; col++){

                if(this.player.coordinates().equals(this.points[row][col].coordinates())){
                    this.points[row][col].setOccupant(player);
                    continue columnLoop;
                }

                for(Vampire vampire : this.vampires)
                    if(vampire.coordinates().equals(this.points[row][col].coordinates())){
                        this.points[row][col].setOccupant(vampire);
                        continue columnLoop;
                    }
                
                this.points[row][col].setOccupant(new String());
                
            }
    }

    private void displayDungeon() {
        System.out.println(moves);
        System.out.println();

        System.out.println("@ " + this.player.coordinates());
        for(Vampire vampire : this.vampires)
            System.out.println("v " + vampire.coordinates());

        System.out.println();

        for(int row = 0; row < this.points.length; row++){
            for(int col = 0; col < this.points[row].length; col++)
                System.out.print(this.points[row][col]);
            System.out.println();
        }
        System.out.println("Input: ");
    }

    private void execute(String input) {
        for(Character playerMove : input.toCharArray()){
            if(this.legalMoves(this.player).contains(playerMove))
                this.player.move(playerMove);

            this.destroyVampires();

            if(this.vampiresMove){
                for(Vampire vampire : this.vampires){
                    Character vampireDirection = RandomMovement.get(this.legalMoves(vampire));
                    vampire.move(vampireDirection);
                }
            }

            this.destroyVampires();
        }
        this.moves--;
    }

    private void destroyVampires() {
        this.vampires.removeIf(vampire -> (vampire.coordinates().equals(this.player.coordinates())));
    }

    private HashSet<Character> legalMoves(Entity entity) {
        HashSet<Character> legalMoves = new HashSet<Character>();
        int x = entity.getX();
        int y = entity.getY();

        if(y - 1 >= 0)
            legalMoves.add('w');
        
        if(x - 1 >= 0)
            legalMoves.add('a');
        
        if(y + 1 < this.height)
            legalMoves.add('s');

        if(x + 1 < this.length)
            legalMoves.add('d');
        
        if(entity instanceof Vampire)
            legalMoves = this.vampireLegalMoves(x, y, legalMoves);

        return legalMoves;
    }

    private HashSet<Character> vampireLegalMoves(int x, int y, HashSet<Character> legalMoves) {
        if(legalMoves.contains('w') && !(this.points[y-1][x].occupant() instanceof Vampire))
            legalMoves.add('w');

        if(legalMoves.contains('a') && !(this.points[y][x-1].occupant() instanceof Vampire))
            legalMoves.add('a');

        if(legalMoves.contains('s') && !(this.points[y+1][x].occupant() instanceof Vampire))
            legalMoves.add('s');

        if(legalMoves.contains('d') && !(this.points[y][x+1].occupant() instanceof Vampire))
            legalMoves.add('d');
        
        return legalMoves;
    }
}
