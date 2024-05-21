package com.courses;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import com.exceptions.ExceptionHandler;
import com.exceptions.InvalidFileException;
import com.exceptions.NotIntegerException;
import com.exceptions.SpecialCharacterException;
public class Content {
	private int contentid;
	private String contentName;
	private int topicId;
	private ArrayList<Files> fileList=new ArrayList<Files>();
	public Content(int contentid, String contentName, int topicId) {
		super();
		this.contentid = contentid;
		this.contentName = contentName;
		this.topicId = topicId;
	}
	public void pushContent(Files f)
	{
		fileList.add(f);
	}
	public Content(int contentid, String contentName) {
		super();
		this.contentid = contentid;
		this.contentName = contentName;
	}
	
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public int getContentid() {
		return contentid;
	}
	public void setContentid(int contentid) {
		this.contentid = contentid;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	@Override
	public String toString() {
		return "Content [contentid=" + contentid + ", contentName=" + contentName + ", getContentid()=" + getContentid()
				+ ", getContentName()=" + getContentName() + "]";
	}
	
}
