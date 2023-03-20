<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>First JSP</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
     <div class="container my-5">
        <h1 class="display-4">Welcome in Aurel E-commerce</h1>

        <form method="post" action="/register">
           <div class="form-group">
             <label for="username">Username</label>
             <input type="text" class="form-control" id="username" name="username" required>
           </div>
           <div class="form-group">
             <label for="password">Password</label>
             <input type="password" class="form-control" id="password" name="password" required>
           </div>
           <button type="submit" class="btn btn-primary">Log in</button>
         </form>





     </div>


</body>
</html>