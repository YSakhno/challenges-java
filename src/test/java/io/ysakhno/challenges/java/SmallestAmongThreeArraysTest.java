package io.ysakhno.challenges.java;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.assertEquals;

import static io.ysakhno.challenges.java.SmallestAmongThreeArrays.optimal;
import static io.ysakhno.challenges.java.SmallestAmongThreeArrays.concise;
import static io.ysakhno.challenges.java.SmallestAmongThreeArrays.streaming;


/**
 * Tests solutions for the problem:<br>
 * Given 3 random arrays of integers, write a method to find the smallest number
 * that is common among the 3 arrays.
 *
 * @see SmallestAmongThreeArrays
 * @author Yuri Sakhno
 */
@RunWith(JUnitParamsRunner.class)
public class SmallestAmongThreeArraysTest {

    /**
     * Tests optimal solution.
     */
    @Test
    @Parameters(method = "params")
    public final void testOptimal(int[] A, int[] B, int[] C, int result) {
        assertEquals(result, optimal(A, B, C));
    }

    /**
     * Tests sub-optimal but concise solution.
     */
    @Test
    @Parameters(method = "params")
    public final void testConcise(int[] A, int[] B, int[] C, int result) {
        assertEquals(result, concise(A, B, C));
    }

    /**
     * Tests solution written in functional style using Java 8 Streams.
     */
    @Test
    @Parameters(method = "params")
    public final void testStreaming(int[] A, int[] B, int[] C, int result) {
        assertEquals(result, streaming(A, B, C));
    }

    /**
     * Defines various test cases for solution verification.
     */
    @SuppressWarnings("unused")
    private Object params() {
        return new Object[] {
            new Object[] { new int[] { 1 }, new int[] { 1 }, new int[] { 1 }, 1 },
            new Object[] { new int[] { 6, 2, 3 }, new int[] { 2, 4, 9 }, new int[] { 2, 5, 7 }, 2 },
            new Object[] { new int[] { 1, 2, 3, 4 }, new int[] { 8, 7, 3 }, new int[] { 7, 3 }, 3 },
            new Object[] { new int[] { 1, 2, 3 }, new int[] { 1, 2, 4 }, new int[] { 2, 3, 5 }, 2 },
            new Object[] { new int[] { 1, 2, 3 }, new int[] { 2, 3, 4 }, new int[] { 1, 3, 4, 5 }, 3 },
            new Object[] { new int[] { 1, 4, 5, 5, 6 }, new int[] { 2, 5, 6, 7 }, new int[] { 3, 6, 8 }, 6 },
            new Object[] { new int[] { 1, 2, 4, 5, 6 }, new int[] { 2, 2, 5, 6, 7 }, new int[] { 3, 5, 6, 8 }, 5 },
            new Object[] { new int[] { 1, 2, 4, 5, 7 }, new int[] { 3, 5, 6, 7 }, new int[] { 3, 3, 5, 7, 8 }, 5 },
            new Object[] { new int[] { 3, 4, 6, 9 }, new int[] { 2, 3, 4, 5 }, new int[] { 1, 2, 4, 5, 7 }, 4 },

            new Object[] { new int[] { 1, 4, 8 }, new int[] { 2, 6, 7 }, new int[] { 3, 5, 9 }, -1 },
            new Object[] { new int[] { 12, 19, 24 }, new int[] { 112, 90, 3 }, new int[] { 70, 33, 42 }, -1 },
            new Object[] { new int[] { 12 }, new int[] { 42 }, new int[] { 91 }, -1 },
        };
    }
}
