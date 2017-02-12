<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>Front</title>
  <base href="/">

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" type="image/x-icon" href="<c:url value="/resources/ico/favicon.ico"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>">
</head>
<body>
<app-root>Loading...</app-root>
<app-user-list></app-user-list>
<script type="text/javascript" src="<c:url value="/resources/js/inline.bundle.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/polyfills.bundle.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/styles.bundle.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/vendor.bundle.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/main.bundle.js"/>"></script>
</body>
</html>
