<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
			<th>choice</th>
		</tr>
		${select_character} 
</table>



<a href="/s">로그아웃</a>
<a href="/s/fight_screen">대전</a>
</body>
</html>