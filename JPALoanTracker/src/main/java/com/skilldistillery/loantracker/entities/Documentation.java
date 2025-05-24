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
@Table(name="documentation")
public class Documentation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @Column(name = "doc_type")
    private String docType;

    @Column(name = "file_path")
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

	public Documentation() {
		super();
	}
	public Documentation(int id, Application application, String docType, String filePath, User uploadedBy,
			LocalDateTime uploadedAt) {
		super();
		this.id = id;
		this.application = application;
		this.docType = docType;
		this.filePath = filePath;
		this.uploadedBy = uploadedBy;
		this.uploadedAt = uploadedAt;
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
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public User getUploadedBy() {
		return uploadedBy;
	}
	public void setUploadedBy(User uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	public LocalDateTime getUploadedAt() {
		return uploadedAt;
	}
	public void setUploadedAt(LocalDateTime uploadedAt) {
		this.uploadedAt = uploadedAt;
	}
	@Override
	public int hashCode() {
		return Objects.hash(application, docType, filePath, id, uploadedAt, uploadedBy);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documentation other = (Documentation) obj;
		return Objects.equals(application, other.application) && Objects.equals(docType, other.docType)
				&& Objects.equals(filePath, other.filePath) && id == other.id
				&& Objects.equals(uploadedAt, other.uploadedAt) && Objects.equals(uploadedBy, other.uploadedBy);
	}
	@Override
	public String toString() {
		return "Documentation [id=" + id + ", application=" + application + ", docType=" + docType + ", filePath="
				+ filePath + ", uploadedBy=" + uploadedBy + ", uploadedAt=" + uploadedAt + "]";
	}

}
