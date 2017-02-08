<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
  <title>Create</title>
</head>
<body>

<form:form action="/operator/create" method="post" modelAttribute="operator">
  <form:input path="name" placeholder="Enter username"/>
  <form:input path="email" placeholder="Enter email"/>
  <form:input path="password" type="password" placeholder="Enter password"/>
  <button>Submit</button>
</form:form>

</body>
</html>
