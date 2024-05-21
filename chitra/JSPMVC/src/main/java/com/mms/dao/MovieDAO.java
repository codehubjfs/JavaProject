package com.mms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mms.beans.Movie;
import com.mms.util.DBUtil;

public class MovieDAO {
	
	Connection connection = null;
	ResultSet resultSet = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	
	public List<Movie> get(){
		
		List<Movie> list = null;
		Movie movie = null;
		try {
			
			list = new ArrayList<Movie>();
			String sql = "SELECT * FROM DATABASE.MOVIE";
			connection = DBUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				movie = new Movie();
				movie.setMovieId(resultSet.getInt("movie_id"));
				movie.setMovieName(resultSet.getString("movie_name"));
				movie.setMovieLanguage(resultSet.getString("movie_lang"));
				movie.setMovieType(resultSet.getString("movie_type"));
				movie.setMovieDuration(resultSet.getInt("movie_duration"));
				list.add(movie);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public Movie get(int id) {
		Movie movie = null;
		try {
			movie = new Movie();
			String sql = "SELECT * FROM DATABASE.MOVIE WHERE movie_id="+id;
			connection = DBUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				movie = new Movie();
				movie.setMovieId(resultSet.getInt("movie_id"));
				movie.setMovieName(resultSet.getString("movie_name"));
				movie.setMovieLanguage(resultSet.getString("movie_lang"));
				movie.setMovieType(resultSet.getString("movie_type"));
				movie.setMovieDuration(resultSet.getInt("movie_duration"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return movie;
	}

	public boolean save(Movie e) {
		
		
		boolean flag = false;
		try {
			String sql = "INSERT INTO DATABASE.MOVIE(movie_name,movie_type,movie_lang,movie_duration) VALUES"
					+"('"+e.getMovieName()
					+"', '"+e.getMovieType()
					+"', '"+e.getMovieLanguage()
					+"', "+e.getMovieDuration()
					+")";
			connection = DBUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	public boolean delete(int id) {
		boolean flag = false;
		try {
			String sql = "DELETE FROM DATABASE.MOVIE where movie_id="+id;
			connection = DBUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean update(Movie movie) {
		boolean flag = false;
		try {
			String sql = "UPDATE DATABASE.MOVIE SET movie_name = '"+movie.getMovieName()+"'"+"where movie_id ="+movie.getMovieId();
			connection = DBUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
