package com.skilldistillery.loantracker.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ctc")
public class ClearToClose {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "application_id")
	private Application application;

	@Column(name = "cleared_by")
	private String clearedBy;

	@Column(name = "ctc_date")
	private LocalDate ctcDate;

	@Column(columnDefinition = "TEXT")
	private String notes;

	// constructors
	public ClearToClose() {
		super();
	}

	public ClearToClose(int id, Application application, String clearedBy, LocalDate ctcDate, String notes) {
		super();
		this.id = id;
		this.application = application;
		this.clearedBy = clearedBy;
		this.ctcDate = ctcDate;
		this.notes = notes;
	}

//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getClearedBy() {
		return clearedBy;
	}

	public void setClearedBy(String clearedBy) {
		this.clearedBy = clearedBy;
	}

	public LocalDate getCtcDate() {
		return ctcDate;
	}

	public void setCtcDate(LocalDate ctcDate) {
		this.ctcDate = ctcDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(application, clearedBy, ctcDate, id, notes);
	}

	// equals and hashcode
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClearToClose other = (ClearToClose) obj;
		return Objects.equals(application, other.application) && Objects.equals(clearedBy, other.clearedBy)
				&& Objects.equals(ctcDate, other.ctcDate) && id == other.id && Objects.equals(notes, other.notes);
	}

	// toString
	@Override
	public String toString() {
		return "ClearToClose [id=" + id + ", application=" + application + ", clearedBy=" + clearedBy + ", ctcDate="
				+ ctcDate + ", notes=" + notes + "]";
	}

}
