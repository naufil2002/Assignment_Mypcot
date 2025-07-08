package com.example.assignment;

import jakarta.persistence.*;

@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(length = 1000)
    private String description;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Record(int id, String name, String description, boolean active, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.active = active;
		this.category = category;
	}

	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", name=" + name + ", description=" + description + ", active=" + active
				+ ", category=" + category + "]";
	}

}
