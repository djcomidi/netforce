package desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

import netforcechallenge.Permissions;
import netforcechallenge.SecurityDatabase;
import netforcechallenge.User;

public class LoginChallengeDesktop extends JFrame implements ActionListener {

	private JButton btnSubmit;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JLabel lblInfo;
	private SecurityDatabase sdb;

	public LoginChallengeDesktop() {
		super("Login Challenge Desktop");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initLayout();
		loadDatabase();
	}

	private void initLayout() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.add(lblUsername, gbc);
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		txtUsername = new FocusedTextField();
		this.add(txtUsername, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		JLabel lblPassword = new JLabel("Password:");
		this.add(lblPassword, gbc);
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		txtPassword = new FocusedTextField();
		this.add(txtPassword, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		btnSubmit = new JButton("Authenticate");
		btnSubmit.addActionListener(this);
		this.add(btnSubmit, gbc);
		gbc.gridy = 3;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		JPanel pnlInfo = new JPanel(new BorderLayout());
		pnlInfo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		lblInfo = new JLabel("");
		lblInfo.setOpaque(true);
		lblInfo.setBackground(Color.BLACK);
		lblInfo.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 18));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setVerticalAlignment(SwingConstants.CENTER);
		pnlInfo.add(lblInfo, BorderLayout.CENTER);
		this.add(pnlInfo, gbc);
	}

	private void loadDatabase() {
		InputStream is = LoginChallengeDesktop.class.getResourceAsStream("/database");
		try {
			ObjectInputStream ois = new ObjectInputStream(is);
			sdb = (SecurityDatabase) ois.readObject();
			updateInfo("Database loaded", Color.GREEN);
		} catch (IOException e) {
			updateInfo("ERROR: Stream corrupt");
			sdb = null;
		} catch (ClassNotFoundException e) {
			updateInfo("ERROR: Class exception (class not found)");
			sdb = null;
		}
	}

	private void updateInfo(String text) {
		updateInfo(text, Color.RED);
	}

	private void updateInfo(String text, Color color) {
		lblInfo.setText(text);
		lblInfo.setForeground(color);
	}

	public byte[] getHash(String text) {
		byte[] hash = null;
		try {
			byte[] bytes = text.getBytes();
			MessageDigest msgDigest = MessageDigest.getInstance("MD5");
			hash = msgDigest.digest(bytes);
		} catch (Exception e) {
			hash = null;
		}
		return hash;
	}

	private boolean byteArrayCompare(byte arrA[], byte arrB[]) {
		if (arrA.length != arrB.length) {
			return false;
		}
		boolean allEqual = true;
		for (int i = 0; i < arrA.length; i++) {
			allEqual &= arrA[i] == arrB[i];
		}
		return allEqual;
	}

	private void authenticate() {
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		User user = sdb.getUserByName(username);
		if (user == null) {
			updateInfo("Invalid user...");
			return;
		}
		byte[] hashBytes = getHash(password);
		if (byteArrayCompare(hashBytes, user.getPwdBytes())) {
			Permissions perms = user.getPermissions();
			if (perms.isUser()) {
				updateInfo("This is an user account... sorry");
			} else if (perms.isAdmin()) {
				updateInfo("This is an admin account... sorry");
			} else if (perms.isSuperUser()) {
				updateInfo("Access granted... well done", Color.GREEN);
			}
		} else {
			updateInfo("Invalid password...");
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btnSubmit) {
			authenticate();
		}
	}

	private class FocusedTextField extends JTextField implements FocusListener, KeyListener {
		public FocusedTextField() {
			super();
			addFocusListener(this);
			addKeyListener(this);
		}

		@Override
		public void focusGained(FocusEvent e) {
			JTextField tf = (JTextField) e.getSource();
			tf.selectAll();
		}

		@Override
		public void focusLost(FocusEvent e) {
			JTextField tf = (JTextField) e.getSource();
			tf.select(0, 0);
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				authenticate();
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		LoginChallengeDesktop lcd = new LoginChallengeDesktop();
		lcd.setSize(400, 200);
		lcd.setLocationRelativeTo(null);
		lcd.setVisible(true);
	}

}
