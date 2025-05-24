package com.skilldistillery.loantracker.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "applications")
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "property_address")
	private String propertyAddress;
	@Column(name = "loan_amount")
	private String loanAmount;
	@Column(name = "loan_type")
	private String loanType;
	private String purpose;
	@Column(name = "submitted_date")
	private LocalDate submittedDate;
	private String status;

	// Constructors
	public Application() {
		super();
	}

	public Application(int id, String userId, String propertyAddress, String loanAmount, String loanType,
			String purpose, LocalDate submittedDate, String status) {
		super();
		this.id = id;
		this.userId = userId;
		this.propertyAddress = propertyAddress;
		this.loanAmount = loanAmount;
		this.loanType = loanType;
		this.purpose = purpose;
		this.submittedDate = submittedDate;
		this.status = status;
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public LocalDate getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(LocalDate submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// Hashcode and Equals
	@Override
	public int hashCode() {
		return Objects.hash(id, loanAmount, loanType, propertyAddress, purpose, status, submittedDate, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Application other = (Application) obj;
		return id == other.id && Objects.equals(loanAmount, other.loanAmount)
				&& Objects.equals(loanType, other.loanType) && Objects.equals(propertyAddress, other.propertyAddress)
				&& Objects.equals(purpose, other.purpose) && Objects.equals(status, other.status)
				&& Objects.equals(submittedDate, other.submittedDate) && Objects.equals(userId, other.userId);
	}

	// toString
	@Override
	public String toString() {
		return "Application [id=" + id + ", userId=" + userId + ", propertyAddress=" + propertyAddress + ", loanAmount="
				+ loanAmount + ", loanType=" + loanType + ", purpose=" + purpose + ", submittedDate=" + submittedDate
				+ ", status=" + status + "]";
	}
}
