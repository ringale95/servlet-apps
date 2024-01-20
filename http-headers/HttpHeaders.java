// Write servlet code to print all HTTP request headers and display them in HTML table
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpHeaders extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType ("text/html");
        response.getWriter().write("<html>");
        response.getWriter().write("<head><title>HTTP Request Headers</title></head>");
        response.getWriter().write("<body>");
        response.getWriter().write("<h1>HTTP Request Headers</h1>");
        response.getWriter().write("<table border='1'>");
        response.getWriter().write("<tr><th>Header Name</th><th>Header Value</th></tr>");

        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);

            response.getWriter().write("<tr><td>" +headerName+ "</td><td>" +headerValue+ "</td></tr>");
        }
        response.getWriter().write("</table>");
        response.getWriter().write("</body>");
        response.getWriter().write("</html>");
    }
}