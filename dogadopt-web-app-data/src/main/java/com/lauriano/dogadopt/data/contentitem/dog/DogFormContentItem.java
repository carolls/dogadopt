package com.lauriano.dogadopt.data.contentitem.dog;

import java.io.Serializable;

public class DogFormContentItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String content;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
