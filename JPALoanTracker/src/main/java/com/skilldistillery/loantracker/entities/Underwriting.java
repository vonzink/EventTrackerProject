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
@Table(name = "underwriting")
public class Underwriting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "underwriter_name")
	private String underwriterName;
	private String findings;
	@Column(name = "reviewed_date")
	private LocalDate reviewedDate;
	private String decision;

	@OneToOne
	@JoinColumn(name = "application_id")
	@JsonIgnore
	private Application application;
	public Underwriting() {
		super();
	}

	public Underwriting(int id, Application application, String underwriterName, String findings,
			LocalDate reviewedDate, String decision) {
		this.id = id;
		this.application = application;
		this.underwriterName = underwriterName;
		this.findings = findings;
		this.reviewedDate = reviewedDate;
		this.decision = decision;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUnderwriterName() {
		return underwriterName;
	}

	public void setUnderwriterName(String underwriterName) {
		this.underwriterName = underwriterName;
	}

	public String getFindings() {
		return findings;
	}

	public void setFindings(String findings) {
		this.findings = findings;
	}

	public LocalDate getReviewedDate() {
		return reviewedDate;
	}

	public void setReviewedDate(LocalDate reviewedDate) {
		this.reviewedDate = reviewedDate;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public int hashCode() {
		return Objects.hash(application, decision, findings, id, reviewedDate, underwriterName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Underwriting other = (Underwriting) obj;
		return Objects.equals(application, other.application) && Objects.equals(decision, other.decision)
				&& Objects.equals(findings, other.findings) && id == other.id
				&& Objects.equals(reviewedDate, other.reviewedDate)
				&& Objects.equals(underwriterName, other.underwriterName);
	}

	@Override
	public String toString() {
		return "Underwriting [id=" + id + ", underwriterName=" + underwriterName + ", findings=" + findings
				+ ", reviewedDate=" + reviewedDate + ", decision=" + decision + ", application=" + application + "]";
	}

}
