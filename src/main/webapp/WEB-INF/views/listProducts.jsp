<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>First JSP</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

  <div class="container">
   <br>
   <div class="panel panel-primary">
    <div class="panel-heading">
     <h3 class="text-center">Lista di prodotti</h3>
    </div>
    <div class="panel-body">
     <table class="table table-striped">
      <thead>
       <tr>
         <th width="30%">nome</th>
        <th width="30%">Prezzo</th>
        <th width="30%">Quantity</th>
        <th width="10%"></th>
       </tr>
      </thead>
      <tbody>
       <c:forEach items="${catalogo}" var="prodotto">
        <tr>
         <td>${prodotto.nome}</td>
         <td>${prodotto.prezzo}</td>
         <td>
         <form class="d-flex" action="/addCarrello" method="post">
          <input type="hidden" name="id" value="${prodotto.id}">
          <input type="text" name="quantity">
         <button type="submit"  class="btn btn-success">
         Aggiungi al carrello
         </button>
         </form>
       </td>
         </tr>
       </c:forEach>
      </tbody>
     </table>
    </div>
   </div>
</body>
</html>