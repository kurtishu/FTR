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
		try {
			int indexSet = str.indexOf(":");
			String targetStr = str.substring(indexSet + 1).trim();
			targetStr = targetStr.replaceAll("\\[", "|");
			targetStr = targetStr.replaceAll("\\]", "|");
			String[] values = targetStr.trim().split("\\|");
			for (int i = 0 ; i < values.length -1 ; i++) {
				String key = values[i];
				String value = values[i+1];
				if ("reviewer".equalsIgnoreCase(key)) {
					this.reviewer = value;
				} else if ("priority".equalsIgnoreCase(key)) {
					this.priorityLevel = value;
				} else if ("description".equalsIgnoreCase(key)) {
					this.description = value;
				}
			}
		} catch ( Exception e) {
			e.printStackTrace();
		}
		
		this.reviewer =  (this.reviewer  == null) ? "N/A" : this.reviewer;
		this.priorityLevel =  (this.priorityLevel  == null) ? "N/A" : this.priorityLevel;
		this.description =  (this.description  == null) ? str : this.description;
	}

	@Override
	public String toString() {
		return "ResultMode [fileName=" + fileName + ", commentLine="
				+ commentLine + ", reviewer=" + reviewer + ", priorityLevel="
				+ priorityLevel + ", description=" + description + "]";
	}
}
