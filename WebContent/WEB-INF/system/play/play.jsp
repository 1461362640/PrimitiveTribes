<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link type="text/css" href="./source/tool/video/css/flarevideo.css"/>
<link type="text/css" href="./source/tool/video/css/flarevideo.default.css"/>
<link type="text/css" href="./source/tool/video/css/flarevideo.spotify.css"/>
<link type="text/css" href="./source/tool/video/css/flarevideo.vimeo.css"/>

<!-- 载入播放插件 start -->
<script type="text/javascript" src="./source/easyui/jquery.min.js"></script>
<script type="text/javascript" src="./source/tool/video/jwplayer.js"></script>
<!-- 载入播放插件 end-->
<!-- 右侧浮动条 start -->
<link rel="stylesheet" href="./source/page/qq/css/lanrenzhijia.css" type="text/css"/>
<script src="./source/page/qq/js/lanrenzhijia.js"></script>
<!-- 右侧浮动条 end -->
<script>
var isindex = true;
var visitor = true;
</script>

<style type="text/css" media="screen">
body {
	 background: #27282C url(./source/page/logo.png) repeat;
}

#video {
	-webkit-box-shadow: 0 0 20px #000;
	-moz-box-shadow: 0 0 20px #000;
	width: 900px;
	height:800px;
	overflow: none;
	margin: 5% auto;
}

</style>
</head>
<body>
<!--#set(List<Video> videos,Video video)-->

<div id="video">
	<div id="container">播放器载入中...</div>
	<script type="text/javascript">
		jwplayer("container").setup({
			flashplayer : "./source/tool/video/player.swf",
			file : "",
			autostart:true,
			repeat:"list",//重复播放
			showdownload:"true",
			logo:"./source/page/logo.png",
			overstretch:"true",//影片自适应
			showeq:"true",
			/**版本切换[高清版]**/
			 plugins: {
					hd: { file: "${video.videoUrl}", fullscreen: true },
				},
				playlist: [
				{ /* duration: 132,*/file: "${video.videoUrl}", image:"${video.imageUrl}" ,title:"${video.name}",description:"${video.describes}",keywords:"流行"},
				<!--#for(Video vod:videos)-->
				 { /* duration: 132,*/file: "${vod.videoUrl}", image:"${vod.imageUrl}" ,title:"${vod.name}",description:"${vod.describes}",keywords:"流行"},
				 <!--#end-->
				  
				],
				"playlist.position": "bottom",
				"playlist.size": 150,
		    skin: "./source/tool/video/nacht.zip", 
			image: "${video.imageUrl}",
			height :800,
			width : 900
		});
	</script>
  
    
    <!-- 浮动小人 -->
    <div id="spig" class="spig">
    <div id="message">正在加载中……</div>
    <div id="mumu" class="mumu"></div>
</div>
	 
</body>
</html>

