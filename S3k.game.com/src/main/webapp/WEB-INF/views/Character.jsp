<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>캐릭터 정보</title>
<link href="${pageContext.request.contextPath}/resources/character.css" rel="stylesheet" />
</head>
<body>
<table>
		<tr>
			<th>공격력</th>
			<th>방어력</th>
			<th>hp</th>
		</tr>
		<%-- ${select_result}--%>
</table>

<a href="/s">로그아웃</a>
<a href="/game/battle">대전</a>
</body>
</html>