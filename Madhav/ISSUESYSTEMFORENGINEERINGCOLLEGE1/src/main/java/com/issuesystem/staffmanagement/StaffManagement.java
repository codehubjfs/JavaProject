package com.issuesystem.staffmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.issuesystem.authentication.DriverAuthentication;
import com.issuesystem.category.Category;
import com.issuesystem.dbconnection.DBConnection;
import com.issuesystem.userdefinedxception.EmailException;
import com.issuesystem.userdefinedxception.InvalidInputException;
import com.issuesystem.userdefinedxception.InvalidNumberException;
import com.issuesystem.users.Profile;
import com.issuesytem.issue.IssueDetails;
import com.issuesytem.issue.ViewIssue;

import com.issuesytem.issue.*;

public class StaffManagement{
	
	public static void chooseMenu(Scanner sc,String username,int role) throws SQLException, InvalidInputException, EmailException {
		boolean menuFlag=true;
		
		while(menuFlag) {
			System.out.println("1.To New Issue "+"\n2.View All Issue"+"\n3.Edit Issue"+"\n4.Edit Profile"+"\n5.To back");
			System.out.print("Enter your choice: ");
			String status=null;
			try {
				int click=Integer.parseInt(sc.next());
				if(click!=1 && click!=2 && click!=3 && click!=4 &&click !=5) {
				   throw new InvalidNumberException("You have entered invalid number");
				
				}
				else {		
					switch(click){
					
					case 1:
						System.out.println("+--------------------------+");
						System.out.println("|     Open New Issue       |");
						System.out.println("+--------------------------+");
						
						if(click==1) {
							 status="Opened";
						}
						newIssue(status,sc);
						
						
						break;
					case 2:
						System.out.println("+--------------------------+");
						System.out.println("|     View All Issue       |");
						System.out.println("+--------------------------+");
						viewissue(sc);
						break;
						
					case 3:
						System.out.println("+---------------------+");
						System.out.println("|     Edit Issue      |");
						System.out.println("+---------------------+");
						editIssue(sc);
						
						break;
						
					case 4:
						System.out.println("+------------------------+");
						System.out.println("|     Profile change     |");
						System.out.println("+------------------------+");
						Profile.editProfile(sc,username,role);
						break;
					case 5:
						menuFlag=false;
						break;
				
					}
				}
		
			
		}
			catch (NumberFormatException e) {
				System.out.println("--------------------------------------------------------");
                System.out.println("   !You have enter a character,Please enter a number!  ");
                System.out.println("--------------------------------------------------------");
                System.out.println();
                sc.nextLine(); 
			}
			catch (InvalidNumberException e) {
				System.out.println("--------------------------------------------------------");
                System.out.println("!"+e.getMessage()+"! ");
                System.out.println("--------------------------------------------------------");
            }
	
		}
		
	}
	
	
	
	
	//New Issue 
	
	public static void newIssue(String status,Scanner sc) throws SQLException {
		String r="Raised";

		String sql1="select * from issue where status=?";
		PreparedStatement stmt1=DBConnection.getDBConnextion().prepareStatement(sql1);
		stmt1.setString(1,r);
		ResultSet rs=stmt1.executeQuery();
		
		List<IssueManage> issueList = new ArrayList<>();
		
		boolean rowsFetched = false;
			while(rs.next()) {
				rowsFetched = true;
				rs.getInt("Issue_id");
			}
			
			if (rowsFetched) {
				String sql2="select * from issue";
				PreparedStatement stmt2=DBConnection.getDBConnextion().prepareStatement(sql2);
				stmt1.setString(1,r);
				ResultSet rs1=stmt1.executeQuery();
				
				while(rs1.next()) {
		            IssueManage issuemanage = new IssueManage(
		                    rs1.getInt("ISSUE_ID"), rs1.getString("DESCRIPTION"), rs1.getString("LOCATION"),
		                    rs1.getDate("TICKET_RAISED_DATE").toLocalDate(), rs1.getInt("CATEGORY_ID"), rs1.getString("RAISED_BY"),
		                    rs1.getString("ALLOCATED_TO"), rs1.getString("PRIORITY"), rs1.getString("ISSUETITLE"),
		                    rs1.getDate("ISSUE_DATE").toLocalDate(), rs1.getString("STATUS")
		            );
		            
		            issueList.add(issuemanage);
		        }
				
				
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(String.format("%-10s%-75s%-55s%-25s%-15s%-20s%-25s%-20s%-30s%-20s%-25s",
				        "ISSUE_ID", "DESCRIPTION", "LOCATION", "TICKET_RAISED_DATE",
				        "CATEGORY_ID", "RAISED_BY", "ALLOCATED_TO", "PRIORITY", "ISSUETITLE",
				        "ISSUE_DATE", "STATUS"));
	
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				
				Collections.sort(issueList);
				//Using Stream printing 
				issueList.stream()
			    .filter(issue -> issue.getStatus().equals("Raised"))
			    .forEach(issuemanage -> {
			        System.out.printf("%-10s%-75s%-55s%-25s%-15s%-20s%-25s%-20s%-30s%-20s%-25s%n",
			                issuemanage.getIssueId(), issuemanage.getDescription(), issuemanage.getLocation(),
			                issuemanage.getTicketRaisedDate(), issuemanage.getCategoryId(), issuemanage.getRaisedBy(),
			                issuemanage.getAllocatedTo(), issuemanage.getPriority(), issuemanage.getIssueTitle(),
			                issuemanage.getIssueTitle(), issuemanage.getStatus());
			        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			    });
				System.out.println();
				
			}
			else {
				System.out.println("  ╔════════════════════════════════════════════════════════════════════════════╗");
				System.out.println("  ║                                                                            ║");
				System.out.println("  ║                        No New issues raised                                ║");
				System.out.println("  ║                                                                            ║");
				System.out.println("  ╚════════════════════════════════════════════════════════════════════════════╝");
	
			}
		 
		
		Statement stmt=DBConnection.getDBConnextion().createStatement();
		String sql="update issue set status='"+status+"' where status='"+r+"'";
		stmt.executeUpdate(sql);

	}
	
	

	// ViewAll Issue
	public static void viewissue(Scanner sc)throws SQLException {
		
		Statement stmt1=DBConnection.getDBConnextion().createStatement();
		ResultSet rs=stmt1.executeQuery("select * from issue");
		
		List<IssueManage> issueList = new ArrayList<>();
		
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(String.format("%-10s%-75s%-55s%-25s%-15s%-20s%-25s%-20s%-30s%-20s%-25s",
		        "ISSUE_ID", "DESCRIPTION", "LOCATION", "TICKET_RAISED_DATE",
		        "CATEGORY_ID", "RAISED_BY", "ALLOCATED_TO", "PRIORITY", "ISSUETITLE",
		        "ISSUE_DATE", "STATUS"));

		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		while(rs.next()) {
	            IssueManage issuemanage = new IssueManage(
	                    rs.getInt("ISSUE_ID"), rs.getString("DESCRIPTION"), rs.getString("LOCATION"),
	                    rs.getDate("TICKET_RAISED_DATE").toLocalDate(), rs.getInt("CATEGORY_ID"), rs.getString("RAISED_BY"),
	                    rs.getString("ALLOCATED_TO"), rs.getString("PRIORITY"), rs.getString("ISSUETITLE"),
	                    rs.getDate("ISSUE_DATE").toLocalDate(), rs.getString("STATUS")
	            );
	            
	            issueList.add(issuemanage);
	        }
			
		Collections.sort(issueList);
			
		issueList.forEach(issuemanage -> {
		    System.out.printf("%-10s%-75s%-55s%-25s%-15s%-20s%-25s%-20s%-30s%-20s%-25s%n",
		    		issuemanage.getIssueId(), issuemanage.getDescription(), issuemanage.getLocation(),
		    		issuemanage.getTicketRaisedDate(), issuemanage.getCategoryId(), issuemanage.getRaisedBy(),
		    		issuemanage.getAllocatedTo(), issuemanage.getPriority(), issuemanage.getIssueTitle(),
		    		issuemanage.getIssueTitle(), issuemanage.getStatus());
		    System.out.println();
		});

			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            
         	System.out.println(); 
   	 
         	int input=checkInput(sc);
		
	
	}
	
	
	
	//check Input
	public static int checkInput(Scanner sc) {
		int input=0;
		boolean inputflag=true;
		while(inputflag) {
		
			try {
				System.out.print("ENTER 1 TO GO_BACK : ");
				input=Integer.parseInt(sc.next());
				if(input!=1) {
					throw new InvalidNumberException("You have entered invalid number");
				}
				inputflag=false;
			}
			
			catch (NumberFormatException e) {
				System.out.println("---------------------------------------------------------");
	            System.out.println("   !You have enter a character,Please enter a number!  ");
	            System.out.println("---------------------------------------------------------");
	            System.out.println();
	            sc.nextLine(); 
			}
			catch (InvalidNumberException e) {
				System.out.println("--------------------------------------------------------");
	            System.out.println("!"+e.getMessage()+"! ");
	            System.out.println("--------------------------------------------------------");
	        }
		}
		return input;
		
	}
	
	
	//Edit Issue
	
	public static void editIssue(Scanner sc) throws SQLException {
		boolean menuFlag=true;
		
		while(menuFlag) {
			System.out.println("1.To Allocate "+"\n2.Update the status"+"\n3.To back");
			System.out.print("Enter your choice: ");
			String status=null;
			try {
				int click=Integer.parseInt(sc.next());
				if(click!=1 && click!=2 && click!=3) {
				   throw new InvalidNumberException("You have entered invalid number");
				
				}
				else {		
					switch(click){
					
					case 1:
						System.out.println("+------------------------+");
						System.out.println("|      Allocate To       |");
						System.out.println("+------------------------+");
						issueAllocation(sc);
						
						break;
					case 2:
						System.out.println("+-----------------------------------------+");
						System.out.println("|     Update the Status to Completed      |");
						System.out.println("+-----------------------------------------+");
						toStatusUpdate(sc);
						break;
						
					case 3:
						menuFlag=false;
						break;
				
					}
				}	
			}
			
			catch (NumberFormatException e) {
				System.out.println("--------------------------------------------------------");
                System.out.println("   !You have enter a character,Please enter a number!  ");
                System.out.println("--------------------------------------------------------");
                System.out.println();
                sc.nextLine(); 
			}
			catch (InvalidNumberException e) {
				System.out.println("--------------------------------------------------------");
                System.out.println("!"+e.getMessage()+"! ");
                System.out.println("--------------------------------------------------------");
            }
	
		}
		
	}

	
	
	// Issue Allocation
	
	public static void issueAllocation(Scanner sc) throws SQLException {
		
		String red = "\u001B[31m";
		String green = "\u001B[32m";
    	String reset = "\u001B[0m";

        System.out.println("-------------------Allocate the person for issue------------------------");

        String r = "Opened";
        boolean flag = false;

        // Try-with-resources for the first query to check if there are issues with "Opened" status
        try (Connection conn = DBConnection.getDBConnextion();
             PreparedStatement stmt1 = conn.prepareStatement("SELECT * FROM issue WHERE status=?")) {

            stmt1.setString(1, r);
            try (ResultSet rs = stmt1.executeQuery()) {
                flag = rs.next();
            }
        }

        if (flag) {
            List<IssueManage> issueList = new ArrayList<>();

            // Try-with-resources for the second query to retrieve all issues
            try (Connection conn = DBConnection.getDBConnextion();
                 PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM issue");
                 ResultSet rs1 = stmt2.executeQuery()) {

                while (rs1.next()) {
                    IssueManage issueManage = new IssueManage(
                            rs1.getInt("ISSUE_ID"), rs1.getString("DESCRIPTION"), rs1.getString("LOCATION"),
                            rs1.getDate("TICKET_RAISED_DATE").toLocalDate(), rs1.getInt("CATEGORY_ID"), rs1.getString("RAISED_BY"),
                            rs1.getString("ALLOCATED_TO"), rs1.getString("PRIORITY"), rs1.getString("ISSUETITLE"),
                            rs1.getDate("ISSUE_DATE").toLocalDate(), rs1.getString("STATUS")
                    );
                    issueList.add(issueManage);
                }
            }

            // Print the issue details
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-10s%-75s%-55s%-25s%-15s%-20s%-25s%-20s%-30s%-20s%-25s",
                    "ISSUE_ID", "DESCRIPTION", "LOCATION", "TICKET_RAISED_DATE",
                    "CATEGORY_ID", "RAISED_BY", "ALLOCATED_TO", "PRIORITY", "ISSUETITLE",
                    "ISSUE_DATE", "STATUS"));
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            issueList.stream()
                    .filter(issue -> "Opened".equals(issue.getStatus()))
                    .sorted()
                    .forEach(issueManage -> {
                        System.out.printf("%-10s%-75s%-55s%-25s%-15s%-20s%-25s%-20s%-30s%-20s%-25s%n",
                                issueManage.getIssueId(), issueManage.getDescription(), issueManage.getLocation(),
                                issueManage.getTicketRaisedDate(), issueManage.getCategoryId(), issueManage.getRaisedBy(),
                                issueManage.getAllocatedTo(), issueManage.getPriority(), issueManage.getIssueTitle(),
                                issueManage.getIssueDate(), issueManage.getStatus());
                    });

            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            boolean isValid = true;
            while (isValid) {
                System.out.println();
                

                int id = 0;
                while (true) {
                    try {
                    	System.out.println("Enter the id that you are going to allocate: ");
                        id = Integer.parseInt(sc.next());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("--------------------------------------------------------");
                        System.out.println("   !You have entered a character, Please enter a number!   ");
                        System.out.println("--------------------------------------------------------");
                        System.out.println();
                    }
                }

                // Try-with-resources for checking and updating the issue status
                try (Connection conn = DBConnection.getDBConnextion()) {
                    String sqlCheck = "SELECT * FROM issue WHERE issue_id=? AND allocated_to=?";
                    try (PreparedStatement stmt3 = conn.prepareStatement(sqlCheck)) {
                        stmt3.setInt(1, id);
                        stmt3.setString(2, "Not Allocated");
                        try (ResultSet rs2 = stmt3.executeQuery()) {
                            if (rs2.next()) {
                                sc.nextLine(); // Consume newline left-over
                                System.out.print("Enter the Person name to be allocated: ");
                                String name = sc.nextLine();

                                // Update the allocated_to column
                                String sql3 = "UPDATE issue SET allocated_to=?, status='In_Progress' WHERE issue_id=?";
                                try (PreparedStatement stmt4 = conn.prepareStatement(sql3)) {
                                    stmt4.setString(1, name);
                                    stmt4.setInt(2, id);
                                    int rowsAffected = stmt4.executeUpdate();
                                    if (rowsAffected > 0) {
                                        System.out.println();
                                        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        				            	System.out.println("┃'"+green+"'Allocated Successfully '"+reset+"'         ┃");
        				            	System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                                        isValid = false;
                                    }
                                }
                            } else {
                            	System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				            	System.out.println("┃'"+red+"'Please enter an available ID. '"+reset+"'                      ┃");
				            	System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Already all issues are Assigned");
            System.out.println("  ╔═══════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("  ║                                                                                   ║");
            System.out.println("  ║                        Already all issues are Assigned                            ║");
            System.out.println("  ║                                                                                   ║");
            System.out.println("  ╚═══════════════════════════════════════════════════════════════════════════════════╝");
        }
    }
	
	
	
	
	//Update the status has completed if the work is finished, which was already assigned
	
	public static void toStatusUpdate(Scanner sc) throws SQLException {
		String red = "\u001B[31m";
		String green = "\u001B[32m";
    	String reset = "\u001B[0m";
    	
    	
        String r = "In_Progress";
        String sql1 = "SELECT * FROM issue WHERE status=?";
        boolean flag = false;

        // Try-with-resources for the first query to check if there are issues with "In_Progress" status
        try (Connection conn = DBConnection.getDBConnextion();
             PreparedStatement stmt1 = conn.prepareStatement(sql1)) {

            stmt1.setString(1, r);
            try (ResultSet rs = stmt1.executeQuery()) {
                flag = rs.next();
            }
        }

        if (flag) {
            List<IssueManage> issueList = new ArrayList<>();
            
            // Try-with-resources for the second query to retrieve issues with "In_Progress" status
            try (Connection conn = DBConnection.getDBConnextion();
                 PreparedStatement stmt2 = conn.prepareStatement(sql1)) {

                stmt2.setString(1, r);
                try (ResultSet rs1 = stmt2.executeQuery()) {
                    while (rs1.next()) {
                        IssueManage issueManage = new IssueManage(
                                rs1.getInt("ISSUE_ID"), rs1.getString("DESCRIPTION"), rs1.getString("LOCATION"),
                                rs1.getDate("TICKET_RAISED_DATE").toLocalDate(), rs1.getInt("CATEGORY_ID"), rs1.getString("RAISED_BY"),
                                rs1.getString("ALLOCATED_TO"), rs1.getString("PRIORITY"), rs1.getString("ISSUETITLE"),
                                rs1.getDate("ISSUE_DATE").toLocalDate(), rs1.getString("STATUS")
                        );
                        issueList.add(issueManage);
                    }
                }
            }

            // Print the issue details
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-10s%-75s%-55s%-25s%-15s%-20s%-25s%-20s%-30s%-20s%-25s",
                    "ISSUE_ID", "DESCRIPTION", "LOCATION", "TICKET_RAISED_DATE",
                    "CATEGORY_ID", "RAISED_BY", "ALLOCATED_TO", "PRIORITY", "ISSUETITLE",
                    "ISSUE_DATE", "STATUS"));
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            Collections.sort(issueList);
            issueList.forEach(issueManage -> {
                System.out.printf("%-10s%-75s%-55s%-25s%-15s%-20s%-25s%-20s%-30s%-20s%-25s%n",
                        issueManage.getIssueId(), issueManage.getDescription(), issueManage.getLocation(),
                        issueManage.getTicketRaisedDate(), issueManage.getCategoryId(), issueManage.getRaisedBy(),
                        issueManage.getAllocatedTo(), issueManage.getPriority(), issueManage.getIssueTitle(),
                        issueManage.getIssueDate(), issueManage.getStatus());
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            });
            System.out.println();

            boolean isValid = true;

            while (isValid) {
                
                int id = 0;
                try {
                	System.out.print("Enter the id that issue has completed: ");
                    id = Integer.parseInt(sc.next());
                } catch (NumberFormatException e) {
                    System.out.println("--------------------------------------------------------");
                    System.out.println("   !You have entered a character, Please enter a number!   ");
                    System.out.println("--------------------------------------------------------");
                    System.out.println();
                    continue; // Re-prompt for ID
                }

                // Try-with-resources for checking and updating the issue status
                try (Connection conn = DBConnection.getDBConnextion()) {
                    String sqlCheck = "SELECT * FROM issue WHERE issue_id=? AND status=?";
                    try (PreparedStatement stmt3 = conn.prepareStatement(sqlCheck)) {
                        stmt3.setInt(1, id);
                        stmt3.setString(2, "In_Progress");
                        try (ResultSet rs3 = stmt3.executeQuery()) {
                            if (rs3.next()) {
                                String sql3 = "UPDATE issue SET status=? WHERE issue_id=?";
                                try (PreparedStatement stmt4 = conn.prepareStatement(sql3)) {
                                    stmt4.setString(1, "Completed");
                                    stmt4.setInt(2, id);
                                    int rowsAffected = stmt4.executeUpdate();
                                    if (rowsAffected > 0) {
                                    	System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        				            	System.out.println("┃'"+green+"'Status Updated Successfully '"+reset+"'    ┃");
        				            	System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                                        
                                        isValid = false;
                                        break;
                                    }
                                }
                            } else {
                            	System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				            	System.out.println("┃'"+red+"'Please enter an available ID. '"+reset+"'                      ┃");
				            	System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                                System.out.println();
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Already all issues are Assigned");
            System.out.println("  ╔═══════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("  ║                                                                                   ║");
            System.out.println("  ║                        Already all issues are Completed                           ║");
            System.out.println("  ║                                                                                   ║");
            System.out.println("  ╚═══════════════════════════════════════════════════════════════════════════════════╝");
        }
    }
	
	
}
