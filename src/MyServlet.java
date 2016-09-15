
import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MyServlet
 */

public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
