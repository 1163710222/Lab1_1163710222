/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
//    private static final Instant d1 = Instant.parse("2016-02-19T10:00:00Z");
//    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
//    private static final Instant d3 = Instant.parse("2016-02-28T12:00:00Z");
//    
//    private static final Tweet tweet0 = new Tweet(0, "ooo", "hello world", d2);
//    private static final Tweet tweet1 = new Tweet(1, "alyssa", "RT @a1b-_", d1);
//    private static final Tweet tweet2 = new Tweet(2, "ooo", " @a1b-_'s", d2);
//    private static final Tweet tweet3 = new Tweet(3, "ooo", " bitdiddle@mit.edu ", d2);
//    private static final Tweet tweet4 = new Tweet(4,"JIM","hello @hit .edu",d3);
//    
//    @Test(expected=AssertionError.class)
//    public void testAssertionsEnabled() {
//        assert false; // make sure assertions are enabled with VM argument: -ea
//    }
//    @Test
//    public void testGetTimespanTwoTweets() {
//        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
//        assertEquals("start", d2, timespan.getStart());
//        assertEquals("end", d1, timespan.getEnd());
//    }
//    
//    @Test
//    // 用于测试从文章中提取提及的用户,针对不同的@方式
//    public void testGetMentionedUsersNoMention() {
//    	Set<String> metionedUsers = new HashSet<>();
//    	assertTrue(Extract.getMentionedUsers(Arrays.asList(tweet0)).isEmpty());
//
//    	metionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet2));
//    	assertTrue(metionedUsers.contains("a1b-_"));      
//    	
//    	//test whether case-insensitive
//    	metionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3));
//    	assertTrue(metionedUsers.isEmpty());
//    	
//    	metionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet4));
//    	assertTrue(metionedUsers.contains("hit"));
//    	
//    }
		static final Instant s1 = Instant.parse("2016-02-19T10:00:00Z");
		static final Instant s2 = Instant.parse("2018-02-17T10:00:00Z");
		static final Instant s3 = Instant.parse("2016-02-28T10:00:00Z");
		private static final Tweet tweet0 = new Tweet(0, "CCC", "hello world", s2);
	    private static final Tweet tweet1 = new Tweet(1, "BBB", "RT @a1b-_", s1);
	    private static final Tweet tweet2 = new Tweet(2, "CCC", " @a1b-_'s", s2);
	    private static final Tweet tweet3 = new Tweet(3, "CCC", " bitdiddle@Mit.edu ", s1);
	    private static final Tweet tweet4 = new Tweet(4,"JIM","hello @hit .edu",s3);
	    
	    @Test(expected=AssertionError.class)
	    public void testAssertionsEnabled() {
	        assert false; // make sure assertions are enabled with VM argument: -ea
	    }
        @Test
        // 测试最简单的提取时间区间的方法
        public void testGetTimespanTwoTweets() {
          Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
          assertEquals("start", s1, timespan.getStart());
          assertEquals("end", s2, timespan.getEnd());}
        
         
        @Test
      // 用于测试从文章中提取提及的用户,针对不同的@方式
      public void testGetMentionedUsersNoMention() {
      	Set<String> metionedUsers = new HashSet<>();
      	//测试没有@时的匹配情况
      	assertTrue(Extract.getMentionedUsers(Arrays.asList(tweet0)).isEmpty());
  
      	//测试当指定字符串后有空字符时的匹配情况
      	metionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet2));
      	assertTrue(metionedUsers.contains("a1b-_"));      
      	
      	//测试是否是case-insensitive
      	metionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3));
      	assertTrue(metionedUsers.isEmpty());
      	
      	//测试当指定字符后为空格时的匹配情况
      	metionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet4));
      	assertTrue(metionedUsers.contains("hit"));
      	
      }
        
		/*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}