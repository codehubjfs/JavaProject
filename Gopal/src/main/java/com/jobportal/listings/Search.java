package com.jobportal.listings;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
public interface Search {
	
	public void searchJobs(String string) throws ClassNotFoundException, SQLException;
}
