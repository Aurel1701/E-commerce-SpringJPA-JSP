
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
<html>
<head>
<meta charset="UTF-8">
<title>Carrello</title>
</head>
<body>

         <div class="container mt-5">
             <h1 class="text-center">Checkout</h1>

          <div class="card mt-3">
                <table class="table table-striped mb-0">
              <thead>
                <tr>
                    <th class="text-center" width="20%">Sku</th>
                  <th class="text-center" width="20%">Nome</th>
                  <th class="text-center" width="20%">Prezzo</th>
                  <th class="text-center" width="20%">Quantità</th>
                  <th class="text-center" width="20%">Subtotale</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${carrello}" var="prodotto">
                      <tr>
                     <td class="text-center">${prodotto.product.sku}</td>
                    <td class="text-center">${prodotto.product.name}</td>
                    <td class="text-center">${prodotto.product.price}</td>
                      <td class="text-center">${prodotto.quantity}</td>
                     <td class="text-center">${prodotto.product.price * prodotto.quantity}</td>
                   </tr>
                </c:forEach>
              </tbody>
                </table>
          </div>

          <div class="text-center mt-3">
              <h4>Totale: <span class="text-success">${totale} $</span></h4>
            </div>

      <div class="d-flex justify-content-center mt-3">
            <button class="btn btn-danger" onclick="alert('Grazie per l\'acquisto')">Paga</button>
          </div>

        <div class="text-center mt-3">
            <a class="btn btn-secondary" href="/listProducts">Torna al catalogo</a>
          </div>
        </div>



</body>


</html>