import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a game of Row of Bowls.
 * For the games rules see Blatt05. The goal is to find an optimal strategy.
 */
public class RowOfBowls {

    int[] maxGain;

    public RowOfBowls() {
    }

    public int calc_index(int a, int b, int c, int d) {
        return (a * 3 * d) + (b * 3) + c;
    }

    public int totalArray(int[] values) {
        int totalArray = 0;

        for (int i = 0; i < values.length; i++) {
            totalArray += values[i];
        }

        return totalArray;
    }
    
    /**
     * Implements an optimal game using dynamic programming
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */
    public int maxGain(int[] values) {

        // TODO
        maxGain = new int[values.length * values.length * 3];
        int score;
        //maxgain = new int[values.length][values.length][3];
        for (int i = 0; i < values.length; i++) {
            maxGain[calc_index(i, i, 0, values.length)] = values[i];
            maxGain[calc_index(i, i, 2, values.length)] = i;
        }

        for(int i1 = 2; i1 <= values.length; i1++){
            for(int i2=0; i2 <= values.length - i1; i2++){
                int i3 = i2 + i1 - 1;

                if(values[i2] + maxGain[calc_index(i2+1, i3, 1, values.length)] > maxGain[calc_index(i2, i3-1, 1, values.length)] + values[i3]){
                    maxGain[calc_index(i2, i3, 0, values.length)] = values[i2] + maxGain[calc_index(i2+1, i3, 1, values.length)];
                    maxGain[calc_index(i2, i3, 1, values.length)] = maxGain[calc_index(i2+1, i3, 0, values.length)];
                    maxGain[calc_index(i2, i3, 2, values.length)] = i2;
                } else{
                    maxGain[calc_index(i2, i3, 0, values.length)] = values[i3] + maxGain[calc_index(i2, i3-1, 1, values.length)];
                    maxGain[calc_index(i2, i3, 1, values.length)] = maxGain[calc_index(i2, i3-1, 0, values.length)];
                    maxGain[calc_index(i2, i3, 2, values.length)] = i3;
                }
            }
        }

        score = maxGain[calc_index(0, values.length-1, 0, values.length)] - maxGain[calc_index(0, values.length-1, 1, values.length)];

        return score;
    }

    /**
     * Implements an optimal game recursively.
     *
     * @param values array of the number of marbles in each bowl
     * @return number of game points that the first player gets, provided both parties play optimally
     */
    public int maxGainRecursive(int[] values) {

        // TODO
        int score = 0;
        int first = 0;
        int last = values.length - 1;



        score = maxGainRecursive(1, values, first, last, Integer.MIN_VALUE, Integer.MAX_VALUE, score);
        score = score - (totalArray(values) - score);

        return score;
    }

    public int maxGainRecursive(int player, int[] values, int leftChecker, int rightChecker, int alpha, int beta, int score) {

        if (leftChecker == rightChecker) {
            if (player == -1) {
                return -score;
            } else {
                return score + (values[leftChecker]);
            }
        }

        int maxScore = alpha;
        for (int i = 0; i < 1; i++) {

            if (player == 1) {
                score += values[leftChecker];
            }
            int eachScore = -maxGainRecursive(-player, values, leftChecker + 1, rightChecker, -beta, maxScore, score);
            if (player == 1) {
                score -= values[leftChecker];
            }

            if (player == 1) {
                score += values[rightChecker];
            }
            int eachScore2 = -maxGainRecursive(-player, values, leftChecker, rightChecker - 1, alpha, maxScore, score);
            if (player == 1) {
                score -= values[rightChecker];
            }

            if (eachScore > eachScore2) {
                maxScore = eachScore;
            } else {
                maxScore = eachScore2;
            }

            if (alpha >= beta) {
                break;
            }
        }

        return maxScore;
    }



    
    /**
     * Calculates an optimal sequence of bowls using the partial solutions found in maxGain(int values)
     * @return optimal sequence of chosen bowls (represented by the index in the values array)
     */
    public Iterable<Integer> optimalSequence() {

        // TODO

        Iterable<Integer> optimalSequence = new ArrayList<>();

        int i1 = 0;
        int i2 = (int) Math.sqrt(maxGain.length/3) - 1;
        int index;

        for (int i3 = 0; i3 < Math.sqrt(maxGain.length/3); i3++) {
            index = maxGain[calc_index(i1, i2, 2, (int) Math.sqrt(maxGain.length/3))];

            ((ArrayList<Integer>) optimalSequence).add(index);

            if (index <= i1) {
                i1 = i1 + 1;
            } else {
                i2 = i2 - 1;
            }
        }

        return optimalSequence;
    }


    public static void main(String[] args)
    {
        // For Testing
        RowOfBowls rob = new RowOfBowls();
        int[] values1 = {3, 4, 1, 2, 8, 5}; //Right answer is 1.
        int[] values2 = {4, 7, 2, 3}; //Right answer is 4.

        System.out.println(rob.maxGain(values1));
        System.out.println(rob.maxGain(values2));
        System.out.println(rob.maxGainRecursive(values1));
        System.out.println(rob.maxGainRecursive(values2));

        //Whole system can be tried with complexer examples, especially "public int maxGain(int[] values)" which uses Dynamic Programming.
    }
}

