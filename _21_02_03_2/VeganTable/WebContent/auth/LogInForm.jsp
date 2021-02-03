<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 화면 최적화 -->
<meta name="viewport" content="width-device-width", initial-scale="2">
<!-- 루트 폴더에 부트스트랩을 참조하는 링크 -->
<link rel="stylesheet" href="../css/bootstrap.css">
<title>Login</title>
</head>
<style>
* {
   text-decoration: none;
   user-select: none;
   font-family: "Noto Sans KR", sans-serif;
   font-family: "Roboto", sans-serif;
}

.full{
	clear: both;
	background:url(../images/bg3.jfif);
	background-size:cover;
	width:100%;
	height:100%;
}
.containerform{
 padding: 150px 0px;}
.jumbotron {
  padding: 30px 15px;
  margin-bottom: 30px;
  background-color:rgba(213, 238, 189, 0.396);
  backgorund-position :center;
  position:veritcal-center;
  
}
.jumbotron h3{
font-color:#fff;
}
.form-group{
margin:10px 70px; 
}
</style>
<body>
<jsp:include page="/Header.jsp"/>
	<!-- 부트스트랩 참조 영역 -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>

<!-- 로그인 양식 -->
<div class="full">
<div class="containerform">		

 
<!-- 하나의 영역 생성 -->
			<div class="jumbotron center-block" style="width:400px;   padding-bottom:100px;">
				<form method="post" action="login.do" onsubmit="LoginForm__submit(this); return false">
				
				<div class="form-group">
					<img src="../images/logo.png" class="img-square center-block"
						style="width: 80px;">
				</div>
				
				
				
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="id" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="pw" maxlength="20">
					</div>
					</br>
					 <div class="col-md-4 col-md-offset-4 text-center">
					<button type="submit" class="btn btn-success" >로그인</button>
					</br>
					</br>
					<a href="../member/add.do" class="loginhere-link">회원가입</a>
					</div>
					
				</form>
			</div>
		</div>

</div>
<jsp:include page="/Tail.jsp"/>

</body>
</html>


