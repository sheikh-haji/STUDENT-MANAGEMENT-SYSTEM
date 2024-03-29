/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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


@WebServlet("/register_course")
public class register_course extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           String course_id = (String) request.getParameter("course_id");
           String uname =  (String) request.getSession().getAttribute("uname");
           
           Class.forName("com.mysql.cj.jdbc.Driver");
//                session.setAttribute("A", "SA");
                String course_name="";
                long roll=0;int total=0;
                int temp=20;
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/faculty_login", "root", "");
               String qr1 = "select course_name,total from courses where course_id=?";
               PreparedStatement ps1 = con.prepareStatement(qr1);
               ps1.setString(1,course_id);
               ResultSet rs1 =  ps1.executeQuery();
               if(rs1.next()){   
                    course_name=rs1.getString("course_name");
                    total=rs1.getInt("total");
               }
               con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_login", "root", "");
               qr1 = "select roll from courses where username=?";
               ps1 = con.prepareStatement(qr1);
               ps1.setString(1,uname);
                rs1 =  ps1.executeQuery();
               if(rs1.next())   
                    roll=rs1.getLong("roll");
               
                String qr="insert into courses(roll,username,course_name,attended,total,marks,course_id) values(?,?,?,?,?,?,?)";
                PreparedStatement ps=con.prepareStatement(qr);
                ps.setLong(1,roll);
                ps.setString(2,uname);
                ps.setString(3,course_name);
                ps.setInt(4,temp);
                ps.setInt(5,total);
                int t=50;
                ps.setInt(6,t);
                ps.setString(7,course_id);
                ps.executeUpdate();
                response.sendRedirect("student_home.jsp?username="+uname);
                
//                ResultSet rs = ps.executeQuery();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(register_course.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
