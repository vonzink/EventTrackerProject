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
@Table(name = "declined")
public class Declined {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 

    @Column(name = "declined_date")
    private LocalDate declinedDate;

    @Column(columnDefinition = "TEXT")
    private String reason;
    
    @OneToOne
    @JoinColumn(name = "application_id")
    private Application application;

    
	public Declined() {
		super();
	}

	public Declined(int id, LocalDate declinedDate, String reason, Application application) {
		super();
		this.id = id;
		this.declinedDate = declinedDate;
		this.reason = reason;
		this.application = application;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDeclinedDate() {
		return declinedDate;
	}

	public void setDeclinedDate(LocalDate declinedDate) {
		this.declinedDate = declinedDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public int hashCode() {
		return Objects.hash(application, declinedDate, id, reason);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Declined other = (Declined) obj;
		return Objects.equals(application, other.application) && Objects.equals(declinedDate, other.declinedDate)
				&& id == other.id && Objects.equals(reason, other.reason);
	}

	@Override
	public String toString() {
		return "Declined [id=" + id + ", declinedDate=" + declinedDate + ", reason=" + reason + ", application="
				+ application + "]";
	}
 
}
