/*
 * 08-600
 * Homework #9
 * Jiali Chen
 * andrewID: jialic
 * Dec 4, 2014
 */
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;







import databeans.User;
import model.Model;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {
	public void init() throws ServletException {
		Model model = new Model(getServletConfig());

		Action.add(new ChangePwdAction(model));
		Action.add(new CreateFundAction(model));
		Action.add(new LoginAction(model));
		Action.add(new LogoutAction(model));
		Action.add(new DepositCheckAction(model));
		Action.add(new RequestCheckAction(model));
		Action.add(new ResearchFundAction(model));
		Action.add(new ViewCusDetailAccAction(model));
		Action.add(new ViewAccAction(model));
		Action.add(new ResetCusPwdAction(model));
		Action.add(new CreateEmpAccAction(model));
		Action.add(new CreateCusAccAction(model));
		//Action.add(new SellFundAction(model));
        Action.add(new TransactionHistoryAction(model));
        Action.add(new ViewCusDetailAccAction(model));
        Action.add(new ViewCusAccAction(model));
        Action.add(new ViewCusTransactionHistoryAction(model));
		Action.add(new BuyFundAction(model));
		Action.add(new ResearchFundAction(model));

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = performTheAction(request);
		sendToNextPage(nextPage, request, response);
	}

	/*
	 * Extracts the requested action and (depending on whether the user is
	 * logged in) perform it (or make the user login).
	 * 
	 * @param request
	 * 
	 * @return the next page (the view)
	 */
	private String performTheAction(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		User user = (User) session.getAttribute("user");
		String action = getActionName(servletPath);

		if (user == null) {
			// If the user hasn't logged in, direct him to the login page
			return Action.perform("login.do", request);
		}


		// Let the logged in user run his chosen action
		return Action.perform(action, request);
	}

	/*
	 * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
	 * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
	 * page (the view) This is the common case
	 */
	private void sendToNextPage(String nextPage, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					request.getServletPath());
			return;
		}

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher("WEB-INF/"
					+ nextPage);
			d.forward(request, response);
			return;
		}

		if (nextPage.startsWith("http://")) {
			response.sendRedirect(nextPage);
			return;
		} else {
			response.sendRedirect("http://" + nextPage);
			return;
		}
		// throw new
		// ServletException(Controller.class.getName()+".sendToNextPage(\"" +
		// nextPage + "\"): invalid extension.");
	}

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
	private String getActionName(String path) {
		// We're guaranteed that the path will start with a slash
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
}