/*
 * 08-600
 * Homework #9
 * Jiali Chen
 * andrewID: jialic
 * Dec 4, 2014
 */
package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import databeans.Employee;


public class Model {
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	private Fund_Price_HistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	// constructor
	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");			
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
		
			
			customerDAO  = new CustomerDAO("Customer", pool);
			employeeDAO = new EmployeeDAO("Employee", pool);
			fundDAO = new FundDAO("Fund", pool);
			positionDAO = new PositionDAO("position", pool);
			fundPriceHistoryDAO = new Fund_Price_HistoryDAO("Fund_Price_History", pool);
			transactionDAO = new TransactionDAO("transaction", pool);
              initialization();
		} catch (DAOException e) {
			throw new ServletException(e);
		} catch (RollbackException e) {
			e.printStackTrace();
		}

	}
	private void initialization() throws RollbackException {
		   if(employeeDAO.getCount()==0)
		   {
			   //String employee[]=new String[]{"adminj","jiali","chen","jiali"};
			   Employee e=new Employee();
			   e.setUsername("adminj");
			   e.setFirstname("jiali");
			   e.setLastname("Chen");
			   e.setPassword("adminj");
		       employeeDAO.create(e);
		   }
		}
	
	// get 
	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}
	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}
	public FundDAO getFundDAO(){
		return fundDAO;
	}
	public Fund_Price_HistoryDAO getFund_Price_HistoryDAO() {
		return fundPriceHistoryDAO;
	}
	public PositionDAO getPositionDAO() {
		return positionDAO;
	}
	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}


}