package org.jivesoftware;

import javax.swing.JFrame;

public interface IAccountCreationWizard {

	/**
	 * Returns the username to use for the new account.
	 *
	 * @return the username.
	 */
	public abstract String getUsername();

	/**
	 * Returns the password to use for the new account.
	 *
	 * @return the password to use for the new account.
	 */
	public abstract String getPassword();

	/**
	 * Returns the server to use with the new account.
	 *
	 * @return the server to use.
	 */
	public abstract String getServer();

	/**
	 * Invokes the AccountCreationWizard.
	 *
	 * @param parent the parent frame to use.
	 */
	public abstract void invoke(JFrame parent);

	/**
	 * Returns true if the user is registered.
	 *
	 * @return true if the user is registered.
	 */
	public abstract boolean isRegistered();
	
	public abstract void cleanFields();

}