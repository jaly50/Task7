package employeeFormbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateFundForm  extends FormBean  {
	private String name;
	private String symbol;
	static String pattern= "^[a-zA-Z0-9._-]*$";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.trim();
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol.trim();
	}
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (name == null || name.length() == 0) {
			errors.add("Fund Name is required");
		}
		if (!name.matches(pattern)) {
			errors.add("Only character, number, and \"-\", \"_\", \".\" are allowed for fund name.");
		}

		if (symbol == null || symbol.length() == 0) {
			errors.add("Fund symbol is required");
		}
		if (!symbol.matches(pattern)) {
			errors.add("Only character, number, and \"-\", \"_\", \".\" are allowed for fund symbol.");
		}

		if (errors.size() > 0) {
			return errors;
		}


		return errors;
	}
	
}
