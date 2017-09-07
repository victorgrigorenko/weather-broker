<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select City</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css">

        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/2.2.4/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    </head>
    <body style="background-color:#495f59;	color:#888">
        <div class="container" style="background-color:aliceblue; min-height:100vh;">
            <h3>Выбор города</h3>

            <form role="form" method="get" action="${pageContext.request.contextPath}/HandlerSrvl">
                <div class="form-group">
                    <label for="inputCity">Город</label>
                    <input name="city" type="text" class="form-control" id="inputCity" placeholder="Введите город">
                </div>

                <button type="submit" class="btn btn-default">Отправить</button>
            </form>
            <p><c:out value="${status}"></c:out></p>
            <p><c:out value="${cityInfo}"></c:out></p>
        </div>
    </body>
</html>