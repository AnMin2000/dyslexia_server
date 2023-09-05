<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>OCR Result</title>
</head>
<body>
<h1>OCR Result</h1>

<!-- GET 요청을 보내는 폼 -->
<form action="http://127.0.0.1:8080/ocr" method="GET">
    <input type="submit" value="Get OCR Result">
</form>

<!-- 컨트롤러에서 가져온 ocrResult를 출력 -->
<p th:text="${ocrResult}"></p>
</body>
</html>


http://127.0.0.1:8080/test123/session.jsp
