package com.skilldistillery.outdistancing.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@Column (name = "in_reply_id")
	private Integer inReplyId;
	
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;
	
//	@ManyToOne
//	@JoinColumn( name = "user_id")
//	private User user;
	
	//METHODS BEGIN:
	
	public EventComment() {
		
	}
	
	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		this.event = event;
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



//	public int getUserId() {
//		return userId;
//	}
//
//
//
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}



//	public int getEventId() {
//		return eventId;
//	}
//
//
//
//	public void setEventId(int eventId) {
//		this.eventId = eventId;
//	}



	public Integer getInReplyId() {
		return inReplyId;
	}



	public void setInReplyId(Integer inReplyId) {
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
//		builder.append(", userId=");
//		builder.append(userId);
//		builder.append(", eventId=");
//		builder.append(eventId);
		builder.append(", inReplyId=");
		builder.append(inReplyId);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
