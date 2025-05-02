package com.Project3.Project3.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "assistancerequest2")
public class AssistanceRequest {
	public AssistanceRequest(int requestId, Users user, @NotNull String issueDescription, @NotNull String status,
			Timestamp resolutionTime) {
		super();
		this.requestId = requestId;
		this.user = user;
		this.issueDescription = issueDescription;
		this.status = status;
		this.resolutionTime = resolutionTime;
	}

	public AssistanceRequest() {
	}

	@Id
	private int requestId;
	@ManyToOne
	@JoinColumn(name="userid")
	private Users user;
	@NotNull
	private String issueDescription;
	@NotNull
	private String status;
	private Timestamp resolutionTime;

	public void setResolutionTime(Timestamp resolutionTime) {
		this.resolutionTime = resolutionTime;
	}

//	public AssistanceRequest(int requestId, @NotNull int userId, @NotNull String issueDescription,
//			@NotNull String status, Timestamp resolutionTime) {
//		super();
//		this.requestId = requestId;
//		this.userId = userId;
//		this.issueDescription = issueDescription;
//		this.status = status;
//		this.resolutionTime = resolutionTime;
//	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public Users getUserId() {
		return user;
	}

	public void setUserId(Users user) {
		this.user = user;
	}

	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getResolutionTime() {
		return resolutionTime;
	}

	@Override
	public String toString() {
		return "AssistanceRequest [requestId=" + requestId + ", userId=" + user + ", issueDescription="
				+ issueDescription + ", status=" + status + ", resolutionTime=" + resolutionTime + "]";
	}

}