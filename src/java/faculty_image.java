

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author DELL
 */
@WebServlet("/faculty_image")
public class faculty_image extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
//                    String uname = (String) request.getSession().getAttribute("uname");
                    String faculty_username= (String) request.getSession().getAttribute("faculty_username");
//                    String faculty_user = (String) request.getSession().getAttribute("faculty_user");
                    String A = (String) request.getSession().getAttribute("A");
//                    out.print(user+"      "+A);
                    String u = (String) request.getSession().getAttribute("uname");
            try
                {
                    String ImageFile="";
                   
                    String itemName = "";
                    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//to see that user uploads only one file
                    if (!isMultipart)
                    {
                    }
                    else
                    {
                       //FileItemFactory and Servletfileuploadtogether help to uplod files in the server
                        FileItemFactory factory = new DiskFileItemFactory();
                        ServletFileUpload upload = new ServletFileUpload(factory);
//                        //Only one image
                        List items = null;
                        try
                        {
                            //upload could be having the file to be uploaded in the server 
                            //it is now assigned to items
                           items = upload.parseRequest(request);
                        }
                        catch (FileUploadException e)
                        {
                            e.getMessage();
                        }
                        Iterator itr = items.iterator();
                        while (itr.hasNext())
                        {
                            FileItem item = (FileItem) itr.next();
                            //to see if item is of simple form-->pdf jpg word etc
                            //returns true if there already exist a file
                            //return false if no such exit in the server
                            //FormField is type of the file
                            if (item.isFormField())
                            {
                                //gets the type of file if it is a ImageFile then only accept
                                String name = item.getFieldName();
                                String value = item.getString();
                                if(name.equals("ImageFile"))
                                {
                                    ImageFile=value;
                                }

                            }
                            else
                            {
                                try
                                {
                                    itemName = item.getName();
                                    File savedFile = new File("C:\\Users\\DELL\\OneDrive\\Desktop\\project web lab\\debugging11\\debugging1\\web\\images\\"+itemName);
                                    item.write(savedFile);
                                }
                                catch (Exception e)
                                {
                                    out.println("Errorasfdsgsdg"+e.getMessage());
                                }
                            }
                        }
                        try{
                            if(A.equals("AA"))
                            {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/faculty_login","root","");
                            Statement st = con.createStatement();
////                            out.println(itemName);
                                st.executeUpdate("update admin set image_path='"+itemName+"' where username='"+u+"'");
                                response.sendRedirect("admin_home.jsp?username="+u);
//                        
                            }
                            if(A.equals("FA"))
                            {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/faculty_login","root","");
                            Statement st = con.createStatement();
////                            out.println(itemName);
                                st.executeUpdate("update login set image_path='"+itemName+"' where username='"+faculty_username+"'");
                                response.sendRedirect("show_faculty_details.jsp?faculty_username="+faculty_username);
//                        
                            }
                         if(A.equals("GG"))
                            {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/faculty_login","root","");
                            Statement st = con.createStatement();
////                            out.println(itemName);
                                st.executeUpdate("update login set image_path='"+itemName+"' where username='"+faculty_username+"'");
                                response.sendRedirect("faculty_home.jsp?username="+faculty_username);
//                        
                            }
//                        
                        }
                        
                        catch(Exception el)
                        {
                            out.println("Inserting error"+el.getMessage());
                        }
                    }
                }
                catch (Exception e){
                    out.println(e.getMessage()+"sdfgdfhdfhdghdghgfgfgfj");
                                    }


        }
    }

   

}
    
