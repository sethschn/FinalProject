package com.skilldistillery.outdistancing.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "user_event")
public class UserEvent {

	// FIELDS

	@EmbeddedId
	private UserEventId id;

	private Integer rating;
	
	@Column(name = "rating_comment")
	private String ratingComment;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@MapsId(value = "userId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "event_id")
	@MapsId(value = "eventId")
	private Event event;
	
//	private List<User> users;

	public UserEvent() {
		super();
	}

	public UserEvent(UserEventId id, Integer rating, String ratingComment, User user, Event event) {
		super();
		this.id = id;
		this.rating = rating;
		this.ratingComment = ratingComment;
		this.user = user;
		this.event = event;
	}

	public UserEventId getId() {
		return id;
	}

	public void setId(UserEventId id) {
		this.id = id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getRatingComment() {
		return ratingComment;
	}

	public void setRatingComment(String ratingComment) {
		this.ratingComment = ratingComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

//	public void addUser(User user) {
//		if (this.users == null) {
//			this.users = new ArrayList<User>();
//		}
//		if (!this.users.contains(user)) {
//			this.users.add(user);
////			user.addEvent(this);
//		}
//	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UserEvent other = (UserEvent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserEvent [id=").append(id).append(", rating=").append(rating).append(", ratingComment=")
				.append(ratingComment).append(", user=").append(user).append(", event=").append(event).append("]");
		return builder.toString();
	}
	
	
	
}
