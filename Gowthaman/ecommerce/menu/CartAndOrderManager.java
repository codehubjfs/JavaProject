package com.ecommerce.menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecommerce.customizedexceptions.InvalidCreditCardNumberException;
import com.ecommerce.customizedexceptions.InvalidCvvException;
import com.ecommerce.customizedexceptions.Validation;
import com.ecommerce.users.Customer;
import com.ecommerce.users.DbmsConnection;
import com.ungalkadai.components.Card;
import com.ungalkadai.components.Cart;
import com.ungalkadai.components.Order;
import com.ungalkadai.components.Product;

public class CartAndOrderManager {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
//	static StringBuilder exitBox = new StringBuilder();
//	
//	static {
//        exitBox.append("+----------------------------------+\n");
//        exitBox.append("|   Are you sure to Exit?          |\n");
//        exitBox.append("|  (1-yes | 0-no)                  |\n");
//        exitBox.append("+----------------------------------+\n");
//	}
//	
	public static Customer getCart(Customer customer) {
		String sql = "select cart_id from cart where c_id='"+customer.getCustomerId()+"'";
		
		try {
			Statement statement = DbmsConnection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				customer.setCart(new Cart(resultSet.getInt("cart_id")));
				System.out.println("Data retrieved sucess");
			}else {
				
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return customer;
	}
	
	public static void updateProductTable(Product p) {
		String sql = "update product set p_quantity=p_quantity-? where p_id=?";
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setInt(1, p.getQuantity());
			statement.setInt(2, p.getProductId());
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected>0) {
				//System.out.println("Cart table updated sucessfully");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static int getSequenceNumber() {
		String sql= "select order_sequence.nextval from dual";
		try {
		Statement statement = DbmsConnection.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		if(rs.next()) {
			int id = rs.getInt("nextval");
			return id;
		}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}
	
	public static void makeOrder(Order order,Product p) {
		String sql = "insert into order_product values(?,?,?)";
		try {
			PreparedStatement statement = DbmsConnection.getConnection().prepareStatement(sql);
			statement.setInt(1,order.getOrderId());
			statement.setInt(2, p.getProductId());
			statement.setInt(3, p.getQuantity());
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected>0) {
//				Card card = new Card();
//				card.setOrder(order);
//				card.setAmount(p.getProductPrice()*p.getQuantity());
//				makePayments(card);
				System.out.println("Order has been placed sucessfully");
			}else {
				System.out.println("Error occured!!");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Product> getMyCart(Customer customer) {
		//String sql = "
		List<Product> myProducts = new ArrayList<>();
		//int i =1;
		String sql = "select p.warranty,p.p_brand,p.p_description,p.p_id,p.p_name,p.p_subtitle,p.p_price,cd.quantity \r\n"
				+ "from product p,cart_detail cd,cart ca,customer c\r\n"
				+ "where c.c_id=ca.c_id and c.c_id=? and cd.cart_id=ca.cart_id and cd.p_id=p.p_id";
		try {
		PreparedStatement st = DbmsConnection.getConnection().prepareStatement(sql);
		st.setInt(1, customer.getCustomerId());
		ResultSet rs = st.executeQuery();
		//Product(String productName, int productId, double productPrice, String brand, int quantity, String subtitle
		//,String description,String warranty)
		while(rs.next()) {
			myProducts.add( new Product(
					rs.getString("p_name"),
					rs.getInt("p_id"),
					rs.getDouble("p_price"),
					rs.getString("p_brand"),
					rs.getInt("quantity"),
					rs.getString("p_subtitle"),
					rs.getString("p_description"),
					rs.getString("warranty")
					));
			//i++;
		}
		//Map<Integer, Product> myProducts2 = myProducts;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return myProducts;
	}
	
	public static boolean makePayments(Card card) {
		boolean flag = true;
		int choice = 0,confirmation = 0;
		String cardNumber = "";
		String cardHolderName ="";
		int cvv = 0;
		try {
		do {
		System.out.println("Enter your credit card number in(XXXX XXXX XXXX XXXX)");
		try {
		cardNumber = reader.readLine();
		cardNumber = Validation.isCreditCardNumberValid(cardNumber);
		}catch(InvalidCreditCardNumberException e) {
			System.out.println(e.getMessage());
			continue;
		}
		flag = false;
		}while(flag);
		flag = true;
		do {
			System.out.println("Enter the credit card holder name");
			cardHolderName = reader.readLine();
			if(cardHolderName.length()<=5) {
				System.out.println("Invalid holder name minimum 5 letters");
				continue;
			}
			flag = false;
		}while(flag);
		flag = true;
		do {
			System.out.println("Enter the cvv of the credit card");
			try {
				cvv = Integer.parseInt(reader.readLine());
				cvv = Validation.isCvvValid(cvv);
			}catch(NumberFormatException | InvalidCvvException e) {
				System.out.println(e.getMessage());
				continue;
			}
			flag = false;
		}while(flag);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		card.setCardHolderName(cardHolderName);
		card.setCardNumber(cardNumber);
		card.setCvv(cvv);
		//System.out.println("")
		return card.makePayment(card);
		
	}
}
