package employeeFormbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ResetCusPwdForm  extends FormBean  {
	private String username;
	private String newPassword;
	private String confirm;
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (username == null || username.length() == 0) {
			errors.add("Customer Username is required");
		}

		if (newPassword == null || newPassword.length() == 0) {
			errors.add("New Password is required");
		}
		if (confirm == null || confirm.length() == 0) {
			errors.add("The confirm password is required");
		}

		if (errors.size() > 0) {
			return errors;
		}


		return errors;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
}