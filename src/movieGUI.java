import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class movieGUI extends JPanel {

	modifyUser mU;
	starRating sR = new starRating();
	buttonPressed bP = new buttonPressed();
	private JPanel swapper, movie;
	JLabel movieDisplay;
	private JButton modifyUserBtn, submit, btnClear,backButton, btnSubmit;
	JButton logoutBtn, clearOptions, submitOptions, rateMovie, addMovie;
	private CardLayout cardLayout = new CardLayout();
	writeFile wF = new writeFile();
	predictedCalculations pC;
	ImageIcon icon;
	ImageIcon moviePic;
	private boolean firstTime = true, saveMovie = false;
	private String currentUser = "";
	JCheckBox[] checkBoxes = new JCheckBox[16];
	String[] movieNams;
	private String[] genreList = {"Adventure", "Biography", "Drama", "Thriller", "Comedy",
			"Action", "Fantasy", "War", "Horror", "Family", "Sci-Fi", "Mystery", "Sport",
			"Romance", "Crime", "Animation"};
	private ArrayList<String> genre = new ArrayList<String>();
	JComboBox comboBox; 
	JLabel pic;
	JPanel options,holder;
	Timer tm;
	int x = 0;

	ImageIcon back = new ImageIcon(getClass().getResource("/resources/mainMenu.png"));
	ImageIcon modify = new ImageIcon(getClass().getResource("/resources/modify1.jpg"));
	ImageIcon empty = new ImageIcon(getClass().getResource("/resources/blank.jpg"));
	ArrayList<String> list = new ArrayList();

	private JPanel rate, pictures;
	private JLayeredPane layeredPane;
	private JLabel current, newPic, old;


	public movieGUI(String currentUser)
	{
		this.currentUser = currentUser;
		pC = new predictedCalculations("student1449");

		list = pC.getmovieList();

		addMovie();
		for(int i = 0; i < 16;i++)
		{
			checkBoxes[i] = new JCheckBox(genreList[i]);
		}

		swapper = new JPanel();
		swapper.setLayout(cardLayout);

		JLabel background = new JLabel(back);

		movie = new JPanel();

		movie.add(background);

		GridBagLayout gbl_movie = new GridBagLayout();
		gbl_movie.columnWidths = new int[]{90,320,86,192,192,111};
		gbl_movie.rowHeights = new int[]{20,150,240,100,270};
		gbl_movie.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_movie.rowWeights = new double[]{Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0};
		background.setLayout(gbl_movie);

		pictures = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		gbc_panel.gridwidth = 4;
		pictures.setOpaque(false);

		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setPreferredSize(new Dimension(645, 250));


		old = new JLabel();
		old.setBounds(145, 0, 140, 212);
		layeredPane.add(old, new Integer(200));

		current = new JLabel();
		current.setBounds(265, 22, 140, 212);
		layeredPane.add(current, new Integer(400));

		newPic = new JLabel();
		newPic.setBounds(385, 0, 140, 212);
		layeredPane.add(newPic, new Integer(200));


		//Call The Function SetImageSize
		SetImageSize (4);


		//set a timer
		tm = new Timer (900, new ActionListener ()
		{

			//@ Override
			public void actionPerformed (ActionEvent e)
			{
				SetImageSize (x);
				x += 1;
				if (x >= list.size()-1)
					x = 0;
			}
		});


		tm.start ();

		rate = new JPanel();
		GridBagConstraints gbc_Ratepanel = new GridBagConstraints();
		gbc_Ratepanel.insets = new Insets(0, 0, 0, 0);
		gbc_Ratepanel.fill = GridBagConstraints.BOTH;
		gbc_Ratepanel.gridwidth = 5;
		gbc_Ratepanel.gridheight = 2;
		gbc_Ratepanel.gridx = 0;
		gbc_Ratepanel.gridy = 3;
		rate.setOpaque(false);

		holder = new JPanel();	
		holder.setOpaque(false);
		holder.setPreferredSize(new Dimension(750, 200));
		holder.setLayout(null);

		options = new JPanel();
		options.setOpaque(false);
		options.setPreferredSize(new Dimension(350, 200));
		options.setBounds(0,0,700,350);
		options.setLayout(new GridLayout(1,1,0,0));

		JPanel temp1 = new JPanel();
		temp1.setLayout(null);
		temp1.setOpaque(false);
		options.add(temp1);

		JPanel temp2 = new JPanel();
		temp2.setLayout(null);
		temp2.setOpaque(false);
		options.add(temp2);

		pic = new JLabel();
		pic.setOpaque(false);
		pic.setBounds(150, 0, 100, 150);
		temp2.add(pic);

		sR.setOpaque(false);
		sR.setBounds(80, 150, 250, 51);
		temp2.add(sR);

		JPanel checkboxPanel = new JPanel();
		checkboxPanel.setOpaque(false);
		checkboxPanel.setBounds(0, 30, 350, 100);
		checkboxPanel.setLayout(new GridLayout(4,5,0,0));

		comboBox = new JComboBox(movieNams);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				int x = comboBox.getSelectedIndex();
				if(x < variables.movieName.size())
				{
					String nam = variables.movieName.get(comboBox.getSelectedIndex());

					for(int i = 0 ; i < variables.movieName.size() ; i++)
					{
						if (nam.equalsIgnoreCase(variables.movieName.get(i)));
						{
							moviePic = new ImageIcon(getClass().getResource("/resources/"+ nam +".jpg"));
							ImageIcon resized = new ImageIcon(moviePic.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH));
							pic.setIcon(resized);
							break;
						}
					}
				}
			}
		});
		comboBox.setBounds(0, 0, 340, 20);
		temp1.add(comboBox);

		for(int i = 0; i < 16 ; i ++)
		{
			checkBoxes[i].setOpaque(false);
			checkBoxes[i].setForeground(Color.WHITE);
			checkboxPanel.add(checkBoxes[i]);
		}
		temp1.add(checkboxPanel);

		clearOptions = new JButton("Clear");
		clearOptions.setBounds(0,150,100,30);
		clearOptions.addActionListener(bP);
		temp1.add(clearOptions);	

		addMovie = new JButton("Add Movie");
		addMovie.setBounds(100,150,100,30);
		addMovie.addActionListener(bP);
		temp1.add(addMovie);	

		submitOptions = new JButton("Submit");
		submitOptions.setBounds(200,150,140,30);
		submitOptions.addActionListener(bP);
		temp1.add(submitOptions);	

		JLabel nam = new JLabel("Current User: " + currentUser);
		nam.setFont(new Font("Tahoma", Font.BOLD, 16));
		nam.setForeground(Color.WHITE);

		JPanel name = new JPanel();
		GridBagConstraints gbc_name = new GridBagConstraints();
		gbc_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_name.insets = new Insets(0, 0, 0, 5);
		gbc_name.gridx = 1;
		gbc_name.gridy = 4;
		name.add(nam);
		name.setOpaque(false);
		background.add(name, gbc_name);

		rateMovie = new JButton("Rate Movie");
		GridBagConstraints gbc_rateMovie = new GridBagConstraints();
		gbc_rateMovie.fill = GridBagConstraints.HORIZONTAL;
		gbc_rateMovie.insets = new Insets(0, 0, 0, 5);
		gbc_rateMovie.gridx = 2;
		gbc_rateMovie.gridy = 4;
		background.add(rateMovie, gbc_rateMovie);
		rateMovie.addActionListener(bP);

		modifyUserBtn = new JButton("Modify User");
		GridBagConstraints gbc_modifyUserBtn = new GridBagConstraints();
		gbc_modifyUserBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_modifyUserBtn.insets = new Insets(0, 0, 0, 5);
		gbc_modifyUserBtn.gridx = 3;
		gbc_modifyUserBtn.gridy = 4;
		background.add(modifyUserBtn, gbc_modifyUserBtn);
		modifyUserBtn.addActionListener(bP);

		logoutBtn = new JButton("Sign Out");
		GridBagConstraints gbc_logoutBtn = new GridBagConstraints();
		gbc_logoutBtn.insets = new Insets(0, 0, 0, 5);
		gbc_logoutBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_logoutBtn.gridx = 4;
		gbc_logoutBtn.gridy = 4;
		background.add(logoutBtn, gbc_logoutBtn);

		background.add(rate, gbc_Ratepanel);
		pictures.add(layeredPane);
		background.add(pictures, gbc_panel);
		swapper.add(movie, "MOVIE");
		add(swapper);

	}

	public void SetImageSize (int i)
	{
		if(firstTime == true)
		{
			icon = new ImageIcon(getClass().getResource("/resources/"+ list.get(i-1)+".jpg"));
			old.setIcon(icon);

			icon = new ImageIcon(getClass().getResource("/resources/"+ list.get(i)+".jpg"));
			current.setIcon(icon);

			icon = new ImageIcon(getClass().getResource("/resources/"+ list.get(i+1)+".jpg"));
			newPic.setIcon(icon);
			firstTime = false;
		}
		else 
		{
			icon = new ImageIcon(getClass().getResource("/resources/"+ list.get(i+1)+".jpg"));
			newPic.setIcon(icon);
			old.setIcon(current.getIcon());
			icon = new ImageIcon(getClass().getResource("/resources/"+ list.get(i)+".jpg"));
			current.setIcon(icon);

		}
	}
	public void getInfo()
	{
		String temp = "";
		String movie = "";
		getGenre();

		if(comboBox.getItemCount() == variables.movieName.size())
		{
			movie = variables.movieName.get(comboBox.getSelectedIndex());
		}
		else{
			movie = (String)(comboBox.getSelectedItem());

			for(int l = 0; l < genre.size();l++)
			{
				if(l == genre.size()-1)
				{
					temp += genre.get(l);
				}
				else
				{
					temp += genre.get(l) + " ";
				}
			}
			movie.replaceAll(" ", "[_]");
			variables.movieName.add(movie);
			variables.genre.add(temp);
			saveMovie = true;

		}

		wF.setMovie(movie);
		wF.setScore(calculateScore());
		wF.setUsername(currentUser);


	}
	public int calculateScore()
	{
		int score = 0;

		for(int i = 0; i < 10;i++)
		{
			if(sR.golden[i] == true)
			{
				score++;
			}
		}
		score = convertScore(score);

		return score;
	}

	public void getGenre()
	{
		for(int i = 0; i < 16; i++)
		{
			if(checkBoxes[i].isSelected() == true)
			{
				genre.add(genreList[i]);
			}
		}
	}

	public int convertScore(int score)
	{
		int value = 0;

		switch(score)
		{
		case 1:
			value = -5;
			break;
		case 2:
			value = -4;
			break;
		case 3:
			value = -3;		
			break;
		case 4:
			value = -2;		
			break;
		case 5:
			value = -1;	
			break;
		case 6:
			value = 1;
			break;
		case 7:
			value = 2;
			break;
		case 8:
			value = 3;
			break;
		case 9:
			value = 4;
			break;
		case 10:
			value = 5;
			break;
		}
		return value;
	}

	public void addMovie()
	{
		ArrayList<String> userGenre = new ArrayList();
		ArrayList<String> cropped = new ArrayList();
		ArrayList<String> updatedList = new ArrayList();

		for(int l = 0; l < variables.userWatched.size();l++)
		{
			if(currentUser.equalsIgnoreCase(variables.studentNumber.get(l)) == true)
			{
				userGenre.add(variables.userWatched.get(l));
			}
		}

		for(int i = 0; i < userGenre.size(); i++)
		{
			userGenre.set(i, userGenre.get(i).replaceAll("[_]", " "));
		}

		Object[] remove = userGenre.toArray();
		for (Object s : remove)
		{
			if (userGenre.indexOf(s) != userGenre.lastIndexOf(s)) 
			{
				userGenre.remove(userGenre.lastIndexOf(s));
			}
		}	

		for(int i = 0; i < variables.movieName.size();i++)
		{
			cropped.add(variables.movieName.get(i));
			cropped.set(i, cropped.get(i).replaceAll("[_]", " "));
		}

		for(int l = 0; l < userGenre.size(); l++)
		{
			for(int i = 0; i < cropped.size(); i++)
			{
				String holder = cropped.get(i);
				String gen = userGenre.get(l);
				if(gen.equalsIgnoreCase(holder) == true)
				{
					cropped.remove(i);
					i = 0;
				}
			}
		}
		movieNams = cropped.toArray(new String[cropped.size()]);

		updatedList.clear();
		cropped.clear();
		userGenre.clear();
	}

	public class buttonPressed implements ActionListener
	{

		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == modifyUserBtn)
			{
				mU = new modifyUser();
				swapper.add(mU, "MODIFY");
				cardLayout.show(swapper, "MODIFY");
			}
			else if(e.getSource() == backButton)
			{
				cardLayout.show(swapper, "MOVIE");
			}
			else if(e.getSource() == addMovie)
			{
				String nam = JOptionPane.showInputDialog("Enter Movie Name");
				comboBox.setSelectedItem(nam);
				comboBox.addItem(nam);
				addMovie();
				comboBox.setSelectedItem(nam);
				addMovie.setEnabled(false);
				comboBox.setEnabled(false);
				pic.setIcon(empty);
			}
			else if(e.getSource() == rateMovie)
			{
				holder.add(options);
				holder.revalidate();
				holder.repaint();
				for(int i = 0; i < 16 ; i ++)
				{
					checkBoxes[i].setSelected(false);
				}
				pic.setIcon(null);
				rate.add(holder);
			}
			else if(e.getSource() == clearOptions)
			{
				sR.reset();
				comboBox.removeItemAt(comboBox.getItemCount()-1);
				comboBox.setSelectedIndex(0);
				addMovie.setEnabled(true);
				comboBox.setEnabled(true);
				for(int i = 0; i < 16 ; i ++)
				{
					checkBoxes[i].setSelected(false);
				}
				pic.setIcon(null);

			}
			else if(e.getSource() == btnClear)
			{
				mU.usernameTxt.setText("");
				mU.passwordTxt.setText("");
				mU.retypePassTxt.setText("");
			}
			else if(e.getSource() == submitOptions)
			{
				getInfo();			
				sR.reset();
				if(saveMovie == true)
				{
					wF.writeInfo("movie", false);
				}
				wF.writeInfo("movieData", true);
				comboBox.setSelectedIndex(0);
				addMovie.setEnabled(true);
				comboBox.setEnabled(true);
				holder.remove(options);
				holder.revalidate();
				holder.repaint();

			}
			else if(e.getSource() == submit)
			{
				if(mU.currentUser.getText().equalsIgnoreCase(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter a value", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(mU.passwordTxt.getText().equals(mU.retypePassTxt.getText()))
					{
						mU.changeInfo(mU.currentUser.getText(),mU.usernameTxt.getText(), mU.passwordTxt.getText());
						wF.writeInfo("passwords", false);
						cardLayout.show(swapper, "MOVIE");
					}
				}
			}
		}
	}

	public class modifyUser extends JPanel
	{
		private int position;
		private JTextField usernameTxt;
		private JTextField passwordTxt;
		private JTextField retypePassTxt;
		private JTextField currentUser;
		private JTextField oldPass;

		public modifyUser()
		{
			JLabel backgroundLbl = new JLabel(modify);

			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{159, 251, 0, 0, 66, 97, 98, 72};
			gbl_panel.rowHeights = new int[]{122, 47, 47, 47, 47, 47, 100, 50};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			backgroundLbl.setLayout(gbl_panel);

			JLabel lblNewLabel = new JLabel("Username:");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 1;
			backgroundLbl.add(lblNewLabel, gbc_lblNewLabel);

			currentUser = new JTextField();
			GridBagConstraints gbc_currentUser = new GridBagConstraints();
			gbc_currentUser.gridwidth = 3;
			gbc_currentUser.insets = new Insets(0, 0, 5, 5);
			gbc_currentUser.fill = GridBagConstraints.HORIZONTAL;
			gbc_currentUser.gridx = 1;
			gbc_currentUser.gridy = 1;
			backgroundLbl.add(currentUser, gbc_currentUser);
			currentUser.setColumns(10);

			JLabel lblNewUsername = new JLabel("New Username:");
			lblNewUsername.setForeground(Color.WHITE);
			lblNewUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblNewUsername = new GridBagConstraints();
			gbc_lblNewUsername.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewUsername.gridx = 0;
			gbc_lblNewUsername.gridy = 2;
			backgroundLbl.add(lblNewUsername, gbc_lblNewUsername);

			usernameTxt = new JTextField();
			GridBagConstraints gbc_newUsername = new GridBagConstraints();
			gbc_newUsername.gridwidth = 3;
			gbc_newUsername.insets = new Insets(0, 0, 5, 5);
			gbc_newUsername.fill = GridBagConstraints.HORIZONTAL;
			gbc_newUsername.gridx = 1;
			gbc_newUsername.gridy = 2;
			backgroundLbl.add(usernameTxt, gbc_newUsername);
			usernameTxt.setColumns(10);

			JLabel lblOldPassword = new JLabel("Old Password:");
			lblOldPassword.setForeground(Color.WHITE);
			lblOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblOldPassword = new GridBagConstraints();
			gbc_lblOldPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblOldPassword.gridx = 0;
			gbc_lblOldPassword.gridy = 3;
			backgroundLbl.add(lblOldPassword, gbc_lblOldPassword);

			oldPass = new JTextField();
			GridBagConstraints gbc_oldPass = new GridBagConstraints();
			gbc_oldPass.gridwidth = 3;
			gbc_oldPass.insets = new Insets(0, 0, 5, 5);
			gbc_oldPass.fill = GridBagConstraints.HORIZONTAL;
			gbc_oldPass.gridx = 1;
			gbc_oldPass.gridy = 3;
			backgroundLbl.add(oldPass, gbc_oldPass);
			oldPass.setColumns(10);

			JLabel lblPassword = new JLabel("New Password:");
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPassword.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPassword.gridx = 0;
			gbc_lblPassword.gridy = 4;
			backgroundLbl.add(lblPassword, gbc_lblPassword);

			passwordTxt = new JTextField();
			GridBagConstraints gbc_newPass = new GridBagConstraints();
			gbc_newPass.gridwidth = 3;
			gbc_newPass.insets = new Insets(0, 0, 5, 5);
			gbc_newPass.fill = GridBagConstraints.HORIZONTAL;
			gbc_newPass.gridx = 1;
			gbc_newPass.gridy = 4;
			backgroundLbl.add(passwordTxt, gbc_newPass);
			passwordTxt.setColumns(10);

			JLabel lblRetypePassword = new JLabel("Retype New Password:");
			lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblRetypePassword.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblRetypePassword = new GridBagConstraints();
			gbc_lblRetypePassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblRetypePassword.gridx = 0;
			gbc_lblRetypePassword.gridy = 5;
			backgroundLbl.add(lblRetypePassword, gbc_lblRetypePassword);

			retypePassTxt = new JTextField();
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.gridwidth = 3;
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.insets = new Insets(0, 0, 5, 5);
			gbc_textField_2.gridx = 1;
			gbc_textField_2.gridy = 5;
			backgroundLbl.add(retypePassTxt, gbc_textField_2);
			retypePassTxt.setColumns(10);

			btnClear = new JButton("Clear");
			GridBagConstraints gbc_gbbtnClear = new GridBagConstraints();
			gbc_gbbtnClear.gridwidth = 3;
			gbc_gbbtnClear.fill = GridBagConstraints.HORIZONTAL;
			gbc_gbbtnClear.insets = new Insets(0, 0, 0, 5);
			gbc_gbbtnClear.gridx = 2;
			gbc_gbbtnClear.gridy = 7;
			backgroundLbl.add(btnClear, gbc_gbbtnClear);
			btnClear.addActionListener(bP);

			backButton = new JButton("Back");
			GridBagConstraints gbc_backButton = new GridBagConstraints();
			gbc_backButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_backButton.insets = new Insets(0, 0, 0, 5);
			gbc_backButton.gridx = 5;
			gbc_backButton.gridy = 7;
			backgroundLbl.add(backButton, gbc_backButton);
			backButton.addActionListener(bP);

			submit = new JButton("Submit");
			GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
			gbc_btnSubmit.gridwidth = 2;
			gbc_btnSubmit.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnSubmit.gridx = 6;
			gbc_btnSubmit.gridy = 7;
			backgroundLbl.add(submit, gbc_btnSubmit);
			submit.addActionListener(bP);
			add(backgroundLbl);
		}
		public void changeInfo(String oldUser, String newUser, String pass)
		{
			if(variables.password.get(position).equalsIgnoreCase(oldPass.getText()))
			{
				position = variables.username.indexOf(oldUser);	
				if(newUser.isEmpty() == false)
				{

					variables.username.set(position, newUser);
					variables.password.set(position, pass);
				}
				else
				{
					newUser = oldUser;
					variables.username.set(position, newUser);
					variables.password.set(position, pass);
				}
			}
			else
			{
				JOptionPane.showMessageDialog (null, "Check your password", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
