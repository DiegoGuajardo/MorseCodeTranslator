package morsecodetranslatorapplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

// The KeyListener interface is used here so that we can listen to keyboard events, e.g., keystrokes.
public class MainFrame extends JFrame implements KeyListener {

	private static final long serialVersionUID = -4580987434784352330L;

	// Declares the private instance variable of translator.
	private Translator translator;

	private JPanel userInputPanel; // Panel for user input
	private JTextArea userInputTextArea; // Text area for user input
	private JPanel programOutputPanel; // Panel for program output
	private JTextArea programOutputTextArea; // Text area for program output

	private Highlighter highlighter; // For highlighting program output
	private boolean highlighted = false; // For removing highlights

	private Font orbitron;

	private ImageIcon imageLogo;

	/**
	 * Purpose of Method: Creates the MainFrame() constructor method. This
	 * constructor method creates the main frame for our Morse code translator
	 * application.
	 */
	public MainFrame() {

		// The title of our Morse code translator application.
		super("The DotDashDevs' Morse Code Translator");

		try {
			// create the font to use. Specify the size!
			orbitron = Font.createFont(Font.TRUETYPE_FONT,
					new File("./src/fonts/Orbitron-VariableFont_wght.ttf"));
			// orbitronB = Font.createFont(Font.TRUETYPE_FONT, new
			// File("./src/fonts/static/Orbitron-Bold.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			// register the font
			ge.registerFont(orbitron);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}

		// Sets the size of the main frame to 700 pixels by 530 pixels,
		// respectively.
		setSize(new Dimension(700, 530));

		// Makes it so that the main frame cannot be resized.
		setResizable(false);

		// When our Morse code translator application is executed, the main
		// frame will be placed in the center of the screen.
		setLocationRelativeTo(null);

		// Sets the layout of the main frame to null. By doing so, we get to
		// manually position and set the size of the components in our Morse
		// code translator application.
		setLayout(null);

		// Changes the background color of our Morse code translator
		// application.
		getContentPane().setBackground(Color.decode("#CECFD6")); // [Earlier
																	// version
																	// color:
																	// (Color.decode("#ECE9D8"))]

		// When the main frame is closed, our Morse code translator application
		// is terminated.
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Creates a new instance of the Translator class.
		translator = new Translator();

		userInputPanel = createUserInputPanel(
				" USER INPUT: [Plain Text/Morse Code] "); // Label for user
															// input panel
		programOutputPanel = createProgramOutputPanel(" TRANSLATION: "); // Label
																			// for
																			// program
																			// output
																			// panel

		addGUIComponents(); // Calls upon the addGUIComponents() method.

	} // End of the MainFrame() constructor method.

	/**
	 * Purpose of Method: Creates the addGUIComponents() method. This method
	 * adds the GUI components within the main frame of our Morse code
	 * translator application.
	 */
	private void addGUIComponents() {

		// Creates a new JLabel object.
		JLabel titleLabel = new JLabel("Morse Code Translator");

		// Defines the design constraints that are imposed on the JLabel object.
		titleLabel.setBounds(0, 0, 500, 40);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("orbitron", Font.BOLD, 21));
		titleLabel.setForeground(Color.BLACK);

		// Sets both the x and y positions, as well as the width and height
		// dimensions of the two JPanel objects.
		userInputPanel.setBounds(20, 40, 450, 200);
		programOutputPanel.setBounds(20, 250, 450, 200);

		// Creates six new JButton objects.
		JButton legendButton = new JButton("LEGEND");
		JButton copyButton = new JButton("COPY");
		JButton pasteButton = new JButton("PASTE");
		JButton playButton = new JButton("PLAY");
		JButton clearButton = new JButton("CLEAR");
		JButton licenseButton = new JButton("LICENSE");

		// Sets both the x and y positions, as well as the width and height
		// dimensions of the six JButton objects.
		legendButton.setBounds(510, 155, 140, 40);
		copyButton.setBounds(510, 210, 140, 40);
		pasteButton.setBounds(510, 255, 140, 40);
		playButton.setBounds(510, 300, 140, 40);
		clearButton.setBounds(510, 345, 140, 40);
		licenseButton.setBounds(510, 400, 140, 40);

		// Changes the color of the six JButton objects.
		legendButton.setBackground(Color.decode("#F4F3EB"));
		copyButton.setBackground(Color.decode("#F4F3EB"));
		pasteButton.setBackground(Color.decode("#F4F3EB"));
		playButton.setBackground(Color.decode("#F4F3EB"));
		clearButton.setBackground(Color.decode("#F4F3EB"));
		licenseButton.setBackground(Color.decode("#F4F3EB"));

		// Changes the font of the six JButton objects.
		legendButton.setFont(new Font("orbitron", Font.PLAIN, 14));
		copyButton.setFont(new Font("orbitron", Font.PLAIN, 14));
		pasteButton.setFont(new Font("orbitron", Font.PLAIN, 14));
		playButton.setFont(new Font("orbitron", Font.PLAIN, 14));
		clearButton.setFont(new Font("orbitron", Font.PLAIN, 14));
		licenseButton.setFont(new Font("orbitron", Font.PLAIN, 14));

		/*
		 * Adding action listeners to the LEGEND, COPY, PASTE, PLAY, CLEAR, and
		 * LICENSE JButton objects.
		 */
		// For the LEGEND JButton object:
		legendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tablePopupWindow(); // Calls upon the tablePopupWindow() method.

			} // End of the actionPerformed() method.

		});

		// For the COPY JButton object:
		copyButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Selects all the text in the programOutputTextArea JTextArea
				// object.
				programOutputTextArea.selectAll();

				// Copies the selected text in the programOutputTextArea
				// JTextArea object to the system clipboard.
				programOutputTextArea.copy();

				// Obtains the highlighter associated with the
				// programOutputTextArea JTextArea object.
				highlighter = programOutputTextArea.getHighlighter();

				// Creates a new HighlightPainter object with a cyan color using
				// the DefaultHighlighter class.
				HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(
						Color.CYAN);

				try {

					// Removes any existing highlights in the output area.
					highlighter.removeAllHighlights();

					// Highlights all the text in the output area using the
					// specified painter, i.e., highlight color.
					highlighter.addHighlight(0,
							programOutputTextArea.getText().length(), painter);

					// Sets the flag to indicate that all the text is currently
					// highlighted.
					highlighted = true;

				} catch (BadLocationException e1) {

					// Prints the stack trace if an exception of the
					// BadLocationException type occurs during this process.
					e1.printStackTrace();

				}

			} // End of the actionPerformed() method.

		});

		// For the PASTE JButton object:
		pasteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Clears the input area before pasting to avoid appending to
				// any existing text.
				userInputTextArea.setText("");

				// Pastes the copied text from the system clipboard into the
				// input area.
				userInputTextArea.paste();

				// If there was text highlighted in the output area, the
				// removeAllHighlights() method removes the highlights.
				if (highlighted)
					highlighter.removeAllHighlights();

				// Resets the flag to indicate that there are currently no
				// highlights.
				highlighted = false;

				translateUserInput(); // Calls upon the translateUserInput()
										// method.

			} // End of the actionPerformed() method.

		});

		// For the PLAY JButton object:
		playButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Disables the PLAY JButton object. This prevents the audible
				// Morse code message from getting interrupted.
				playButton.setEnabled(false);

				// Creates a new Thread object.
				Thread playAudibleMorseCodeMessage = new Thread(new Runnable() {

					// Implements the run() method from the Runnable interface.
					@Override
					public void run() {

						/*
						 * Attempts to play the audible Morse code message
						 */
						try {

							// Splits a sequence of signal durations in the
							// programOutputTextArea JTextArea object into
							// individual signal durations.
							String[] morseCodeMessage = programOutputTextArea
									.getText().split(" ");

							// Plays the audible Morse code message.
							translator.playSound(morseCodeMessage);

						} catch (LineUnavailableException lineUnavailableException) {

							// Prints the stack trace if an exception of the
							// LineUnavailableException type occurs during this
							// process.
							lineUnavailableException.printStackTrace();

						} catch (InterruptedException interruptedException) {

							// Prints the stack trace if an exception of the
							// InterruptedException type occurs during this
							// process.
							interruptedException.printStackTrace();

						} finally {

							// Enables the PLAY JButton object to allow for user
							// interaction after the audible Morse code message
							// has played to its entirety.
							playButton.setEnabled(true);

						}

					} // End of the run() method.

				});

				// Starts the playAudibleMorseCodeMessage Thread object to play
				// the audible Morse code message.
				playAudibleMorseCodeMessage.start();

			} // End of the actionPerformed() method.

		});

		// For the CLEAR JButton object:
		clearButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Clears the text content in the userInputTextArea JTextArea
				// object.
				userInputTextArea.setText("");

				// Clears the text content in the programOutputTextArea
				// JTextArea object.
				programOutputTextArea.setText("");

			} // End of the actionPerformed() method.

		});

		// For the LICENSE JButton object:
		licenseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				licensePopupWindow(); // Calls upon the licensePopupWindow()
										// method.

			} // End of the actionPerformed() method.

		});

		// Creates three new JLabel objects.
		JLabel leftLabel = new JLabel("For CMSC 495 Fall 2023"); // Displays
																	// course
																	// information
		JLabel centerLabel = new JLabel("Group B / DotDashDevs"); // Displays
																	// group/development
																	// team name
		JLabel rightLabel = new JLabel("Layout Version 3.1"); // Displays
																// application
																// version
																// information

		// Changes the font, font weight, and font size of the three JLabel
		// objects.
		leftLabel.setFont(new Font("orbitron", Font.BOLD, 10));
		centerLabel.setFont(new Font("orbitron", Font.BOLD, 10));
		rightLabel.setFont(new Font("orbitron", Font.BOLD, 10));

		// Changes the font color of the three JLabel objects.
		leftLabel.setForeground(Color.BLACK);
		centerLabel.setForeground(Color.BLACK);
		rightLabel.setForeground(Color.BLACK);

		// Sets both the x and y positions, as well as the width and height
		// dimensions of the three JLabel objects.
		leftLabel.setBounds(10, 470, 150, 20);
		centerLabel.setBounds(280, 470, 150, 20);
		rightLabel.setBounds(560, 470, 150, 20);

		// Adds 12 GUI components to the main frame of our Morse code translator
		// application.
		add(titleLabel);
		add(userInputPanel);
		add(programOutputPanel);
		add(legendButton);
		add(copyButton);
		add(pasteButton);
		add(playButton);
		add(clearButton);
		add(licenseButton);
		add(leftLabel);
		add(centerLabel);
		add(rightLabel);

		// Try to load the logo image using the class resource URL.
		try {
			// Get the URL of the logo image from the classpath. Note: The
			// image must be included in the morsecodetranslatorapplication
			// package.
			URL imageURL = getClass().getResource("DotDashDevs_Logo.png");

			// Check to make sure image is not null and create a new instance of
			// ImageIcon using the loaded image.
			if (imageURL != null) {
				imageLogo = new ImageIcon(imageURL);

				// Create width and height and call resizeImage to resize the
				// image to fit the GUI.
				int scaledWidth = 140;
				int scaledHeight = 100;
				imageLogo = resizeImage(imageLogo, scaledWidth, scaledHeight);

				// Create a JLabel to display the image.
				JLabel imageLabel = new JLabel(imageLogo);

				// Set the position and size of the JLabel on the main frame.
				imageLabel.setBounds(510, 30, scaledWidth, scaledHeight);

				// Add the imageLabel to the main frame.
				add(imageLabel);
			} else {
				// Print an error message if the image is not found.
				System.err.println("Image Not Found");
			}

		} catch (Exception e) {
			// Print the stack trace if an exception occurs.
			e.printStackTrace();
		} // End of try-catch.

	} // End of the addGUIComponents() method.

	/**
	 * Purpose of Method: Creates the createUserInputPanel() method. This method
	 * defines the design constraints that are imposed on the panel for user
	 * input.
	 */
	private JPanel createUserInputPanel(String title) {

		// Creates a new JPanel object with a BorderLayout and overrides its
		// paintComponent() method.
		JPanel panel = new JPanel(new BorderLayout()) {

			private static final long serialVersionUID = 8375506471317380761L;

			@Override
			protected void paintComponent(Graphics g) {

				// Calls the superclass paintComponents() method to ensure
				// proper painting.
				super.paintComponents(g);

				// Sets the graphics color to fully transparent black.
				g.setColor(new Color(0, 0, 0, 0));

				// Fills a rectangle with the fully transparent black graphics
				// color to make the JPanel object transparent.
				g.fillRect(0, 0, getWidth(), getHeight());

			} // End of the paintComponent() method.

		};

		// Creates a Border object.
		Border blackLine = BorderFactory.createLineBorder(Color.BLACK);

		// Creates a TitledBorder object.
		TitledBorder titledBorder = BorderFactory.createTitledBorder(blackLine,
				title);

		// Set TitledBorder font.
		titledBorder.setTitleFont(new Font("orbitron", Font.PLAIN, 14));

		// Sets the TitledBorder object as the border for the JPanel object.
		panel.setBorder(BorderFactory.createCompoundBorder(titledBorder,
				BorderFactory.createEmptyBorder(1, 12, 8, 12)));

		// Creates a new JTextArea object.
		userInputTextArea = new JTextArea();

		// Changes the font, font weight, and font size of the userInputTextArea
		// JTextArea object.
		userInputTextArea.setFont(new Font("orbitron", Font.PLAIN, 19));

		// Maintains an all-around padding of five pixels in this text area.
		userInputTextArea
				.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// Makes it so that keyboard events, e.g., keystrokes, are being
		// listened to whenever a user is typing in this text area.
		userInputTextArea.addKeyListener(this);

		// Makes it so that user input wraps to the next line after reaching the
		// end of this text area.
		userInputTextArea.setLineWrap(true);

		// Makes it so that if user input does get wrapped, it does not get
		// split up in this text area.
		userInputTextArea.setWrapStyleWord(true);

		// Creates a responsive scroll bar, i.e., adds scrolling capabilities,
		// in this text area.
		JScrollPane userInputPanelScrollBar = new JScrollPane(
				userInputTextArea);
		panel.add(userInputPanelScrollBar, BorderLayout.CENTER);

		// Returns the JPanel object.
		return panel;

	} // End of the createUserInputPanel() method.

	/**
	 * Purpose of Method: Creates the createProgramOutputPanel() method. This
	 * method defines the design constraints that are imposed on the panel for
	 * program output.
	 */
	private JPanel createProgramOutputPanel(String title) {

		// Creates a new JPanel object with a BorderLayout and overrides its
		// paintComponent() method.
		JPanel panel = new JPanel(new BorderLayout()) {

			private static final long serialVersionUID = -7942162614623604589L;

			@Override
			protected void paintComponent(Graphics g) {

				// Calls the superclass paintComponents() method to ensure
				// proper painting.
				super.paintComponents(g);

				// Sets the graphics color to fully transparent black.
				g.setColor(new Color(0, 0, 0, 0));

				// Fills a rectangle with the fully transparent black graphics
				// color to make the JPanel object transparent.
				g.fillRect(0, 0, getWidth(), getHeight());

			} // End of the paintComponent() method.

		};

		// Creates a Border object.
		Border blackLine = BorderFactory.createLineBorder(Color.BLACK);

		// Creates a TitledBorder object.
		TitledBorder titledBorder = BorderFactory.createTitledBorder(blackLine,
				title);

		// Set TitledBorder font.
		titledBorder.setTitleFont(new Font("orbitron", Font.PLAIN, 14));

		// Sets the TitledBorder object as the border for the JPanel object.
		panel.setBorder(BorderFactory.createCompoundBorder(titledBorder,
				BorderFactory.createEmptyBorder(1, 12, 8, 12)));

		// Creates a new JTextArea object.
		programOutputTextArea = new JTextArea();

		// Changes the font, font weight, and font size of the
		// programOutputTextArea JTextArea object.
		programOutputTextArea.setFont(new Font("orbitron", Font.BOLD, 19));

		// Maintains an all-around padding of five pixels in this text area.
		programOutputTextArea
				.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// Makes it so that program output cannot be edited, i.e., a user cannot
		// type in this text area.
		programOutputTextArea.setEditable(false);

		// Makes it so that program output wraps to the next line after reaching
		// the end of this text area.
		programOutputTextArea.setLineWrap(true);

		// Makes it so that if program output does get wrapped, it does not get
		// split up in this text area.
		programOutputTextArea.setWrapStyleWord(true);

		// Creates a responsive scroll bar, i.e., adds scrolling capabilities,
		// in this text area.
		JScrollPane programOutputPanelScrollBar = new JScrollPane(
				programOutputTextArea);
		panel.add(programOutputPanelScrollBar, BorderLayout.CENTER);

		// Returns the JPanel object.
		return panel;

	} // End of the createProgramOutputPanel() method.

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {

		// Ignores a shift key press.
		if (e.getKeyCode() != KeyEvent.VK_SHIFT) {

			translateUserInput(); // Calls upon the translateUserInput() method.

		}

	} // End of the keyReleased() method.

	/**
	 * Purpose of Method: Creates the translateUserInput() method. This method
	 * translates user input into Morse code or plain text.
	 */
	private void translateUserInput() {

		// Retrieves user input; that is, plain text or Morse code that is to be
		// translated.
		String userInput = userInputTextArea.getText();

		// Checks if user input contains only characters supported by
		// International Morse code (i.e., letters, numbers, special
		// characters).
		boolean containsSupportedCharacters = userInput
				.matches("[A-Za-z0-9\\s&@)+(:,=!\"?/'%.-]*");

		if (containsSupportedCharacters) {

			// Checks if user input contains Morse code characters (i.e., dots,
			// dashes, spaces, forward slashes).
			boolean containsMorseCodeCharacters = userInput
					.matches("[\\.\\-\\s/]+");

			// Determines whether to use the translateToPlainText() method or
			// the translateToMorseCode() method based on the type of user
			// input.
			if (containsMorseCodeCharacters) {

				// Displays program output; that is, the plain text translation
				// of the Morse code.
				programOutputTextArea
						.setText(translator.translateToPlainText(userInput));

			} else {

				// Displays program output; that is, the Morse code translation
				// of the plain text.
				programOutputTextArea
						.setText(translator.translateToMorseCode(userInput));

			}

		} else {

			// Displays error message; that is, prompts user to take appropriate
			// action for invalid input.
			JOptionPane.showMessageDialog(this,
					"Invalid character detected in input. Please enter only valid plain text or Morse code characters.");

			// Clears the text content in the userInputTextArea JTextArea
			// object.
			userInputTextArea.setText("");

			// Clears the text content in the programOutputTextArea JTextArea
			// object.
			programOutputTextArea.setText("");
		}

	} // End of the translateUserInput() method.

	/**
	 * Purpose of Method: Creates the tablePopupWindow() method. This method
	 * displays a JTable with all the characters our Morse code translator
	 * application accepts.
	 */
	private void tablePopupWindow() {

		// Creates a new JFrame object.
		JFrame popupWindow = new JFrame("Application Legend");

		// Sets the default close operation for the popup window to dispose when
		// closed.
		popupWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Morse code mappings
		Object[][] tableData = {{"Aa", ".-"}, {"Bb", "-..."}, {"Cc", "-.-."},
				{"Dd", "-.."}, {"Ee", "."}, {"Ff", "..-."}, {"Gg", "--."},
				{"Hh", "...."}, {"Ii", ".."}, {"Jj", ".---"}, {"Kk", "-.-"},
				{"Ll", ".-.."}, {"Mm", "--"}, {"Nn", "-."}, {"Oo", "---"},
				{"Pp", ".--."}, {"Qq", "--.-"}, {"Rr", ".-."}, {"Ss", "..."},
				{"Tt", "-"}, {"Uu", "..-"}, {"Vv", "...-"}, {"Ww", ".--"},
				{"Xx", "-..-"}, {"Yy", "-.--"}, {"Zz", "--.."}, {"0", "-----"},
				{"1", ".----"}, {"2", "..---"}, {"3", "...--"}, {"4", "....-"},
				{"5", "....."}, {"6", "-...."}, {"7", "--..."}, {"8", "---.."},
				{"9", "----."}, {"{space}", "/"}, {"&", ".-..."},
				{"\'", ".----."}, {"@", ".--.-."}, {")", "-.--.-"},
				{"(", "-.--."}, {":", "---..."}, {",", "--..--"},
				{"=", "-...-"}, {"!", "-.-.--"}, {".", ".-.-.-"},
				{"-", "-....-"}, {"x", "-..-"}, {'%', "------..-.-----"},
				{"+", ".-.-."}, {"\"", ".-..-."}, {"?", "..--.."},
				{"/", "-..-."},};

		// Name of columns
		String[] columnHeadings = {"Character", "Morse Code"};

		// Creates a new DefaultTableModel object using the tableData array and
		// the columnHeadings String array.
		DefaultTableModel model = new DefaultTableModel(tableData,
				columnHeadings);

		// Creates a new JTable object using the specified DefaultTableModel
		// object.
		JTable table = new JTable(model);

		// Sets the grid color of the JTable object to light gray.
		table.setGridColor(Color.LIGHT_GRAY);

		// Obtains the table headers associated with the JTable object.
		JTableHeader tableHeaders = table.getTableHeader();

		// Changes the background color of the table headers.
		tableHeaders.setBackground(Color.decode("#CECFD6"));

		// Changes the font, font weight, font size, font color, and cell size
		// of the table headers.
		tableHeaders.setFont(new Font("Courier", Font.PLAIN, 24));
		tableHeaders.setForeground(Color.BLACK);
		tableHeaders
				.setPreferredSize(new Dimension(tableHeaders.getWidth(), 30));

		// Changes the font, font weight, and font size of the table data.
		Font tableFont = new Font("Courier", Font.PLAIN, 20);
		table.setFont(tableFont);

		// Sets the height of each row in the JTable object to 30 pixels.
		table.setRowHeight(30);

		// Sets the preferred width of the first column and the second column in
		// the JTable object to 100 pixels.
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);

		// Sets the intercell spacing, i.e., gap between cells, in the JTable
		// object to ten pixels horizontally and five pixels vertically.
		table.setIntercellSpacing(new Dimension(10, 5));

		// Creates a new DefaultTableCellRenderer object for center alignment of
		// cell content.
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

		// Sets the horizontal alignment of the renderer to center and sets the
		// default cell renderer for all columns in the JTable object to the
		// DefaultTableCellRenderer object.
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);

		// Changes the background color of the JTable object.
		table.setBackground(Color.decode("#F4F3EB"));

		// Changes the background color of selected cells in the JTable object.
		table.setSelectionBackground(new Color(173, 216, 230));

		// Creates a responsive scroll bar, i.e., adds scrolling capabilities,
		// in this JTable object.
		JScrollPane scrollPane = new JScrollPane(table);

		// Adds the JScrollPane object to the JFrame object.
		popupWindow.getContentPane().add(scrollPane);

		// Sets the size of the JFrame object to 350 pixels by 265 pixels,
		// respectively.
		popupWindow.setSize(350, 265);

		// When the LEGEND JButton object is pressed, the JFrame object will be
		// placed in the center of the screen.
		popupWindow.setLocationRelativeTo(null);

		// Makes the JFrame object visible by invoking the setVisible(true)
		// method on it.
		popupWindow.setVisible(true);

	} // End of the tablePopupWindow() method.

	/**
	 * Purpose of Method: Creates the licensePopupWindow() method. This method
	 * displays a JOptionPane with credits, license information, and permissions
	 * related to our Morse code translator application.
	 */
	private void licensePopupWindow() {

		// Displays message; that is, provides credits, license information, and
		// permissions related to our Morse code translator application.
		JOptionPane.showMessageDialog(null,
				"Thank you for using our Morse Code Translator Application - DotDashDevs\n\nCopyright © 2023 DotDashDevs\r\nJames Michael Asuncion\r\n"
						+ "Jeanie Ye Ji Kim\r\n" + "Joshua Gray\r\n"
						+ "Abi Acharya\r\n" + "Abbie Garth Brookman\r\n"
						+ "Diego Guajardo\n\n"
						+ "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and\r\n"
						+ "associated documentation files (the \"Software\"), to deal in the Software without restriction,\r\n"
						+ "including without limitation the rights to use, copy, modify, merge, publish, distribute,\r\n"
						+ "sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is\r\n"
						+ "furnished to do so, subject to the following conditions:\r\n"
						+ "\nThe above copyright notice and this permission notice shall be included in all copies or\r\n"
						+ "substantial portions of the Software.",
				"License", JOptionPane.INFORMATION_MESSAGE);

	} // End of the licensePopupWindow() method.

	/**
	 * Resizes the given ImageIcon to the specified width and height.
	 *
	 * @param originalImage
	 *            The original ImageIcon to be resized.
	 * @param width
	 *            The desired width of the resized image.
	 * @param height
	 *            The desired height of the resized image.
	 * @return A new ImageIcon representing the resized image.
	 */

	private ImageIcon resizeImage(ImageIcon originalImage, int width,
			int height) {

		// Get the Image object from the original ImageIcon.
		Image image = originalImage.getImage();

		// Scale the Image to the specified width and height.
		Image scaledImage = image.getScaledInstance(width, height,
				Image.SCALE_SMOOTH);

		// Create and return a new ImageIcon from the scaled Image.
		return new ImageIcon(scaledImage);
	}// End of the resizeImage() method.

} // End of our MainFrame program.