/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
    	Instant starttep = tweets.get(0).getTimestamp();
    	Instant endtep = tweets.get(0).getTimestamp();
    	for(Tweet temp:tweets){
    		if(temp.getTimestamp().isBefore(starttep))
    			starttep=temp.getTimestamp();
    		if(temp.getTimestamp().isAfter(endtep))
    			endtep=temp.getTimestamp();
    	}
    	Timespan box = new Timespan(starttep,endtep);
    	return box;
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
    	String regEx = "\\s@[a-zA-Z0-9-_]+\\W";
		Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
    	Set<String> namebox = new HashSet<String>();
    	for(Tweet  temp : tweets)
    	{
    		Matcher matcher =pattern.matcher(temp.getText());
    		while(matcher.find())
    		{
    			String temp1 = matcher.group(0);
    			String temp2[] = temp1.split("@");
    			String regEx1 = "[a-zA-Z0-9-_]+";
    			Pattern pattern1 = Pattern.compile(regEx1);
    			Matcher matcher1 = pattern1.matcher(temp2[1]);
    			while(matcher1.find()){
    				if(namebox.contains(matcher1.group(0)))
    					continue;
    				namebox.add(matcher1.group(0));
    			}
    			
    		}
    	}
		return namebox;
        
    }

}
