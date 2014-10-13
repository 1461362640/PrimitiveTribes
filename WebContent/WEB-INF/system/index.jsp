<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--[if IE]>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>XXX管理系统</title>
<link rel="stylesheet" type="text/css" href="./source/page/css/main.css" />
<script type="text/javascript" src="./source/easyui/jquery.min.js"></script>
<!-- 首页轮播 start -->
<link rel="stylesheet" href="./source/tool/Gallery/css/blueimp-gallery.css">
<link rel="stylesheet" href="./source/tool/Gallery/css/blueimp-gallery-indicator.css">
<link rel="stylesheet" href="./source/tool/Gallery/css/blueimp-gallery-video.css">
<link rel="stylesheet" href="./source/tool/Gallery/css/demo.css">
<!-- 首页轮播 end -->
<!-- 菜单栏 start-->
 <!--[if lt IE 9]>
          <script src="./source/page/menu/js/html5.js"></script>
 <![endif]-->
 <link rel="stylesheet" href="./source/page/menu/css/styles.css" />
<!-- 菜单栏 end -->
<script type="text/javascript">
	//鼠标一定到影片上效果
	$(document).ready(function() {
		$(".frist").mousemove(function() {
			//$(this).children("a").children("span").addClass("shadow");
			$(this).children("span").addClass("shadow");
			   
		});
		$(".frist").mouseleave(function() {
			$(this).children("span").removeClass("shadow");
		});

	});
</script>

<style type="text/css">
.box {
	height:300px;
}

.box h2 {
	background: #000;
	color: #fff;
	padding: 5px;
	text-indent: 0.5em;
}

.box li {
	width: 126px;
	height: 168px;
	float: left;
	margin: 0px 26px 80px;
	position: relative;
	list-style-type: none;
	margin-top: 20px;
}

.box li span {
	
}
/* 	//鼠标一定到影片上效果 */
.shadow {
	background: url(./source/page/css/btn.png) no-repeat;
	width: 54px;
	height: 54px;
	position: absolute;
	top: 60px;
	left: 35px;
}
</style>
 
</head>
<!--#set(List<Video> mvs)-->
<!--#set(List<Video> erzs)-->
<!--#set(List<Video> dys)-->
<!--#set(List<Banner> banners)-->
<body>

	<div class="main">
	<!-- 首页轮播图 start -->
<!-- The Gallery as inline carousel, can be positioned anywhere on the page -->
<div id="blueimp-image-carousel" class="blueimp-gallery blueimp-gallery-carousel">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="play-pause"></a>
</div>
<br>
 
<!-- The Gallery as inline carousel, can be positioned anywhere on the page -->
<div id="blueimp-video-carousel" class="blueimp-gallery blueimp-gallery-controls blueimp-gallery-carousel">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="play-pause"></a>
</div>
<br>
 
<!-- The container for the list of example images -->
<div id="links" class="links"></div>
<!-- The Gallery as lightbox dialog, should be a child element of the document body -->
<div id="blueimp-gallery" class="blueimp-gallery">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div>
<script src="./source/tool/Gallery/js/blueimp-helper.js"></script>
<script src="./source/tool/Gallery/js/blueimp-gallery.js"></script>
<script src="./source/tool/Gallery/js/blueimp-gallery-fullscreen.js"></script>
<script src="./source/tool/Gallery/js/blueimp-gallery-indicator.js"></script>
<script src="./source/tool/Gallery/js/blueimp-gallery-video.js"></script>
<script src="./source/tool/Gallery/js/blueimp-gallery-vimeo.js"></script>
<script src="./source/tool/Gallery/js/blueimp-gallery-youtube.js"></script>
<!-- <script src="./source/tool/Gallery/jquery-1.8.3.js"></script> -->
<script>
/*jslint evil: true */
/*global window, document*/
// Including jQuery via the protocol relative url above works for both http and https.
// Explicitly including jQuery via http allows running the Gallery demo as local file:
if (!window.jQuery) {
    document.write(
        '<script src="./source/tool/Gallery/jquery.min.js"><\/script>'
    );
}
</script>
<script src="./source/tool/Gallery/js/jquery.blueimp-gallery.js"></script>
<script type="text/javascript">
$(function () {
    'use strict';
    // Load demo images from flickr:
    // Initialize the Gallery as video carousel:
    blueimp.Gallery([ 
                      <!--#for(Banner banner:banners)-->
                      {
                          title: '${banner.name}',
                          href: '${banner.videoUrl}',
                          type: 'video/mp4',
                          poster: '${banner.imageUrl}'
                      },
                      <!--#end-->
    ], {
        container: '#blueimp-video-carousel',
        carousel: true
    });

});
</script>
<!-- 首页轮播图 end -->
 
<!-- 菜单栏 start-->
 <nav>
            <ul class="fancyNav">
                <li id="home"><a href="#home" class="homeIcon">首页</a></li>
                <li id="mv"><a href="./video.do?mv&id=f90c067a02cb4301abb7bd233522eca1">歌曲MV</a></li>
                <li id="erz"><a href="#about">二人转</a></li>
                <li id="xp"><a href="#services">小品</a></li>
                <li id="xs"><a href="#contact">相声</a></li>
                  <li id="kg"><a href="#contact">K歌专区</a></li>
                <li id="audio"><a href="#contact">音频专版</a></li>
            </ul>
 </nav>
<!-- 菜单栏 end -->
 <div class="clear"></div>
		<div class="box">
			<h2>高清MV<label><a class="more" target="_black" href="./video.do?mv&id=f90c067a02cb4301abb7bd233522eca1">更多</a></label></h2>
			<!--#if(mvs)-->
			<ul class="videolist">
				<!--#for(Video video:mvs)-->
				<li class="play">
				<a class="frist"
					href="./playvideo.do?play&id=${video.id}&type=f90c067a02cb4301abb7bd233522eca1" target="_blank"><span></span>
						<img style="cursor: pointer;" src="${video.imageUrl }"
						height="168" width="126"></a>
					<p
						style="height: 15px; overflow: hidden; display: block; margin-bottom: 7px; padding: 0px; margin-top: 0px;">
						<a href=""
							style="font-size: 12px; color: #333; text-decoration: none;">${video.name }</a>
					</p>
					<p
						style="height: 30px; overflow: hidden; display: block; margin: 0px; padding: 0px;">
						<a href=""
							style="font-size: 12px; text-decoration: none; color: #333;">${video.describes}更多</a>
					</p></li>
				<!--#end-->
			<!--#else-->
			<div> <img src="./source/page/images/novideo.gif"/></div>
			<!--#end-->
			</ul>
		</div>
		<div class="clear"></div>
		<div class="box">
			<h2>二人转<label><a class="more" target="_black" href="./video.do?erz&id=a8906494a66d4c91b70a98831bf6ed9f">更多</a></label></h2>
			<!--if(erzs)-->
			<ul class="videolist">
				<!--#for(Video video:erzs)-->
				<li class="play">
				<a class="frist"
					href="./playvideo.do?play&id=${video.id}&type=a8906494a66d4c91b70a98831bf6ed9f" target="_blank"><span></span>
						<img style="cursor: pointer;" src="${video.imageUrl }"
						height="168" width="126"></a>
					<p
						style="height: 15px; overflow: hidden; display: block; margin-bottom: 7px; padding: 0px; margin-top: 0px;">
						<a href=""
							style="font-size: 12px; color: #333; text-decoration: none;">${video.name }</a>
					</p>
					<p
						style="height: 30px; overflow: hidden; display: block; margin: 0px; padding: 0px;">
						<a href=""
							style="font-size: 12px; text-decoration: none; color: #333;">${video.describes}更多</a>
					</p></li>
				<!--#else-->
			<div> <img src="./source/page/images/novideo.gif"/></div>
			<!--#end-->
			</ul>
		</div>
		
			<div class="clear"></div>
		<div class="box">
			<h2>MKV<label><a class="more" target="_black" href="./video.do?dy&id=188ae7bc320b4c6b821d5da5f24bb9cb">更多</a></label></h2>
			<!--if(dys)-->
			<ul class="videolist">
				<!--#for(Video video:dys)-->
				<li class="play">
				<a class="frist"
					href="./playvideo.do?play&id=${video.id}&type=188ae7bc320b4c6b821d5da5f24bb9cb" target="_blank"><span></span>
						<img style="cursor: pointer;" src="${video.imageUrl }"
						height="168" width="126"></a>
					<p
						style="height: 15px; overflow: hidden; display: block; margin-bottom: 7px; padding: 0px; margin-top: 0px;">
						<a href=""
							style="font-size: 12px; color: #333; text-decoration: none;">${video.name }</a>
					</p>
					<p
						style="height: 30px; overflow: hidden; display: block; margin: 0px; padding: 0px;">
						<a href=""
							style="font-size: 12px; text-decoration: none; color: #333;">${video.describes}更多</a>
					</p></li>
				<!--#else-->
			<div> <img src="./source/page/images/novideo.gif"/></div>
			<!--#end-->
			</ul>
		</div>
		<!--2  -->
		<div class="clear"></div>
	</div>
	<!-- 页脚设置 -->
    <footer class="footer-stress">
	<div class="copyright">
			<div>
				<a href="#">关于我们</a>
				<span>|</span>
				<a href="#">联系我们</a>
				<span>|</span>
				<a href="#">商务合作</a>
				<span>|</span>
				<a href="./login.htm" target="_blank">管理入口</a>
			</div>
			<p>
				&copy; Copyright 2014 在线影音 版权所有&nbsp;&nbsp;&nbsp;浙ICP备88888888
			</p>
		</div>
</footer>
</body>
</html>