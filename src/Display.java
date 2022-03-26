import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.*;

//TODO: add forgot password link

public class Display {
	private JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
	private final JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7;	
	private JPasswordField password;
	private JButton b1, b2, b3, b4;
	private JFrame f;
	private int requestNum = 0;
	private ArrayList<Request> requests = new ArrayList<Request>();
	private ArrayList<Request> recipes = new ArrayList<Request>();
	private byte buttonCode = -1;
	private long userSize; 
	private Set<Long> users;
	
	public Display() {
				
		l1 = new JLabel("Register");
		l2 = new JLabel("Login");
		l3 = new JLabel("First Name:");
		l4 = new JLabel("Last Name:");
		l5 = new JLabel("Date of Birth:"); //TODO: add calendar/date picker
		l6 = new JLabel("Email Address:");
		l7 = new JLabel("Username:");
		l8 = new JLabel("Password:");
		l9 = new JLabel("Username:");
		l10 = new JLabel("Password:");
		l11 = new JLabel(""); //Please use valid characters
		l12 = new JLabel("");
		tf1 = new JTextField();
		tf2 = new JTextField();
		tf3 = new JTextField();
		tf4 = new JTextField();
		tf5 = new JTextField();
		tf6 = new JTextField();
		tf7 = new JTextField();

		password = new JPasswordField(20);

		b1 = new JButton("Register");
		b2 = new JButton("Log in");
		b3 = new JButton("New User? Register here!");
		b3.setForeground(Color.BLUE);
		b4 = new JButton("Already have an account? Log in here!");
		b4.setForeground(Color.BLUE);

		this.initDisplay();
		
	}
	
	private void initDisplay() {
		f = new JFrame("Recipe Manager Login");
		
		l1.setBounds(50,50,200,30);
		l1.setFont(new Font("Calibri", Font.BOLD, 30));
		l1.setVisible(false);
		
		l2.setBounds(50,50,200,30);
		l2.setFont(new Font("Calibri", Font.BOLD, 30));
		
		l3.setBounds(50, 125, 200,30);
		l3.setFont(new Font("Calibri", Font.PLAIN, 22));
		tf1.setBounds(240, 125, 200, 30);
		l3.setVisible(false);
		tf1.setVisible(false);

		l4.setBounds(50, 160, 200,30);
		l4.setFont(new Font("Calibri", Font.PLAIN, 22));
		tf2.setBounds(240, 160, 200, 30);
		l4.setVisible(false);
		tf2.setVisible(false);

		l5.setBounds(50, 195, 250,30);
		l5.setFont(new Font("Calibri", Font.PLAIN, 22));
		tf3.setBounds(240, 195, 200, 30);
		l5.setVisible(false);
		tf3.setVisible(false);

		l6.setBounds(50, 230, 250,30);
		l6.setFont(new Font("Calibri", Font.PLAIN, 22));
		tf4.setBounds(240, 230, 200, 30);
		l6.setVisible(false);
		tf4.setVisible(false);

		l7.setBounds(50, 265, 200,30);
		l7.setFont(new Font("Calibri", Font.PLAIN, 22));
		tf5.setBounds(240, 265, 200, 30);
		l7.setVisible(false);
		tf5.setVisible(false);

		l8.setBounds(50, 300, 200,30);
		l8.setFont(new Font("Calibri", Font.PLAIN, 22));
		tf6.setBounds(240, 300, 200, 30);
		l8.setVisible(false);
		tf6.setVisible(false);
		
		l9.setBounds(50, 125, 200,30);
		l9.setFont(new Font("Calibri", Font.PLAIN, 22));
		tf7.setBounds(240, 125, 200, 30);
		
		l10.setBounds(50, 160, 200,30);
		l10.setFont(new Font("Calibri", Font.PLAIN, 22));
		password.setBounds(240, 160, 200, 30);
		
		l11.setBounds(125, 350, 1200,30);
		l11.setFont(new Font("Calibri", Font.BOLD, 22));
		l11.setForeground(Color.RED);
		l11.setVisible(false);

		l12.setBounds(100, 350, 1200,30);
		l12.setFont(new Font("Calibri", Font.BOLD, 22));
		l12.setForeground(Color.RED);

		b1.setBounds(200, 400, 100, 40);
		b1.setVisible(false);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				registerButton();
			}
		});
		
		b2.setBounds(200, 400, 100, 40);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				loginButton();
			}
		});
		
		b3.setBounds(100, 450, 300, 40);
		b3.setContentAreaFilled(false);
		b3.setBorder(null);
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				l1.setVisible(true);
				l2.setVisible(false);
				l3.setVisible(true);
				l4.setVisible(true);
				l5.setVisible(true);
				l6.setVisible(true);
				l7.setVisible(true);
				l8.setVisible(true);
				l9.setVisible(false);
				l10.setVisible(false);
				l11.setVisible(true);
				l12.setVisible(false);
				tf1.setVisible(true);
				tf2.setVisible(true);
				tf3.setVisible(true);
				tf4.setVisible(true);
				tf5.setVisible(true);
				tf6.setVisible(true);
				tf7.setVisible(false);
				password.setVisible(false);
				b1.setVisible(true);
				b2.setVisible(false);
				b3.setVisible(false);
				b4.setVisible(true);
			}
		});
		
		b4.setBounds(100, 450, 300, 40);
		b4.setVisible(false);
		b4.setContentAreaFilled(false);
		b4.setBorder(null);
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				l1.setVisible(false);
				l2.setVisible(true);
				l3.setVisible(false);
				l4.setVisible(false);
				l5.setVisible(false);
				l6.setVisible(false);
				l7.setVisible(false);
				l8.setVisible(false);
				l9.setVisible(true);
				l10.setVisible(true);
				l11.setVisible(false);
				l12.setVisible(true);
				tf1.setVisible(false);
				tf2.setVisible(false);
				tf3.setVisible(false);
				tf4.setVisible(false);
				tf5.setVisible(false);
				tf6.setVisible(false);
				tf7.setVisible(true);
				password.setVisible(true);
				b1.setVisible(false);
				b2.setVisible(true);
				b3.setVisible(true);
				b4.setVisible(false);
				
			}
		});
		
		f.add(b1);	
		f.add(b2);	
		f.add(b3);	
		f.add(b4);	
		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.add(l4);
		f.add(l5);
		f.add(l5);
		f.add(l6);
		f.add(l7);
		f.add(l8);
		f.add(l9);
		f.add(l10);
		f.add(l11);
		f.add(l12);
		f.add(tf1);
		f.add(tf2);
		f.add(tf3);
		f.add(tf4);
		f.add(tf5);
		f.add(tf6);
		f.add(tf7);
		f.add(password);
				
		f.setSize(525, 550);
		f.setLayout(null);
		f.setVisible(true);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
		
	private void registerButton() {
		
		long l = Systems.registerUser(tf1.getText(), tf2.getText(), tf4.getText(), tf3.getText(), tf5.getText(), tf6.getText());
				
		if(l == -1) {
			//user exists already
			l11.setText("Email address already in use!");
			return;
		}if(l == -2) {
			//invalid first
			l11.setText("Username address already in use!");
			return;

		}if(l == -3) {
			//invalid last
			l11.setText("First Name has invalid format!");
			return;
		}if(l == -4) {
			//invalid email
			l11.setText("Last Name has invalid format!");
			return;
		}if(l == -5) {
			//invalid username
			l11.setText("Email has invalid format!");
			return;
		}if(l == -6) {
			//invalid password
			l11.setText("Username has invalid format!");
			return;
		}if(l == -7) {
			//invalid password
			l11.setText("Password has invalid format!");
			return;
		}
		tf1.setText("");
		tf2.setText("");
		tf3.setText("");
		tf4.setText("");
		tf5.setText("");
		tf6.setText("");
		tf7.setText("");
		password.setText("");
		l11.setText("");
		l12.setText("");
		
		initUser(l);
		
	}
	
	private void loginButton() {
		String s1 = tf7.getText().trim();
		String s2 = new String(password.getPassword());
		long id = Systems.login(s1, s2);
				
		
		//if userID == 0, go to admin screen
		if(id == Systems.getAdminID()) 
			initAdmin(id);
		//else, go to user screen;
		else if(id >= 0){
			initUser(id);
		}else {
			if(s1 == null || s1.equals("")) {
				l12.setText("Please enter username!");

			}
			
			else if(s2 == null || s2.equals("")) {
				l12.setText("Please enter password!");

			}
			
			else {
				l12.setText("Username or Password is incorrect!");

			}

			return;
		}
		
		
		tf1.setText("");
		tf2.setText("");
		tf3.setText("");
		tf4.setText("");
		tf5.setText("");
		tf6.setText("");
		tf7.setText("");
		password.setText("");
		l11.setText("");
		l12.setText("");
	}
	
	private void logoutButton(JFrame frame) {
		frame.dispose();
		initDisplay();
		
	}
	
	private void initAdmin(long id){
		Systems.viewRequests(Systems.getAdminID());
		
		f.dispose();
		
		JFrame frame = new JFrame("Admin");
		frame.setSize(850,900);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextField la1 = new JTextField();
		la1.setBounds(150, 150, 500, 30);
		
		JLabel la3 = new JLabel();
		la3.setBounds(300, 800, 500, 30);
		
		//remove user button
		JButton a1 = new JButton("Remove User");
		a1.setBounds(625, 300, 150, 30);
		a1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String text = la1.getText();
				long num = 0;
				try {
					num = Long.parseLong(text);
					Systems.removeUser(num);

					
				}catch(NumberFormatException ex) {
					Systems.removeUser(text, Systems.getUId(text));
				}finally {
					la1.setText("");
				}
			}
		});
		
		//output field
		final JTextArea la2 = new JTextArea("") ;
		la2.setBounds(50, 200, 550, 600);
		la2.setFont(new Font("Calibri", Font.BOLD, 20));
		la2.setLineWrap(true);
		la2.setWrapStyleWord(true);
		la2.setOpaque(true);
		la2.setEditable(false);
		la2.setFocusable(false);
		la2.setBackground(Color.white);
		la2.setBorder(UIManager.getBorder("Label.border"));
		a1.setVisible(false);
				
		//remove recipe button
		JButton a2 = new JButton("Remove Recipe");
		a2.setBounds(625, 300, 150, 30);
		a2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String text = la1.getText();
				String text1 = la2.getText();

				long num = 0;
				try {
					num = Long.parseLong(text);
					Systems.removeRecipe(num, Systems.getUId(getUsername(la3.getText())), text, text1);

					
				}catch(NumberFormatException ex) {
					Systems.removeRecipe(text, Systems.getUId(getUsername(la3.getText())), text1);

				}finally {
					la1.setText("");
					la2.setText("");
					la3.setText("");
				}
			}
		});
			
		//TODO:accept request button
		JButton a3 = new JButton("Accept Request");
		a3.setBounds(625, 450, 150, 30);
		a3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//long num = 0;
				try {
					Systems.acceptRequest(Systems.getUId(getUsername(la3.getText())), requestNum);
					la1.setText("");
					la2.setText("");
					la3.setText("");
				}catch(NumberFormatException ex) {
					
				}
			}
		});
			
		//logout button
		JButton a4 = new JButton("Logout");
		a4.setBounds(700, 10, 100, 30);	
		a4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				logoutButton(frame);
			}
		});
		
		//TODO: view recipes button
		JButton a7 = new JButton("View Recipes");
		a7.setBounds(625, 200, 150, 30);
		a7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					requestNum = 0;
					buttonCode = 1;

					recipes = Systems.viewRecipes(id);
					
					Request r = recipes.get(requestNum);
					System.out.println("size of recipes " + recipes.size());
					
					for(int i = 0; i < recipes.size(); i++) {
						if(r.approved == false) {
							r = recipes.get(++requestNum);
							//System.out.println()
						}else {
							System.out.println("recipe name in breake is " + r.getRecipeName());
							break;
						}
					}
					
					la1.setText(r.getRecipeName());
					la2.setText(r.getRecipeInfo());
					la3.setText(Systems.getUName(r.getUserID()));
				}
				catch(NullPointerException ex) {
					
				}
			}
		});
		
		//find recipes button
		JButton a8 = new JButton("Find Recipe");
		a8.setBounds(625, 250, 150, 30);
		a8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){					
				String text = la1.getText();
				
				long num = 0;
				String[] s = null;
				
				try {
					num = Long.parseLong(text);
					s = Systems.findRecipe(num, id);
					
				}catch(NumberFormatException ex) {
					s = Systems.findRecipe(text, id);

				}finally{
					if(s == null) {
						la1.setText("");
						la2.setText("");
						la3.setText("");
					}else {
						la1.setText(s[0]);
						la2.setText(s[1]);
						la3.setText("Username: " + s[2]);
					}
				}
			}
		});
		
		//TODO: view request button
		JButton a9 = new JButton("View Requests");
		a9.setBounds(625, 400, 150, 30);
		a9.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
				requestNum = 0;
				buttonCode = 2;

				try {
					HashMap<Long, ArrayList<Request>> h = (HashMap<Long, ArrayList<Request>>)Systems.viewRequests(id);
					h.forEach((key, value) -> {
						requests.addAll(value);
					});
					
					Request r = requests.get(requestNum);
					
					for(int i = 0; i < requests.size(); i++) {
						System.out.println(r.getRecipeName() + " is the name and the index is " + i);
						if(r.approved == true) {
							r = requests.get(++requestNum);
						}else {
							break;
						}
					}
					la1.setText(r.getRecipeName());
					la2.setText(r.getRecipeInfo());
					la3.setText("Username: " + Systems.getUName(r.getUserID()));
				}catch(NumberFormatException ex) {
					
				}
			}
		});
				
		//find request button
		JButton a10 = new JButton("Find User");
		a10.setBounds(625, 250, 150, 30);
		a10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String text = la1.getText();
				String[] s = null;
				
				long num = 0;
				try {
					num = Long.parseLong(text);
					s = Systems.findUser(num);
					
				}catch(NumberFormatException ex) {
					s = Systems.findUser(text);
				}
				
				if(s == null) {
					la1.setText("");
					la2.setText("");
				}else {
					la1.setText(s[5]);
					la2.setText("UserID: " + s[0] + "\nUsername:" + s[5] + "\nFirst Name: " + s[1] + "\nLast Name:: " + s[2] + "\nJoin Date: " + s[3] + "\nEmailAddress: " + s[4]);
				}
			}
		});
		
		//TODO: deny request button
		JButton a11 = new JButton("Deny Request");
		a11.setBounds(625, 500, 150, 30);
		a11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){		
				System.out.println("requestNum is " + requestNum);
					Systems.denyRequest(Systems.getUId(getUsername(la3.getText())), la1.getText(), la2.getText());
					la1.setText("");
					la2.setText("");
					la3.setText("");
			}
		});
				
		//TODO: view users button
		JButton a12 = new JButton("View Users");
		a12.setBounds(625, 200, 150, 30);
		a12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){	
				requestNum = 0;
				buttonCode = 3;
				users = Systems.viewUsers();
				Long[] userList = (Long[]) users.toArray();
				long userID = userList[requestNum];	
				userSize = userList.length;
				String[] s = Systems.findUser(userID);
				la1.setText(s[5]);
				la2.setText("UserID: " + s[0] + "\nUsername:" + s[5] + "\nFirst Name: " + s[1] + "\nLast Name:: " + s[2] + "\nJoin Date: " + s[3] + "\nEmailAddress: " + s[4]);
			}
		});
		
		//backward button
		JButton a14 = new JButton("Forward"); // forward button
		
		JButton a13 = new JButton("Backward");
		a13.setBounds(625, 600, 150, 30);					
		a13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				requestNum--;
				long max = -1;
				
				if(buttonCode == 1) {
					max = recipes.size();
				}else if(buttonCode == 2) {
					max = requests.size();
				}else if(buttonCode == 3){
					max = userSize;
				}
						
				if(requestNum == 0) {
					a13.setEnabled(false);
				}else if(requestNum < max) {
					a14.setEnabled(true);
				}
				
				//show results
				if(buttonCode == 1) {					
					Request r = recipes.get(requestNum);
					
					for(int i = 0; i < recipes.size(); i++) {
						if(r.approved == false) {
							r = recipes.get(++requestNum);
						}else {
							break;
						}
					}
					
					la1.setText(r.getRecipeName());
					la2.setText(r.getRecipeInfo());
					la3.setText("Username: " + Systems.getUName(r.getUserID()));
				}
				else if(buttonCode == 2) {
					Request r = requests.get(requestNum);
					
					for(int i = 0; i < requests.size(); i++) {
						if(r.approved == true) {
							r = requests.get(++requestNum);
						}else {
							break;
						}
					}
					la1.setText(r.getRecipeName());
					la2.setText(r.getRecipeInfo());
					la3.setText("Username: " + Systems.getUName(r.getUserID()));
				}
				else if(buttonCode == 3) {
					users = Systems.viewUsers();
					Long[] userList = (Long[]) users.toArray();
					long userID = userList[requestNum];	
					userSize = userList.length;
					String[] s = Systems.findUser(userID);
					la1.setText(s[5]);
					la2.setText("UserID: " + s[0] + "\nUsername:" + s[5] + "\nFirst Name: " + s[1] + "\nLast Name:: " + s[2] + "\nJoin Date: " + s[3] + "\nEmailAddress: " + s[4]);
					la3.setText("Username: " + s[5]);
				}
				
			}
		});
		
		//forward button
		
		a14.setBounds(625, 650, 150, 30);					
		a14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){		
				requestNum++;
				
				long max = -1;
				
				if(buttonCode == 1) {
					max = recipes.size();
				}else if(buttonCode == 2) {
					max = requests.size();
				}else if(buttonCode == 3){
					max = userSize;
				}
				
				if(requestNum == max) {
					a14.setEnabled(false);
				}else if(requestNum > 0) {
					a13.setEnabled(true);
				}
				
				//show results
				if(buttonCode == 1) {					
					Request r = recipes.get(requestNum);
					
					for(int i = 0; i < recipes.size(); i++) {
						if(r.approved == false) {
							r = recipes.get(++requestNum);
						}else {
							break;
						}
					}
					
					la1.setText(r.getRecipeName());
					la2.setText(r.getRecipeInfo());
					la3.setText("Username: " + Systems.getUName(r.getUserID()));
				}
				else if(buttonCode == 2) {
					Request r = requests.get(requestNum);
					
					for(int i = 0; i < requests.size(); i++) {
						if(r.approved == true) {
							r = requests.get(++requestNum);
						}else {
							break;
						}
					}
					la1.setText(r.getRecipeName());
					la2.setText(r.getRecipeInfo());
					la3.setText("Username: " + Systems.getUName(r.getUserID()));
				}
				else if(buttonCode == 3) {
					users = Systems.viewUsers();
					Long[] userList = (Long[]) users.toArray();
					long userID = userList[requestNum];	
					userSize = userList.length;
					String[] s = Systems.findUser(userID);
					la1.setText(s[5]);
					la2.setText("UserID: " + s[0] + "\nUsername:" + s[5] + "\nFirst Name: " + s[1] + "\nLast Name:: " + s[2] + "\nJoin Date: " + s[3] + "\nEmailAddress: " + s[4]);
					la3.setText("Username: " + s[5]);
				}
			}
		});
		
		//recipe button
		JButton a5 = new JButton("Recipes");
		a5.setBounds(200, 100, 100, 30);
		a5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				a1.setVisible(false);
				a2.setVisible(true);
				a3.setVisible(true);
				a7.setVisible(true);
				a8.setVisible(true);
				a9.setVisible(true);
				a10.setVisible(false);
				a11.setVisible(true);
				a12.setVisible(false);
				a13.setVisible(true);
				a14.setVisible(true);

				requestNum = 0;
			}
		});
		
		//settings button
		JButton a6 = new JButton("Settings");
		a6.setBounds(500, 100, 100, 30);					
		a6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				a1.setVisible(true);
				a2.setVisible(false);
				a3.setVisible(false);
				a7.setVisible(false);
				a8.setVisible(false);
				a9.setVisible(false);
				a10.setVisible(true);
				a11.setVisible(false);
				a12.setVisible(true);
				a13.setVisible(false);
				a14.setVisible(false);
				
				requestNum = 0;

			}
		});
		
		
		
		a1.setVisible(false);
		a12.setVisible(false);
		a10.setVisible(false);
		
		frame.add(a1);
		frame.add(la1);
		frame.add(la2);
		frame.add(la3);
		frame.add(a2);
		frame.add(a3);
		frame.add(a4);
		frame.add(a5);
		frame.add(a6);
		frame.add(a7);
		frame.add(a8);
		frame.add(a9);
		frame.add(a10);
		frame.add(a11);
		frame.add(a12);
		frame.add(a13);
		frame.add(a14);
	}
	
	private void initUser(long id) {
		Systems.viewRequests(Systems.getAdminID());
		JButton u17 = new JButton("Forward"); // forward button

		JButton u16 = new JButton("Backward");
		
		f.dispose();
		
		JFrame frame = new JFrame("User");
		frame.setSize(850,900);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextField ul1 = new JTextField("");
		ul1.setBounds(150, 150, 500, 30);
		
		JLabel ul2 = new JLabel();
		ul2.setBounds(300, 800, 500, 30);
		
		JTextArea ut1 = new JTextArea("");
		ut1.setBounds(50, 200, 550, 600);
		ut1.setFont(new Font("Calibri", Font.BOLD, 20));
		ut1.setLineWrap(true);
		ut1.setWrapStyleWord(true);
		ut1.setOpaque(true);
		ut1.setBackground(Color.white);
		ut1.setBorder(UIManager.getBorder("Label.border"));
		
		//delete recipe button
		JButton u1 = new JButton("Remove Recipe");	
		u1.setBounds(625, 350, 150, 30);
		u1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String text = ul1.getText();
				String text1 = ut1.getText();
				long num = 0;
				
				try {
					num = Long.parseLong(text);
					Systems.removeRecipe(text, num, text1);

					
				}catch(NumberFormatException ex) {
					Systems.removeRecipe(num, id, text, text1);

				}finally {
					ul1.setText("");
					ut1.setText("");
				}
			}
		});
		
		//edit recipe button
		JButton u3 = new JButton("Edit Recipe");		
		u3.setBounds(625, 300, 150, 30);
		u3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){					
				String text = ul1.getText();	
				System.out.println(text + " is text");
				long num = 0;
				try {
					num = Long.parseLong(text);
					Systems.editRecipe(num, id, ut1.getText());
				}catch(NumberFormatException ex) {
					Systems.editRecipe(id, text, ut1.getText());
				}finally {
					ul1.setText("");
					ut1.setText("");
				}
			}
		});
		
		//find recipe button
		JButton u4 = new JButton("Find Recipe");
		u4.setBounds(625, 250, 150, 30);
		u4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){					
				String text = ul1.getText();
				
				long num = 0;
				String[] s = null;
				
				try {
					num = Long.parseLong(text);
					s = Systems.findRecipe(num, id);
					
				}catch(NumberFormatException ex) {
					s = Systems.findRecipe(text, id);

				}finally{
					if(s == null) {
						ul1.setText("");			
						ut1.setText("");
					}else {
						ul1.setText(s[0]);
						ut1.setText(s[1]);
					}
				}
			}
		});
		
		//view recipe button
		JButton u5 = new JButton("View Recipes");
		u5.setBounds(625, 200, 150, 30);
		u5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){					
				try {
					System.out.println("inside");
					requestNum = 0;
					buttonCode = 1;
					u16.setEnabled(false);

					recipes = Systems.viewRecipes(id);
					System.out.println("returned");
					System.out.println("recipes size is " + recipes.size());
					
					if(recipes.size() == 0) return;
					
					Request r = recipes.get(requestNum);
					
					System.out.println(r.getRecipeName() + " is the recipe name");
					for(int i = 0; i < recipes.size(); i++) {
						if(r.approved == false) {
							r = recipes.get(++requestNum);
							System.out.println("new name is " + r.getRecipeName());
						}else {
							break;
						}
					}
					
					System.out.println("final is " + r.getRecipeName());
					ul1.setText(r.getRecipeName());
					ut1.setText(r.getRecipeInfo());
					ul2.setText(Systems.getUName(r.getUserID()));
				}
				catch(NullPointerException ex) {
					
				}
			
			}
		});
		
		//request recipe button
		JButton u6 = new JButton("Request Recipe");
		u6.setBounds(625, 500, 150, 30);
		u6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){					
				String text1 = ul1.getText();
				String text2 = ut1.getText();
		
				Systems.sendRequest(id, text1, text2);				
				ul1.setText("");
				ut1.setText("");
			}
		});
				
		//delete request button
		JButton u7 = new JButton("Delete Request");
		u7.setBounds(625, 550, 150, 30);
		u7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){					
				Systems.denyRequest(Systems.getUId(getUsername(ul2.getText())), ul1.getText(), ut1.getText());		
				ul1.setText("");
				ut1.setText("");
			}
		});
		
		//view request button
		JButton u8 = new JButton("View Requests");
		u8.setBounds(625, 450, 150, 30);
		u8.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
				requestNum = 0;
				buttonCode = 2;
				u16.setEnabled(false);
				try {
					HashMap<Long, ArrayList<Request>> h = (HashMap<Long, ArrayList<Request>>)Systems.viewRequests(id);
					
					
					System.out.println(Systems.viewRequests(id) == null);
					h.forEach((key, value) -> {
						System.out.println(value == null);
						System.out.println(value.size());
						requests.addAll(value);
					});
					
					//Request r = requests.get(requestNum);
					
					for(int i = 0; i < requests.size(); i++) {
						if(r.approved == true) {
							r = requests.get(++requestNum);
						}else {
							break;
						}
					}
					ul1.setText(r.getRecipeName());
					ut1.setText(r.getRecipeInfo());
					ul2.setText("Username: " + Systems.getUName(r.getUserID()));
				}catch(ClassCastException ex) {
					requests = (ArrayList<Request>)Systems.viewRequests(id);

					Request r = requests.get(requestNum);
					
					for(int i = 0; i < requests.size(); i++) {
						if(r.approved == true) {
							r = requests.get(++requestNum);
						}else {
							break;
						}
					}
					ul1.setText(r.getRecipeName());
					ut1.setText(r.getRecipeInfo());
					ul2.setText("Username: " + Systems.getUName(r.getUserID()));
				}
			}
		});
		
		//TODO: edit username button
		JButton u11= new JButton("Edit Username");
		u11.setBounds(625, 200, 150, 30);

		//TODO: edit password button
		JButton u12= new JButton("Edit Password");
		u12.setBounds(625, 250, 150, 30);
	
		//delete account button
		JButton u13= new JButton("DELETE ACCOUNT");
		u13.setBounds(625, 300, 150, 30);
		u13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){		
				Systems.removeUser(id);	
				logoutButton(frame);	
			}
		});
		
		//backward button
		
		u16.setBounds(625, 650, 150, 30);					
		u16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.out.println("before backward " + requestNum);
				requestNum--;
				long max = -1;
						
				if(buttonCode == 1) {
					max = recipes.size();
				}else if(buttonCode == 2) {
					max = requests.size();
				}else if(buttonCode == 3){
					max = userSize;
				}
								
				if(requestNum == 0) {
					u16.setEnabled(false);
				}else if(requestNum < max) {
					u17.setEnabled(true);
				}
						
				//show results
				if(buttonCode == 1) {					
					Request r = recipes.get(requestNum);
							
					for(int i = 0; i < recipes.size(); i++) {
						if(r.approved == false) {
							r = recipes.get(++requestNum);
						}else {
							break;
						}
					}
						
					ul1.setText(r.getRecipeName());
					ut1.setText(r.getRecipeInfo());
					ul2.setText("Username: " + Systems.getUName(r.getUserID()));
				}
				else if(buttonCode == 2) {
					Request r = requests.get(requestNum);
							
					for(int i = 0; i < requests.size(); i++) {
						if(r.approved == true) {
							r = requests.get(++requestNum);
						}else {
							break;
						}
					}
					
					ul1.setText(r.getRecipeName());
					ut1.setText(r.getRecipeInfo());
					ul2.setText("Username: " + Systems.getUName(r.getUserID()));
				}
						
			}
		});
				
		//forward button
				
		u17.setBounds(625, 700, 150, 30);					
		u17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){		
				System.out.println("before forward " + requestNum);

				requestNum++;
						
				long max = -1;
				
				if(buttonCode == 1) {
					max = recipes.size() - 1;
				}else if(buttonCode == 2) {
					max = requests.size() - 1;
				}else if(buttonCode == 3){
					max = userSize - 1;
				}
						
				if(requestNum == max) {
					u17.setEnabled(false);
				}else if(requestNum > 0) {
					u16.setEnabled(true);
				}
						
				//show results
				if(buttonCode == 1) {					
					Request r = recipes.get(requestNum);
							
					for(int i = 0; i < recipes.size(); i++) {
						if(r.approved == false) {
							r = recipes.get(++requestNum);
						}else {
							break;
						}
					}
							
					ul1.setText(r.getRecipeName());
					ut1.setText(r.getRecipeInfo());
					ul2.setText("Username: " + Systems.getUName(r.getUserID()));
				}
				else if(buttonCode == 2) {
					Request r = requests.get(requestNum);
							
					for(int i = 0; i < requests.size(); i++) {
						if(r.approved == true) {
							r = requests.get(++requestNum);
						}else {
							break;
						}
					}
					ul1.setText(r.getRecipeName());
					ut1.setText(r.getRecipeInfo());
					ul2.setText("Username: " + Systems.getUName(r.getUserID()));
				}
			}
		});
		
		//recipes  button
		JButton u14= new JButton("Recipes");
		u14.setBounds(200, 100, 100, 30);	
		u14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				requestNum = 0;
				
				u1.setVisible(true);
				u3.setVisible(true);
				u4.setVisible(true);
				u5.setVisible(true);
				u6.setVisible(true);
				u7.setVisible(true);
				u8.setVisible(true);
				u11.setVisible(false);
				u12.setVisible(false);
				u13.setVisible(false);
			}
		});
		
		//settings button
		JButton u15= new JButton("Setting");
		u15.setBounds(500, 100, 100, 30);						
		u15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				requestNum = 0;

				u1.setVisible(false);
				u3.setVisible(false);
				u4.setVisible(false);
				u5.setVisible(false);
				u6.setVisible(false);
				u7.setVisible(false);
				u8.setVisible(false);
				u11.setVisible(true);
				u12.setVisible(true);
				u13.setVisible(true);
			}
		});
	
		//logout button
		JButton u9 = new JButton("Logout");
		u9.setBounds(700, 10, 100, 30);
		u9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				logoutButton(frame);
			}
		});
		
		u11.setVisible(false);
		u12.setVisible(false);
		u13.setVisible(false);
		
		frame.add(u1);
		frame.add(u3);
		frame.add(u4);
		frame.add(u5);
		frame.add(u6);
		frame.add(u7);
		frame.add(u8);
		frame.add(u9);
		frame.add(u11);
		frame.add(u12);
		frame.add(u13);
		frame.add(u14);
		frame.add(u15);
		frame.add(ul1);
		frame.add(ut1);
		frame.add(ul2);
		frame.add(u16);
		frame.add(u17);
	}
	
	private static String getUsername(String s) {
		System.out.println(s.substring(10) + " is the substring in getUsername");
		return s.substring(10);
	}
}
