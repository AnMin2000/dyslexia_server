<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Image Result</title>
</head>
<body>
<h1>Image Result</h1>

<!-- GET 요청을 보내는 폼 -->
<form action="${pageContext.request.contextPath}/pictures" method="GET">
    <input type="submit" value="Get pictures Result">
</form>

<!-- 이미지를 표시할 영역 -->
<div>
    <img src="${imageFileName}" alt="Image">
</div>
</body>
</html>







<%--http://127.0.0.1:8080/test123/session.jsp--%>
