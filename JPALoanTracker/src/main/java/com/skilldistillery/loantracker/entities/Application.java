package com.skilldistillery.loantracker.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "application")
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "loan_number")
	private Integer loanNumber;
	@Column(name = "property_address")
	private String propertyAddress;
	@Column(name = "loan_amount")
	private String loanAmount;
	@Column(name = "loan_type")
	private String loanType;
	@Column(name = "enable")
	private boolean enable = true;
	private String purpose;
	@Column(name = "submitted_date")
	private LocalDate submittedDate;
	private String status;

	@ManyToOne
	@JoinColumn(name = "borrower_id")
	private Borrower borrower;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	@OneToOne(mappedBy = "application", cascade = CascadeType.ALL)
	@JsonIgnore
	private Underwriting underwriting;

	@OneToMany(mappedBy = "application")
	@JsonIgnore
	private List<Status> statuses;
	
	
	// Constructors
	public Application() {
		super();
	}

	public Application(int id, Integer loanNumber, String propertyAddress, String loanAmount, String loanType,
			boolean enable, String purpose, LocalDate submittedDate, String status, Borrower borrower, User user,
			Underwriting underwriting, List<Status> statuses) {
		super();
		this.id = id;
		this.loanNumber = loanNumber;
		this.propertyAddress = propertyAddress;
		this.loanAmount = loanAmount;
		this.loanType = loanType;
		this.enable = enable;
		this.purpose = purpose;
		this.submittedDate = submittedDate;
		this.status = status;
		this.borrower = borrower;
		this.user = user;
		this.underwriting = underwriting;
		this.statuses = statuses;
	}



	// getters and setters

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}
	
	public boolean isEnabled() {
		return enable;
	}

	public void setEnabled(boolean enabled) {
		this.enable = enabled;
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

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public List<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}

	public Underwriting getUnderwriting() {
		return underwriting;
	}

	public void setUnderwriting(Underwriting underwriting) {
		this.underwriting = underwriting;
	}
	
	public Integer getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(Integer loanNumber) {
		this.loanNumber = loanNumber;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void addStatus(Status status) {
		if (statuses == null)
			statuses = new ArrayList<>();
		statuses.add(status);
		status.setApplication(this);
	}

	// Hashcode and Equals
	@Override
	public int hashCode() {
		return Objects.hash(id, loanAmount, loanType, propertyAddress, purpose, status, submittedDate);
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
				&& Objects.equals(submittedDate, other.submittedDate);
	}

	// toString
	@Override
	public String toString() {
		return "Application [id=" + id + ", userId=" + ", propertyAddress=" + propertyAddress + ", loanAmount="
				+ loanAmount + ", loanType=" + loanType + ", purpose=" + purpose + ", submittedDate=" + submittedDate
				+ ", status=" + status + "]";
	}
}
