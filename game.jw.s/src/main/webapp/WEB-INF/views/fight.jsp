<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fight</title>
<link href="${pageContext.request.contextPath}/resources/fight.css"
	rel="stylesheet" />
</head>
<body>
	<table id="userInfo">
		<tr>
			<th>idx</th>
			<th>id</th>
			<th>password</th>
			<th>name</th>
			<th>attackpoint</th>
			<th>guardpoint</th>
			<th>HP</th>
			<th></th>
		</tr>
		${select_result}
	</table>
		<table>
		<tr>
			<th>전투 내역</th>
		</tr>
		${select_battle}
	</table>
	<a href="/character">일시정지</a><br>
	<form action="fight_logic" method="get">
	<input type="button" id="attack" value="공격"><br>
	</form>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
		var user_attackPoint=$("#userInfo").rows[1].cells[1].html();
		var user_HP=$("#userInfo").rows[1].cells[2].html();
		$("#attack").click(function(){
			$.ajax({
				url:"/s/fight_logic",
				data:{
					attackPoint_M:user_attackPoint,
					HP_M:user_HP
				},
				success: function(){
					alert("success")
				}
			})
		})
	</script>
</body>
</html>