// Challenge 54
import java.util.*;
import java.io.*;

/* In the card game poker, a hand consists of five cards and are ranked, in order
 * from lowest to highest, in the following way:
 *
 *  High Card: Highest value card                                          1
 *  One Pair: Two cards of the same value                                  2
 *  Two Pairs: Two different pairs                                         3
 *  Three of a kind: Three cards of the same value                         4
 *  Straight: All cards are consecutive values                             5
 *  Flush: All cards of the same suit                                      6
 *  Full House: Three of a kind and a pair                                 7
 *  Four of a kind: Four cards of the same value                           8
 *  Straight Flush: All cards are consecutive values of the same suit      9
 *  Royal Flush: Ten, Jack, Queen, King, Ace in same suit                 10
 *
 * The cards are valued in the order:
 * 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace
 *                              11    12     13    14
 * If two players have the same ranked hand then the rank made up of the highest
 * value wins; for example, a pair of eights beats a pair of fives. But if two ranks
 * tie, for example, both players have a pair of queens, then highest cards in each
 * hand are compared; if the highest cards tie then the next highest cards are compared
 * and so on.
 *
 * The file poker.txt contains one-thousand random hands dealt to two players. Each
 * line of the file contains ten cards (separated by a single space): the first five
 * are Player 1's cards and the last five are Player 2's cards. You can assume that all
 * hands are valid (no invalid characters or repeated cards), each player's hand is in
 * no specific order, and in each hand there is a clear winner.
 *
 * How many hands does Player 1 win?
 */

class Card implements Comparable<Card> {
  int value;
  char cardSuit;

  Card(char v, char type) {
    value = this.valueOfCard(v);
    cardSuit = type;
  }

  void printCard() {
    System.out.print(value + "" + cardSuit + " ");
  }

  int valueOfCard(char v) {
    if(v-'0' < 10) return v-'0';
    else {
      switch(v) {
        case 'T':
          return 10;
        case 'J':
          return 11;
        case 'Q':
          return 12;
        case 'K':
          return 13;
        default:
          return 14;
      }
    }
  }

  // Overriding compareTo()
  public int compareTo(Card c) {
    return this.value - c.value;
  }
}

class Hand {
  Card[] cards;
  HashMap<Character, Integer> suits = new HashMap<>();
  HashMap<Integer, Integer> values = new HashMap<>();

  Hand(String givenHand) {
    String[] individualCards = givenHand.split(" ");
    cards = new Card[individualCards.length];

    // Add cards to the hand
    for(int i = 0; i < cards.length; i++)
      cards[i] = new Card(individualCards[i].charAt(0), individualCards[i].charAt(1));

    // Sort the cards in the hand
    Arrays.sort(cards);

    // Get the count of suits and values in the hand
    for(int i = 0; i < cards.length; i++) {
      suits.put(cards[i].cardSuit, suits.getOrDefault(cards[i].cardSuit, 0) + 1);
      values.put(cards[i].value, values.getOrDefault(cards[i].value, 0) + 1);
    }
  }

  // Function to print the hand
  void printHand(String msg) {
    System.out.println(msg);
    for(int i = 0; i < cards.length; i++) cards[i].printCard();
    System.out.println();
  }

  // Function to print the suits count
  void printSuits(String msg) {
    System.out.println(msg);
    for(Character c : suits.keySet()) System.out.print(c + ":" + suits.get(c) + " ");
    System.out.println();
  }

  // Function to print the values count
  void printValues(String msg) {
    System.out.println(msg);
    for(Integer v : values.keySet()) System.out.print(v + ":" + values.get(v) + " ");
    System.out.println();
  }

  // Function that returns the highest value of a n kind of a hand, less than t (top)
  int highestValue(int n, int t) {
    int highest = 0;

    for(Integer value : values.keySet()) {
      if(values.get(value) == n && value > highest && value < t) highest = value;
    }

    return highest;
  }
}

public class challenge54 {
  // Function to check if a hand is a royal flush
  static boolean isRoyalFlush(Hand h) {
    if(h.suits.size() > 1 || h.values.size() < 5) return false;
    if(h.cards[0].value != 10) return false;
    for(int i = 1; i < h.cards.length; i++) {
      if(h.cards[i].value <= h.cards[i-1].value) return false;
    }

    return true;
  }

  //  Function to check if a hand is a straight flush
  static boolean isStraightFlush(Hand h) {
    if(h.suits.size() > 1 || h.values.size() < 5) return false;
    for(int i = 1; i < h.cards.length; i++) {
      if(h.cards[i].value != h.cards[i-1].value+1) return false;
    }

    return true;
  }

  // Function to check if a hand is a four of a kind
  static boolean isFourOfAKind(Hand h) {
    if(h.values.size() > 2) return false;

    for(Integer v : h.values.keySet()) {
      if(h.values.get(v) == 4) return true;
    }

    return false;
  }
  static boolean compareFourOfAKind(Hand one, Hand two) {
    if(one.highestValue(4, 15) != two.highestValue(4, 15)) return one.highestValue(4, 15) > two.highestValue(4, 15);
    else return compareHighestValues(one, two);
  }

  // Function to check if a hand is a full house
  static boolean isFullHouse(Hand h) {
    if(isFourOfAKind(h)) return false;
    if(h.values.size() > 2) return false;

    return true;
  }
  static boolean compareFullHouse(Hand one, Hand two) {
    if(one.highestValue(3, 15) != two.highestValue(3, 15)) return one.highestValue(3, 15) > two.highestValue(3, 15);
    else return compareOnePair(one, two);
  }

  // Function to check if a hand is a flush
  static boolean isFlush(Hand h) {
    return h.suits.size() == 1;
  }

  // Function to check if a hand is a straight
  static boolean isStraight(Hand h) {
    if(h.values.size() < 5) return false;
    for(int i = 1; i < h.cards.length; i++) {
      if(h.cards[i].value != h.cards[i-1].value+1) return false;
    }

    return true;
  }

  // Function to check if a hand is a three of a kind
  static boolean isThreeOfAKind(Hand h) {
    if(h.values.size() > 3) return false;
    if(isFullHouse(h)) return false;

    for(Integer v : h.values.keySet()) {
      if(h.values.get(v) == 3) return true;
    }

    return false;
  }
  static boolean compareThreeOfAKind(Hand one, Hand two) {
    if(one.highestValue(3, 15) != two.highestValue(3, 15)) return one.highestValue(3, 15) > two.highestValue(3, 15);
    else return compareHighestValues(one, two);
  }

  // Function to check if a hand is two pairs
  static boolean isTwoPairs(Hand h) {
    if(h.values.size() != 3) return false;
    int pairCount = 0;

    for(Integer v : h.values.keySet()) {
      if(h.values.get(v) == 2) pairCount++;
    }

    return pairCount == 2;
  }
  static boolean compareTwoPairs(Hand one, Hand two) {
    if(one.highestValue(2, 15) != two.highestValue(2, 15)) return one.highestValue(2, 15) > two.highestValue(2, 15);
    else {
      int top = one.highestValue(2, 15);
      if(one.highestValue(2, top) != two.highestValue(2, top)) return one.highestValue(2, top) > two.highestValue(2, top);
      else return compareHighestValues(one, two);
    }
  }

  // Function to check if a hand is one pair, and function to compare one pairs
  static boolean isOnePair(Hand h) {
    if(h.values.size() != 4) return false;
    int pairCount = 0;

    for(Integer v : h.values.keySet()) {
      if(h.values.get(v) == 2) pairCount++;
    }

    return pairCount == 1;
  }
  static boolean compareOnePair(Hand one, Hand two) {
    if(one.highestValue(2, 15) != two.highestValue(2, 15)) return one.highestValue(2, 15) > two.highestValue(2, 15);
    else return compareHighestValues(one, two);
  }

  // Function to compare the single highest values in two hands
  static boolean compareHighestValues(Hand one, Hand two) {
    int top = 15;

    while(one.highestValue(1, top) == two.highestValue(1, top)) top = one.highestValue(1, top);
    return one.highestValue(1, top) > two.highestValue(1, top);
  }

  // Function that returns the value of a hand (1 - 10)
  static int handValue(Hand h) {
    if(isRoyalFlush(h)) return 10;
    else if(isStraightFlush(h)) return 9;
    else if(isFourOfAKind(h)) return 8;
    else if(isFullHouse(h)) return 7;
    else if(isFlush(h)) return 6;
    else if(isStraight(h)) return 5;
    else if(isThreeOfAKind(h)) return 4;
    else if(isTwoPairs(h)) return 3;
    else if(isOnePair(h)) return 2;
    else return 1;
  }

  // Function to check if player one's hand wins
  static boolean playerOneWins(Hand one, Hand two) {
    int oneValue = handValue(one);
    int twoValue = handValue(two);
    // Values are 10 [Royal Flush] -> 1 [Highest Card]

    // Check if the first value is greater than the second value
    if(oneValue > twoValue) return true;
    else if(oneValue < twoValue) return false;
    else {
      if(oneValue == 1) return compareHighestValues(one, two);
      else if(oneValue == 2) return compareOnePair(one, two);
      else if(oneValue == 3) return compareTwoPairs(one, two);
      else if(oneValue == 4) return compareThreeOfAKind(one, two);
      else if(oneValue == 5) return compareHighestValues(one, two);
      else if(oneValue == 6) return compareHighestValues(one, two);
      else if(oneValue == 7) return compareFullHouse(one, two);
      else if(oneValue == 8) return compareFourOfAKind(one, two);
      else if(oneValue == 9) return compareHighestValues(one, two);
      else return true;
    }
  }

  // Function that finds the winner of the given two hands
  // True is first player wins, false is second player wins
  static boolean winningHand(String hands) {
    Hand handOne = new Hand(hands.substring(0, hands.length()/2));
    Hand handTwo = new Hand(hands.substring(hands.length()/2 + 1));

    return playerOneWins(handOne, handTwo);
  }

  public static void main(String args[]) {
    // Examples for testing comparing two hands
    String ex1 = "5H 5C 6S 7S KD 2C 3S 8S 8D TD"; // Player 2
    String ex2 = "5D 8C 9S JS AC 2C 5C 7D 8S QH"; // Player 1
    String ex3 = "2D 9C AS AH AC 3D 6D 7D TD QD"; // Player 2
    String ex4 = "4D 6S 9H QH QC 3D 6D 7H QD QS"; // Player 1
    String ex5 = "2H 2D 4C 4D 4S 3C 3D 3S 9S 9D"; // Player 1

    String[] examples = {ex1, ex2, ex3, ex4, ex5};
    System.out.println();
    // Checking the examples given
    for(int i = 0; i < examples.length; i++) {
      if(winningHand(examples[i])) System.out.println("For hand " + (i+1) + ", player 1 wins!\n");
      else System.out.println("For hand " + (i+1) + ", player 2 wins!\n");
    }

    try {
      // Create a reader to read the contents of the test file
      BufferedReader br = new BufferedReader(new FileReader(new File("p054_poker.txt")));

      String st;
      int totalWins = 0;

      while((st = br.readLine()) != null) {
        if(winningHand(st)) totalWins++;
      }

      System.out.println("Player one wins " + totalWins + " games in total.");
    } catch(Exception e) {
      System.out.println(e);
    }
  }
}
