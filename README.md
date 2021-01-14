# Dungeon-Game
A game where the goal is to destroy all vampires in the dungeon.

The player first defines the game variables: size of the dungeon, how many moves he can make, how many vampires are there and will the vampires move.

Then, the dungeon is printed out, along with the remaining number of moves and coordinates of each entity (player and vampires).
Commands W, A, S, D move the player up, left, down, and right respecively.
The player can input as many commands using W, A, S, D as they like. When they press Enter, each of those commands are executed squentially and this counts as 1 move.
Vampires move in a random fashion. They cannot move to a point that is already occupied by another vampire. Neither the player nor the vampires can move outside the dungeon.

When the player touches the vampire (when they have the same coordinates), the vampire is destroyed.
If all the vampires are destroyed, the player wins the game. If the player runs out of moves, they lose the game.
