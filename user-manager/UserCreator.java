import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreator extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String name = request.getParameter("firstName");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        String country = request.getParameter("country");

        String message = "User Information Received :\n"+ 
                        "Name :" +name+ "\n" +
                        "Email :" +email+ "\n" +
                        "Age :" +age+ "\n" +
                        "Country :" +country;

        response.setContentType("text/html");
        response.getWriter().write("<html>");
        response.getWriter().write("<body>");
        response.getWriter().write("<p>"+message+"</p>");
        response.getWriter().write("</body>");
        response.getWriter().write("</html>");

    }
}