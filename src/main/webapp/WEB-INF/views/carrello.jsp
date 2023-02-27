
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
	<h1 class="text-center">Prodotti nel carrello:</h1>
	<table class="table table-striped text-center mt-3">
		 <thead >
               <tr>
                 <th width="20%">name</th>
                 <th width="20%">price</th>
                <th width="20%">quantity</th>
                <th width="20%"></th>
               </tr>
              </thead>
		<tbody>
			<c:forEach items="${cartentry}" var="cart" >
				<tr>
                    <td>${cart.product.nome}</td>
					<td>${cart.product.prezzo}</td>
					<td>${cart.quantita}</td>
					<td>

					<form method="POST" action="/removeFromCart">
                    <input type="hidden" name="id" value="${cart.product.id}"/>
					    <button type="submit"  class="btn btn-warning">
                                    Cancella dal carrello
                         </button>
                    </form>
                    </td>
				</tr>


			</c:forEach>



		</tbody>
	</table>

	<br>


    <div class="text-center mt-3">
	    <a href="/checkout" class="btn btn-danger">Procedi al checkout</a>
    </div>




	<h5 class="text-center mt-3">
	<a href="/listProducts">Torna al catalogo</a>
	</h5>
</body>


</html>