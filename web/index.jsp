
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles.css">
        
        <title>CEG GUINDY</title>
        <style type = "text/css">
        body {
        font-family: "Trebuchet MS";
        }
        span.psw {
            float: right;
            padding-top: 0px;
        }
 </style>
 <script>
     function alert_message(){
         alert("Contact the administrator department!!");
     }
 </script>
 <!--<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">-->
<link rel="icon" type="image/ico" href="https://acoe.annauniv.edu/sems/assests/img/anna-univ.ico">
</head>
<body bgcolor="cyan">
    <a class="navbar-brand">
            <h1><img class="img-responsive" src="https://acoe.annauniv.edu/sems/assests/img/home/logo.png" alt="logo"></h1>
          </a>

    <div class="centered" style="width:400px;height: 400px">
      <h1 align="center">CEG GUINDY</h1>
      <p align="center" style="font-size: 20px">Login Portal</p>
      <%
          response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");//HTTP 1.1
             response.setHeader("Pragma","no-cache");//HTTPS
             response.setHeader("Expires","0");
          String status = (String)request.getParameter("status");
          if(status==null)
            status="T";
      %>
    <form action="login" method="GET">
        <input type="radio" name="type_of_login" value="1">Faculty &emsp;
        <input type="radio" name="type_of_login" value="0">Student login &emsp;
        <br>
        <br>
        <%if(status.equals("F"))
        {%>
        <font style="color: red">Username and Password do not match!!</font>
        <%}
            else if(status.equals("L"))
        {
        %>
        <font style="color: red">Select login account type!!</font>
        <%}
        else{}
        %>
        <input type="text" name="username" placeholder="Username" ><br>
        <input type="password" name="password" placeholder="Password" ><br>
        
        <label>
           <input type="checkbox" checked="unchecked" name="remember"> Remember me
        </label> &emsp;&emsp;&emsp;&emsp;
        <label onclick="alert_message()" style="border: none; background-color: white; cursor: pointer; color: black;
                 font-size: 15px">Forget Password</label>
        <input type="submit" value="Login">
    </form>
        
      
    </div>
   
<!--</div>-->
</body>
</html>
