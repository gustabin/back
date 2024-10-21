package ar.com.santanderrio.obp.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pablo.martin.gore on 7/15/2016.
 */
@WebServlet(value = "/home", loadOnStartup = 1)
public class IndexServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5014801045205844892L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		if (!response.isCommitted()) {
			RequestDispatcher view = req.getRequestDispatcher("/angular/index.html");
			view.forward(req, response);
			return;
		}
	}
}
