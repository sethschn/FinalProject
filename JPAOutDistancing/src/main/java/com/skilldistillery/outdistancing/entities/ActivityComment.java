package com.skilldistillery.outdistancing.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "activity_comment")
public class ActivityComment {
	
	// F I E L D S 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String content;
	
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	private boolean enabled;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
//	@Column(name = "activity_id")
//	private int activityId;
	
	@ManyToOne
	@JoinColumn(name = "in_reply_id")
	private ActivityComment parentComment;
	
	@ManyToOne
	@JoinColumn(name="activity_id")
	private Activity activity;
	
	@JsonIgnore
	@OneToMany(mappedBy="parentComment")
	private List<ActivityComment> replies;


	//CONSTRUCTORS
	public ActivityComment() {}

	

	


	public ActivityComment(int id, String content, LocalDateTime createDate, boolean enabled, User user,
			ActivityComment parentComment, Activity activity) {
		super();
		this.id = id;
		this.content = content;
		this.createDate = createDate;
		this.enabled = enabled;
		this.user = user;
		this.parentComment = parentComment;
		this.activity = activity;
	}

	





	//GETTERS & SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ActivityComment> getReplies() {
		return replies;
	}






	public void setReplies(List<ActivityComment> replies) {
		this.replies = replies;
	}






	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}




	
	public ActivityComment getParentComment() {
		return parentComment;
	}






	public void setParentComment(ActivityComment parentComment) {
		this.parentComment = parentComment;
	}





	

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public String toString() {
		return "ActivityComment [id=" + id + ", content=" + content + ", createDate=" + createDate + ", enabled="
				+ enabled + ", user=" + user + ", parentComment=" + parentComment
				+ ", activity=" + activity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityComment other = (ActivityComment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	

}
