package com.skilldistillery.outdistancing.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private LocalDate createDate;
	
	//CONSTRUCTORS
	public UserGroup() {}

	public UserGroup(int id, String name, String shortDescription, String description, String imageUrl,
			LocalDate createDate) {
		super();
		this.id = id;
		this.name = name;
		this.shortDescription = shortDescription;
		this.description = description;
		this.imageUrl = imageUrl;
		this.createDate = createDate;
	}

	//GETTERS & SETTERS
	public int getId() {
		return id;
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

	//TOSTRING
	@Override
	public String toString() {
		return "UserGroup [id=" + id + ", name=" + name + ", shortDescription=" + shortDescription + ", description="
				+ description + ", imageUrl=" + imageUrl + ", createDate=" + createDate + "]";
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
