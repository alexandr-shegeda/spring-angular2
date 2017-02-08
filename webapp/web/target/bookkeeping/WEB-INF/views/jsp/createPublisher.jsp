<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
  <title>Create publisher</title>
</head>
<body>

<form:form action="/publisher/create" method="post" modelAttribute="user">
  <form:input path="name"/>
  <form:input path="email"/>
  <button>Submit</button>
</form:form>

</body>
</html>
