package com.skilldistillery.outdistancing.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Activity {
	// F I E L D S 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	@Column(name = "short_description")
	private String shortDescription;
	
	private int enabled;
	
	@Column(name = "creator_id")
	private int creatorId;
	
	private String description;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "equipment_level")
	@Enumerated(EnumType.STRING)
	private EquipmentLevel equipmentLevel;
	
	@Column(name = "equipment_description")
	private String equipmentDescription;
	
	@Column(name = "create_date")
	private LocalDate createDate;
	
	public Activity() {}

	public Activity(int id, String title, String shortDescription, int enabled, int creatorId, String description,
			String imageUrl, EquipmentLevel equipmentLevel, String equipmentDescription, LocalDate createDate) {
		super();
		this.id = id;
		this.title = title;
		this.shortDescription = shortDescription;
		this.enabled = enabled;
		this.creatorId = creatorId;
		this.description = description;
		this.imageUrl = imageUrl;
		this.equipmentLevel = equipmentLevel;
		this.equipmentDescription = equipmentDescription;
		this.createDate = createDate;
	}

	//GETTERS & SETTERS
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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
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

	public EquipmentLevel getEquipmentLevel() {
		return equipmentLevel;
	}

	public void setEquipmentLevel(EquipmentLevel equipmentLevel) {
		this.equipmentLevel = equipmentLevel;
	}

	public String getEquipmentDescription() {
		return equipmentDescription;
	}

	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", title=" + title + ", shortDescription=" + shortDescription + ", enabled="
				+ enabled + ", creatorId=" + creatorId + ", description=" + description + ", imageUrl=" + imageUrl
				+ ", equipmentLevel=" + equipmentLevel + ", equipmentDescription=" + equipmentDescription
				+ ", createDate=" + createDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + creatorId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + enabled;
		result = prime * result + ((equipmentDescription == null) ? 0 : equipmentDescription.hashCode());
		result = prime * result + ((equipmentLevel == null) ? 0 : equipmentLevel.hashCode());
		result = prime * result + id;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((shortDescription == null) ? 0 : shortDescription.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Activity other = (Activity) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (creatorId != other.creatorId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (enabled != other.enabled)
			return false;
		if (equipmentDescription == null) {
			if (other.equipmentDescription != null)
				return false;
		} else if (!equipmentDescription.equals(other.equipmentDescription))
			return false;
		if (equipmentLevel != other.equipmentLevel)
			return false;
		if (id != other.id)
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (shortDescription == null) {
			if (other.shortDescription != null)
				return false;
		} else if (!shortDescription.equals(other.shortDescription))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
	

}
