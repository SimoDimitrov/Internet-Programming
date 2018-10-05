import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> hmp = new HashMap<String, String>();
    
    public Servlet() 
    {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
	    String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	         
	    response.getWriter().println(docType +
	         "<html>\n" +
	            "<head></head>\n" +
	            "<body background = \"https://i.imgur.com/1stl5oq.jpg\">" +
	               "<form action = \"Servlet\" method = \"POST\">" +
	               "<div style=\"color:blue; font-weight: bold;\"><font size=\"6\"> Key: <input type = \"text\" name = \"key\"> </div>" +
	               "<br/>" +
	               "<div style=\"color:blue; font-weight: bold;\"><font size=\"6\"> Value: <input type = \"text\" name = \"value\"> </div>" +
	               "<input type = \"submit\" value = \"Submit\">" +
	               "</form>"
	      );
	      
	    response.getWriter().println(
	    		        "<table>" + 
	    		  		"<tr>" +
	    		  			"<th><font size=\"6\"> Key </th>" + 
	    		  			"<th><font size=\"6\"> Value </th>" +
	    		  		"</tr>"
	      );
	    
	      for(Map.Entry<String, String> entry : hmp.entrySet()) 
	      {
	    	  String key = entry.getKey();
	    	  String value = entry.getValue();
	    	  response.getWriter().println(
	    			      "<tr>" + 
	    			  			"<td> <font size=\"6\">" + key + "</td>" +
	    			  			"<td> <font size=\"6\">" + value + "</td>" +
	    			  	  "</tr>"
	    	  );
	      }
	      
	      response.getWriter().println(
	    		  "</table>" +
	      		  "</body>" +
	    		  "</html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		hmp.put(key, value);
		doGet(request, response);
	}
}