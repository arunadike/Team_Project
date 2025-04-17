package com.Project3.Project3.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "assistancerequest")
public class AssistanceRequest {
	public AssistanceRequest() {
	}

	@Id
	private int requestId;
	@NotNull
	private int userId;
	@NotNull
	private String issueDescription;
	@NotNull
	private String status;
	private LocalDateTime resolutionTime;

	public AssistanceRequest(int requestId, @NotNull int userId, @NotNull String issueDescription,
			@NotNull String status, LocalDateTime resolutionTime) {
		super();
		this.requestId = requestId;
		this.userId = userId;
		this.issueDescription = issueDescription;
		this.status = status;
		this.resolutionTime = resolutionTime;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public LocalDateTime getResolutionTime() {
		return resolutionTime;
	}

	public void setResolutionTime(LocalDateTime resolutionTime) {
		this.resolutionTime = resolutionTime;
	}

	@Override
	public String toString() {
		return "AssistanceRequest [requestId=" + requestId + ", userId=" + userId + ", issueDescription="
				+ issueDescription + ", status=" + status + ", resolutionTime=" + resolutionTime + "]";
	}

}