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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

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
	@CreationTimestamp
	private LocalDate createDate;
	
	@ManyToMany(mappedBy = "activities")
	 private List<Resource> resources;
	
	@OneToMany(mappedBy="activity_id")
	private List<Event> events;
	
	@ManyToOne
	@JoinColumn(name="creator_id")
	private User user;
	
	
	
//	@OneToMany(mappedBy="")
	private List<ActivityComment> activityComments;
	
	//CONSTRUCTORS
	public Activity() {}

	public Activity(int id, String title, String shortDescription, int enabled, int creatorId, String description,
			String imageUrl, EquipmentLevel equipmentLevel, String equipmentDescription, LocalDate createDate,
			List<Resource> resources) {
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
		this.resources = resources;
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
	
	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	//TOSTRING
	@Override
	public String toString() {
		return "Activity [id=" + id + ", title=" + title + ", shortDescription=" + shortDescription + ", enabled="
				+ enabled + ", creatorId=" + creatorId + ", description=" + description + ", imageUrl=" + imageUrl
				+ ", equipmentLevel=" + equipmentLevel + ", equipmentDescription=" + equipmentDescription
				+ ", createDate=" + createDate + ", resources=" + resources + "]";
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
		Activity other = (Activity) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	

}
