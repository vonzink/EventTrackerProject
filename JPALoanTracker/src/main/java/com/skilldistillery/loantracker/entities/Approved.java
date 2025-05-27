package com.skilldistillery.loantracker.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "approved")
public class Approved {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "approval_date")
	private LocalDate approvalDate;

	@Column(name = "approval_notes", columnDefinition = "TEXT")
	private String approvalNotes;

	@Column(name = "interest_rate")
	private Double interestRate;

	@Column(name = "term_years")
	private int termYears;

	@OneToOne
	@JoinColumn(name = "application_id")
	private Application application;

	public Approved() {
		super();
	}

	public Approved(int id, LocalDate approvalDate, String approvalNotes, Double interestRate, int termYears,
			Application application) {
		super();
		this.id = id;
		this.approvalDate = approvalDate;
		this.approvalNotes = approvalNotes;
		this.interestRate = interestRate;
		this.termYears = termYears;
		this.application = application;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(LocalDate approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getApprovalNotes() {
		return approvalNotes;
	}

	public void setApprovalNotes(String approvalNotes) {
		this.approvalNotes = approvalNotes;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public int getTermYears() {
		return termYears;
	}

	public void setTermYears(int termYears) {
		this.termYears = termYears;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public int hashCode() {
		return Objects.hash(application, approvalDate, approvalNotes, id, interestRate, termYears);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Approved other = (Approved) obj;
		return Objects.equals(application, other.application) && Objects.equals(approvalDate, other.approvalDate)
				&& Objects.equals(approvalNotes, other.approvalNotes) && id == other.id
				&& Objects.equals(interestRate, other.interestRate) && termYears == other.termYears;
	}

	@Override
	public String toString() {
		return "Approved [id=" + id + ", approvalDate=" + approvalDate + ", approvalNotes=" + approvalNotes
				+ ", interestRate=" + interestRate + ", termYears=" + termYears + ", application=" + application + "]";
	}

}
