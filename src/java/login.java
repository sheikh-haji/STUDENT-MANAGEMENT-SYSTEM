

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class login extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String type_of_login = request.getParameter("type_of_login");
            if(type_of_login==null)
            {
                type_of_login="5";
            }
            if(type_of_login.equals("1"))
            {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
              Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/faculty_login", "root", "");
            String qr="select username,password from login where username=? and password=?";
            PreparedStatement ps=con.prepareStatement(qr);
            ps.setString(1,username);
            ps.setString(2,password);
            
            ResultSet rs=ps.executeQuery();
//            out.print(password);
            if(rs.next())
                response.sendRedirect("faculty_home.jsp?username="+username);  //URL REWRITING SENDING REQUEST TO faculty_home.JSP WITH THE USERNAME
            else
                response.sendRedirect("index.jsp?status="+"F");
            
            }
            else if (type_of_login.equals("0"))
            {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_login", "root", "");
                String qr="select * from login where username=? and password=?";
                PreparedStatement ps=con.prepareStatement(qr);
                ps.setString(1,username);
                ps.setString(2,password);
                ResultSet rs=ps.executeQuery();
    //            out.print(password);
                if(rs.next())
                {
                   response.sendRedirect("student_home.jsp?username="+username);  //URL REWRITING SENDING REQUEST TO student_home.JSP WITH THE USERNAME
                   //out.println("sucessful");
                }else
                response.sendRedirect("index.jsp?status="+"F");                
            }else
                response.sendRedirect("index.jsp?status="+"L");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    @Override
   protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
       
   }
    
   protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
       
   }

}
