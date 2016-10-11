import javax.swing.*;  

import java.awt.*;  
import java.awt.event.*;  
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
 /**
  * Class that creates a GUI login window. 
  * @author McKayla Reilly
  *
  */
public class LoginWindow {  
  
    JTextField usernameTxt;  
    JPasswordField passwordTxt;  
    JInternalFrame loginFrame;
    private ArrayList<String> userpass = new ArrayList<String>();
    private ArrayList<String> user = new ArrayList<String>();
    private ArrayList<String> pass = new ArrayList<String>();
    private JFrame mainFrame = new JFrame("Main");
    
      
    public static void main(String[] args) throws FileNotFoundException {  
        LoginWindow login = new LoginWindow();  
        login.loginFrame();  
    }  
    
    public void loginFrame() throws FileNotFoundException{  
          
        mainFrame.setSize(1000,600);  
        loginFrame = new JInternalFrame("Login");  
        loginFrame.setSize(400,200);  
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);  
        JDesktopPane mainPanel = new JDesktopPane();  
        JPanel loginPanel = new JPanel();  
        
        File usernamePasswordList = new File("//home//softskeleton//workspace//LoginWindow//src//usernamePasswordList");
        Scanner in = new Scanner(usernamePasswordList);
        	while (in.hasNext()) {
        		userpass.add(in.next());
        	}
        in.close();
        
        for (int i = 0; i < userpass.size(); i++) {
        	user.add(userpass.get(i));
        	i++;
        }
        
        for(int i = 1; i < userpass.size(); i++) {
        	pass.add(userpass.get(i));
        	i++;
        }
         
        JTextArea textArea = new JTextArea(25,25);  
        usernameTxt = new JTextField(25);     
        passwordTxt = new JPasswordField(25);  
        JLabel usernameLbl = new JLabel("Username: ");  
        JLabel passwordLbl = new JLabel("Password: ");    
  
        mainPanel.add(textArea);  
        mainPanel.add(loginFrame);  
        loginPanel.add(usernameLbl);  
        loginPanel.add(usernameTxt);  
        loginPanel.add(passwordLbl);  
        loginPanel.add(passwordTxt);  
        loginPanel.add(createLoginButton());  
          
        loginFrame.getContentPane().add(BorderLayout.CENTER,loginPanel);  
        mainFrame.getContentPane().add(BorderLayout.CENTER,mainPanel);  
  
        loginFrame.setVisible(true);                      
        mainFrame.setVisible(true);       
                          
  
    }  
  
  
    /**
     * Creates a login button that checks the username and password. If correct,
     * login window exits. If incorrect, after 3 attempts the program terminates.
     * @return loginButton
     * @throws FileNotFoundException
     */
    private JButton createLoginButton() throws FileNotFoundException
	{
		JButton loginButton = new JButton("Login");
		
		class LoginButtonListener implements ActionListener
	    {
			public void actionPerformed(ActionEvent event)
			{
				String username = usernameTxt.getText();
				String password = passwordTxt.getText();
				int attempts = 3;
						if (attempts > 0 ) {
							for (int k = 0; k < user.size(); k++) {
								if (username.equals(user.get(k))) {
									for (int j = 0; j < pass.size(); j++) {
										if (password.equals(pass.get(j))) {
											loginFrame.setVisible(false);
										}
										else if (!password.equals(pass.get(j))) { 
											attempts = attempts - 1;
										}
									}
								}
							}
						}
						else if (attempts <= 0) {
							System.out.println("Attempts exhausted. Program will exit.");
							mainFrame.setVisible(false); 
							mainFrame.dispose();
						}
				}
			}
	            
		  ActionListener listener = new LoginButtonListener();
	      loginButton.addActionListener(listener);      

	      return loginButton;
	}
}  