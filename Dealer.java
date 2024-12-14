//Project done by Naomi "Nigh" Johnson
//for CTE Software Development Class 2024
//Instructor: Kim Gross

/*This file contains the class for the dealer.*/

import java.util.ArrayList;
//The dealer extends the player, so also have cards and can show them and calculate their points.
public class Dealer extends Player {

    public Dealer(String name) {
        super(name);
    }

    void shuffle(ArrayList<Integer> numbers, ArrayList<String> face) { //Additionally, a dealer can shuffle the deck.
        for(byte l = 0; l < 3; l++) { //Peforms the shuffle three times to be more thorough.
            //The shuffle loops through the deck and swaps two random cards at each iteration.
            for(int i = 0; i< numbers.size(); i++) {
                int rand = (int)(Math.random() * numbers.size());
                int temp = numbers.get(i);
                numbers.set(i, numbers.get(rand));
                numbers.set(rand, temp);
                String tempString = face.get(i);
                face.set(i, face.get(rand));
                face.set(rand, tempString);
            }    
        }
        System.out.println(name + " shuffles the deck thorougly.");
    }
    //The dealer also has a method that gives five cards from the deck to a specified player.
    void deal(ArrayList<Integer> numbers, ArrayList<String> face, Player player) {
        for(byte i = 0; i < 5; i++) {
            player.handValues.add(numbers.get(0));
            player.handSuits.add(face.get(0));
            numbers.remove(0);
            face.remove(0);
        }
    }
}