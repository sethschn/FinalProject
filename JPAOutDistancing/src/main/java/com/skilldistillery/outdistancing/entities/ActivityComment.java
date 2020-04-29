package com.skilldistillery.outdistancing.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
//	@Column(name = "activity_id")
//	private int activityId;
	
//	@OneToOne
	@Column(name = "in_reply_id")
	private Integer parentComment;
	
	@ManyToOne
	@JoinColumn(name="activity_id")
	private Activity activity;


	//CONSTRUCTORS
	public ActivityComment() {}

	

	


	public ActivityComment(int id, String content, LocalDate createDate, int enabled, User user,
			Integer parentComment, Activity activity) {
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

	

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}




	public Integer getParentComment() {
		return parentComment;
	}

	public void setParentComment(Integer parentComment) {
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
