<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>데이터 입력</title>
<link href="${pageContext.request.contextPath}/resources/input.css"
	rel="stylesheet" />
</head>

<body>
	<a href="/game/character">목록으로</a>
	<form action="update_data" method="get">
		<input type="hidden" name="idx" value="${idx}" />
		<label>ID</label> <input type="text" placeholder="아이디 입력" name="id" value="${id}" />
		<label>PASSWORD</label> <input type="password"  name="pass" value="${pass}" />
		<label>NAME</label> <input type="text" name="name" value="${name}" />
		<label>공격력</label> <input type="text" name="attackPoint" value="${attackPoint}" />
		<label>방어력</label> <input type="text" name="guardPoint" value="${guardPoint}" />
		<label>HP</label> <input type="text" placeholder="HP" name="HP" value="${HP}" />
	
		<input type="submit" value="내 선택" />
		<input type="submit" value="상대방 선택" />
		<input type="submit" value="입력 완료" />
	</form>
</body>

</html>
