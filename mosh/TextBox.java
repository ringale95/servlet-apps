import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;

public class TextBox extends HttpServlet {
    public String text;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.write("<html><body>");
        writer.write("<h1>Text Box Servlet</h1>");
        // Do not make another instance for TextBox as TextBox is a servelet which is Singleton
        this.setText("Box 1"); //if we dont set this it gives nullpointerException if any function is called on text: To avoid error either initialize with "" or do setText
        writer.write("<p>Current Text: " + text.toUpperCase() + "</p>");
 
        
    }

    public void setText(String text) {
        this.text = text;
    }

    public void clear() {
        text = "";
    }
}
