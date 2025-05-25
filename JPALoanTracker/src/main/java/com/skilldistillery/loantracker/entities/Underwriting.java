package com.skilldistillery.loantracker.entities;

import java.time.LocalDate;
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
@Table(name = "underwriting")
public class Underwriting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "application_id")
	private int applicationId;
	@Column(name = "underwriter_name")
	private String underwriterName;
	private String findings;
	@Column(name = "reviewed_date")
	private LocalDate reviewedDate;
	private String decision;

	public Underwriting() {
		super();
	}

	public Underwriting(int id, int applicationId, String underwriterName, String findings, LocalDate reviewedDate,
			String decision, User user) {
		super();
		this.id = id;
		this.applicationId = applicationId;
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

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
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

	@Override
	public int hashCode() {
		return Objects.hash(applicationId, decision, findings, id, reviewedDate, underwriterName);
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
		return applicationId == other.applicationId && Objects.equals(decision, other.decision)
				&& Objects.equals(findings, other.findings) && id == other.id
				&& Objects.equals(reviewedDate, other.reviewedDate)
				&& Objects.equals(underwriterName, other.underwriterName);
	}

	@Override
	public String toString() {
		return "Underwriting [id=" + id + ", applicationId=" + applicationId + ", underwriterName=" + underwriterName
				+ ", findings=" + findings + ", reviewedDate=" + reviewedDate + ", decision=" + decision + ", user="
				+ "]";
	}

}
