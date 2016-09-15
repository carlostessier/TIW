
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */

public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	private ServletContext context;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
		context = config.getServletContext();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		hitCounter(response);
	}

	private void hitCounter(HttpServletResponse response) throws IOException {
		int counter = 1;
		Object objectCounter = context.getAttribute("hitCounter");
		if (objectCounter != null) {
			counter = Integer.parseInt((String) objectCounter);
			counter++;
		}
		context.setAttribute("hitCounter", String.valueOf(counter));
		response.setContentType("text/html");
		// we get the writer
		PrintWriter out = response.getWriter();
		out.println("Number of times the get method has been called:" + counter);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sRedirect = (String) request.getParameter("Forward");

		if (sRedirect != null && sRedirect.compareTo("y") == 0) {
			RequestDispatcher reqDis = context.getRequestDispatcher("/otherPage.html");
			reqDis.forward(request, response);
		} else {
			readParameters(request, response);
		}

	}

	private void readParameters(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Read the parameters from the request
		String sName = request.getParameter("Name");
		String sEmail = request.getParameter("Email");

		// Set the content type
		response.setContentType("text/html");
		// Get the writer
		PrintWriter out = response.getWriter();
		// Write the output page
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Form Fields</title>");
		out.println("</head>");
		out.println("<body>");

		out.println("Client information ");
		out.println("<h2>Name: " + sName + "</h2>");
		out.println("<h2>Email: " + sEmail + "</h2>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close(); // Inform the server that we finished
		// sending the information
	}

}
