import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
public class Application extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        writer.write("<html>");
        writer.write("<body>");
        writer.write("<h1>Login</h1>");
        writer.write("<form action = \"login.htm\" method=\"post\">");
        writer.write("<label for=\"username\">Username : </label>");
        writer.write("<input name=\"username\" required> <br> <br>");
        writer.write("<label for=\"password\">Password : </label>");
        writer.write("<input type=\"password\" name=\"password\" required> <br> <br>");
        writer.write("<input type=\"submit\" value=\"Submit\">");
        writer.write("</form>");
        writer.write("</body>");
        writer.write("</html>");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        writer.write("<html>");
        writer.write("<body>");
        
        if(validateLogin(username, password))
            // Homepage
            writer.write("<h2>Valid Login!</h2>");
        else {
            // Invalid Login Page
            writer.write("<h2>Invalid Login</h2>");
            writer.write("<br><br><a href=\"http://localhost:8080/lms\">Login</a>");
        }

        writer.write("</body>");
        writer.write("</html>");

    }

    private boolean validateLogin(String username, String password){
        return username.equals("Rav") && password.equals("123");
    }

}