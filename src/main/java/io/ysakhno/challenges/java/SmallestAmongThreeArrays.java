package io.ysakhno.challenges.java;

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Solutions for the following problem:<br>
 * Given 3 random arrays of integers, write a method to find the smallest number
 * that is common among the 3 arrays. If no such value could be found, return
 * -1.
 * <p>
 * <b>Limitations:</b>
 * </p>
 * <ul>
 * <li>Arrays are not necessarily sorted.</li>
 * <li>Each array can contain duplicate integers (with respect to that
 * particular array).</li>
 * <li>Each array will contain at least one integer.</li>
 * <li>Each array will contain at most 1000 integers total.</li>
 * <li>All values in the arrays are integers greater than zero.</li>
 * </ul>
 *
 * @author Yuri Sakhno
 */
public class SmallestAmongThreeArrays {

    /**
     * This method solves the challenge in the most optimal way - by doing something
     * analogous to 3-way merging. Not counting initial arrays sorting, complexity
     * is linear in the size of the arrays combined, in the worst case.
     *
     * @param A the first array. Cannot be {@code null} or empty.
     * @param B the second array. Cannot be {@code null} or empty.
     * @param C the third array. Cannot be {@code null} or empty.
     * @return the minimum value found such that it is present in all three input
     *         arrays, or {@code -1} if three input arrays do not contain values
     *         common to all three of them.
     */
    public static int optimal(int[] A, int[] B, int[] C) {
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);

        int a = 0;
        int b = 0;
        int c = 0;

        while (a < A.length && b < B.length && c < C.length) {
            if (A[a] == B[b] && A[a] == C[c]) {
                return A[a];
            }

            if (A[a] <= B[b] && A[a] <= C[c]) {
                ++a;
            } else if (B[b] <= A[a] && B[b] <= C[c]) {
                ++b;
            } else {
                ++c;
            }
        }

        return -1;
    }

    /**
     * This method works slightly worse than optimal one above by iterating through
     * the first array (sorted) and attempting to find an element from the first
     * array in both other arrays (which are also sorted, so the search is binary).
     * Not counting initial arrays sorting, complexity is N*logN, in the worst case.
     * <p>
     * The advantage of this method is its conciseness and relative simplicity.
     * </p>
     *
     * @param A the first array. Cannot be {@code null} or empty.
     * @param B the second array. Cannot be {@code null} or empty.
     * @param C the third array. Cannot be {@code null} or empty.
     * @return the minimum value found such that it is present in all three input
     *         arrays, or {@code -1} if three input arrays do not contain values
     *         common to all three of them.
     */
    public static int concise(int[] A, int[] B, int[] C) {
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);

        for (int elA : A) {
            if (Arrays.binarySearch(B, elA) >= 0 && Arrays.binarySearch(C, elA) >= 0) {
                return elA;
            }
        }

        return -1;
    }

    /**
     * This method solves the challenge in completely functional way using Java 8
     * Steams.
     * <p>
     * <b>General idea:</b><br>
     * First each array is converted to stream of distinct {@code Integer}s (any
     * duplicate entries in each array/stream are merged into a single entry in that
     * corresponding stream). Then the three separate streams are concatenated one
     * after the other to form one big stream. If input arrays contained any common
     * elements among each other, this big stream will contain several repeated
     * elements. These elements will come in no particular order. Next, elements are
     * grouped together, counting how many times each element occurred in the united
     * stream. If any element occurred exactly 3 times, that means that it was
     * present in all three input arrays. Just need to find smallest such element
     * (provided by ordering of {@code TreeMap}).
     * </p>
     *
     * @param A the first array. Cannot be {@code null} or empty.
     * @param B the second array. Cannot be {@code null} or empty.
     * @param C the third array. Cannot be {@code null} or empty.
     * @return the minimum value found such that it is present in all three input
     *         arrays, or {@code -1} if three input arrays do not contain values
     *         common to all three of them.
     */
    public static int streaming(int[] A, int[] B, int[] C) {
        return Stream.concat(Stream.concat(
                Arrays.stream(A).boxed().distinct(),
                Arrays.stream(B).boxed().distinct()),
                Arrays.stream(C).boxed().distinct())
            .collect(Collectors.groupingBy(
                    Function.identity(),
                    TreeMap::new,
                    Collectors.counting()))
            .entrySet()
            .stream()
            .filter(e -> e.getValue() == 3L)
            .map(Entry::getKey)
            .findFirst()
            .orElse(-1);
    }
}
