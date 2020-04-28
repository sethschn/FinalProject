package com.skilldistillery.outdistancing.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String type;
	
	@Column(name = "short_description")
	private String shortDescription;
	private boolean enabled;
	private String description;
	@Column(name = "image_url")
	private String imageUrl;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category [id=").append(id).append(", type=").append(type).append(", shortDescription=")
				.append(shortDescription).append(", enabled=").append(enabled).append(", description=")
				.append(description).append(", imageUrl=").append(imageUrl).append("]");
		return builder.toString();
	}
	public Category(int id, String type, String shortDescription, boolean enabled, String description,
			String imageUrl) {
		super();
		this.id = id;
		this.type = type;
		this.shortDescription = shortDescription;
		this.enabled = enabled;
		this.description = description;
		this.imageUrl = imageUrl;
	}
	public Category() {
		super();
	}
	
	
}
