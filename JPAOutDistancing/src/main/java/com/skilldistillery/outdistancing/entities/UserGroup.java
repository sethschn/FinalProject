package com.skilldistillery.outdistancing.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "user_group")
public class UserGroup {
	
	//F I E L D S 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name = "short_description")
	private String shortDescription;
	
	private String description;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDate createDate;
	
	@JsonIgnoreProperties(value="groups")
	@ManyToOne
	@JoinColumn(name = "creator_id")
//	@Column(name = "creator_id")
	private User creator;
	
	@JsonIgnoreProperties(value="groups")
	@ManyToMany(mappedBy="groups", cascade = {CascadeType.ALL})
	private List<User> users;
	
	
	private boolean enabled;
	
	
	
	
	//CONSTRUCTORS
	public UserGroup() {}


	public UserGroup(int id, String name, String shortDescription, String description, String imageUrl,
			LocalDate createDate, User creator, List<User> users, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.shortDescription = shortDescription;
		this.description = description;
		this.imageUrl = imageUrl;
		this.createDate = createDate;
		this.creator = creator;
		this.users = users;
		this.enabled = enabled;
	}


	//GETTERS & SETTERS
	
	
	public int getId() {
		return id;
	}

	public List<User> getUsers() {
		return users;
	}



	public void setUsers(List<User> users) {
		this.users = users;
	}



	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
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
	
	

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	

	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void addUser(User user) {
		if (this.users == null) {
			this.users = new ArrayList<User>();
		}
		if (!this.users.contains(user)) {
			this.users.add(user);
			user.addGroup(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserGroup [id=")
		.append(id).append(", name=")
		.append(name).append(", shortDescription=")
		.append(shortDescription)
		.append(", description=")
		.append(description)
		.append(", imageUrl=")
		.append(imageUrl)
		.append(", createDate=")
		.append(createDate)
		.append(", creator=")
		.append(creator)
		.append(", enabled=")
		.append(enabled)
		.append("]");
		return builder.toString();
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
		UserGroup other = (UserGroup) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	

}
