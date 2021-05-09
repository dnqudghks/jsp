<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>MOA project</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="author" content="">

  <!-- Facebook and Twitter integration -->
	<meta property="og:title" content="">
	<meta property="og:image" content="">
	<meta property="og:url" content="">
	<meta property="og:site_name" content="">
	<meta property="og:description" content="">
	<meta name="twitter:title" content="">
	<meta name="twitter:image" content="">
	<meta name="twitter:url" content="">
	<meta name="twitter:card" content="">

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="favicon.ico">

	<link href="https://fonts.googleapis.com/css?family=Quicksand:300,400,500,700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,400i,700" rel="stylesheet">
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="/cafe/css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="/cafe/css/icomoon.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="/cafe/css/bootstrap.css">
	<!-- Flexslider  -->
	<link rel="stylesheet" href="/cafe/css/flexslider.css">
	<!-- Flaticons  -->
	<link rel="stylesheet" href="/cafe/css/font/flaticon.css">
	<!-- Owl Carousel -->
	<link rel="stylesheet" href="/cafe/css/owl.carousel.min.css">
	<link rel="stylesheet" href="/cafe/css/owl.theme.default.min.css">
	<!-- Theme style  -->
	<link rel="stylesheet" href="/cafe/css/style.css">
	<link rel="stylesheet" type="text/css" href="/cafe/css/w3.css">
	<link rel="stylesheet" type="text/css" href="/cafe/css/user.css">
	<script type="text/javascript" src="/cafe/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/cafe/js/w3color.js"></script>
	<!-- Modernizr JS -->
	<script src="/cafe/js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
	<!-- jQuery -->
	<script src="/cafe/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="/cafe/js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="/cafe/js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="/cafe/js/jquery.waypoints.min.js"></script>
	<!-- Flexslider -->
	<script src="/cafe/js/jquery.flexslider-min.js"></script>
	<!-- Owl carousel -->
	<script src="/cafe/js/owl.carousel.min.js"></script>
	<!-- Counters -->
	<script src="/cafe/js/jquery.countTo.js"></script>
	
	
	<!-- MAIN JS -->
	<script src="/cafe/js//main.js"></script>
	<style>
	
	</style>
	</head>
</head>
<body>
	<body class="">
	<div id="colorlib-page">
		<a href="#" class="js-colorlib-nav-toggle colorlib-nav-toggle" data-toggle="collapse" data-target="#navbar" aria-expanded="true" aria-controls="navbar"><i></i></a>
		<div id="colorlib-aside" class="w3-col border js-fullheight">
			<div class="text-center">
				<div class="author-img" style="background-image: url(images/about.jpg);"></div>
				<h1 id="colorlib-logo"><a href="index.html">마이페이지</a></h1>
				<span class="position">마이 페이지 홈 ></span>
			</div>
			<nav id="colorlib-main-menu" role="navigation" class="navbar">
				<div id="navbar" class="collapse in" aria-expanded="true" style="">
					<ul>
						<li><a href="#" data-nav-section="check">구매확인/취소</a></li>
						<li><a href="#" data-nav-section="wish">찜 목록</a></li>
						<li><a href="#" data-nav-section="myre">나의 리뷰</a></li>
						<li><a href="#" data-nav-section="review">리뷰 작성</a></li>
						<li><a href="#" data-nav-section="myone">나의 1:1 문의</a></li>
						<li><a href="#" data-nav-section="change">회원정보 수정</a></li>
						<li><a href="#" data-nav-section="out">회원탈퇴</a></li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
</body></body>
</body>
		<div id="colorlib-main">
			<section id="colorlib-hero" class="js-fullheight" data-section="home" style="height: 900px;">
				<div class="flexslider js-fullheight" style="height: 600px;">
					<div class="gallary-header text-center">
					<br>
					<h3>
						회원님의 잔여 포인트 : 
						<input type="text" name="point" id="point" 
									class=" w3-input w3-border w3-border-grey w3-margin-top" 
									placeholder="10,000 P"style="text-align:right; width:200px; ">
					</h3>
				</div>
				<div class="packages-content">
					<div class="row">
					
						<div class="w3-margin-left col-md-4 col-sm-6 w3-margin-top">
							<div class="single-package-item">
								<div class="single-package-item-txt">
									<h3>최근 본 전시 <span class="pull-right"></span></h3>
									<div class="packages-para">
								<img src="/cafe/img/images/cover_bg_1.jpg" width="150">
								<img src="/cafe/img/images/blog-4.jpg" width="150">
									
									</div><!--/.packages-review-->
									<!--
									<div class="about-btn">
										<button class="w3-margin-top w3-margin-bottom about-view packages-btn">
											더보기
										</button>
									</div><!--/.about-btn-->
								</div><!--/.single-package-item-txt-->
							</div><!--/.single-package-item-->

						</div><!--/.col-->
						</div>
						
						<div class="col-md-4 col-sm-6 w3-margin-top">
							<div class="single-package-item">
								<div class="single-package-item-txt">
									<h3>최근 본 이벤트 <span class="pull-right"></span></h3>
									<div class="packages-para">
										<img src="/cafe/img/images/blog-3.jpg" width="150">
										<img src="/cafe/img/images/blog-2.jpg" width="150">
									</div><!--/.packages-para-->
									<!-- 
									<div class="about-btn">
									
										<button class="w3-margin-top w3-margin-bottom about-view packages-btn">
											더보기
										</button>
									</div><!--/.about-btn-->
									
									 
								</div><!--/.single-package-item-txt-->
							</div><!--/.single-package-item-->

						</div><!--/.col-->
						<div class="col-md-4 col-sm-6 ">
							<div class="single-package-item">
								<div class="single-package-item-txt">
									<h3>찜 목록 <span class="pull-right"></span></h3>
									<div class="packages-para">
								<img src="/cafe/img/images/blog-1.jpg" width="150">
								<img src="/cafe/img/images/img_bg_2.jpg" width="150">
										
											
									</div><!--/.packages-review-->
									<div class="about-btn">
										<button class="w3-color-black w3-margin-top w3-margin-bottom  about-view packages-btn">
											더보기
										</button>
									</div><!--/.about-btn-->
								</div><!--/.single-package-item-txt-->
							</div><!--/.single-package-item-->

						</div><!--/.col-->
						
		</div><!-- end:colorlib-main -->
	</div><!-- end:container-wrap -->
	</div>
</html>