/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
		Map<String, Set<String>> box = new HashMap<String, Set<String>>();
		Map<String, Set<String>> ff = new HashMap<>();
		for(Tweet xx:tweets)
		{
			String man = xx.getAuthor();
			Set<String> uni = new HashSet<String>();
			String regEx = "\\s@[a-zA-Z0-9-_]+\\b";
			Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
	    	Matcher matcher =pattern.matcher(" " + xx.getText());
    		while(matcher.find())
    		{
    			Set<String> namebox = new HashSet<String>();
    			String temp1 = matcher.group(0);
    			String temp2[] = temp1.split("@");
    			String regEx1 = "[a-zA-Z0-9-_]+";
    			Pattern pattern1 = Pattern.compile(regEx1);
    			Matcher matcher1 = pattern1.matcher(temp2[1]);
    			while(matcher1.find()){
    				if(box.containsKey(matcher1.group(0)))
        			{
        				namebox = box.get(matcher1.group());
        			} 
        			namebox.add(man);
        			box.put(matcher1.group(0), namebox);			
    			}
    		}
    		if(!box.containsKey(man))
    		{
    			Set<String> namebox = new HashSet<String>();
    			box.put(man, namebox);
    		}
    	}
//		Implement of the guessFollowGraph
		Map<String, List<String>> vb = new HashMap<String, List<String>>();
		for(Tweet yy:tweets)
		{
			String man = yy.getAuthor();
			String regEx = " #[a-zA-Z0-9_-]+ ";//还是不会写正则表达式啊！！！！！
			Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
	    	Matcher matcher =pattern.matcher(yy.getText());
	    	while(matcher.find())
	    	{
	    		if(vb.containsKey(regEx))
	    		{
	    			if(vb.get(regEx).contains(man))
	    				continue;
	    			else
	    				vb.get(regEx).add(man);
	    		}
	    		else
	    		{
	    			List<String> temp = new ArrayList<String>();
	    			temp.add(man);
	    			vb.put(regEx, temp);
	    		}	
	    	}
		} 
		for(String x:vb.keySet()){//相同的tag
    		for(String y:vb.get(x)){//相同tag中的人
    			Set<String> z = new HashSet<>();
    			for(String zz:vb.get(x)){
    				if(!zz.equals(y))
    					z.add(zz);
    			}
    			ff.put(y, z);//完成将有相同tag的人放入一个容器中
    		}
    	}	
		for(String xx:ff.keySet())
		{
			for(String yy:ff.get(xx)){
				if(!box.get(xx).contains(yy))
					box.get(xx).add(yy);
			}
		}
		
 	return box;
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
		Map<String,Integer> box = new HashMap<String, Integer>();
		List<String> tempbox = new ArrayList<>();
		List<String> tempbottle= new ArrayList<>();
    	for(String xx : followsGraph.keySet()){
    		box.put(xx, followsGraph.get(xx).size());
    		tempbox.add(xx);
    	}
    	String[] tempx = new String[tempbox.size()];
    	for(int i = 0;i<tempbox.size();i++)
    	{
    		tempx[i] = tempbox.get(i);
    	}
    	for(int i = 0;i<box.size();i++)
		{
			for(int j = i;j<box.size();j++)
			{
				if(box.get(tempx[j])>box.get(tempx[i])){
					String temp = tempx[i];
					tempx[i] = tempx[j];
					tempx[j] = temp;
				}
			}
		}
    	for(int i = 0;i<box.size();i++)
    	{
    		tempbottle.add(tempx[i]);
    	}
    	return tempbottle;
    }

}
