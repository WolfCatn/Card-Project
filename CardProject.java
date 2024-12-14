//Project done by Naomi "Nigh" Johnson
//for CTE Software Development Class 2024
//Instructor: Kim Gross

/*
This project is a relpicated card game of poker. 
It starts by having a standard deck of 52 cards shuffled and dealt to five players. 
The player with the best hand wins.
If there is a tie for the best hand, the one with the highest card wins. 
If there is still a tie for that, then there are multiple winners.
*/

//Allows ArrayLists to be used for more flexibility in the list size.
import java.util.ArrayList; 

public class CardProject {
    //This large method returns the winner of the game based on a list of each player's hands.
    static String calculateWinner(ArrayList<String> scores, Dealer theDealer, ArrayList<Player> thePlayers) {
        int highestScore = Integer.parseInt(scores.get(0).substring(0, 1));
        int highIndex = 0;
        //Finding which player has the best hand:
        for(int i = 1; i < scores.size(); i++) { 
            if(Integer.parseInt(scores.get(i).substring(0, 1)) < highestScore) {
                highestScore = Integer.parseInt(scores.get(i).substring(0, 1));
                highIndex = i;
            }
        }
        int counter = 0;
        //Seeing how many players have that same best hand:
        for(int i = 0; i < scores.size(); i++) { 
            if(highestScore == Integer.parseInt(scores.get(i).substring(0, 1))) {
                counter++;
            } else {
                //If they don't have one of the best hands, this will prevent them from winning with the highest card.
                scores.set(i, "90"); 
            }
        }
        if(counter == 1) {
            //If no other player has that best hand, they are the winner!
            if(highIndex == 0) { 
                return theDealer.name; 
            } else {
                return thePlayers.get(highIndex - 1).name;
            }
        } else { //If multiple players have the best hand, the one with the highest card wins.
            int highestCard = Integer.parseInt(scores.get(0).substring(1));
            int highCardIndex = 0;
            for(int i = 1; i < scores.size(); i++) {
                if(Integer.parseInt(scores.get(i).substring(1)) > highestCard) {
                    highestCard = Integer.parseInt(scores.get(i).substring(1));
                    highCardIndex = i;
                }
            }
            counter = 0;
            //Sees how many players have that same highest card.
            for(int i = 0; i < scores.size(); i++) { 
                if(highestCard == Integer.parseInt(scores.get(i).substring(1))) {
                    counter++;
                } else {
                    scores.set(i, "90");
                }
            }
            if(counter == 1) { //If no other player has that highest card, they are the winner!
                if(highCardIndex == 0) {
                    return theDealer.name; 
                } else {
                    return thePlayers.get(highCardIndex - 1).name;
                }
            } else { //If multiple players have the same best hand and highest card, the are all winners.
                int winners = 0;
                for(int i = 0; i < scores.size(); i++) { //Finds how many of the players are winners.
                    if(scores.get(i) != "90") {
                        winners++;
                    }
                }
                String winner = "";
                int currentWinner = 0;
                for(int i = 0; i < scores.size(); i++) { //Adds the winners to a string.
                    if(scores.get(i) != "90") {
                        currentWinner++;
                        if(i == 0) {
                            winner += theDealer.name;
                        } else {
                            winner += thePlayers.get(i - 1).name;
                        }
                        if(currentWinner == winners - 1) {
                            winner += " and ";
                        } else if(currentWinner < winners - 1) {
                            winner += ", ";
                        }    
                    }
                }
                return winner;
            }
        }
    }
    public static void main(String[] args) { //Runs the main code.
        ArrayList<Integer> values = new ArrayList<Integer>(); //List for the value (face) of each card.
        ArrayList<String> suits = new ArrayList<String>(); //List for the suit of each card.

        //Creating all the cards of the Hearts suit.
        for(int i = 2; i < 15; i++) { 
            values.add(i);
            suits.add("Hearts");
        }

        //Creating all the cards of the Diamonds suit.
        for(int i = 2; i < 15; i++) { 
            values.add(i);
            suits.add("Diamonds");
        }

        //Creating all the cards of the Clubs suit.
        for(int i = 2; i < 15; i++) { 
            values.add(i);
            suits.add("Clubs");
        }

        //Creating all the cards of the Spades suit.
        for(int i = 2; i < 15; i++) { 
            values.add(i);
            suits.add("Spades");
        }

        //A list for some random player names.
        ArrayList<String> names = new ArrayList<String>();
        names.add("Nate");
        names.add("Dayton");
        names.add("Samantha");
        names.add("Ari");
        names.add("Oli");
        names.add("Liam");
        names.add("Violet");
        names.add("Katherine");
        names.add("Maria");

        //Creates the dealer and gives them a random name.
        int name = (int)(Math.random() * names.size());
        Dealer dealer = new Dealer(names.get(name));
        names.remove(name);
        
        //Creates all other players and gives them random names.
        ArrayList<Player> players = new ArrayList<Player>();
        for(byte i = 0; i < 5; i++) {
            name = (int)(Math.random() * names.size());  
            players.add(new Player(names.get(name)));
            names.remove(name);
        }

        System.out.println("And so the card game begins...");
        System.out.println();
        System.out.println(dealer.name + " is the Dealer");

        //Shuffles the deck so the results are randomized.
        dealer.shuffle(values, suits); 

        //The dealer deals to themself first.
        dealer.deal(values, suits, dealer); 
        //The dealer then deals to each player.
        for(int i = 0; i < players.size(); i++) { 
            dealer.deal(values, suits, players.get(i));
        }
        
        System.out.println(dealer.name + " deals five cards to each player.");
        System.out.println();

        System.out.println("The cards of each player:");
        System.out.println();

        dealer.showHand(); //The dealer and each player will print the cards they have.
        for(int i = 0; i < players.size(); i++) {
            players.get(i).showHand();
        }

        //Finds the points of each player's hand using the Player class function calculatePoints().
        ArrayList<String> points = new ArrayList<String>();
        points.add(dealer.calculatePoints());
        for(int i = 0; i < players.size(); i++) {
            points.add(players.get(i).calculatePoints());
        }
        
        //With the scores calculated and put into an array, we can now check and display who won.
        System.out.println();
        System.out.println("The Winner is...");
        System.out.println();
        System.out.println(calculateWinner(points, dealer, players) + "!");
    }
}