<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>run</title>
<meta charset="UTF-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/sign_in.css"/>
</head>

<body>
  <div class="wrap">
    <div class="login_content">
      <div class="logo_sec">
        <h3>게임</h3>
      </div>
      <form class="login_sec" action="do_sign_in">
        <input type="text" placeholder="아이디" class="login_input" required="required" name="id"/>
        <input type="password" placeholder="비밀번호" class="login_input" required="required" name="pass"/>
        <input type="submit" class="login_btn" value="확인" />
      </form>
    </div>
  </div>
</body>

</html>