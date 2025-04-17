package com.Project3.Project3.model;
 
import java.time.LocalDateTime;
 
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
 
@Entity
@Table(name="assistancerequest")
public class AssistanceRequest {
	public  AssistanceRequest() {
	}
	@Id
	private int RequestId;
	@NotNull
	private int UserId;
	@NotNull
	private String IssueDescription;
	@NotNull
	private String Status;
	private LocalDateTime ResolutionTime;

 
	public AssistanceRequest(int requestId, @NotNull int userId, @NotNull String issueDescription,
			@NotNull String status, LocalDateTime resolutionTime) {
		super();
		this.RequestId = requestId;
		this.UserId = userId;
		this.IssueDescription = issueDescription;
		this.Status = status;
		this.ResolutionTime = resolutionTime;
	}
 
	public int getRequestId() {
		return RequestId;
	}
 
	public void setRequestId(int requestId) {
		RequestId = requestId;
	}
 
	public int getUserId() {
		return UserId;
	}
 
	public void setUserId(int userId) {
		UserId = userId;
	}
 
	public String getIssueDescription() {
		return IssueDescription;
	}
 
	public void setIssueDescription(String issueDescription) {
		IssueDescription = issueDescription;
	}
 
	public String getStatus() {
		return Status;
	}
 
	public void setStatus(String status) {
		Status = status;
	}
 
	public LocalDateTime getResolutionTime() {
		return ResolutionTime;
	}
 
	public void setResolutionTime(LocalDateTime resolutionTime) {
		ResolutionTime = resolutionTime;
	}
 
	@Override
	public String toString() {
		return "AssistanceRequest [RequestId=" + RequestId + ", UserId=" + UserId + ", IssueDescription="
				+ IssueDescription + ", Status=" + Status + ", ResolutionTime=" + ResolutionTime + "]";
	}





 
}