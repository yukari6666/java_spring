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
			<th>idx</th>
			<th>id</th>
			<th>pass</th>
			<th>name</th>
			<th>attackPoint</th>
			<th>guardPoint</th>
			<th>HP</th>
		</tr>
		${select_result} 
</table>

<a href="/game/sign_in">로그아웃</a>
<a href="/game/Battle">대전</a>
</body>
</html>