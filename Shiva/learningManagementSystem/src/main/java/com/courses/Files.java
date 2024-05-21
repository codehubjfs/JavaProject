package com.courses;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class Files {
	private int fileid;
	private String fileName;
	private String contentType;
	private int contentId;
	public Files(String fileName, String contentType, int contentId) {
		super();
		this.fileName = fileName;
		this.contentType = contentType;
		this.contentId = contentId;
	}
	public Files(int fileid, String fileName, String contentType, int contentId) {
		super();
		this.fileid = fileid;
		this.fileName = fileName;
		this.contentType = contentType;
		this.contentId = contentId;
	}
	public int getFileid() {
		return fileid;
	}
	public void setFileid(int fileid) {
		this.fileid = fileid;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	@Override
	public String toString() {
		return "files [fileid=" + fileid + ", fileName=" + fileName + ", contentType=" + contentType + ", contentId="
				+ contentId + ", getFileid()=" + getFileid() + ", getFileName()=" + getFileName()
				+ ", getContentType()=" + getContentType() + ", getContentId()=" + getContentId() + "]";
	}
	public static void viewFiles(Statement st) throws SQLException
	{
		ResultSet rs=st.executeQuery("select * from files");
		while(rs.next())
		{
			System.out.println(rs.getInt("fileid")+" "+rs.getString("filepath")+" "+rs.getString("filetype")+" "+rs.getInt("contentid"));
		}
	}
}
