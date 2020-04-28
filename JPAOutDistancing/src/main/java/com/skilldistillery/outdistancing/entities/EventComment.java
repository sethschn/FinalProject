package com.skilldistillery.outdistancing.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table (name = "event_comment")
public class EventComment {

	//FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String content;
	@Column (name = "create_date")
	@CreationTimestamp
	private LocalDateTime createDate;
	private boolean enabled;
	@Column(name = "user_id")
	private int userId;
	@Column (name = "event_id")
	private int eventId;
	@Column (name = "in_reply_id")
	private int inReplyId;
	
	//METHODS BEGIN:
	
	public EventComment() {
		
	}

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



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public int getEventId() {
		return eventId;
	}



	public void setEventId(int eventId) {
		this.eventId = eventId;
	}



	public int getInReplyId() {
		return inReplyId;
	}



	public void setInReplyId(int inReplyId) {
		this.inReplyId = inReplyId;
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
		EventComment other = (EventComment) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventComment [id=");
		builder.append(id);
		builder.append(", content=");
		builder.append(content);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", eventId=");
		builder.append(eventId);
		builder.append(", inReplyId=");
		builder.append(inReplyId);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
