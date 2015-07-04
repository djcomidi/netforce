package applet;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;

import netforcechallenge.Permissions;
import netforcechallenge.SecurityDatabase;
import netforcechallenge.User;

public class Challenge extends Applet implements ActionListener {
	private static final String DB_NAME = decrypt("[\004~\b=^\026o");
	private static final String MSG_ACCESS_GRANTED = decrypt("~\006i\f,LEm\033>Q\021o\rq\021K*\036:S\t*\r0Q\0");
	private static final String MSG_CLASS_NOT_FOUND = decrypt("z7X&\r\005EI\005>L\026*\f'\\\000z\0356P\013*A<S\004y\032\177Q\n~I9P\020d\rv");
	private static final String MSG_FILE_NOT_FOUND = decrypt("z7X&\r\005E_;\023\037\000r\n:O\021c\0061\037Ml\0003ZEd\006+\037\003e\0341[L");
	private static final String MSG_INVALID_PASSWORD = decrypt("v\013|\b3V\001*\031>L\026}\006-[K$G");
	private static final String MSG_INVALID_URL = decrypt("z7X&\r\005E_;\023\037\fyI1P\021*\037>S\fn");
	private static final String MSG_INVALID_USER = decrypt("v\013|\b3V\001*\034,Z\027$Gq");
	private static final String MSG_IS_ADMIN_ACCOUNT = decrypt("k\rc\032V\026*\b1\037\004n\0046QEk\n<P\020d\035q\021K*\0320M\027s");
	private static final String MSG_IS_USER_ACCOUNT = decrypt("k\rc\032V\026*\b1\037\020y\f-\037\004i\n0J\013~Gq\021Ey\006-M\034");
	private static final String MSG_STREAM_CORRUPT = decrypt("z7X&\r\005EY\035-Z\004gI<P\027x\034/K");
	private static final String TXT_AUTHENTICATE = decrypt("~\020~\001:Q\021c\n>K\0");
	private static final String TXT_MD5 = decrypt("r!?");
	private static final String TXT_PASSWORD = decrypt("o\004y\032(P\027nS");
	private static final String TXT_USERNAME = decrypt("j\026o\0331^\boS");
	private Label lblUsername;
	private TextField txtUsername;
	private Label lblPassword;
	private TextField txtPassword;
	private Button btnSubmit;
	private Label lblInfo;
	private SecurityDatabase sdb;

	@Override
	public void init() {
		setLayout(new GridBagLayout());
		setBackground(Color.BLACK);
		lblUsername = new Label(TXT_USERNAME);
		lblUsername.setForeground(Color.GREEN);
		addToGridBag(lblUsername, 0, 0, 1, 1, 0, 1, 1, 17, new Insets(0, 0, 0, 0));
		txtUsername = new TextField(20);
		txtUsername.setBackground(Color.BLACK);
		txtUsername.setForeground(Color.GREEN);
		addToGridBag(txtUsername, 0, 1, 1, 1, 0, 1, 1, 17, new Insets(0, 0, 0, 0));
		lblPassword = new Label(TXT_PASSWORD);
		lblPassword.setForeground(Color.GREEN);
		addToGridBag(lblPassword, 1, 0, 1, 1, 0, 1, 1, 17, new Insets(0, 0, 0, 0));
		txtPassword = new TextField(20);
		txtPassword.setBackground(Color.BLACK);
		txtPassword.setForeground(Color.GREEN);
		addToGridBag(txtPassword, 1, 1, 1, 1, 0, 1, 1, 17, new Insets(0, 0, 0, 0));
		btnSubmit = new Button(TXT_AUTHENTICATE);
		btnSubmit.addActionListener(this);
		btnSubmit.setForeground(Color.WHITE);
		addToGridBag(btnSubmit, 2, 1, 1, 1, 0, 1, 1, 17, new Insets(0, 0, 0, 0));
		lblInfo = new Label("");
		lblInfo.setForeground(Color.GREEN);
		addToGridBag(lblInfo, 0, 2, 3, 1, 0, 1, 1, 10, new Insets(10, 0, 0, 0));
		InputStream is = getDatabaseStream();
		if (is != null) {
			sdb = getSecurityDatabase(is);
		} else {
			sdb = null;
		}
	}

	public InputStream getDatabaseStream() {
		InputStream is;
		try {
			URL codebase = getCodeBase();
			URL url = new URL(codebase, DB_NAME);
			is = url.openStream();
		} catch (MalformedURLException e) {
			updateInfo(MSG_INVALID_URL);
			is = null;
		} catch (IOException e) {
			System.out.println(e);
			updateInfo(MSG_FILE_NOT_FOUND);
			is = null;
		}
		return is;
	}

	public SecurityDatabase getSecurityDatabase(InputStream is) {
		SecurityDatabase db;
		try {
			ObjectInputStream ois = new ObjectInputStream(is);
			db = (SecurityDatabase) ois.readObject();
		} catch (IOException e) {
			System.out.println(e);
			updateInfo(MSG_STREAM_CORRUPT);
			db = null;
		} catch (ClassNotFoundException e) {
			updateInfo(MSG_CLASS_NOT_FOUND);
			db = null;
		}
		return db;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSubmit) {
			String username = txtUsername.getText();
			String password = txtPassword.getText();
			if (sdb == null) {
				return;
			}
			User user = sdb.getUserByName(username);
			if (user == null) {
				updateInfo(MSG_INVALID_USER);
				return;
			}
			byte[] hashBytes = getHash(password);
			if (new String(hashBytes).equals(new String(user.getPwdBytes()))) {
				Permissions permissions = user.getPermissions();
				if (permissions.isUser()) {
					updateInfo(MSG_IS_USER_ACCOUNT);
				}
				if (permissions.isSuperUser()) {
					updateInfo(MSG_ACCESS_GRANTED, Color.GREEN);
				}
				if (permissions.isAdmin()) {
					updateInfo(MSG_IS_ADMIN_ACCOUNT);
				}
			} else {
				updateInfo(MSG_INVALID_PASSWORD);
			}
		}
	}

	public byte[] getHash(String text) {
		byte[] hash = null;
		try {
			byte[] bytes = text.getBytes();
			MessageDigest msgDigest = MessageDigest.getInstance(TXT_MD5);
			hash = msgDigest.digest(bytes);
		} catch (Exception e) {
			hash = null;
		}
		return hash;

	}

	public void updateInfo(String text) {
		updateInfo(text, Color.RED);
	}

	public void updateInfo(String text, Color color) {
		lblInfo.setText(text);
		lblInfo.setForeground(color);
		lblInfo.invalidate();
		validate();
		repaint();
	}

	protected void addToGridBag(Component component, int x, int y, int width, int height, int fill, int weightx,
			int weighty, int anchor, Insets insets) {
		addToGridBag(this, component, x, y, width, height, fill, weightx, weighty, anchor, insets);
	}

	protected void addToGridBag(Container container, Component component, int x, int y, int width, int height,
			int fill, int weightx, int weighty, int anchor, Insets insets) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.fill = fill;
		gbc.weightx = 0;
		if (fill == GridBagConstraints.HORIZONTAL || fill == GridBagConstraints.BOTH) {
			gbc.weightx = weightx;
		}
		gbc.weighty = 0;
		if (fill == GridBagConstraints.VERTICAL || fill == GridBagConstraints.BOTH) {
			gbc.weighty = weighty;
		}
		gbc.anchor = anchor;
		((GridBagLayout) container.getLayout()).setConstraints(component, gbc);
		container.add(component);
	}

	private static String decrypt(String text) {
		char[] chars = text.toCharArray();
		int[] hashes = new int[] { 63, 101, 10, 105, 95 };
		for (int i = 0; i < chars.length; i++) {
			chars[i] = (char) (chars[i] ^ hashes[i % 5]);
		}
		return new String(chars);
	}
}
