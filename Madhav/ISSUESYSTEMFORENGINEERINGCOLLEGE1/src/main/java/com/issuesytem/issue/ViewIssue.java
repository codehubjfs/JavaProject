package com.issuesytem.issue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.issuesystem.dbconnection.*;

  class IssueIdSortiong implements Comparator<IssueDetails>{
	public int compare(IssueDetails o1,IssueDetails o2) {
		return o1.getIssueId()-o2.getIssueId();
		
	}
	
}

  
  
  //Individual history  of all Issue
public class ViewIssue {
	
	public static void toViewIssue(Scanner sc, String username) throws SQLException {
		
		List<IssueDetails> issueList = new ArrayList<>();
		String sql="select * from issue where raised_by=?";
		PreparedStatement stmt=DBConnection.getDBConnextion().prepareStatement(sql);
		stmt.setString(1,username);
		
		ResultSet rs=stmt.executeQuery();
		boolean idFlag = false;
        while(rs.next()) {
            idFlag = true;
            break;
        }
        if(idFlag) {
        	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    		System.out.println(String.format("%-10s%-75s%-55s%-25s%-15s%-20s%-25s%-20s%-30s%-20s%-25s",
    		        "ISSUE_ID", "DESCRIPTION", "LOCATION", "TICKET_RAISED_DATE",
    		        "CATEGORY_ID", "RAISED_BY", "ALLOCATED_TO", "PRIORITY", "ISSUETITLE",
    		        "ISSUE_DATE", "STATUS"));

    		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        	
    		String sql1="select * from issue where raised_by=?";
    		PreparedStatement stmt1=DBConnection.getDBConnextion().prepareStatement(sql);
    		stmt1.setString(1,username);
    		
    		ResultSet rs1=stmt1.executeQuery();
    		
    		while(rs1.next()) {
                 IssueDetails issue = new IssueDetails(
                         rs1.getInt("ISSUE_ID"), rs1.getString("DESCRIPTION"), rs1.getString("LOCATION"),
                         rs1.getDate("TICKET_RAISED_DATE").toLocalDate(), rs1.getInt("CATEGORY_ID"), rs1.getString("RAISED_BY"),
                         rs1.getString("ALLOCATED_TO"), rs1.getString("PRIORITY"), rs1.getString("ISSUETITLE"),
                         rs1.getDate("ISSUE_DATE").toLocalDate(), rs1.getString("STATUS")
                 );
                 issueList.add(issue);
             }
    			
        	 issueList.sort(new IssueIdSortiong());
    		
        	 
        
        	 issueList.forEach(issue -> {
        		 
     		    System.out.printf("%-10s%-75s%-55s%-25s%-15s%-20s%-25s%-20s%-30s%-20s%-25s%n",
     		    		issue.getIssueId(), issue.getDescription(), issue.getLocation(),
     		    		issue.getTicketRaisedDate(), issue.getCategoryId(), issue.getRaisedBy(),
     		    		issue.getAllocatedTo(), issue.getPriority(),issue.getIssueTitle(),
     		    		issue.getIssueDate(), issue.getStatus());
     		   System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
     	         
     		});

            System.out.println(); 
        }
        else {
        	System.out.println("╔════════════════════════════╗");
            System.out.println("║                            ║");
            System.out.println("║  No issue raised by you    ║");
            System.out.println("║                            ║");
            System.out.println("╚════════════════════════════╝");
            System.out.println();
        }
        
        
	}
}
