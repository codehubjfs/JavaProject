package com.ecommerce.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ecommerce.customizedexceptions.InvalidMenuChoiceException;
import com.ecommerce.customizedexceptions.Validation;
import com.ecommerce.users.Customer;
import com.ecommerce.users.DbmsConnection;
import com.ungalkadai.components.Card;
import com.ungalkadai.components.Category;
import com.ungalkadai.components.Order;
import com.ungalkadai.components.PaymentStatus;
import com.ungalkadai.components.Product;
import com.ungalkadai.components.SubCategory;

public class SearchAndSortManager {
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	static StringBuilder exitBox = new StringBuilder();
	
	static Map<Integer,SubCategory> subCategories = SellerActivity.getAllSubCategoryMap();
	
	static Map<Integer,Product> products = getAllProduct();
	 
	static {
        exitBox.append("+----------------------------------+\n");
        exitBox.append("|   Are you sure to Exit?          |\n");
        exitBox.append("|  (1-yes | 0-no)                  |\n");
        exitBox.append("+----------------------------------+\n");
	}
	
	public static void showFunctionalMenu(Customer customer,Map<Integer,Product> filtered) {
		int choice = 0,confirmation = 0;
		customer = CartAndOrderManager.getCart(customer);
		int o_id = CartAndOrderManager.getSequenceNumber();
		Product choosen = null;
		String address = "";
		int quantity = 0;
		//int product_no = 0;
		boolean flag = true;
		try {
		do {
		System.out.println("+----------------------------------+");
        System.out.println("|        SHOPPING OPTIONS          |");
        System.out.println("+----------------------------------+");
        System.out.println("| 1. Buy a product                 |");
        System.out.println("| 2. Add a Product to Cart         |");
        System.out.println("| 3. Sort                          |");
        System.out.println("| 4. Go Back                       |");
        System.out.println("| 5. Exit                          |");
        //System.out.println("| 6. Exit                          |");
        System.out.println("+----------------------------------+");
        try {
        	choice = Integer.parseInt(reader.readLine());
        	choice = Validation.isOptionValid(1, 5, choice);
        }catch(NumberFormatException e) {
        	System.out.println("Input should be a number.The letter or symbols are not allowed");
			continue;
        }catch(InvalidMenuChoiceException e) {
        	System.out.println(e.getMessage());
        }
        switch(choice) {
       
        case 1:{
        	System.out.println("you have choosen to buy a product");
        	 //filtered.forEach((k,v)->System.out.println(k+" "+v));
        	System.out.println("Enter the s.no of the product");
        	try {
    			choice = Integer.parseInt(reader.readLine());
    			if(!filtered.containsKey(choice)) {
    				System.out.println("Invalid product number");
    				continue;
    			}
    			//choice = Validation.isOptionValid(1, categoryNames.size(), choice);
    		}catch(NumberFormatException e) {
    			System.out.println("Input should be a number.The letter or symbols are not allowed");
    			continue;
    		}catch(Exception e) {
    			System.out.println(e.getMessage());
    			continue;
    		}
        	choosen = filtered.get(choice);
        	do {
        		System.out.println("Enter the number of quantity for the choosen product range(1-10)");
        		try {
        			quantity = Integer.parseInt(reader.readLine());
        			quantity = Validation.isOptionValid(1, 10,quantity);
        		}catch(NumberFormatException e) {
        			System.out.println("Numbers are only allowed");
        			continue;
        		}catch(InvalidMenuChoiceException e) {
        			System.out.println(e.getMessage());
        			continue;
        		}
        		choosen.setQuantity(quantity);
        		flag = false;
        	}while(flag);
        	System.out.println("Enter your address for place the order : ");
        	address = reader.readLine();
        	Order order = new Order();
        	order.setCustomer(customer);
        	order.setOrderDate(LocalDate.now());
        	order.setOrderId(o_id);
        	order.setAddress(address);
        	//boolean status = customer.makeOrder(order);
        	//if(status) {
        		
        		Card card = new Card();
        		card.setOrder(order);
        		card.setAmount(choosen.getProductPrice()*choosen.getQuantity());
        		card.setPaymentStatus(PaymentStatus.COMPLETED);
        		card.setPaymentType("CREDIT CARD");
        		System.out.println("Total amount to be paid : "+(choosen.getProductPrice()*choosen.getQuantity()));
        		customer.makeOrder(order);
        		if(CartAndOrderManager.makePayments(card)) {
        		//customer.makeOrder(order);
        		CartAndOrderManager.updateProductTable(choosen);
        		CartAndOrderManager.makeOrder(order, choosen);
        		System.out.println("Total rs : "+choosen.getProductPrice()*choosen.getQuantity());
        		showFunctionalMenu(customer,filtered);
        		}
        		//}
    
        	//System.out.println(filtered.get(choice));
        	break;
        }
        case 2:{
        	System.out.println("you have choosen to add a product to your cart");
        	 //filtered.forEach((k,v)->System.out.println(k+" "+v));
        	System.out.println("Enter the s.no of the product");
        	try {
    			choice = Integer.parseInt(reader.readLine());
    			if(!filtered.containsKey(choice)) {
    				System.out.println("Invalid product number");
    				continue;
    			}
    			//choice = Validation.isOptionValid(1, categoryNames.size(), choice);
    		}catch(NumberFormatException e) {
    			System.out.println("Input should be a number.The letter or symbols are not allowed");
    			continue;
    		}catch(Exception e) {
    			System.out.println(e.getMessage());
    			continue;
    		}
        	choosen = filtered.get(choice);
        	flag = true;
        	//System.out.println(choosen);
        	do {
        		System.out.println("Enter the number of quantity for the choosen product range(1-10)");
        		try {
        			quantity = Integer.parseInt(reader.readLine());
        			quantity = Validation.isOptionValid(1, 10,quantity);
        		}catch(NumberFormatException e) {
        			System.out.println("Numbers are only allowed");
        			continue;
        		}catch(InvalidMenuChoiceException e) {
        			System.out.println(e.getMessage());
        			continue;
        		}
        		choosen.setQuantity(quantity);
        		flag = false;
        	}while(flag);
        	boolean status = customer.addProductToCart(choosen);
        	if(status) {
        		
        	}
        	break;
        }
        case 3:{
        	System.out.println("You have choosen to sort");
        	showSortOptions(customer,filtered);
        	break;
        }
        case 4:{
        	System.out.println("You are heading to the previous menu");
        	CustomerActivity.showSearchMenu(customer);
        	break;
        }
        case 5:{
        	System.out.println("You have choosen to exit!!!");
			System.out.println(exitBox);
			System.out.println("Enter your choice");
			try {
				confirmation = Integer.parseInt(reader.readLine());
				confirmation = Validation.isOptionValid(0, 1, confirmation);
			}catch(NumberFormatException e) {
				System.out.println("Input should be a number.The letter or symbols are not allowed");
				continue;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			if(confirmation==1) {
				System.out.println("Thanks for visiting "+customer.getAccount().getUserName()+" Have a Nice day :)");
				System.exit(0);
			}
			break;
        }
        }
		}while(true);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
        
	}
	
	public static void showSortOptions(Customer customer,Map<Integer,Product> filtered) {
		int choice = 0,confirmation = 0;
		List<Map.Entry<Integer, Product>> sortedEntries = new ArrayList<>(filtered.entrySet());
		try {
		do {
			System.out.println("+----------------------------------+");
	        System.out.println("|          SORT OPTIONS            |");
	        System.out.println("+----------------------------------+");
	        System.out.println("| 1. Sort by Price (Low to High)   |");
	        System.out.println("| 2. Sort by Price (High to Low)   |");
	        System.out.println("| 3. Sort by Name (A-Z)            |");
	        System.out.println("| 4. Go Back                       |");
	        System.out.println("| 5. Exit                          |");
	        System.out.println("+----------------------------------+");
	        try {
	        	choice = Integer.parseInt(reader.readLine());
	        	choice = Validation.isOptionValid(1, 5, choice);
	        }catch(NumberFormatException e) {
	        	System.out.println("Allowed to enter only number.No letter or symbols are aloowed");
	        	continue;
	        }catch(InvalidMenuChoiceException e) {
	        	System.out.println(e.getMessage());
	        	continue;
	        }
	        switch(choice) {
	        case 1:{
	        	System.out.println("You have choosen to sort the product by price(Low to High)");
	            sortedEntries.sort((entry1, entry2) -> Double.compare(entry1.getValue().getProductPrice(), entry2.getValue().getProductPrice()));
	            sortedEntries.forEach((entry)->System.out.println(entry.getKey()+" "+entry.getValue()));
	            showFunctionalMenu(customer,filtered);
	            break;
	        }
	        case 2:{
	        	System.out.println("You have choosen to sort the product by price(High to Low)");
	            sortedEntries.sort((entry1, entry2) -> Double.compare(entry2.getValue().getProductPrice(), entry1.getValue().getProductPrice()));
	            sortedEntries.forEach((entry)->System.out.println(entry.getKey()+" "+entry.getValue()));
	            break;
	        }
	        case 3:{
	        	System.out.println("You have choosen to sort the product by price(A-Z)");
	        	sortedEntries.sort((entry1,entry2)->entry1.getValue().getProductName().compareTo(entry2.getValue().getProductName()));
	        	sortedEntries.forEach((entry)->System.out.println(entry.getKey()+" "+entry.getValue()));
	            break;
	        }
	        case 4:{
	        	System.out.println("You are heading to the previous menu");
	        	showFunctionalMenu(customer,filtered);
	        	break;
	        }
	        case 5:{
	        	System.out.println("You have choosen to exit!!!");
				System.out.println(exitBox);
				System.out.println("Enter your choice");
				try {
					confirmation = Integer.parseInt(reader.readLine());
					confirmation = Validation.isOptionValid(0, 1, confirmation);
				}catch(NumberFormatException e) {
					System.out.println("Input should be a number.The letter or symbols are not allowed");
					continue;
				}catch(Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				if(confirmation==1) {
					System.out.println("Thanks for visiting "+customer.getAccount().getUserName()+" Have a Nice day :)");
					System.exit(0);
				}
				break;
	        }
	        }
	        showFunctionalMenu(customer,filtered);
		}while(true);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

	
	public static void showAllProduct(Customer customer) {
		products.forEach((k,v)->System.out.println(k+" "+v));
		showFunctionalMenu(customer,products);
	}
	
	public static void searchByCategory(Customer customer) { 
		int choice = 0;
		boolean flag = true;
		List<String> categoryNames = subCategories.values().stream()
			    .map(SubCategory::getCategory) 
			    .map(Category::getCategoryName)
			    .distinct()
			    .collect(Collectors.toList());
		do {
		System.out.println("S.no\tCategoryName");
		categoryNames.forEach((c)->System.out.println((categoryNames.indexOf(c)+1)+"\t"+c));
		System.out.println("Enter your choice : ");
		try {
			choice = Integer.parseInt(reader.readLine());
			choice = Validation.isOptionValid(1, categoryNames.size(), choice);
		}catch(InvalidMenuChoiceException | IOException e) {
			System.out.println(e.getMessage());
			continue;
		}catch(NumberFormatException e) {
			System.out.println("Input should be a number.The letter or symbols are not allowed");
			continue;
		}
		String category = categoryNames.get(choice-1);
		
		System.out.println(category);
		Map<Integer,Product>filtered = products.entrySet().stream()
		.filter(v->v.getValue().getSubCategrory().getCategory().getCategoryName().equalsIgnoreCase(category))
		.collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
//		do {
		filtered.forEach((k,v)->System.out.println(k+"."+" "+v));
//		System.out.println("Enter your choice");
//		try {
//			choice = Integer.parseInt(reader.readLine());
//			if(!filtered.containsKey(choice)) {
//				continue;
//			}else {
//				System.out.println(filtered.get(choice));
//			}
//		}catch(IOException e) {
//			System.out.println(e.getMessage());
//			continue;
//		}catch(NumberFormatException e) {
//			System.out.println("Input should be a number.The letter or symbols are not allowed");
//			continue;
//		}
//		flag =false;
		showFunctionalMenu(customer,filtered);
		//}while(flag);
		}while(true);
	}
	
	public static void searchBySubCategory(Customer customer) { 
		int choice = 0;
		boolean flag = true;
		List<String> categoryNames = subCategories.values().stream()
			    .map(SubCategory::getSubCategoryName) 
			    .distinct()
			    .collect(Collectors.toList());
		do {
		System.out.println("S.no\tSubCategoryName");
		categoryNames.forEach((c)->System.out.println((categoryNames.indexOf(c)+1)+"\t"+c));
		System.out.println("Enter your choice : ");
		try {
			choice = Integer.parseInt(reader.readLine());
			choice = Validation.isOptionValid(1, categoryNames.size(), choice);
		}catch(InvalidMenuChoiceException | IOException e) {
			System.out.println(e.getMessage());
			continue;
		}catch(NumberFormatException e) {
			System.out.println("Input should be a number.The letter or symbols are not allowed");
			continue;
		}
		String subCategory = categoryNames.get(choice-1);
		
		System.out.println(subCategory);
		Map<Integer,Product>filtered = products.entrySet().stream()
		.filter(v->v.getValue().getSubCategrory().getSubCategoryName().equalsIgnoreCase(subCategory))
		.collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
		filtered.forEach((k,v)->System.out.println(k+"."+" "+v));
//		System.out.println("Enter your choice");
//		try {
//			choice = Integer.parseInt(reader.readLine());
//			if(!filtered.containsKey(choice)) {
//				continue;
//			}else {
//				System.out.println(filtered.get(choice));
//			}
//		}catch(IOException e) {
//			System.out.println(e.getMessage());
//			continue;
//		}catch(NumberFormatException e) {
//			System.out.println("Input should be a number.The letter or symbols are not allowed");
//			continue;
//		}
//		flag =false;
		showFunctionalMenu(customer,filtered);
		//}while(flag);
		}while(true);
	}
	public static Map<Integer,Product> getAllProduct(){
		Map<Integer,Product> products = new HashMap<>();
		int i = 1;
		String sql = "SELECT p.p_id,p.p_name,p.p_brand,p.p_subtitle,p.p_description,p.p_price,p.warranty,s.subcategory_id,s.category_id,"
				+ "s.subcategory_name,c.category_name "
				+ "FROM product p,subcategory s,category c WHERE p.category_id=c.category_id AND p.subcategory_id=s.subcategory_id";
		try {
			Statement statement = DbmsConnection.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				//Product(String productName, int productId, double productPrice, String brand, String subtitle,
				//String description, String warranty, SubCategory subCategrory)
				products.put(i, new Product(
						resultSet.getString("p_name"),
						resultSet.getInt("p_id"),
						resultSet.getDouble("p_price"),
						resultSet.getString("p_brand"),
						resultSet.getString("p_subtitle"),
						resultSet.getString("p_description"),
						resultSet.getString("warranty"),
						new SubCategory(
						resultSet.getInt("subcategory_id"),
						resultSet.getString("subcategory_name"),
						new Category(
						resultSet.getInt("category_id"),
						resultSet.getString("category_name")
						)
						)
						));
				i++;
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return products;
	}
}
