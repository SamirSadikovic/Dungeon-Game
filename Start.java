import java.util.Scanner;

public class Start {
    public static void main(String[]args) {
        Scanner input = new Scanner(System.in);
        int length, height, numberOfVampires, moves;
        boolean vampiresMove;
        String vampiresMoveInput;

        System.out.println("***** DUNGEON GAME *****");
        System.out.println();

        System.out.println("Enter the size of your dungeon below:");
        System.out.print("Length: ");
        length = input.nextInt();
        System.out.print("Height: ");
        height = input.nextInt();

        do{
            System.out.println("How many vampires should the dungeon have?");
            numberOfVampires = input.nextInt();
            if(numberOfVampires > height * length - 1)
                System.out.println("That many vampires cannot fit inside the dungeon. Please enter a smaller number.");

        }while(numberOfVampires > height * length - 1);
        
        System.out.println("How many moves will your player be able to make?");
        moves = input.nextInt();

        do{
            System.out.println("Will your vampires move? (yes/no)");
            vampiresMoveInput = input.next();
            vampiresMoveInput = vampiresMoveInput.toLowerCase();
            if(!vampiresMoveInput.matches("(yes|no)"))
                System.out.println("Invalid input. Please try again.");

        }while(!vampiresMoveInput.matches("(yes|no)"));
        vampiresMove = vampiresMoveInput.equals("yes");

        Dungeon dungeon = new Dungeon(length, height, numberOfVampires, moves, vampiresMove);

        dungeon.run();
    }
}
