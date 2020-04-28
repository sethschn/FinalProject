package com.skilldistillery.outdistancing.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "activity_comment")
public class ActivityComment {
	
	// F I E L D S 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String content;
	
	@Column(name = "create_date")
	private LocalDate createDate;
	
	private int enabled;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "activity_id")
	private int activityId;
	
	@Column(name = "in_reply_id")
	private int inReplyId;
	
	@ManyToOne
	@JoinColumn(name="activity_id")
	private Activity activity;


	//CONSTRUCTORS
	public ActivityComment() {}
	
	public ActivityComment(int id, String content, LocalDate createDate, int enabled, int userId, int activityId,
			int inReplyId) {
		super();
		this.id = id;
		this.content = content;
		this.createDate = createDate;
		this.enabled = enabled;
		this.userId = userId;
		this.activityId = activityId;
		this.inReplyId = inReplyId;
	}

	//GETTERS & SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getInReplyId() {
		return inReplyId;
	}

	public void setInReplyId(int inReplyId) {
		this.inReplyId = inReplyId;
	}

	//TO STRING
	@Override
	public String toString() {
		return "ActivityComment [id=" + id + ", content=" + content + ", createDate=" + createDate + ", enabled="
				+ enabled + ", userId=" + userId + ", activityId=" + activityId + ", inReplyId=" + inReplyId + "]";
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
