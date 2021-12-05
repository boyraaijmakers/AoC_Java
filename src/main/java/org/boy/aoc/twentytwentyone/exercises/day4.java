package org.boy.aoc.twentytwentyone.exercises;

import java.util.ArrayList;
import java.util.Arrays;

public class day4 extends SolutionTemplate {
    public String pointOne(String input) {
        String[] inputSplit = input.split("\n\n");

        int[] numbers = Arrays.stream(inputSplit[0].split(",")).mapToInt(Integer::parseInt).toArray();

        BingoCard[] cards = new BingoCard[inputSplit.length - 1];

        for (int i = 1; i < inputSplit.length; i++) {
            cards[i - 1] = new BingoCard(inputSplit[i]);
        }

        for (int number: numbers)
            for (BingoCard card: cards)
                if (card.tickNumberAndCheckBingo(number))
                    return String.valueOf(card.getScore() * number);

        return "";
    }

    public String pointTwo(String input) {
        String[] inputSplit = input.split("\n\n");

        int[] numbers = Arrays.stream(inputSplit[0].split(",")).mapToInt(Integer::parseInt).toArray();
        BingoCard[] cards = new BingoCard[inputSplit.length - 1];
        boolean[] bingos = new boolean[cards.length];
        int cardsLeft = cards.length;

        for (int i = 1; i < inputSplit.length; i++) {
            cards[i - 1] = new BingoCard(inputSplit[i]);
        }

        for (int number: numbers)
            for (int i = 0; i < cards.length; i++)
                if (cards[i].tickNumberAndCheckBingo(number) && !bingos[i]) {
                    bingos[i] = true;
                    cardsLeft--;
                    if (cardsLeft == 0) {
                        return String.valueOf(cards[i].getScore() * number);
                    }
                }

        return "";
    }


    private class BingoCard {
        private static final int CARD_SIZE = 5;

        private int[][] card;
        private boolean[][] checked;

        public BingoCard(String cardString) {
            this.checked = new boolean[CARD_SIZE * 2][CARD_SIZE];
            this.card = new int[CARD_SIZE][CARD_SIZE];

            for (int i = 0; i < CARD_SIZE; i++)
                for (int j = 0; j < CARD_SIZE; j++ )
                    this.card[i][j] = Integer.parseInt(
                            cardString.split("\n")[i].split("(?<=\\G.{3})")[j].strip()
                    );
        }

        public boolean tickNumberAndCheckBingo(int number) {
            for (int i = 0; i < CARD_SIZE; i++)
                for (int j = 0; j < CARD_SIZE; j++)
                    if (this.card[i][j] == number) {
                        this.checked[i][j] = true;
                        this.checked[j + CARD_SIZE][i] = true;
                    }

            return checkBingo();
        }

        private boolean checkBingo() {
            int sum = 0;
            for (boolean[] row: this.checked) {
                for (boolean el: row) sum += el ? 1 : 0;

                if (sum == 5) return true;
                sum = 0;
            }
            return false;
        }

        public int getScore() {
            int score = 0;
            for (int i = 0; i < CARD_SIZE; i++)
                for (int j = 0; j < CARD_SIZE; j++)
                    if (!this.checked[i][j])
                        score += card[i][j];

            return score;
        }
    }
}


