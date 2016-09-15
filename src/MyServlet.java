
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet(name = "MyServlet", urlPatterns = { "/MyServlet" }, initParams = {
		@WebInitParam(name = "author", value = "TIW") })

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
		super.init();
		this.config = config;
		context = config.getServletContext();
		String authorName = config.getInitParameter("author");
		System.out.println("author: " + authorName);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int counter = 1;
		counter = hitCounter(response);
		//counter = sessionHitCounter(request);

		// we get the writer
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("Number of times the get method has been called:" + counter);
		out.flush();
		out.close();

	}

	private int sessionHitCounter(HttpServletRequest request) {
		HttpSession mySession = request.getSession(true);
		if (mySession.getAttribute("counter") == null) {
			System.out.println("Set to 0");
		} else {
			System.out.println((String) mySession.getAttribute("counter"));
		}
		int scount = 0;
		try {
			scount = Integer.parseInt((String) (mySession.getAttribute("counter")));
		} catch (NumberFormatException e) {

		}
		scount++;
		mySession.setAttribute("counter", String.valueOf(scount));
		return scount;
	}

	private int hitCounter(HttpServletResponse response) throws IOException {
		int counter = 0;
		Object objectCounter = context.getAttribute("hitCounter");
		if (objectCounter != null) {
			counter = Integer.parseInt((String) objectCounter);
			counter++;
		}
		context.setAttribute("hitCounter", String.valueOf(counter));
		return counter;

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
