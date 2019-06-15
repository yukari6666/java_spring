<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/input.css"
	rel="stylesheet" />
</head>
<body>
 <a href="/s/login">목록으로</a>
	<form action="input_data" method="get">
		<label>ID</label> <input type="text" placeholder="이름입력" name="id" />
		<label>PASSWORD</label> <input type="password" placeholder="주소" name="pass" />
		<label>name</label><input type="text" placeholder="이름입력" name="name" />
		<label>attackPoint</label><input type="text" placeholder="공격력 입력" name="attackPoint" />
		<label>guardPoint</label><input type="text" placeholder="방어력 입력" name="guardPoint" />
		<label>HP</label><input type="text" placeholder="HP 입력" name="HP" />
		
		<input type="submit" value="입력 완료" />
	</form>
</body>
</html>