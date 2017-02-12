<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<body>
<h1>Title : ${title}</h1>
<h1>Message : ${message}</h1>

<menu>

  <c:if test="${role == 'ROLE_ADMIN'}">
    <p><a href="${pageContext.request.contextPath}/operator">Operators menu</a></p>
  </c:if>
  <c:if test="${role == 'ROLE_ADMIN' || role == 'ROLE_ADOPS'}">
    <p><a href="${pageContext.request.contextPath}/publisher">Publisher menu</a></p>
  </c:if>
  <c:if test="${role == 'ROLE_ADOPS' || role == 'ROLE_PUBLISHER'}">
    <p><a href="${pageContext.request.contextPath}/app">App menu</a></p>
  </c:if>

  <p><a href="${pageContext.request.contextPath}/login">Login</a></p>
  <p><a href="<c:url value="/logout" />">Logout</a></p>
</menu>


</body>
</html>