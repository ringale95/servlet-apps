import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;


public class Movie extends HttpServlet{

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/moviedb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        writer.write("<html>");
        writer.write("<body>");
        writer.write("<h1>Welcome to our Movie Store</h1>");
        writer.write("<p>Please make your selection below</p>");
        writer.write("<form action=\"movie.do\" method=\"post\">");
        writer.write("<select name=\"choice\"> ");
        writer.write("<option value=\"browse\">Browse Movies</option>");
        writer.write("<option value=\"add\">Add new movie to database</option>");
        writer.write("<input type=\"submit\" value=\"Submit\">");
        writer.write("</form>");
        writer.write("</body>");
        writer.write("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String choice = request.getParameter("choice");
        
        if(choice.equals("add"))
            displayAddMovies(response);
        else if(choice.equals("save"))
            displaySaveMovie(request, response);
        else if(choice.equals("browse"))
            displayBrowseMovie(response); 
    }

    private void displayAddMovies(HttpServletResponse response) throws IOException{
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        writer.write("<html>");
        writer.write("<body>");
        writer.write("<h1>Please enter details below:</h1>");
        writer.write("<form action=\"movie.do\" method=\"post\">");
        writer.write("<br><label for=\"title\">Movie Title:</label>");
        writer.write("<input type=\"text\" name=\"title\">");
        writer.write("<br><label for=\"actor\">Lead Actor:</label>");
        writer.write("<input type=\"text\" name=\"actor\">");
        writer.write("<br><label for=\"actress\">Lead Actress:</label>");
        writer.write("<input type=\"text\" name=\"actress\">");
        writer.write("<br><label for=\"genre\">Genre:</label>");
        writer.write("<input type=\"text\" name=\"genre\">");
        writer.write("<br><label for=\"year\">Year:</label>");
        writer.write("<input type=\"text\" name=\"year\">");
        writer.write("<input type=\"hidden\" name=\"choice\" value=\"save\">");
        writer.write("<br><input type=\"submit\" value=\"Add Movie\">");
        writer.write("</form>");
        writer.write("</body>");
        writer.write("</html>");
    }

    private void displaySaveMovie(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String title = request.getParameter("title");
        String actor = request.getParameter("actor");
        String actress = request.getParameter("actress");
        String genre = request.getParameter("genre");
        int year = Integer.parseInt(request.getParameter("year"));

        String query = "INSERT INTO movies (title, actor, actress, genre, year) " +
               "VALUES ('" + title + "', '" + actor + "', '" + actress + "', '" + genre + "', " + year + ")";

        // Add movie to db
        String inserted = addMovieToDb(query);

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        writer.write("<html>");
        writer.write("<body>");
        writer.write("<h1>"+inserted+" Movie Added Successfully</h1>");
        writer.write("<a href=\"/movie\"> Click here to go back to main page</a>");
        writer.write("</body>");
        writer.write("</html>");
    }

     private void displayBrowseMovie(HttpServletResponse response) throws IOException{
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        writer.write("<html>");
        writer.write("<body>");
        writer.write("<h1>Searching Movies:</h1>");
        writer.write("<form action=\"movie.do\" method=\"post\">");
        writer.write("<br><label for=\"keyword\">Keyword:</label>");
        writer.write("<input type=\"text\" name=\"keyword\"><br>");
        writer.write("<input type=\"radio\" id=\"title\" name=\"title\" value=\"title\">");
        writer.write("<label for=\"title\">Search By Title</label><br>");
        writer.write("<input type=\"radio\" id=\"actor\" name=\"actor\" value=\"actor\">");
        writer.write("<label for=\"actor\">Search By Actor</label><br>");  
        writer.write("<input type=\"radio\" id=\"actress\" name=\"actress\" value=\"actress\">");
        writer.write("<label for=\"actress\">Search By Actress</label><br>");
        writer.write("<input type=\"hidden\" name=\"choice\" value=\"search\">");
        writer.write("<br><input type=\"submit\" value=\"Search Movies\">");
        writer.write("</form>");
        writer.write("</body>");
        writer.write("</html>");
    }

    private String addMovieToDb(String query) {
        try {
            // Establish a connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // Create a statement
            Statement stmt = conn.createStatement();

            // Execute the query and insert the data
            int rowsAffected = stmt.executeUpdate(query);

            // Close the statement and connection
            stmt.close();
            conn.close();

            return rowsAffected + "";

        } catch (Exception e) {
            return e.getMessage();
        }
    }


}