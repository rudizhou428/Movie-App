import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JPanel{ 

	private JPanel swapper, loginPanel;
	private CardLayout cardLayout = new CardLayout();
	boolean access = false; 			
	movieGUI movieGUI;
	buttonPressed bP = new buttonPressed();
	createUser cU;
	private JTextField txtS;
	private JPasswordField txtP;
	private JButton createUserButton, exitButton, btnClear, btnClear1,loginButton, submit,backButton;
	writeFile wF = new writeFile();
	readFiles rF = new readFiles();
	ImageIcon back = new ImageIcon(getClass().getResource("/resources/login.jpg"));
	ImageIcon modify = new ImageIcon(getClass().getResource("/resources/modify.jpg"));
	ImageIcon button = new ImageIcon(getClass().getResource("/resources/button.png"));
	ImageIcon rollOver = new ImageIcon(getClass().getResource("/resources/rollOver.png"));
	ImageIcon pressed = new ImageIcon(getClass().getResource("/resources/pressed.png"));


	public login()
	{	
		
		init();
		
		swapper = new JPanel();
		swapper.setLayout(cardLayout);

		loginPanel = new JPanel();

		JLabel background = new JLabel(back);

		GridBagLayout gbl_loginPanel = new GridBagLayout();
		gbl_loginPanel.columnWidths = new int[]{150, 170, 170, 100, 135};
		gbl_loginPanel.rowHeights = new int[]{180, 0, 97, 50, 149, 38};
		gbl_loginPanel.columnWeights = new double[]{0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_loginPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		background.setLayout(gbl_loginPanel);

		txtS = new JTextField();
		txtS.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(5,5, 0, 0);
		gbc_textField.gridwidth = 3;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		background.add(txtS, gbc_textField);
		txtS.setColumns(10);

		txtP = new JPasswordField();
		txtP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.insets = new Insets(5, 5, 0, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 2;
		background.add(txtP, gbc_passwordField);

		loginButton = new JButton();
		GridBagConstraints gbc_loginButton_1 = new GridBagConstraints();
		gbc_loginButton_1.gridwidth = 3;
		gbc_loginButton_1.insets = new Insets(5, 5,0, 0);
		gbc_loginButton_1.fill = GridBagConstraints.BOTH;
		gbc_loginButton_1.gridx = 1;
		gbc_loginButton_1.gridy = 3;
		background.add(loginButton, gbc_loginButton_1);

		loginButton.setIcon(button);
		loginButton.setBorderPainted (false);
		loginButton.setFocusPainted (false);
		loginButton.setContentAreaFilled (false);
		loginButton.setRolloverIcon(rollOver);
		loginButton.setPressedIcon (pressed);

		loginButton.addActionListener(bP);

		createUserButton = new JButton("Create User");
		GridBagConstraints gbc_createUserButton = new GridBagConstraints();
		gbc_createUserButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_createUserButton.insets = new Insets(0, 0, 0, 5);
		gbc_createUserButton.gridx = 2;
		gbc_createUserButton.gridy = 5;
		background.add(createUserButton, gbc_createUserButton);
		createUserButton.addActionListener(bP);

		btnClear = new JButton("Clear");
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClear.insets = new Insets(0, 0, 0, 5);
		gbc_btnClear.gridx = 3;
		gbc_btnClear.gridy = 5;
		btnClear.addActionListener(bP);
		background.add(btnClear, gbc_btnClear);

		exitButton = new JButton("Exit");
		GridBagConstraints gbc_exitbutton = new GridBagConstraints();
		gbc_exitbutton.gridwidth = 2;
		gbc_exitbutton.fill = GridBagConstraints.HORIZONTAL;
		gbc_exitbutton.gridx = 4;
		gbc_exitbutton.gridy = 5;
		exitButton.addActionListener(bP);
		background.add(exitButton, gbc_exitbutton);

		loginPanel.add(background);
		swapper.add(loginPanel, "LOGIN");
		add(swapper);

	}

	public void init()
	{
		rF.readFile("MovieData");
		rF.readFile("passwords");
	}
	public boolean checkLogin()
	{
		char[] p = txtP.getPassword();
		String pass = "";
		for(int i = 0; i < p.length; i++)
		{
			pass += String.valueOf(p[i]);         // Get the password from password field
		}

		for (int l = 0 ; l < variables.username.size(); l++)
		{
			if(txtS.getText().equalsIgnoreCase(variables.username.get(l)) == true && pass.equalsIgnoreCase(variables.password.get(l))== true)
			{
				access = true;
				break;
			}
		}
		return access;
	}


	public class buttonPressed implements ActionListener
	{

		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource () == btnClear || e.getSource() == btnClear1)
			{
				txtS.setText(""); 
				txtP.setText("");
				if(e.getSource() == btnClear1)
				{
					cU.usernameTxt.setText("");
					cU.passwordTxt.setText("");
					cU.retypePassTxt.setText("");
				}
			}
			else if(e.getSource() == backButton)
			{
				cardLayout.show(swapper, "LOGIN");
			}

			else if (e.getSource () == loginButton)
			{
				checkLogin();
				if(access == true){

					movieGUI = new movieGUI(txtS.getText());
					//movieGUI = new movieGUI("Student1449");
					movieGUI.logoutBtn.addActionListener(bP);
					swapper.add(movieGUI,"MOVIE");	
					cardLayout.show(swapper, "MOVIE");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
					txtS.setText(""); 
					txtP.setText("");				
				}

			}
			else if(e.getSource() == createUserButton)
			{
				cU = new createUser();
				swapper.add(cU, "USER");
				cardLayout.show(swapper, "USER");
			}
			else if(e.getSource() == submit)
			{
				cU.createUser(cU.usernameTxt.getText());
				cU.createPass(cU.passwordTxt.getText());
				wF.writeInfo("passwords", false);	
				cardLayout.show(swapper, "LOGIN");
			}
			else if(e.getSource() == exitButton)
			{
				System.exit(0);
			}
			else if(e.getSource() == movieGUI.logoutBtn)
			{
				variables.movieName.clear();
				variables.genre.clear();
				variables.password.clear();
				variables.score.clear();
				variables.studentNumber.clear();
				variables.username.clear();
				variables.userWatched.clear();
				txtS.setText(""); 
				txtP.setText("");	
				cardLayout.show(swapper, "LOGIN");
			}

		}
	}
	public class createUser extends JPanel
	{
		private JTextField usernameTxt;
		private JTextField passwordTxt;
		private JTextField retypePassTxt;

		public createUser()
		{

			JLabel background = new JLabel(modify);

			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{159, 251, 0, 0, 66, 97, 98, 72};
			gbl_panel.rowHeights = new int[]{122, 47, 47, 47, 47, 47, 270, 35, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			background.setLayout(gbl_panel);

			JLabel lblNewLabel = new JLabel("Username:");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 1;
			background.add(lblNewLabel, gbc_lblNewLabel);

			usernameTxt = new JTextField();
			GridBagConstraints gbc_username = new GridBagConstraints();
			gbc_username.gridwidth = 3;
			gbc_username.insets = new Insets(0, 0, 5, 5);
			gbc_username.fill = GridBagConstraints.HORIZONTAL;
			gbc_username.gridx = 1;
			gbc_username.gridy = 1;
			background.add(usernameTxt, gbc_username);
			usernameTxt.setColumns(10);

			JLabel lblPassword = new JLabel("New Password:");
			lblPassword.setForeground(Color.WHITE);
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.insets = new Insets(0, 0, 10, 10);
			gbc_lblPassword.gridx = 0;
			gbc_lblPassword.gridy = 2;
			background.add(lblPassword, gbc_lblPassword);

			passwordTxt = new JTextField();
			GridBagConstraints gbc_newPass = new GridBagConstraints();
			gbc_newPass.gridwidth = 3;
			gbc_newPass.insets = new Insets(0, 0, 5, 5);
			gbc_newPass.fill = GridBagConstraints.HORIZONTAL;
			gbc_newPass.gridx = 1;
			gbc_newPass.gridy = 2;
			background.add(passwordTxt, gbc_newPass);
			passwordTxt.setColumns(10);

			JLabel lblRetypePassword = new JLabel("Retype New Password:");
			lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblRetypePassword.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblRetypePassword = new GridBagConstraints();
			gbc_lblRetypePassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblRetypePassword.gridx = 0;
			gbc_lblRetypePassword.gridy = 3;
			background.add(lblRetypePassword, gbc_lblRetypePassword);

			retypePassTxt = new JTextField();
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.gridwidth = 3;
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.insets = new Insets(0, 0, 5, 5);
			gbc_textField_2.gridx = 1;
			gbc_textField_2.gridy = 3;
			background.add(retypePassTxt, gbc_textField_2);
			retypePassTxt.setColumns(10);

			btnClear1 = new JButton("Clear");
			GridBagConstraints gbc_gbbtnClear = new GridBagConstraints();
			gbc_gbbtnClear.gridwidth = 3;
			gbc_gbbtnClear.fill = GridBagConstraints.HORIZONTAL;
			gbc_gbbtnClear.insets = new Insets(0, 0, 0, 5);
			gbc_gbbtnClear.gridx = 2;
			gbc_gbbtnClear.gridy = 7;
			background.add(btnClear1, gbc_gbbtnClear);
			btnClear1.addActionListener(bP);

			backButton = new JButton("Back");
			GridBagConstraints gbc_backButton = new GridBagConstraints();
			gbc_backButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_backButton.insets = new Insets(0, 0, 0, 5);
			gbc_backButton.gridx = 5;
			gbc_backButton.gridy = 7;
			background.add(backButton, gbc_backButton);
			backButton.addActionListener(bP);

			submit = new JButton("Submit");
			GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
			gbc_btnSubmit.gridwidth = 2;
			gbc_btnSubmit.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnSubmit.gridx = 6;
			gbc_btnSubmit.gridy = 7;
			background.add(submit, gbc_btnSubmit);
			submit.addActionListener(bP);

			add(background);
		}

		public void createUser(String user)
		{
			variables.username.add(user);
		}
		public void createPass(String pass)
		{
			variables.password.add(pass);
		}	
	}

}
