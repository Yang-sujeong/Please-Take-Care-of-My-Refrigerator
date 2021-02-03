<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Refresh" content="2;url=login.do">
<!-- 화면 최적화 -->
<meta name="viewport" content="width-device-width" , initial-scale="2">
<!-- 루트 폴더에 부트스트랩을 참조하는 링크 -->
<link rel="stylesheet" href="../css/bootstrap.css">
<title>LonginFail</title>

<style>
* {
	text-decoration: none;
	user-select: none;
	font-family: "Noto Sans KR", sans-serif;
	font-family: "Roboto", sans-serif;
}

.full {
	clear: both;
	background: url(../images/bg3.jfif);
	background-size: cover;
	width: 100%;
	height: 100%;
}

.containerform {
	padding: 150px 0px;
}

.jumbotron {
	padding: 30px 15px;
	margin-bottom: 30px;
	background-color: rgba(213, 238, 189, 0.396);
	backgorund-position: center;
	position: veritcal-center;
	text-align: center;
}

.jumbotron h3 {
	font-color: #fff;
}

.jumbotron p {
	font-size: 14px;
}
</style>
</head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<body>
	<jsp:include page="/Header.jsp" />



	<div class="full">
		<div class="containerform">

			<div class="jumbotron center-block"
				style="width: 400px; padding-bottom:20px;">
				<h4>
					<strong>로그인 에러</strong>
				</h4>
				<br>
				<p>아이디 또는 비밀번호가 일치하지 않습니다.</p>
				<br> <a href="../auth/login.do" class="loginhere-link">로그인
					하기</a>
			</div>
			</form>
		</div>

		<jsp:include page="/Tail.jsp" />
</body>
</html>








