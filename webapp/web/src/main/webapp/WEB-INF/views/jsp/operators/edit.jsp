<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
  <title>Edit</title>
</head>
<body>

<form:form action="/operator/edit/${user.id}" method="post" modelAttribute="user">
  <form:input path="name" value="${user.name}"/>
  <form:input path="email" value="${user.email}"/>
  <form:checkbox path="changePassword" id="chBoxPasswordChange"/>

  <div id="password-fields" hidden>
    <form:input placeholder="Old password" path="oldPassword" type="password" value=""/>
    <form:input placeholder="New password" path="password" type="password" value=""/>
  </div>

  <button>Submit</button>
  <button onclick="history.back()">Cancel</button>
</form:form>

<script>
  let $chBoxPasswordChange = document.querySelector('#chBoxPasswordChange');
  $chBoxPasswordChange.addEventListener('click', onChBoxPasswordChange);

  function onChBoxPasswordChange(event) {
    if(this.checked) {
      document.querySelector('#password-fields').hidden = false;
    } else {
      document.querySelector('#password-fields').hidden = true;
    }
  }
</script>

</body>
</html>
