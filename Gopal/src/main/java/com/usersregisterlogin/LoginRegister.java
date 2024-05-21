package com.usersregisterlogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.jdbcservice.JdbcConnection;
import com.jobportal.users.Admin;
import com.jobportal.users.Employer;
import com.jobportal.users.JobSeeker;
import com.jobportal.users.UserType;

public class LoginRegister {

    public static void registerAdmin(Admin admin) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = JdbcConnection.connectdatabase();
            String sql = "INSERT INTO Admins (ADMIN_ID,USERNAME, PASSWORD, EMAIL) VALUES (admin_id_seq.NEXTVAL,?, ?, ?)";
            statement = con.prepareStatement(sql);

            
            statement.setString(1, admin.getUsername());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getEmail());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Admin registered successfully.");
            } else {
                System.out.println("Failed to register admin.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close statement and connection in finally block
            try {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    //Employer employee = new Employer(userName,email,password,UserType.EMPLOYER,domain,cName,cRegisterNumber,contactDetails);
    
//    public static void registerEmployee(Employer employer) throws ClassNotFoundException {
//        Connection con = null;
//        PreparedStatement statement = null;
//        try {
//            con = JdbcConnection.connectdatabase();
//           String sql = "INSERT INTO Employers (ID,USERNAME,EMAIL, PASSWORD,COMPANYNAME,REGISTERNUMBER,PHONE_NUMBER,DOMAIN) VALUES (emp_id_seq.NEXTVAL,?, ?, ?, ?, ?, ?, ?)";
//            //String sql = "INSERT INTO Employers (USERNAME, PASSWORD) VALUES (?, ?)";
//
//            statement = con.prepareStatement(sql);
//
//            //statement.setInt(1,    employee.getEmployerId());
//            statement.setString(1, employer.getUsername());
//            statement.setString(2, employer.getEmail());
//            statement.setString(3, employer.getPassword());
//            statement.setString(4, employer.getCompanyName());
//            statement.setInt(5, employer.getRegisterNumber());
//            statement.setString(6, employer.getPhone());
//            statement.setString(7, employer.getDomain());
//            
//                 
//            int rowsInserted = statement.executeUpdate();
//
//            if (rowsInserted > 0) {
//                System.out.println("Employee registered successfully.");
//            } else {
//                System.out.println("Failed to register admin.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // Close statement and connection in finally block
//            try {
//                if (statement != null) {
//                    statement.close();
//                }
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
    public static void registerEmployee(Employer employer) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = JdbcConnection.connectdatabase();
            String sql = "INSERT INTO Employers (ID,USERNAME,EMAIL, PASSWORD,COMPANYNAME,REGISTERNUMBER,PHONE_NUMBER,DOMAIN) VALUES (emp_id_seq.NEXTVAL,?, ?, ?, ?, ?, ?, ?)";
            statement = con.prepareStatement(sql);
            statement.setString(1, employer.getUsername());
            statement.setString(2, employer.getEmail());
            statement.setString(3, employer.getPassword());
            statement.setString(4, employer.getCompanyName());
            statement.setInt(5, employer.getRegisterNumber());
            statement.setString(6, employer.getPhone());
            statement.setString(7, employer.getDomain());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Employee registered successfully.");
            } else {
                System.out.println("Failed to register employee.");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Unique constraint violation: " + e.getMessage());
            // Handle the violation here, maybe inform the user that the provided data violates a unique constraint
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close statement and connection in finally block
            try {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static boolean isUsernameExists(String username) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = JdbcConnection.connectdatabase();
            String sql = "SELECT COUNT(*) FROM Employers WHERE USERNAME = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Returns true if count > 0, indicating that the username exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
       
        }
        return false; // Return false by default (in case of exceptions or no result)
    }

   
    public static void registerJobSeeker( JobSeeker jobseeker) throws SQLException, ClassNotFoundException {
    	Connection con = null;
        PreparedStatement statement = null;
		try {
			 con = JdbcConnection.connectdatabase();
			 String sql = "INSERT INTO Job_Seekers (JOB_SEEKER_ID,USERNAME,PASSWORD, EMAIL) VALUES (job_see_seq.NEXTVAL, ?, ?, ?)";
			 statement = con.prepareStatement(sql);
		     statement.setString(1, jobseeker.getUsername());
	         statement.setString(2, jobseeker.getPassword());
	         statement.setString(3, jobseeker.getEmail());
	         int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
				System.out.println("Job seeker registered successfully.");
			} else {
				System.out.println("Failed to register job seeker.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    public static boolean isSeekerUsernameExists(String username) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = JdbcConnection.connectdatabase();
            String sql = "SELECT COUNT(*) FROM job_seekers WHERE USERNAME = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Returns true if count > 0, indicating that the username exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
       
        }
        return false; // Return false by default (in case of exceptions or no result)
    }
    public static Object userslogin(String username, String password,UserType userType) throws ClassNotFoundException, SQLException {
    	Connection con = JdbcConnection.connectdatabase();
    	String sql ="";
    	switch(userType) {
		case ADMIN:
			 sql = "SELECT * FROM Admins WHERE USERNAME = ? AND PASSWORD = ?";
			break;
		case EMPLOYER:
			sql = "SELECT * FROM employers WHERE USERNAME = ? AND PASSWORD = ? ";
			break;
		case JOBSEEKER:
			sql = "SELECT * FROM Job_Seekers WHERE USERNAME = ? AND PASSWORD = ?";
			break;
		default:
			break;
			}
    	PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, username);
		st.setString(2, password);
	    ResultSet rt = st.executeQuery();
	    if (rt.next()) {
            switch (userType) {
            case EMPLOYER:
                Employer employer = new Employer();
                employer.setUsername(rt.getString("USERNAME"));
                employer.setEmail(rt.getString("EMAIL"));
                employer.setId(rt.getInt("ID"));
                employer.setPassword(rt.getString("PASSWORD"));
                employer.setRegisterNumber(rt.getInt("REGISTERNUMBER"));
                employer.setCompanyName(rt.getString("CONTACT"));
                employer.setDomain(rt.getString("DOMAIN"));
                employer.setContact(rt.getString("CONTACT"));
                // Set other employer properties similarly
                return employer;
			case ADMIN:
				Admin admin =new Admin();
				admin.setAdminID(rt.getInt("ADMIN_ID"));
				admin.setUsername(rt.getString("USERNAME"));
				admin.setEmail(rt.getString("EMAIL"));
				admin.setPassword(rt.getString("PASSWORD"));
				return admin;
				
			case JOBSEEKER:
				JobSeeker jobseeker = new JobSeeker();
				jobseeker.setJobSeekerId(rt.getInt("JOB_SEEKER_ID"));
				jobseeker.setUsername(rt.getString("USERNAME"));
				jobseeker.setPassword(rt.getString("PASSWORD"));
				jobseeker.setEmail(rt.getString("EMAIL"));
				jobseeker.setContactDetails(rt.getString("CONTACT_DETAILS"));
				jobseeker.setEducation(rt.getString("EDUCATION"));
				jobseeker.setEducation(rt.getString("EXPERIENCE"));
				jobseeker.setSkills(rt.getString("SKILLS"));
				jobseeker.setGender(rt.getString("GENDER"));
				jobseeker.setAddress(rt.getString("ADDRESS"));
				jobseeker.setLanguageSkills(rt.getString("LANGUAGE"));
				jobseeker.setAchivements(rt.getString("ACHIVEMENTS"));
				jobseeker.setObjective(rt.getString("OBJECTIVE"));
				jobseeker.setFirstName(rt.getString("FNAME"));
				jobseeker.setLastName(rt.getString("LNAME"));
				return jobseeker;
			default:
				break;
            }
	    }
		//return resultSet.next();
    	
    		return null;
    	}

    

		

	}
