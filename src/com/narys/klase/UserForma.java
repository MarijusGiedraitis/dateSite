package com.narys.klase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import database.Narys;
import database.NarysDao;

public class UserForma extends JFrame implements ActionListener{

	String month[] = {"January", "February", "March", "April", "May", "June", "July",
	      	"August", "September", "October", "November", "December"};
	
	public void actionPerformed(ActionEvent event){
	   	}
		
    public static boolean validateName(String nameStr) { 
		final Pattern VALID_NAME = 
			    Pattern.compile("^[a-zA-Z]{1,20}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_NAME.matcher(nameStr);
		return matcher.find();
	}
	
    public static boolean validateSurname(String surnameStr) { 
		final Pattern VALID_SURNAME = 
			    Pattern.compile("^[a-zA-Z-]{1,20}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_SURNAME.matcher(surnameStr);
		return matcher.find();
	}
    
    public static boolean validatePassword(String passwordStr) { 
		final Pattern VALID_PASSWORD = 
			    Pattern.compile("^.*(?=.{5,20})(?=..*[0-9])(?=.*[a-z]).*$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_PASSWORD.matcher(passwordStr);
		return matcher.find();  
    }
    
    public static boolean validateEmail(String emailStr) { 
    	final Pattern VALID_EMAIL_ADDRESS_REGEX = 
				   Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}
    
    TextField id, name, surname, email;
	JPasswordField password;
	
    public UserForma() {
        setTitle("Find your soulmate at cupid.com!");
        setVisible(true);
        create();
        pack();
    }
    
    public void cleanFields() {
        id.setText("");
        name.setText("");
        surname.setText("");
        email.setText("");
        password.setText("");
    }
    		
    public void create() {
    	
        Container container = getContentPane();
        container.setLayout(new GridLayout(12,0,0,0));
        container.setBackground(Color.darkGray);
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
    
        //Id
        JPanel panelId = new JPanel();
        panelId.setBorder(new TitledBorder("User id "));
        id = new TextField("", 25);
        panelId.add(id);
        container.add(panelId);
        
        //Name
        JPanel panelName = new JPanel();
        panelName.setBorder(new TitledBorder("First name * "));
        name = new TextField("", 25);
        panelName.add(name);
        container.add(panelName);

        //Surname
        JPanel panelSurname = new JPanel();
        panelSurname.setBorder(new TitledBorder("Last name * "));
        surname = new TextField("", 25);
        panelSurname.add(surname);
        container.add(panelSurname);
		
        //Birth date
        JPanel panelBirthDate = new JPanel();
      	panelBirthDate.setBorder(new TitledBorder("Birth date * "));
      	String [] years = new String[100];
      	for(int i = 0; i < 100; i++){
      	int begin = 1901 + i;
      	years[i] = Integer.toString(begin);
      	}
      	String day [] = new String[32];
      	for(int i = 1; i < 32; i++){
      	day[i] = Integer.toString(i);
      	}
      	JComboBox dayList = new JComboBox(day);
      	JComboBox monthList = new JComboBox(month);
      	JComboBox yearList = new JComboBox(years);
      	dayList.setSelectedIndex(1);
      	monthList.setSelectedIndex(0);
      	yearList.setSelectedIndex(74);
      	panelBirthDate.add(yearList);
      	panelBirthDate.add(monthList);
      	panelBirthDate.add(dayList);
      	container.add(panelBirthDate);
      	
      	//Sex
      	JPanel panelSex = new JPanel();
     	panelSex.setBorder(new TitledBorder("Sex * "));
     	JRadioButton man = new JRadioButton("man");
     	JRadioButton woman = new JRadioButton("woman");
     	man.setActionCommand("man");
     	woman.setActionCommand("woman");
     	ButtonGroup bG = new ButtonGroup();
     	bG.add(man);
     	bG.add(woman);
     	man.setSelected(true);
     	panelSex.add(man); 
     	panelSex.add(woman);
     	container.add(panelSex);
		
     	//Password
        JPanel panelPassword = new JPanel();
        panelPassword.setBorder(new TitledBorder("Password * "));
        password = new JPasswordField(18);
        password.setEchoChar('*');
        panelPassword.add(password);
		container.add(panelPassword);
		
     	//Email
     	JPanel panelEmail = new JPanel();
        panelEmail.setBorder(new TitledBorder("Email * "));
        email = new TextField("", 25);
        panelEmail.add(email);
        container.add(panelEmail);
		
        //CheckBox
		JPanel panelCheckBox = new JPanel();
		JCheckBox agree = new JCheckBox("I have read and agree to the terms and conditions");
        panelCheckBox.setBorder(new TitledBorder(""));
		panelCheckBox.add(agree);
		agree.setHorizontalTextPosition(SwingConstants.RIGHT);
		container.add(panelCheckBox);

		//Register and Cancel buttons
		JPanel panelRegister = new JPanel();	
		JButton register = new JButton("Create account");
		JButton cancel = new JButton("Cancel");
		panelRegister.add(register);
		panelRegister.add(cancel);
		container.add(panelRegister);
		cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
		});
		
		//Required fields
		JLabel required = new JLabel("* - these fields are required");
		JPanel panelRequired = new JPanel();
		panelRequired.setBorder(new TitledBorder(""));
		panelRequired.add(required, BorderLayout.WEST);
		container.add(panelRequired);
		
        //Buttons
		JPanel panelSubmit = new JPanel();
        panelSubmit.setBorder(new TitledBorder("Actions "));
        JButton update 	= new JButton ("Update");
        JButton delete 	= new JButton ("Delete");
        JButton search 	= new JButton ("Search");
        panelSubmit.add(update);
        panelSubmit.add(delete);
        panelSubmit.add(search);
        container.add(panelSubmit);

        JTable table = new JTable(new DefaultTableModel(new Object[]{"Number", "Id", 
        		"Name", "Surname", "Email", "Birthday", "Sex", "Password"}, 0));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        JScrollPane scrollPane = JTable.createScrollPaneForTable(table);
	    scrollPane.setPreferredSize(new Dimension(400, 200));
	    container.add(scrollPane);
	    
        NarysDao dao = new NarysDao();

        register.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		String name1 = name.getText().toString();
        		String surname1 = surname.getText().toString();
        		String password1 = password.getText().toString();
        		String email1 = email.getText().toString();

        			if(!validateName(name1))
        			JOptionPane.showMessageDialog(null, "First name field cannot contain numbers, symbols or be empty", "Error", 
        					JOptionPane.ERROR_MESSAGE);
        			
        			else if(!validateSurname(surname1))
        				JOptionPane.showMessageDialog(null, "Last name field cannot contain numbers, symbols or be empty", "Error",
        						JOptionPane.ERROR_MESSAGE);
        			
        			else if(!validatePassword(password1))
        				JOptionPane.showMessageDialog(null, "Password does not meet requirements! "
        						+ "Your password must be at least 5 characters and contain "
        						+ "lower cases and numbers",
        						"Error", JOptionPane.ERROR_MESSAGE);
        			
        			else if(!validateEmail(email1))
        				JOptionPane.showMessageDialog(null, "Please enter a valid email address, e.g. name@example.com", "Error",
        						JOptionPane.ERROR_MESSAGE);
        			
        			else if(agree.isSelected()){
        				Narys narys = new Narys();
        				String firstName = name.getText().toString();
        				narys.setName(firstName);
        				narys.setSurname(surname.getText());
        				narys.setEmail(email.getText());
        				String year = yearList.getSelectedItem().toString();
        				String month2 = monthList.getSelectedItem().toString();
        				String day = (String) dayList.getSelectedItem().toString();
        				int monthInNumberFormat=0;
        				for (String months: month){
            			  monthInNumberFormat++;
            			  	if (months.equals(month2)) {
            				  break;
            			  }
            		  }
            		  String birthDate;
            		  		if((monthInNumberFormat>9) || (Integer.parseInt(day)>9))
            			   birthDate = year + "-" + monthInNumberFormat + "-" + day;
        			  else
        				   birthDate = year + "-0" + monthInNumberFormat + "-0" + day;  
            		  	narys.setBirthDate(birthDate);
            		  String gender = bG.getSelection().getActionCommand();
            		  narys.setSex(gender);
            		  narys.setPassword(password.getText());
            		  dao.addNarys(narys);
            		  	cleanFields();
            		  yearList.setSelectedIndex(74);
            		  monthList.setSelectedIndex(0);
            		  dayList.setSelectedIndex(1);
            		  	agree.setSelected(false);
        		   		JOptionPane.showMessageDialog(agree, "Congratulations! Your registration completed successfully!", "New user created", JOptionPane.INFORMATION_MESSAGE);
        		   		} else {
        		   		JOptionPane.showMessageDialog(agree, "You must read and accept terms and conditions of cupid.com to finish your registration","Warning",
        						JOptionPane.WARNING_MESSAGE);
        		   		}
        	  }
        });
        
        update.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		
        		String name1 = name.getText().toString();
     			String surname1 = surname.getText().toString();
     			String email1 = email.getText().toString();
     			String password1 = password.getText().toString();

     			if(!validateName(name1))
     			JOptionPane.showMessageDialog(null, "First name field cannot contain numbers, symbols or be empty", "Error", 
     					JOptionPane.ERROR_MESSAGE);
     			
     			else if(!validateSurname(surname1))
     				JOptionPane.showMessageDialog(null, "Last name field cannot contain numbers, symbols or be empty", "Error",
     						JOptionPane.ERROR_MESSAGE);
     			
     			else if(!validatePassword(password1))
    				JOptionPane.showMessageDialog(null, "Password does not meet requirements! "
    						+ "Your password must be at least 5 characters and contain "
    						+ "lower cases and numbers",
    						"Error", JOptionPane.ERROR_MESSAGE);
     			
     			else if(!validateEmail(email1))
     				JOptionPane.showMessageDialog(null, "Please enter a valid email address, e.g. name@example.com", "Error",
     						JOptionPane.ERROR_MESSAGE);
     			else {
     				
        		String year = yearList.getSelectedItem().toString();
      		  	String month2 = monthList.getSelectedItem().toString();
      		  	String day = (String) dayList.getSelectedItem().toString();
      		  	int monthInNumberFormat=0;
      		  	for (String months: month){
      		  	monthInNumberFormat++;
      			  if (months.equals(month2)) {
      				  break;
      			  }
      		  }
      		  String birthDate;
      		  if((monthInNumberFormat>9) || (Integer.parseInt(day)>9))
      			   birthDate = year + "-" + monthInNumberFormat + "-" + day;
  			  else
  				   birthDate = year + "-0" + monthInNumberFormat + "-0" + day;
        		String gender = bG.getSelection().getActionCommand();
        		
        		Narys narys = new Narys(Integer.valueOf(id.getText()),
        				name.getText(),
        				surname.getText(), 
        				birthDate,
        				gender,
        				password.getText(),
        				email.getText());
            	dao.updateNarys(narys);
            	JOptionPane.showMessageDialog(container, "user updated successfully", "Info" , 
            	JOptionPane.INFORMATION_MESSAGE);
            		cleanFields();
            		yearList.setSelectedIndex(74);
          		  	monthList.setSelectedIndex(0);
          		  	dayList.setSelectedIndex(1);
          		  	agree.setSelected(false);
        }}});
        
        delete.addActionListener(new ActionListener(){
      	  public void actionPerformed(ActionEvent e){
      		 NarysDao dao = new NarysDao();
      		 dao.deleteNarys(Integer.valueOf(id.getText()));
      		 JOptionPane.showMessageDialog(container, "user deleted successfully", "Info" , 
      	     JOptionPane.INFORMATION_MESSAGE);
      	     	cleanFields();
      	     	yearList.setSelectedIndex(74);
      	     	monthList.setSelectedIndex(0);
      	     	dayList.setSelectedIndex(1);
      	        }});
        
        search.addActionListener(new ActionListener(){
        	  public void actionPerformed(ActionEvent e){
        		  model.setRowCount(0);
        		  java.util.List<Narys> nariai;
        		  if (name.getText().toString().equals("")) {
        			  nariai = dao.getAllNariai();
        		  } else {
        			  nariai = dao.getNarysByName(name.getText().toString());
        		  }
        		  
        		  Object[] data;
        		  int rowNumber = 0;
        		  for (Narys narys : nariai) {
        			  if(!nariai.isEmpty()) {
        				  data = new Object[8];
        				  data[0] = ++rowNumber;
        				  data[1] = narys.getId();
        				  data[2] = narys.getName();
        				  data[3] = narys.getSurname();
        				  data[4] = narys.getEmail();
        				  data[5] = narys.getBirthDate();
        				  data[6] = narys.getSex();
        				  data[7] = narys.getPassword();
        				  model.addRow(data);
        			  } else {
        				  model.setRowCount(0);
        			  }
        		  }
        	  }
        });   
    }
}
