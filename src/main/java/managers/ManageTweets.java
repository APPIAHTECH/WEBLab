package managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Tweet;
import utils.DB;


public class ManageTweets {
	
	private DB db = null ;
	
	public ManageTweets() {
		try {
			db = new DB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalize() {
		try {
			db.disconnectBD();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/* Add a tweet */
	public void addTweet(Tweet tweet) {
		String query = "INSERT INTO tweets (uid,postdatetime,content) VALUES (?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,tweet.getUid());
			statement.setTimestamp(2,tweet.getPostDateTime());
			statement.setString(3,tweet.getContent());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Delete existing tweet */
	public void deleteTweet(Integer id,Integer uid) {
		String query = "DELETE FROM tweets WHERE id = ? AND uid=?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1,id);
			statement.setInt(2,uid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Update existing tweet */
	public void updateTweet(Tweet tweet,Integer uid) {
		String query = "UPDATE tweets,users SET tweets.content=? WHERE tweets.id=? and users.id=?";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1, tweet.getContent());
			statement.setInt(2, tweet.getId());
			statement.setInt(3, uid);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Add comment to tweet */
	public void addComment(Tweet tweet, Integer uid) {
		String query = "INSERT INTO comments (`id`,`uid`,`comment`,`tid`) VALUES (0, ?, ?, ?);";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setInt(1, uid);
			statement.setString(2, tweet.getComment());
			statement.setInt(3, tweet.getId());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Get tweets from a user given start and end*/
	public List<Tweet> getUserTweets(Integer uid,Integer start, Integer end) {
		 String query = "SELECT tweets.id,tweets.uid,tweets.postdatetime,tweets.content,users.name FROM tweets INNER JOIN users ON tweets.uid = users.id where tweets.uid = ? ORDER BY tweets.postdatetime DESC LIMIT ?,? ;";
		
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,uid);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
       		     tweet.setId(rs.getInt("id"));
				 tweet.setUid(rs.getInt("uid"));
				 tweet.setPostDateTime(rs.getTimestamp("postdatetime"));
				 tweet.setContent(rs.getString("content"));
				 tweet.setUname(rs.getString("name"));
				 l.add(tweet);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	/* Get tweets coment from a user given start and end*/
	public List<Tweet> getUserCommentTweets(Integer tid,Integer start, Integer end) {
		 String query = "select id, uid, comment, tid FROM comments where tid= ? ORDER BY comments.tid DESC LIMIT ?,?;";
		 PreparedStatement statement = null;
		 List<Tweet> l = new ArrayList<Tweet>();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,tid);
			 statement.setInt(2,start);
			 statement.setInt(3,end);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 Tweet tweet = new Tweet();
				 tweet.setCid(rs.getInt("id"));
       		     tweet.setId(rs.getInt("tid"));
				 tweet.setUid(rs.getInt("uid"));
				 tweet.setComment(rs.getString("comment"));
				 l.add(tweet);
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  l;
	}
	
	/* Delete tweets coment*/
	public void deletetUserCommentTweets(Integer cid) {
		 String query = "delete FROM comments where id= ?;";
			PreparedStatement statement = null;
			try {
				statement = db.prepareStatement(query);
				statement.setInt(1,cid);
				statement.executeUpdate();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/* Update existing tweet comment*/
	public void updateComment(Tweet tweet) {
		String query = "UPDATE comments SET comments.comment = ? WHERE comments.id = ?;";
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(query);
			statement.setString(1, tweet.getComment());
			statement.setInt(2, tweet.getCid());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/* Get tweets Comment*/
	public Tweet getTweetComment(Integer cid) {
		 String query = "SELECT id, tid, uid, comment from comments where comments.id = ?";
		 PreparedStatement statement = null;
		 Tweet tweet = new Tweet();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,cid);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
				 tweet.setCid(rs.getInt("id"));
       		     tweet.setId(rs.getInt("tid"));
				 tweet.setUid(rs.getInt("uid"));
				 tweet.setComment(rs.getString("comment"));
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  tweet;
	}
	
	
	/* Get 1 tweets from a user given */
	public Tweet getUserTweet(Integer uid,Integer id) {
		 String query = "SELECT tweets.id,tweets.uid,tweets.postdatetime,tweets.content,users.name FROM tweets INNER JOIN users ON tweets.uid = users.id where tweets.uid = ? and tweets.id = ?";
		 PreparedStatement statement = null;
		 Tweet tweet = new Tweet();
		 try {
			 statement = db.prepareStatement(query);
			 statement.setInt(1,uid);
			 statement.setInt(2,id);
			 ResultSet rs = statement.executeQuery();
			 while (rs.next()) {
       		     tweet.setId(rs.getInt("id"));
				 tweet.setUid(rs.getInt("uid"));
				 tweet.setPostDateTime(rs.getTimestamp("postdatetime"));
				 tweet.setContent(rs.getString("content"));
				 tweet.setUname(rs.getString("name"));
			 }
			 rs.close();
			 statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return  tweet;
	}
	
	
}