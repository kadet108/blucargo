package org.jivesoftware.sparkimpl.plugin.blucargo.registration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import org.jivesoftware.IAccountCreationWizard;
import org.jivesoftware.resource.Res;
import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.packet.VCard;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.component.TitlePanel;
import org.jivesoftware.spark.util.DummySSLSocketFactory;
import org.jivesoftware.spark.util.ModelUtil;
import org.jivesoftware.spark.util.ResourceUtils;
import org.jivesoftware.spark.util.SwingWorker;
import org.jivesoftware.spark.util.log.Log;
import org.jivesoftware.sparkimpl.plugin.blucargo.registration.enums.RegistrationFormEnum;
import org.jivesoftware.sparkimpl.plugin.blucargo.registration.enums.RegistrationFormEnum.DoubleString;
import org.jivesoftware.sparkimpl.profile.VCardManager;
import org.jivesoftware.sparkimpl.settings.local.LocalPreferences;
import org.jivesoftware.sparkimpl.settings.local.SettingsManager;

/**
 * Allows the creation of accounts on an XMPP server.
 */
public class CargoCreationAccountWizard extends JPanel implements IAccountCreationWizard {

    private static final long serialVersionUID = 2953713328689927200L;
    
	private JLabel usernameLabel = new JLabel();
    private static JTextField usernameField = new JTextField();

    private JLabel passwordLabel = new JLabel();
    private static JPasswordField passwordField = new JPasswordField();

    private JLabel confirmPasswordLabel = new JLabel();
    private static JPasswordField confirmPasswordField = new JPasswordField();

    private static JButton createAccountButton = new JButton();
    private static JButton closeButton = new JButton();
    
    @SuppressWarnings("unused")
	private JLabel serverLabel = new JLabel();
    private static JTextField serverField = new JTextField();
    
    private JLabel nameLabel = new JLabel();
    private static JTextField nameField = new JTextField();
    
    private JLabel surnameLabel = new JLabel();
    private static JTextField surnameField = new JTextField();
    
    private JLabel companyNameLabel = new JLabel();
    private static JTextField companyNameField = new JTextField();
    
    private JLabel nipLabel = new JLabel();
    private static JTextField nipField = new JTextField();
    
    private JLabel addressLabel = new JLabel();
    private static JTextField addressField = new JTextField();
    
    private JLabel postCodeLabel = new JLabel();
    private static JTextField postCodeField = new JTextField();
    
    private JLabel cityLabel = new JLabel();
    private static JTextField cityField = new JTextField();
    
    private JLabel countryLabel = new JLabel();
    private static JComboBox countryComboBox = new JComboBox(StringConstans.countries());
    
    private JLabel cellLabel = new JLabel();
    private static JTextField cellField = new JTextField();
    
    private JLabel emailLabel = new JLabel();
    private static JTextField emailField = new JTextField();
    
    private static JCheckBox acceptanceStatuteCheckBox = new JCheckBox();
    private JLabel acceptanceStatuteLabel = new JLabel("Akceptuj\u0119 regulamin.");
    private static JLabel wrongAreaLabel = new JLabel();

    private JDialog dialog;

    private boolean registered;
    private XMPPConnection connection = null;
    private JProgressBar progressBar;

    public SwingWorker worker;
    
    public boolean errors;
    
    static Color goodColor = Color.black;
    static Color wrongColor = Color.red;
    
    RegistrationFormEnum registrationFormEnum;
    
    RegistrationFormEnum.DoubleString doublePasswordString = new RegistrationFormEnum.DoubleString(new String(passwordField.getPassword()), new String(confirmPasswordField.getPassword()));
    RegistrationFormEnum.DoubleString doublePostCodeString;
    

    final RegisterFormCorrectness regFormCorrect = new RegisterFormCorrectness();
    
    List<JTextField> textFieldList = new ArrayList<JTextField>();
    
    String text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n " +
	"Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n " +
	"Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\n " +
	"Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    
   
    /**
     * Construct the AccountCreationWizard UI.
     */
    public CargoCreationAccountWizard() {
    	
        // Associate Mnemonics
        ResourceUtils.resLabel(usernameLabel, usernameField, Res.getString("label.username") + ":");
        ResourceUtils.resLabel(passwordLabel, passwordField, Res.getString("label.password") + ":");
        ResourceUtils.resLabel(confirmPasswordLabel, confirmPasswordField, Res.getString("label.confirm.password") + ":");
        ResourceUtils.resButton(createAccountButton, Res.getString("button.create.account"));

        setLayout(new GridBagLayout());
        
        final Color colorOfLabel = Color.blue;
        final Color colorOfMarkedLabel = Color.RED;
        Color kolorTla = new Color(232,227,208);
        this.setBackground(kolorTla);
        
        // Add component to UI
        
        add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        add(usernameField, new GridBagConstraints(1, 0, 3, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 150, 0));

        add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        add(passwordField, new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        add(confirmPasswordLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        add(confirmPasswordField, new GridBagConstraints(1, 2, 3, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

       

        addComponentToLayout(nameLabel, nameField,0, 3, 1, 1);
        addComponentToLayout(surnameLabel, surnameField,0, 4, 1, 1);
        addComponentToLayout(companyNameLabel, companyNameField,0, 5, 1, 1);
        addComponentToLayout(nipLabel, nipField,0, 6, 1, 1);
        addComponentToLayout(addressLabel, addressField, 0, 7, 1, 1);
        addComponentToLayout(postCodeLabel, postCodeField,0, 8, 1, 1);
        addComponentToLayout(cityLabel, cityField, 0, 9, 1, 1);
        addComponentToLayout(cellLabel, cellField, 0, 12, 1, 1);
        add(countryLabel, new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        add(countryComboBox, new GridBagConstraints(1, 11, 3, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        
        createAccountButton.setEnabled(false);
        
        addComponentToLayout(emailLabel, emailField, 0, 13, 1, 1);

        textFieldList.add(nameField);
        textFieldList.add(addressField);
        textFieldList.add(cellField);
        textFieldList.add(cityField);
        textFieldList.add(companyNameField);
        textFieldList.add(emailField);
        textFieldList.add(nipField);
        textFieldList.add(surnameField);
        textFieldList.add(postCodeField);
      
        nameLabel.setText("imi\u0119: ");
        surnameLabel.setText("nazwisko: ");
        companyNameLabel.setText("nazwa firmy: ");
        nipLabel.setText("NIP: ");
        addressLabel.setText("adres: ");
        postCodeLabel.setText("kod pocztowy: ");
        cityLabel.setText("miasto");
        countryLabel.setText("kraj: ");
        cellLabel.setText("tel. kom.: ");
        emailLabel.setText("e-mail: ");
 
	        wrongAreaLabel.setText("... zsota\u0142o \u017ale wpisane.");
        wrongAreaLabel.setVisible(false);
        
        countryComboBox.setSelectedIndex(171);
        
        regFormCorrect.setData(RegistrationFormEnum.SERVER, 0);
                
        progressBar = new JProgressBar();


        add(progressBar, new GridBagConstraints(1, 14, 3, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        progressBar.setVisible(false);
        add(createAccountButton, new GridBagConstraints(2, 15, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));


        ResourceUtils.resButton(closeButton, Res.getString("button.close"));
        add(closeButton, new GridBagConstraints(3, 15, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        add(acceptanceStatuteCheckBox, new GridBagConstraints(0, 15, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        add(acceptanceStatuteLabel, new GridBagConstraints(1, 15, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        acceptanceStatuteLabel.setForeground(colorOfLabel);
        add(wrongAreaLabel, new GridBagConstraints(1, 16, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

        acceptanceStatuteCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				InputFieldVerifier.get().isStatuteAccepted(
						acceptanceStatuteCheckBox.isSelected());
				regFormCorrect.isDataOk(RegistrationFormEnum.ACCEPTANCE);
			}
		});
        
        acceptanceStatuteLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				acceptanceStatuteLabel.setForeground(colorOfLabel);	
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				acceptanceStatuteLabel.setForeground(colorOfMarkedLabel);
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				JDialog fr = new JDialog(dialog, "Blucargo - regulamin.", true);
				fr.setSize(400, 300);
				JEditorPane editor = new JEditorPane();
				editor.setSize(30, 10);	
				fr.add(editor);
				editor.setText(text);
				fr.setVisible(true);
			}
		});
        
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            	createAccountButton.setEnabled(false);
            	String endpoint = "http://localhost:9090/cargo/registration.do";
            	String res = HttpRequestUtils.sendPostRequest(endpoint, createUrlPostParamMap());
            	if( res.equals("SUCH_USER_EXIST") )
            	{
            		userExists();
            	}
            	else
            	{
            	JOptionPane.showMessageDialog(null, res != null && res.equals("REG_OK") ?
            	 registrationSuccesfull() : "Rejestracja nie powiod\u0142a si\u0119");
            	}
            }

			private String registrationSuccesfull() {
				dialog.dispose();
				return "Rejestracja powiod\u0142a si\u0119!";
			}

			private void userExists() {
				usernameField.requestFocus();
				usernameField.setSelectionEnd(usernameField.getText().length());
				usernameField.setSelectionStart(0);
				updateInputBoxGui(usernameField, usernameLabel, 1);
				wrongAreaLabel.setText("Taki u\u017cytkownik jest ju\u017c w systemie!");
				wrongAreaLabel.setVisible(true);
			}
        });

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                dialog.dispose();
            }
        });
        
        usernameField.addKeyListener(new InputFieldKeyHandler(RegistrationFormEnum.USERNAME, usernameField, usernameLabel));
        
        PasswordFieldsKeyHandler passwordHandler = new PasswordFieldsKeyHandler(RegistrationFormEnum.PASSWORD, passwordField, confirmPasswordField, passwordLabel, confirmPasswordLabel );
        passwordField.addKeyListener(passwordHandler);
        confirmPasswordField.addKeyListener(passwordHandler);
        
        nameField.addKeyListener(new InputFieldKeyHandler(RegistrationFormEnum.NAME, nameField, nameLabel));
        surnameField.addKeyListener(new InputFieldKeyHandler(RegistrationFormEnum.SURNAME, surnameField, surnameLabel));
        emailField.addKeyListener(new InputFieldKeyHandler(RegistrationFormEnum.EMAIL, emailField, emailLabel));
        cellField.addKeyListener(new InputFieldKeyHandler(RegistrationFormEnum.CELL, cellField, cellLabel));
        nipField.addKeyListener(new InputFieldKeyHandler(RegistrationFormEnum.NIP, nipField, nipLabel));
        
        CountryPostCodeFieldsHandler postCodeHandler = new CountryPostCodeFieldsHandler(RegistrationFormEnum.POST_CODE, postCodeField, countryComboBox);
        postCodeField.addKeyListener(postCodeHandler);
        countryComboBox.addItemListener(postCodeHandler);
        
        companyNameField.addKeyListener(new InputFieldKeyHandler(RegistrationFormEnum.COMPANY_NAME, companyNameField, companyNameLabel));
        cityField.addKeyListener(new InputFieldKeyHandler(RegistrationFormEnum.CITY, cityField, cityLabel));
        addressField.addKeyListener(new InputFieldKeyHandler(RegistrationFormEnum.ADRESS, addressField, addressLabel));
        acceptanceStatuteCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int errCode = RegistrationFormEnum.ACCEPTANCE.getVerifier().verifyData( acceptanceStatuteCheckBox.isSelected() );
				regFormCorrect.setData(RegistrationFormEnum.ACCEPTANCE, errCode);;
				
				checkForm();
			}
		});
        
        final Component ui = this;

        
        //Create worker
        worker = new SwingWorker() {
            int errorCode;


            public Object construct() {
                try {
                    createAccountButton.setEnabled(false);
                    connection = getConnection(); 

                    }
                catch (XMPPException e) {
                    return e;
                }
                try {
                    final AccountManager accountManager = new AccountManager(connection);
                    accountManager.createAccount(getUsername(), getPassword());
                }
                catch (XMPPException e) {
                    XMPPError error = e.getXMPPError();
                    if (error != null) {
                        errorCode = error.getCode();
                    }
                    else {
                        errorCode = 500;
                    }

                }    
                
                return "ok";
            }

            public void finished() {
                progressBar.setVisible(false);
                if (connection == null) {
                    if (ui.isShowing()) {
                        createAccountButton.setEnabled(true);
                        JOptionPane.showMessageDialog(ui, Res.getString("message.connection.failed", getServer()), Res.getString("title.create.problem"), JOptionPane.ERROR_MESSAGE);
                        createAccountButton.setEnabled(true);
                    }
                    return;
                }

                if (errorCode == 0) {
                    accountCreationSuccessful();
                }
                else {
                    accountCreationFailed(errorCode);
                }
                
                
            }
        };
    }
    
    
    public class InputFieldKeyHandler extends KeyAdapter
    {
    	RegistrationFormEnum type;
    	JTextField field;
    	JLabel label;
    	
    	public InputFieldKeyHandler(RegistrationFormEnum type, JTextField field, JLabel label)
    	{
    		this.type = type;
    		this.field = field;
    		this.label = label;
    	}

    	@Override
    	public void keyReleased(KeyEvent evt)
    	{
	    	int errCode = type.getVerifier().verifyData(field.getText());
			regFormCorrect.setData(type, errCode);
			updateInputBoxGui(field, label, errCode);
			
			checkForm();
    	}
    	
    }

    public class PasswordFieldsKeyHandler extends KeyAdapter
    {
    	RegistrationFormEnum type;
    	JTextField field1;
    	JTextField field2;
    	JLabel label1;
    	JLabel label2;

    	public PasswordFieldsKeyHandler(RegistrationFormEnum type, JComponent field1, JComponent field2, JLabel label1, JLabel label2)
    	{
    		this.type = type;
    		this.field1 = (JTextField)field1;
    		this.field2 = (JTextField)field2;
    		this.label1 = (JLabel)label1;
    		this.label2 = (JLabel)label2;
    	}

    	@Override
    	public void keyReleased(KeyEvent evt)
    	{
	    	int errCode = type.getVerifier().verifyData(new RegistrationFormEnum.DoubleString(this.field1.getText(), this.field2.getText()) );
			regFormCorrect.setData(type, errCode);
			updateInputBoxGui(field1, label1, errCode);
			updateInputBoxGui(field2, label2, errCode);
			
			checkForm();
    	}
    	
    }
    
    public class CountryPostCodeFieldsHandler extends KeyAdapter implements ItemListener
    {
    	RegistrationFormEnum type;
    	JTextField postCodeField;
    	JComboBox countrySelector;

    	public CountryPostCodeFieldsHandler(RegistrationFormEnum type, JTextField field1, JComboBox field2)
    	{
    		this.type = type;
    		this.postCodeField = field1;
    		this.countrySelector = field2;
    	}
    	
    	public void handleDataChange()
    	{
    		DoubleString doubleString = new RegistrationFormEnum.DoubleString(postCodeField.getText(), countryComboBox.getSelectedItem().toString() );
    		int errCode = type.getVerifier().verifyData( doubleString );
			regFormCorrect.setData(type, errCode);
			updateInputBoxGui(postCodeField, postCodeLabel, errCode);
			errCode = RegistrationFormEnum.COUNTRY.getVerifier().verifyData( doubleString );
			regFormCorrect.setData(RegistrationFormEnum.COUNTRY, errCode);
					
			checkForm();
    	}

    	@Override
    	public void keyReleased(KeyEvent evt)
    	{
	    	handleDataChange();
    	}
    	
    	@Override
		public void itemStateChanged(ItemEvent e) {
			
			handleDataChange();
		}
    }

    
    public void addComponentToLayout(JLabel label, JTextField textField,int x, int y, int w, int h){
    	add(label, 	   new GridBagConstraints(x,   y, w,   h, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        add(textField, new GridBagConstraints(x+1, y, w+3, h, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

    }

    /* (non-Javadoc)
	 * @see org.jivesoftware.IAccountCreationWizard#getUsername()
	 */
    @Override
	public String getUsername() {
        return StringUtils.escapeNode(usernameField.getText().toLowerCase());
    }

    public void setUsername(String userName) {
    	usernameField.setText(userName);
    }
    
    /* (non-Javadoc)
	 * @see org.jivesoftware.IAccountCreationWizard#getPassword()
	 */
    @Override
	public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void setPassword(String password) {
    	passwordField.setText(password);
    }
    
    /**
     * Returns the confirmation password to use for the new account.
     *
     * @return the password to use for the new account.
     */
    public String getConfirmPassword() {
        return new String(confirmPasswordField.getPassword());
    }

    public void setConfirmPassword(String password) {
    	confirmPasswordField.setText(password);
    }
    
    /* (non-Javadoc)
	 * @see org.jivesoftware.IAccountCreationWizard#getServer()
	 */
    @Override
	public String getServer() {
        return serverField.getText();
    }
    
    public void setServer (String server){
    	serverField.setText(server);
    }

    /**
     * Returns true if the passwords match.
     *
     * @return true if the passwords match.
     */
    public boolean isPasswordValid() {
        return getPassword().equals(getConfirmPassword());
    }

    
    /**
     * Creates the new account using the supplied information.
     */
    public void createAccount() {
        errors = false;
        String errorMessage = "";

        if (!ModelUtil.hasLength(getUsername())) {
            errors = true;
            usernameField.requestFocus();
            errorMessage = Res.getString("message.username.error");
        }
        else if (!ModelUtil.hasLength(getPassword())) {
            errors = true;
            errorMessage = Res.getString("message.password.error");
        }
        else if (!ModelUtil.hasLength(getConfirmPassword())) {
            errors = true;
            errorMessage = Res.getString("message.confirmation.password.error");
        }
        else if (!ModelUtil.hasLength(getServer())) {
            errors = true;
            errorMessage = Res.getString("message.account.error");
        }
        else if (!isPasswordValid()) {
            errors = true;
            errorMessage = Res.getString("message.confirmation.password.error");
        }

        if (errors) {
            JOptionPane.showMessageDialog(this, errorMessage, Res.getString("title.create.problem"), JOptionPane.ERROR_MESSAGE);
            return;
        }

//        final Component ui = this;
        progressBar.setIndeterminate(true);
        progressBar.setStringPainted(true);
        progressBar.setString(Res.getString("message.registering", getServer()));
        progressBar.setVisible(true);

        worker.start();
    }

    /**
     * Called if the account creation failed.
     *
     * @param errorCode the error code.
     */
    private void accountCreationFailed(int errorCode) {
        String message = Res.getString("message.create.account");
        if (errorCode == 409) {
            message = Res.getString("message.already.exists");
            usernameField.setText("");
            usernameField.requestFocus();
        }
        JOptionPane.showMessageDialog(this, message, Res.getString("title.create.problem"), JOptionPane.ERROR_MESSAGE);
        createAccountButton.setEnabled(true);
    }

    /**
     * Called if the account was created succesfully.
     */
    private void accountCreationSuccessful() {
        registered = true;
        JOptionPane.showMessageDialog(this, Res.getString("message.account.created"), Res.getString("title.account.created"), JOptionPane.INFORMATION_MESSAGE);
        
        if (dialog != null) {
        	dialog.dispose();
        }
        
    }

    /* (non-Javadoc)
	 * @see org.jivesoftware.IAccountCreationWizard#invoke(javax.swing.JFrame)
	 */
    @Override
	public void invoke(JFrame parent) {
        dialog = new JDialog(parent, Res.getString("title.create.new.account"), true);

        TitlePanel titlePanel = new TitlePanel(Res.getString("title.account.create.registration"), Res.getString("message.account.create"), null, true);
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(titlePanel, BorderLayout.NORTH);
        dialog.getContentPane().add(this, BorderLayout.CENTER);
        
        dialog.getContentPane().setBackground(Color.red);
        
        dialog.pack();
        dialog.setSize(700, 600);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
        

        //dialog.setContentPane.getColorModel(Color.red);
        //ustawianie koloru
        //dialog.setBackground(Color.red);
    }

    /**
     * Creates an XMPPConnection based on the users settings.
     *
     * @return the XMPPConnection created.
     * @throws XMPPException thrown if an exception occured creating the connection.
     */
    private XMPPConnection getConnection() throws XMPPException {
        final LocalPreferences localPreferences = SettingsManager.getLocalPreferences();
        XMPPConnection connection = null;

        // Get connection

        int port = localPreferences.getXmppPort();

        String serverName = getServer();

        int checkForPort = serverName.indexOf(":");
        if (checkForPort != -1) {
            String portString = serverName.substring(checkForPort + 1);
            if (ModelUtil.hasLength(portString)) {
                // Set new port.
                port = Integer.valueOf(portString);
            }
        }

        boolean useSSL = localPreferences.isSSL();
        boolean hostPortConfigured = localPreferences.isHostAndPortConfigured();

        ConnectionConfiguration config;

        if (useSSL) {
            if (!hostPortConfigured) {
                config = new ConnectionConfiguration(serverName, 5223);
                config.setSocketFactory(new DummySSLSocketFactory());
            }
            else {
                config = new ConnectionConfiguration(localPreferences.getXmppHost(), port, serverName);
                config.setSocketFactory(new DummySSLSocketFactory());
            }
        }
        else {
            if (!hostPortConfigured) {
                config = new ConnectionConfiguration(serverName);
            }
            else {
                config = new ConnectionConfiguration(localPreferences.getXmppHost(), port, serverName);
            }


        }

        if (config != null) {
            config.setReconnectionAllowed(true);
            boolean compressionEnabled = localPreferences.isCompressionEnabled();
            config.setCompressionEnabled(compressionEnabled);
            connection = new XMPPConnection(config);
        }

        if (connection != null) {
            connection.connect();
        }
        return connection;

    }

    /* (non-Javadoc)
	 * @see org.jivesoftware.IAccountCreationWizard#isRegistered()
	 */
    @Override
	public boolean isRegistered() {
        return registered;
    }
    
    public static void wrongAreaLabelSetText(String text, boolean bool){
    	wrongAreaLabel.setText(text);
    	wrongAreaLabel.setVisible(bool);
    }
    
    public static void createAccountButtonSetEnableTrueOrFalse(boolean bool){
    	createAccountButton.setEnabled(bool);
    }
    
   
    
    public static void updateInputBoxGui(JTextField box, JLabel label, int code) {
    	box.setForeground((code == 0) ? goodColor : wrongColor);
    	label.setForeground((code == 0) ? goodColor : wrongColor);
    }
    
    /**
     * Saves the VCard.	
     */
    @SuppressWarnings("unused")
	private void saveVCard() {
        final VCard vcard = new VCard();

        vcard.setFirstName(nameField.getText());
        vcard.setLastName(surnameField.getText());
        vcard.setEmailHome(emailField.getText());
        vcard.setNickName(cellField.getText());

        try {
            final VCardManager vcardManager = SparkManager.getVCardManager();
            vcardManager.setPersonalVCard(vcard);

            vcard.save(SparkManager.getConnection());
        }
        catch (XMPPException e) {
            Log.error(e);
            JOptionPane.showMessageDialog(SparkManager.getMainWindow(), Res.getString("message.vcard.not.supported"), Res.getString("title.error"), JOptionPane.ERROR_MESSAGE);
        }
    } 
   
    public String generateVCardXmlString(){
    	VCard vCard = new VCard();
    	
		vCard.setNickName(usernameField.getText());
		vCard.setFirstName(nameField.getText());
		vCard.setLastName(surnameField.getText());
		vCard.setOrganization(companyNameField.getText());
		vCard.setAddressFieldWork("LOCALITY", addressField.getText());
		vCard.setAddressFieldWork("REGION", cityField.getText());
		vCard.setAddressFieldWork("CTRY", (String) countryComboBox.getSelectedItem());
		vCard.setPhoneWork("CELL", cellField.getText());
		vCard.setEmailWork(emailField.getText());
		vCard.setField("NIP", nipField.getText());

		return vCard.toString();
    }
    
    private Map<String, String> createUrlPostParamMap() {
    	
    	Map<String, String> params = new HashMap<String, String>(4);
    	params.put("login", usernameField.getText());
    	params.put("password", new String(passwordField.getPassword()));
    	params.put("email", emailField.getText());
    	params.put("vCard", generateVCardXmlString());
		return params;
	}
    
	private void checkForm() {
		if( regFormCorrect.isFormValid() ){
			wrongAreaLabel.setVisible(false);
			createAccountButton.setEnabled(true);
		}
		else{
			RegistrationFormEnum type = regFormCorrect.getErrorStatus();
			wrongAreaLabel.setText(type.getErrorMessage(regFormCorrect.getData(type)));
			wrongAreaLabel.setVisible(true);
			createAccountButton.setEnabled(false);
		}
	}
	
	public void cleanFields()
	{
	    usernameField.setText("");
	    passwordField.setText("");
	    confirmPasswordField.setText("");
	    serverField.setText("");
	    nameField.setText("");
	    surnameField.setText("");
	    companyNameField.setText("");
	    nipField.setText("");
	    addressField.setText("");
	    postCodeField.setText("");
	    cityField.setText("");
	    cellField.setText("");
	    emailField.setText("");
	}
	
}
