package com.skilldistillery.outdistancing.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Event {

	//FIELDS: 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "creator_id")
	private int creatorId;
	@Column (name = "activity_id")
	private int activityId;
	private String title;
	@Column (name = "event_time")
	private LocalTime eventTime;
	@Column (name = "event_date")
	private LocalDate eventDate;
	@Column (name = "short_description")
	private String shortDescription;
	private boolean enabled;
	@Column (name = "location_id")
	private int locationId;
	private String description;
	@Column (name = "image_url")
	private String imageUrl;
	@Column (name = "create_date")
	@CreationTimestamp
	private LocalDate createDate;
	
	//METHODS BEGIN: 
	public Event() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
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
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
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
		builder.append("Event [id=");
		builder.append(id);
		builder.append(", creatorId=");
		builder.append(creatorId);
		builder.append(", activityId=");
		builder.append(activityId);
		builder.append(", title=");
		builder.append(title);
		builder.append(", eventTime=");
		builder.append(eventTime);
		builder.append(", eventDate=");
		builder.append(eventDate);
		builder.append(", shortDescription=");
		builder.append(shortDescription);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", locationId=");
		builder.append(locationId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append("]");
		return builder.toString();
	}
	
}
