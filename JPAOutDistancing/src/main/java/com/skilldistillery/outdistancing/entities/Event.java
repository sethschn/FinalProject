package com.skilldistillery.outdistancing.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Event {

	// FIELDS:
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
//	@Column(name = "creator_id")
//	private int creatorId;

	private String title;
	@Column(name = "event_time")
	private LocalTime eventTime;
	@Column(name = "event_date")
	private LocalDate eventDate;
	@Column(name = "short_description")
	private String shortDescription;
	private boolean enabled;

	private String description;
	@Column(name = "image_url")
	private String imageUrl;
	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDate createDate;
	
	@JsonIgnore
	@ManyToMany(mappedBy="userEvents")
	private List<User> users;
	
	@JsonIgnore
	@OneToMany(mappedBy = "event")
	private List<EventComment> eventCmts;

	
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@ManyToOne
	@JoinColumn(name = "activity_id")
	private Activity activity;
	
	@JsonIgnore
	@OneToMany(mappedBy = "event")
	private List<EventPhoto> eventPhotos;
	
	@ManyToOne
	@JoinColumn(name = "creator_id")
	private User creator;

	// METHODS BEGIN:
	
	public Event() {

	}
	

	public User getCreator() {
		return creator;
	}


	public void setCreator(User creator) {
		this.creator = creator;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public List<EventPhoto> getEventPhotos() {
		return eventPhotos;
	}

	public void setEventPhotos(List<EventPhoto> eventPhotos) {
		this.eventPhotos = eventPhotos;
	}

	public List<EventComment> getEventCmts() {
		return eventCmts;
	}

	public void setEventCmts(List<EventComment> eventCmts) {
		this.eventCmts = eventCmts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalTime eventTime) {
		this.eventTime = eventTime;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
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
		Event other = (Event) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Title=");
		builder.append(title);
		builder.append(", eventTime=");
		builder.append(eventTime);
		builder.append(", eventDate=");
		builder.append(eventDate);
		builder.append(", shortDescription=");
		builder.append(shortDescription);
		builder.append(", description=");
		builder.append(description);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", location=");
		builder.append(location);
		builder.append(", activity=");
		builder.append(activity);
		return builder.toString();
	}

}
