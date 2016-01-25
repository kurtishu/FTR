package com.dreamfactory.ftr.mode;

public class ResultMode {

	private String fileName;
	
	private String commentLine;
	
	private String reviewer;
	
	private String priorityLevel;
	
	private String description;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCommentLine() {
		return commentLine;
	}

	public void setCommentLine(String commentLine) {
		this.commentLine = commentLine;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(String priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void parse(String str) {
		// TODO
		this.description = str;
	}

	@Override
	public String toString() {
		return "ResultMode [fileName=" + fileName + ", commentLine="
				+ commentLine + ", reviewer=" + reviewer + ", priorityLevel="
				+ priorityLevel + ", description=" + description + "]";
	}
}
