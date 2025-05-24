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
@Table(name = "closed")
public class Closed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "closing_date")
	private LocalDate closingDate;

	@Column(name = "settlement_agent")
	private String settlementAgent;

	@Column(columnDefinition = "TEXT")
	private String notes;

	@OneToOne
	@JoinColumn(name = "application_id")
	private Application application;

	public Closed() {
		super();
	}

	public Closed(int id, LocalDate closingDate, String settlementAgent, String notes, Application application) {
		super();
		this.id = id;
		this.closingDate = closingDate;
		this.settlementAgent = settlementAgent;
		this.notes = notes;
		this.application = application;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDate closingDate) {
		this.closingDate = closingDate;
	}

	public String getSettlementAgent() {
		return settlementAgent;
	}

	public void setSettlementAgent(String settlementAgent) {
		this.settlementAgent = settlementAgent;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public int hashCode() {
		return Objects.hash(application, closingDate, id, notes, settlementAgent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Closed other = (Closed) obj;
		return Objects.equals(application, other.application) && Objects.equals(closingDate, other.closingDate)
				&& id == other.id && Objects.equals(notes, other.notes)
				&& Objects.equals(settlementAgent, other.settlementAgent);
	}

	@Override
	public String toString() {
		return "Closed [id=" + id + ", closingDate=" + closingDate + ", settlementAgent=" + settlementAgent + ", notes="
				+ notes + ", application=" + application + "]";
	}

}
