/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FilterTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
     static final Instant s1 = Instant.parse("2016-02-17T10:00:00Z");
     static final Instant s2 = Instant.parse("2016-02-17T11:00:00Z");
     static final Instant s3 = Instant.parse("2016-02-17T18:00:00Z");
    
     static final Tweet tweet0 = new Tweet(0, "alyssa", "No pain why gain", s1);
     static final Tweet tweet1 = new Tweet(1, "alyssa", "no code why life", s1);
     static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "Single life is ok #hyper", s2);
     static final Tweet tweet3 = new Tweet(3, "alyssa", "Think about it", s2);
     static final Tweet tweet4 = new Tweet(4, "Jimmy", "original", s3);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testWrittenByMultipleTweetsSingleResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "alyssa");
        List<Tweet> writtenBy1 = Filter.writtenBy(Arrays.asList(tweet0,tweet1,tweet2),"alyssa");
        assertEquals("The answer is correct", 1, writtenBy.size());
        assertEquals("The answer is correct ,great",2,writtenBy1.size());
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet1));
        assertFalse("unexpected tweets should not exist in the list",writtenBy.contains(tweet2));
    }
    
    @Test
    public void testInTimespanMultipleTweetsMultipleResults() {
        Instant testStart = Instant.parse("2016-02-17T09:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T12:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd));
        
        assertFalse("expected non-empty list", inTimespan.isEmpty());
        
        assertTrue("expected list to contain tweets", inTimespan.containsAll(Arrays.asList(tweet1, tweet2)));
        
        assertFalse("unexpected tweet should not exist in the list", inTimespan.contains(tweet3));
        
        assertEquals("expected the same order", 0, inTimespan.indexOf(tweet1));
    }
    
    @Test
    public void testContaining() { 
    	// tweet0 and tweet3 to test case-insensitive("No" and "no")
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet0, tweet1, tweet2, tweet3, tweet4), Arrays.asList("No", "Why"));
        
        assertFalse("expected non-empty list", containing.isEmpty());
        assertTrue("expected list to contain tweets", containing.containsAll(Arrays.asList(tweet0, tweet1)));
        
        //whether the order is remained
        assertEquals("expected same order", 0, containing.indexOf(tweet0));
        assertEquals("expected same order", 1, containing.indexOf(tweet1));
     
    }

    /*
     * Warning: all the tests you write here must be runnable against any Filter
     * class that follows the spec. It will be run against several staff
     * implementations of Filter, which will be done by overwriting
     * (temporarily) your version of Filter with the staff's version.
     * DO NOT strengthen the spec of Filter or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Filter, because that means you're testing a stronger
     * spec than Filter says. If you need such helper methods, define them in a
     * different class. If you only need them in this test class, then keep them
     * in this test class.
     */

}
