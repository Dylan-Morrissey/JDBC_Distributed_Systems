import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JDBCGUI {

	public JFrame frame;
	private JTextField dateOfBirthField;
	private JTextField firstNameField;
	private JTextField surnameField;
	private JTextField salaryField;
	private JTextField genderField;
	private static JDBC jdbc = new JDBC();
	private int userno = 0;
	private JTextField searchField;
	private JLabel userCount = new JLabel("User 1 out of " + jdbc.count);
	private int currentCount = 0;
	private JTextField socialSecurityNumberField;

	public JDBCGUI() {
		
		initialize();
		socialSecurityNumberField.setText(String.valueOf(jdbc.employees.get(userno).getSocialSecurityNumber()));
		dateOfBirthField.setText(jdbc.employees.get(userno).getDateOfBirth());
		firstNameField.setText(jdbc.employees.get(userno).getFirstName());
		surnameField.setText(jdbc.employees.get(userno).getSurname());
		salaryField.setText(String.valueOf(jdbc.employees.get(userno).getSalary()));
		genderField.setText(String.valueOf(jdbc.employees.get(userno).getGender()));
		
		
		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				

				try {
					jdbc.employees.get(userno).setSocialSecurityNumber(Integer.parseInt(socialSecurityNumberField.getText()));
					jdbc.employees.get(userno).setSalary(Integer.parseInt(salaryField.getText()));
				} catch (NumberFormatException e1) {
					jdbc.run();
					JOptionPane.showMessageDialog(frame, "Please make sure the social security number and salary are less than 2,147,483,647");
					return;
				}
				
				if (dateOfBirthField.getText().matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")){
					jdbc.employees.get(userno).setDateOfBirth(dateOfBirthField.getText());
					
				} else {
					JOptionPane.showMessageDialog(frame, "Please enter in the Format  yyyy-mm-dd");
					return;
				}
				jdbc.employees.get(userno).setFirstName(firstNameField.getText());
				jdbc.employees.get(userno).setSurname(surnameField.getText());
				jdbc.employees.get(userno).setGender(genderField.getText().charAt(0));
				jdbc.update(jdbc.employees.get(userno));
				
				
				socialSecurityNumberField.setText(String.valueOf(jdbc.employees.get(0).getSocialSecurityNumber()));
				dateOfBirthField.setText(jdbc.employees.get(0).getDateOfBirth());
				firstNameField.setText(jdbc.employees.get(0).getFirstName());
				surnameField.setText(jdbc.employees.get(0).getSurname());
				salaryField.setText(String.valueOf(jdbc.employees.get(0).getSalary()));
				genderField.setText(String.valueOf(jdbc.employees.get(0).getGender()));
				userCount.setText("User "+ 1 +" out of " + jdbc.count);
				
				
			}
		});
		GridBagConstraints gbc_btnUpdate_1 = new GridBagConstraints();
		gbc_btnUpdate_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdate_1.gridx = 5;
		gbc_btnUpdate_1.gridy = 11;
		frame.getContentPane().add(btnUpdate_1, gbc_btnUpdate_1);
		
		searchField = new JTextField();
		GridBagConstraints gbc_searchField = new GridBagConstraints();
		gbc_searchField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchField.insets = new Insets(0, 0, 5, 5);
		gbc_searchField.gridx = 3;
		gbc_searchField.gridy = 13;
		frame.getContentPane().add(searchField, gbc_searchField);
		searchField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(searchField.getText());
				if (searchField.getText()!= "") {
				jdbc.search(searchField.getText());		
				socialSecurityNumberField.setText(String.valueOf(jdbc.employees.get(0).getSocialSecurityNumber()));
				dateOfBirthField.setText(jdbc.employees.get(0).getDateOfBirth());
				firstNameField.setText(jdbc.employees.get(0).getFirstName());
				surnameField.setText(jdbc.employees.get(0).getSurname());
				salaryField.setText(String.valueOf(jdbc.employees.get(0).getSalary()));
				genderField.setText(String.valueOf(jdbc.employees.get(0).getGender()));
				userCount.setText("User "+ 1 +" out of " + jdbc.count);
				} 
			}
		});
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.anchor = GridBagConstraints.WEST;
		gbc_btnSearch.gridwidth = 2;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 4;
		gbc_btnSearch.gridy = 13;
		frame.getContentPane().add(btnSearch, gbc_btnSearch);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdbc.run();
				socialSecurityNumberField.setText(String.valueOf(jdbc.employees.get(0).getSocialSecurityNumber()));
				dateOfBirthField.setText(jdbc.employees.get(0).getDateOfBirth());
				firstNameField.setText(jdbc.employees.get(0).getFirstName());
				surnameField.setText(jdbc.employees.get(0).getSurname());
				salaryField.setText(String.valueOf(jdbc.employees.get(0).getSalary()));
				genderField.setText(String.valueOf(jdbc.employees.get(0).getGender()));
				userCount.setText("User "+ 1 +" out of " + jdbc.count);
				searchField.setText("");
			}
		});
		GridBagConstraints gbc_btnRefresh = new GridBagConstraints();
		gbc_btnRefresh.insets = new Insets(0, 0, 0, 5);
		gbc_btnRefresh.gridx = 3;
		gbc_btnRefresh.gridy = 14;
		frame.getContentPane().add(btnRefresh, gbc_btnRefresh);
		
	}
	
	
	private void initialize() {

		frame = new JFrame();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{97, 18, 0, 139, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{14, 35, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblEmployeeDetails_1 = new JLabel("Employee Details");
		GridBagConstraints gbc_lblEmployeeDetails_1 = new GridBagConstraints();
		gbc_lblEmployeeDetails_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmployeeDetails_1.gridx = 3;
		gbc_lblEmployeeDetails_1.gridy = 1;
		frame.getContentPane().add(lblEmployeeDetails_1, gbc_lblEmployeeDetails_1);
		
		JLabel lblSsn = new JLabel("SSn");
		GridBagConstraints gbc_lblSsn = new GridBagConstraints();
		gbc_lblSsn.anchor = GridBagConstraints.EAST;
		gbc_lblSsn.insets = new Insets(0, 0, 5, 5);
		gbc_lblSsn.gridx = 1;
		gbc_lblSsn.gridy = 2;
		frame.getContentPane().add(lblSsn, gbc_lblSsn);
		
		socialSecurityNumberField = new JTextField();
		GridBagConstraints gbc_socialSecurityNumberField = new GridBagConstraints();
		gbc_socialSecurityNumberField.insets = new Insets(0, 0, 5, 5);
		gbc_socialSecurityNumberField.fill = GridBagConstraints.HORIZONTAL;
		gbc_socialSecurityNumberField.gridx = 3;
		gbc_socialSecurityNumberField.gridy = 2;
		frame.getContentPane().add(socialSecurityNumberField, gbc_socialSecurityNumberField);
		socialSecurityNumberField.setColumns(10);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userno != 0) {
				userno--;
				} else {
					userno = jdbc.count-1;
				}
				socialSecurityNumberField.setText(String.valueOf(jdbc.employees.get(userno).getSocialSecurityNumber()));
				dateOfBirthField.setText(jdbc.employees.get(userno).getDateOfBirth());
				firstNameField.setText(jdbc.employees.get(userno).getFirstName());
				surnameField.setText(jdbc.employees.get(userno).getSurname());
				salaryField.setText(String.valueOf(jdbc.employees.get(userno).getSalary()));
				genderField.setText(String.valueOf(jdbc.employees.get(userno).getGender()));
				currentCount = userno + 1;
				userCount.setText("User "+ currentCount +" out of " + jdbc.count);
			}
		});
		GridBagConstraints gbc_btnPrevious = new GridBagConstraints();
		gbc_btnPrevious.insets = new Insets(0, 0, 5, 5);
		gbc_btnPrevious.gridx = 5;
		gbc_btnPrevious.gridy = 3;
		frame.getContentPane().add(btnPrevious, gbc_btnPrevious);
		
		JLabel lblDob = new JLabel("DOB");
		GridBagConstraints gbc_lblDob = new GridBagConstraints();
		gbc_lblDob.anchor = GridBagConstraints.EAST;
		gbc_lblDob.insets = new Insets(0, 0, 5, 5);
		gbc_lblDob.gridx = 1;
		gbc_lblDob.gridy = 4;
		frame.getContentPane().add(lblDob, gbc_lblDob);
		
		dateOfBirthField = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 4;
		frame.getContentPane().add(dateOfBirthField, gbc_textField_1);
		dateOfBirthField.setColumns(10);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (userno < jdbc.count-1) {
				userno++;
				} else if (jdbc.count > 1){
					userno = 0;
				}
				socialSecurityNumberField.setText(String.valueOf(jdbc.employees.get(userno).getSocialSecurityNumber()));
				dateOfBirthField.setText(jdbc.employees.get(userno).getDateOfBirth());
				firstNameField.setText(jdbc.employees.get(userno).getFirstName());
				surnameField.setText(jdbc.employees.get(userno).getSurname());
				salaryField.setText(String.valueOf(jdbc.employees.get(userno).getSalary()));
				genderField.setText(String.valueOf(jdbc.employees.get(userno).getGender()));
				currentCount = userno + 1;
				userCount.setText("User "+ currentCount +" out of " + jdbc.count);
			}
		});
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.insets = new Insets(0, 0, 5, 5);
		gbc_btnNext.gridx = 5;
		gbc_btnNext.gridy = 5;
		frame.getContentPane().add(btnNext, gbc_btnNext);
		
		JLabel lblFirstname = new JLabel("FirstName");
		GridBagConstraints gbc_lblFirstname = new GridBagConstraints();
		gbc_lblFirstname.anchor = GridBagConstraints.EAST;
		gbc_lblFirstname.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstname.gridx = 1;
		gbc_lblFirstname.gridy = 6;
		frame.getContentPane().add(lblFirstname, gbc_lblFirstname);
		
		firstNameField = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 6;
		frame.getContentPane().add(firstNameField, gbc_textField_2);
		firstNameField.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname");
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.anchor = GridBagConstraints.EAST;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 1;
		gbc_lblSurname.gridy = 7;
		frame.getContentPane().add(lblSurname, gbc_lblSurname);
		
		surnameField = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 7;
		frame.getContentPane().add(surnameField, gbc_textField_3);
		surnameField.setColumns(10);
		
		JLabel lblSalary = new JLabel("Salary");
		GridBagConstraints gbc_lblSalary = new GridBagConstraints();
		gbc_lblSalary.anchor = GridBagConstraints.EAST;
		gbc_lblSalary.insets = new Insets(0, 0, 5, 5);
		gbc_lblSalary.gridx = 1;
		gbc_lblSalary.gridy = 8;
		frame.getContentPane().add(lblSalary, gbc_lblSalary);
		
		salaryField = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 8;
		frame.getContentPane().add(salaryField, gbc_textField_4);
		salaryField.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender");
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.EAST;
		gbc_lblGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblGender.gridx = 1;
		gbc_lblGender.gridy = 9;
		frame.getContentPane().add(lblGender, gbc_lblGender);
		
		genderField = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 3;
		gbc_textField_5.gridy = 9;
		frame.getContentPane().add(genderField, gbc_textField_5);
		genderField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userno = (jdbc.count);
				Employee employee = new Employee();
				
				try {
					int social = Integer.parseInt(socialSecurityNumberField.getText());
					boolean alreadyUser = false;
					if (alreadyUser == false) {
						for (int i=0; i< jdbc.employees.size(); i++) {
							if (jdbc.employees.get(i).getSocialSecurityNumber()==social) {
								alreadyUser = true;
							}						
						}
					}
					if (alreadyUser == true) {
						JOptionPane.showMessageDialog(frame, "Social Security Number already in data base please update current user.");
						return;
					}
					employee.setSocialSecurityNumber(Integer.parseInt(socialSecurityNumberField.getText()));
					
					employee.setSalary(Integer.parseInt(salaryField.getText()));
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(frame, "Please make sure the social security number and salary are less than 2,147,483,647");
					jdbc.run();
					return;
				}
				
				if (dateOfBirthField.getText().matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))")){
					employee.setDateOfBirth(dateOfBirthField.getText());
				} else {
					JOptionPane.showMessageDialog(frame, "Please enter in the Format  yyyy-mm-dd");
					jdbc.run();
					return;
				}
				employee.setFirstName(firstNameField.getText());
				employee.setSurname(surnameField.getText());
				if (genderField.getText().matches("((M)|(F)|(O))")) {
					employee.setGender(genderField.getText().charAt(0)); 	
				} else {
					JOptionPane.showMessageDialog(frame, "Please enter gender as M, F or O");
					jdbc.run();	
					return;
				}
				
				jdbc.employees.add(employee);
				jdbc.create(jdbc.employees.get(jdbc.employees.size()-1));
				
				socialSecurityNumberField.setText(String.valueOf(jdbc.employees.get(0).getSocialSecurityNumber()));
				dateOfBirthField.setText(jdbc.employees.get(0).getDateOfBirth());
				firstNameField.setText(jdbc.employees.get(0).getFirstName());
				surnameField.setText(jdbc.employees.get(0).getSurname());
				salaryField.setText(String.valueOf(jdbc.employees.get(0).getSalary()));
				genderField.setText(String.valueOf(jdbc.employees.get(0).getGender()));
				userCount.setText("User "+ 1 +" out of " + jdbc.count);
				}
			
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				socialSecurityNumberField.setText("");
				dateOfBirthField.setText("");
				firstNameField.setText("");
				surnameField.setText("");
				salaryField.setText("");
				genderField.setText("");
				searchField.setText("");
				
			}
		});
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(0, 0, 5, 5);
		gbc_btnClear.gridx = 5;
		gbc_btnClear.gridy = 9;
		frame.getContentPane().add(btnClear, gbc_btnClear);
		
		
		GridBagConstraints gbc_userCount = new GridBagConstraints();
		gbc_userCount.insets = new Insets(0, 0, 5, 5);
		gbc_userCount.gridx = 3;
		gbc_userCount.gridy = 10;
		frame.getContentPane().add(userCount, gbc_userCount);
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.WEST;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 11;
		frame.getContentPane().add(btnAdd, gbc_btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 3;
		gbc_btnDelete.gridy = 11;
		frame.getContentPane().add(btnDelete, gbc_btnDelete);

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdbc.delete(jdbc.employees.get(userno));
				
				socialSecurityNumberField.setText(String.valueOf(jdbc.employees.get(0).getSocialSecurityNumber()));
				dateOfBirthField.setText(jdbc.employees.get(0).getDateOfBirth());
				firstNameField.setText(jdbc.employees.get(0).getFirstName());
				surnameField.setText(jdbc.employees.get(0).getSurname());
				salaryField.setText(String.valueOf(jdbc.employees.get(0).getSalary()));
				genderField.setText(String.valueOf(jdbc.employees.get(0).getGender()));
				userCount.setText("User "+ 1 +" out of " + jdbc.count);
				
			}
		});
		
	}
	
	public static void main(String[] args) {
		jdbc.run();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JDBCGUI window = new JDBCGUI();
					window.frame.setVisible(true);
					window.frame.setBounds(500,155,500,550);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
