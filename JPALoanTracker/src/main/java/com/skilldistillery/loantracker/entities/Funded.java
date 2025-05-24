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
@Table(name = "funded")
public class Funded {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@OneToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @Column(name = "funded_date")
    private LocalDate fundedDate;

    @Column(name = "wire_amount")
    private Double wireAmount;

    @Column(name = "wire_confirmation")
    private String wireConfirmation;
   
	public Funded() {
		super();
	}

	public Funded(int id, Application application, LocalDate fundedDate, Double wireAmount, String wireConfirmation) {
		super();
		this.id = id;
		this.application = application;
		this.fundedDate = fundedDate;
		this.wireAmount = wireAmount;
		this.wireConfirmation = wireConfirmation;
	}

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

	public LocalDate getFundedDate() {
		return fundedDate;
	}

	public void setFundedDate(LocalDate fundedDate) {
		this.fundedDate = fundedDate;
	}

	public Double getWireAmount() {
		return wireAmount;
	}

	public void setWireAmount(Double wireAmount) {
		this.wireAmount = wireAmount;
	}

	public String getWireConfirmation() {
		return wireConfirmation;
	}

	public void setWireConfirmation(String wireConfirmation) {
		this.wireConfirmation = wireConfirmation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(application, fundedDate, id, wireAmount, wireConfirmation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funded other = (Funded) obj;
		return Objects.equals(application, other.application) && Objects.equals(fundedDate, other.fundedDate)
				&& id == other.id && Objects.equals(wireAmount, other.wireAmount)
				&& Objects.equals(wireConfirmation, other.wireConfirmation);
	}

	@Override
	public String toString() {
		return "Funded [id=" + id + ", application=" + application + ", fundedDate=" + fundedDate + ", wireAmount="
				+ wireAmount + ", wireConfirmation=" + wireConfirmation + "]";
	}

	
	
}
