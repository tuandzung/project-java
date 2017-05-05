package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Controller.EmployeeBusiness;
import Model.Employee;

public class FrameLogIn extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLogIn frame = new FrameLogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrameLogIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 727, 262);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome to Fashion Shop Management System!");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(0, 5, 733, 54);
		panel.add(lblWelcome);
		
		JLabel lblLogIn = new JLabel("Log in");
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblLogIn.setBounds(0, 90, 733, 44);
		panel.add(lblLogIn);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Century", Font.PLAIN, 15));
		lblUsername.setBounds(183, 146, 80, 14);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Century", Font.PLAIN, 15));
		lblPassword.setBounds(183, 185, 80, 14);
		panel.add(lblPassword);

		JTextField txtUsername = new JTextField();
		txtUsername.setBounds(285, 143, 200, 25);
		panel.add(txtUsername);
		
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setBounds(285, 182, 200, 25);
		panel.add(txtPassword);
		
		JButton btnLogIn = new JButton("Log in");
		getRootPane().setDefaultButton(btnLogIn);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String username = txtUsername.getText();
				char[] password = txtPassword.getPassword();
				
				boolean isDone = false;
				
				for (Employee e: EmployeeBusiness.list) {
					if (e.getUsername().equals(username)) {
						if (Arrays.equals(e.getPassword(), password)) {
							JOptionPane.showMessageDialog(contentPane, "Hello " + e.getName());
							isDone = true;
							MainFrame frame = new MainFrame();
							frame.setCurrentUser(e);
							frame.displayInformation();
							frame.setVisible(true);
							getFrame().dispose();
							break;
						}
						else {
							JOptionPane.showMessageDialog(contentPane, "Wrong password!");
							txtPassword.setText(null);
							isDone = true;
						}
					}
				}
				if (!isDone) {
					JOptionPane.showMessageDialog(contentPane, "Username not found.");
					txtUsername.setText(null);
					txtPassword.setText(null);
				}
				
			}
		});
		btnLogIn.setBounds(295, 225, 70, 23);
		btnLogIn.setEnabled(false);
		panel.add(btnLogIn);
		
		JButton btnNewAccount = new JButton("New Account");
		btnNewAccount.setBounds(373, 225, 112, 23);
		panel.add(btnNewAccount);
		
		DocumentListener documentListener = new DocumentListener() {
			public void changedUpdate(DocumentEvent event) {
				change();
			}
			
			public void insertUpdate(DocumentEvent event) {
				change();
			}
			
			public void removeUpdate(DocumentEvent event) {
				change();
			}
			
			public void change() {
				if (!txtUsername.getText().equals("") && (txtPassword.getPassword().length != 0))
					btnLogIn.setEnabled(true);
				else btnLogIn.setEnabled(false);
			}
		};
		txtUsername.getDocument().addDocumentListener(documentListener);
		txtPassword.getDocument().addDocumentListener(documentListener);
	}

	public FrameLogIn getFrame() {
		return this;
	}
}
