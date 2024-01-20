import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        String text = "Hello World!";
        res.setContentType("text/html");
        res.getWriter().write("<html>");
        res.getWriter().write("<body>");
        res.getWriter().write("<h1>"+ text +"</h1>");
        res.getWriter().write("</body>");
        res.getWriter().write("</html>");
    }
}