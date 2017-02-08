<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
  <title>All operators</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>

<header>
  <a href="/operator/create">Create new operator</a>
</header>

<table>
  <thead>
  <tr>
    <td>User name</td>
    <td>Email</td>
    <td>Status</td>
    <td colspan="2">Operation</td>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="operator" items="${operators}">
    <tr data-row-id="${operator.id}">
      <td>${operator.name}</td>
      <td>${operator.email}</td>
      <td>Active</td>
      <td>
        <a href="/operator/edit/${operator.id}">Edit</a>
      </td>
      <td>
        <button class="delete-btn" data-user-id="${operator.id}">Delete</button>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>


<script>
  function deleteUser(event) {
    event.preventDefault();
    let userId = event.target.dataset.userId;

    console.log('userId:', userId);
    console.log($('tr[data-row-id=' + userId + ']'));
    $.ajax({
             url: '/operator/delete/' + userId,
             method: 'get',
             success() {
               $('tr[data-row-id=' + userId + ']').remove();
             }
           })
  }

  $('.delete-btn').on('click', deleteUser)
</script>
</body>
</html>
