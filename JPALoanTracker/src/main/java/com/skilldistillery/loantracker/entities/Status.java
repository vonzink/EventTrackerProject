package com.skilldistillery.loantracker.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="status")
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 50)
	private String status;

	@Column(name = "changed_at")
	private LocalDateTime changedAt;

	@Column(columnDefinition = "TEXT")
	private String notes;

	@ManyToOne
	@JoinColumn(name = "changed_by")
	private User changedBy;

	@ManyToOne
	@JoinColumn(name = "application_id")
	private Application application;

	public Status() {
		super();
	}

	public Status(int id, String status, LocalDateTime changedAt, String notes, User changedBy,
			Application application) {
		super();
		this.id = id;
		this.status = status;
		this.changedAt = changedAt;
		this.notes = notes;
		this.changedBy = changedBy;
		this.application = application;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(LocalDateTime changedAt) {
		this.changedAt = changedAt;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public User getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(User changedBy) {
		this.changedBy = changedBy;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public int hashCode() {
		return Objects.hash(application, changedAt, changedBy, id, notes, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		return Objects.equals(application, other.application) && Objects.equals(changedAt, other.changedAt)
				&& Objects.equals(changedBy, other.changedBy) && id == other.id && Objects.equals(notes, other.notes)
				&& Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", status=" + status + ", changedAt=" + changedAt + ", notes=" + notes
				+ ", changedBy=" + changedBy + ", application=" + application + "]";
	}

}
