// Challenge 54
import java.util.*;

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

  // Function that returns the highest value of a hand
  int highestValue() { return cards[cards.length-1].value; }
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
      if(h.cards[i].value <= h.cards[i-1].value) return false;
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

  // Function to check if a hand is a full house
  static boolean isFullHouse(Hand h) {
    if(isFourOfAKind(h)) return false;
    if(h.values.size() > 2) return false;

    return true;
  }

  // Function to check if a hand is a flush
  static boolean isFlush(Hand h) {
    return h.suits.size() == 1;
  }

  // Function to check if a hand is a straight
  static boolean isStraight(Hand h) {
    if(h.values.size() < 5) return false;
    for(int i = 1; i < h.cards.length; i++) {
      if(h.cards[i].value <= h.cards[i-1].value) return false;
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

  // Function to check if a hand is two pairs
  static boolean isTwoPairs(Hand h) {
    if(h.values.size() != 3) return false;
    int pairCount = 0;

    for(Integer v : h.values.keySet()) {
      if(h.values.get(v) == 2) pairCount++;
    }

    return pairCount == 2;
  }

  // Function to check if a hand is one pair
  static boolean isOnePair(Hand h) {
    if(h.values.size() != 4) return false;
    int pairCount = 0;

    for(Integer v : h.values.keySet()) {
      if(h.values.get(v) == 2) pairCount++;
    }

    return pairCount == 1;
  }

  public static void main(String args[]) {
    // Examples to test checking what kind of hand
    // Hand royalFlush = new Hand("TD JD QD KD AD");
    // royalFlush.printHand("Royal flush: ");
    // System.out.println("This is a royal flush: " + isRoyalFlush(royalFlush));
    //
    // Hand straightFlush = new Hand("2D 4D 5D 3D 6D");
    // straightFlush.printHand("Straight flush: ");
    // System.out.println("This is a straight flush: " + isStraightFlush(straightFlush));

    // Hand fourOfAKind = new Hand("2D 2S 2C 2H 3S");
    // fourOfAKind.printHand("Four of a kind: ");
    // System.out.println("This is a four of a kind: " + isFourOfAKind(fourOfAKind));

    // Hand fullHouse = new Hand("2D 2S 2C 3H 3C");
    // fullHouse.printHand("Full house: ");
    // System.out.println("This is a full house: " + isFullHouse(fullHouse));

    // Hand flush = new Hand("2D 3D 4D 5D 7D");
    // flush.printHand("Flush: ");
    // System.out.println("This is a flush: " + isFlush(flush));

    // Hand straight = new Hand("7C 3D 4H 5C 6S");
    // straight.printHand("Straight: ");
    // System.out.println("This is a straight: " + isStraight(straight));

    // Hand threeOfAKind = new Hand("2D 2C 2S 3D 5C");
    // threeOfAKind.printHand("Three of a kind: ");
    // System.out.println("This is a three of a kind: " + isThreeOfAKind(threeOfAKind));

    // Hand twoPairs = new Hand("2D 2C 3D 3C 4H");
    // twoPairs.printHand("Two pairs: ");
    // System.out.println("This is two pairs: " + isTwoPairs(twoPairs));

    // Hand onePair = new Hand("2D 2C 3D 7H 9D");
    // onePair.printHand("One pair: ");
    // System.out.println("This is one pair: " + isOnePair(onePair));

    // Examples for testing comparing two hands
    String exHands1 = "5H 5C 6S 7S KD 2C 3S 8S 8D TD"; // Player 2
    String exHands2 = "5D 8C 9S JS AC 2C 5C 7D 8S QH"; // Player 1
    String exHands3 = "2D 9C AS AH AC 3D 6D 7D TD QD"; // Player 2
    String exHands4 = "4D 6S 9H QH QC 3D 6D 7H QD QS"; // Player 1
    String exHands5 = "2H 2D 4C 4D 4S 3C 3D 3S 9S 9D"; // Player 1




  }
}
