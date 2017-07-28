<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/main.css" rel="stylesheet" type="text/css">
<title>Mysite</title>
</head>
<body>
	<div id="container">
		
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		

		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img style="width: 150px" id="profile" src="/mysite/assets/images/profile.png">
					<h2>안녕하세요.<br> 강부민의 mysite에 방문하신 것을<br/> 환영합니다.</h2>
					<p>
						이 사이트는 발챙이 사이트입니다.
						<br>
						발챙이 만세
						<br>
						발챙이 진화는 수륙챙이
						<br>
						삥글삥글 귀엽습니다.
						<br>
						<br>
						<a href="">방명록</a>에 글 남기기
						<br>
					</p>
				</div>
			</div>
		</div>
		
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>

	</div>
</body>
</html>