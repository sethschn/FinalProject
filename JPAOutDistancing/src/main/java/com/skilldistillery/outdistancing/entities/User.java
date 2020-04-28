package com.skilldistillery.outdistancing.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private boolean enabled;

	@Column(name = "location_id")
	private int locationId;

//	@ManyToOne
//	@JoinColumn(name="location_id")
//	private Location location;

	private String description;

	@Column(name = "image_url")
	private String imageUrl;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDate createDate;

	@ManyToMany
	@JoinTable(name = "user_event", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
	private List<Event> userEvents;

	@ManyToMany
	@JoinTable(name = "favorites_list", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "activity_id"))
	private List<Activity> favoriteActivites;

	@OneToMany(mappedBy = "user")
	private List<Activity> activities;

	@OneToMany(mappedBy = "user")
	private List<Event> events;
	
	@ManyToMany
	@JoinTable(name="group_member",
	joinColumns= @JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="group_id"))
	private List<UserGroup> groups;
	
	@OneToMany(mappedBy="user")
	private List<ActivityComment> activityComments;

	
	@OneToMany(mappedBy="user")
	private List<EventComment> eventComments;
	//METHODS BEGIN: 
	
	
	public User() {
		super();
	}

	public List<Event> getUserEvents() {
		return userEvents;
	}

	public void setUserEvents(List<Event> userEvents) {
		this.userEvents = userEvents;
	}

	public List<Activity> getFavoriteActivites() {
		return favoriteActivites;
	}

	public void setFavoriteActivites(List<Activity> favoriteActivites) {
		this.favoriteActivites = favoriteActivites;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<ActivityComment> getActivityComments() {
		return activityComments;
	}

	public void setActivityComments(List<ActivityComment> activityComments) {
		this.activityComments = activityComments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", username=").append(username).append(", password=")
				.append(password).append(", email=").append(email).append(", firstName=").append(firstName)
				.append(", lastName=").append(lastName).append(", enabled=").append(enabled).append(", locationId=")
				.append(locationId).append(", description=").append(description).append(", imageUrl=").append(imageUrl)
				.append(", role=").append(role).append(", createDate=").append(createDate).append("]");
		return builder.toString();
	}

}
