package models;
import java.sql.Timestamp;

public class Tweet implements java.io.Serializable {

	 private static final long serialVersionUID = 1L;

	 private int id;
	 private int uid;
	 private int cid;
	 private boolean liked;
	 private boolean cliked;
	 private String uname;
	 private Timestamp postDateTime;
	 private String content;
	 private String comment;

	 public Tweet() {
		 this.liked = false;
		 this.cliked = false;
	 }

	 public Integer getId() {
		 return this.id;
	 }
	 
	 public Integer getCid() {
		 return this.cid;
	 }
	 
	 public void setCid(Integer cid) {
		 this.cid = cid;
	 }
	 
	 public void setId(Integer id) {
		 this.id = id;
	 }

	 public int getUid() {
		 return this.uid;
	 }
	 
	 public void setUid(int uid) {
		 this.uid = uid;
	 }
	 
	 public boolean getLiked() {
		 return this.liked;
	 }
	 
	 public void setLiked(boolean liked) {
		 this.liked = liked;
	 }
	 
	 public boolean getCliked() {
		 return this.cliked;
	 }
	 
	 public void setCliked(boolean commentLiked) {
		 this.cliked = commentLiked;
	 }
	 
	 
	 public String getUname() {
		 return this.uname;
	 }
	 
	 public void setUname(String uname) {
		 this.uname = uname;
	 }
	 
	 public void setComment(String comment) {
		 this.comment = comment;
	 }
	 public String getComment() { return this.comment;}
	 
	 public Timestamp getPostDateTime() {
		 return this.postDateTime;
	 }
	 public void setPostDateTime(Timestamp postDateTime) {
		 this.postDateTime = postDateTime;
	 }
	 
	 public String getContent() {
		 return this.content;
	 }
	 public void setContent(String content) {
		 this.content = content;
	 }

}