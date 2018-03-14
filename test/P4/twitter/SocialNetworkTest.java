/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;import javax.print.attribute.SetOfIntegerSyntax;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
	 static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
     static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
     static final Tweet tweet0 = new Tweet(0, "a", "@b @c @d @e - #hype ", d1);
     static final Tweet tweet1 = new Tweet(1, "b", " @c @d", d1);
     static final Tweet tweet2 = new Tweet(2, "c", "No more sentence!  ", d2);
     static final Tweet tweet3 = new Tweet(3, "d", "@c @e", d2);
     static final Tweet tweet4 = new Tweet(4, "e", "@c @d s #hype ", d2);
     static final Tweet tweet5 = new Tweet(5, "f", "hahahaha", d2);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    //针对socialNetwork的关系建立进行测试
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet0, tweet1, tweet2, tweet3, tweet4,tweet5));
        Set<String> tempbox = new HashSet<>();
        
        //用于证明补充的方法是有效的
        assertFalse(followsGraph.get("a").isEmpty());
        
        //根据上述的tweet文章情况来进行比对
        tempbox.clear();
        tempbox.add("a");
        assertTrue(followsGraph.get("b").containsAll(tempbox));
        
        tempbox.clear();
        tempbox.add("a");
        tempbox.add("d");
        assertTrue(followsGraph.get("e").containsAll(tempbox));
        
        tempbox.clear();
        tempbox.add("a");
        tempbox.add("b");
        tempbox.add("d");
        tempbox.add("e");
        assertTrue(followsGraph.get("c").containsAll(tempbox)); 
        
        tempbox.clear();
        assertTrue(followsGraph.get("f").isEmpty());
    }
    
    @Test
    public void testInfluencersEmpty() {
    	Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet0, tweet1, tweet2, tweet3, tweet4, tweet5));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertTrue(influencers.get(0).equals("c"));
        assertTrue(influencers.get(1).equals("d"));
        assertTrue(influencers.get(2).equals("e"));
        assertTrue(influencers.get(3).equals("b"));
        assertTrue(influencers.get(4).equals("a"));
        assertTrue(influencers.get(5).equals("f"));

        
    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
}
