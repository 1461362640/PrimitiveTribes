<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="./source/easyui/themes/default.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="./source/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="./source/easyui/themes/icon.css" />

<script type="text/javascript" src="./source/easyui/jquery.min.js"></script>
<script type="text/javascript" src="./source/easyui/jquery.easyui.min.js"></script>

<!-- 文件上传 start -->
<link rel="stylesheet" href="./source/tool/plupload/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css"></link>
<!-- <script type="text/javascript" src="./source/tool/plupload/jquery-1.8.3.js"></script> -->
<script type="text/javascript" src="./source/tool/plupload/plupload/js/plupload.full.js"></script>
<script type="text/javascript" src="./source/tool/plupload/plupload/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script type="text/javascript" src="./source/tool/plupload/plupload/js/i18n/zh-CN.js"></script>
<!-- 文件上传 end -->
<script type="text/javascript">
	$(function() {
		$("#uploader").pluploadQueue({
			runtimes : 'html5,flash',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
			flash_swf_url : './source/tool/plupload/plupload/js/plupload.flash.swf',// Flash环境路径设置
			silverlight_xap_url : './source/tool/plupload/plupload/js/plupload.silverlight.xap',//silverlight环境路径设置
			url :'uploader?picType=max',//上传文件路径
			//url : './fileUpload.do?picUpload',//上传文件路径
			max_file_size : '3gb',//100b, 10kb, 10mb, 1gb
			chunk_size : '500mb',//分块大小，小于这个大小的不分块
			unique_names : true,//生成唯一文件名
			// 如果可能的话，压缩图片大小
			 resize : { width : 1320, height : 750, quality : 90 },
			// 指定要浏览的文件类型
			filters : [ {
				title : '选择上传视频文件',
				extensions : 'mp4'
			} ],
			init : {
				FileUploaded : function(up, file, info) {//文件上传完毕触发
					console.info(up);
					console.info(file);
					console.info(info);
					var json= eval('(' + info.response + ')');
					//var response = $.parseJSON(info.response);
					//var json = eval('(' + info.response + ')');
					if (json.status) {
						console.info(file.name.split(".")+"-----"+json.picPath);
						//$('#fm').append('<input type="hidden" name="videoUrl" value="'+json.relPath+"//"+json.fileName+'"/>');
						/* $('#fm').append('<input type="hidden" name="fileName" value="'+json.fileName+'"/><br/>');
						$('#fm').append('<input type="hidden" name="relName" value="'+file.name.split(".")[0]+'"/><br/>');
						$('#fm').append('<input type="hidden" name="imageUrl" value="'+json.picPath+'"/><br/>'); */
						document.getElementById('videoUrl').value=json.relPath+"//"+json.fileName;
						$('#fm').append('<input type="hidden" id="imageUrl" name="imageUrl" value="'+json.picPath+'"/><br/>');
					}
				}
			}
		});

		// 客户端表单验证
		$('fm').submit(function(e) {
			var uploader = $('#uploader').pluploadQueue();
			if (uploader.files.length > 0) {// 判断队列中是否有文件需要上传
				uploader.bind('StateChanged', function() {// 在所有的文件上传完毕时，提交表单
					if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
						$('fm')[0].submit();
					}
				});
				uploader.start();
			} else {
				alert('请选择至少一个文件进行上传！');
			}
			return false;
		});

	});
</script>
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}
</style>
</head>
<body>
	<div style="width: auto; height: auto">
		<table id="banner_manage"></table>
	</div>
	<script type="text/javascript">
		editing = false;
		$("#banner_manage").datagrid({
			id : 'getPager',
			title : '首页横幅列表',
			width : '100%',
			height : 500,
			singleSelect : true,
			iconCls : 'icon-edit',
			url : './banner.do?bannerList',
			method : 'get',
			columns : [ [ {
				field : 'name',
				title : '横幅名称',
				width : 200,
				editor : 'text'
			}, {
				field : 'videoUrl',
				title : '视频地址',
				width : 250,
				editor : 'text'
			}, {
				field : 'imageUrl',
				title : '横幅地址',
				width : 250,
				editor : 'text'
			}, {
				field : 'uploadDate',
				title : '上传时间',
				width : 200,
				editor : 'text'
			}
			] ],
			pagination : true,
			rownumbers : true,
			 offset: { width: -250, height: -150} ,
			toolbar : [ {
				id : 'btn_add',
				iconCls : 'icon-add',
				text : '新增',
				handler : function() {
					newUser();
				}
			}, {
				id : 'btn_edit',
				iconCls : 'icon-edit',
				text : '编辑',
				handler : function() {
					editUser();
				}
			}, {
				id : 'btn_delete',
				iconCls : 'icon-remove',
				text : '删除',
				handler : function() {
					destroyUser();
				}
			} ]
		});
		
	</script>
	<!-- //添加 -->
	<div id="dlg" class="easyui-window"
		style="width: 600px; height: 530px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">添加横幅</div>
		<form id="fm" name="fm" method="POST" novalidate>
		<input type="hidden" id="id" name="id" />
			<div class="fitem">
				<label>横幅名称:</label> <input id= "name" name="name"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>选择文件:</label><input type="hidden" id="videoUrl" name="videoUrl" />
			<div id="uploader">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
			</div>
			<div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
            <a id="dlg-buttons" href="javascript:void(0)" class="easyui-linkbutton" icon="icon-ok" onclick="saveUser()">确定</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-cancel" onclick="clearForm()">取消</a>
            </div>
		</form>
	</div>
	<script type="text/javascript">
		var url;
		function newUser() {
			$('#dlg').dialog('open').dialog('setTitle', '添加');
			$('#fm').form('clear');
			url = './banner.do?saveBanner';
		}
		function editUser() {
			var row = $('#banner_manage').datagrid('getSelected');
			 if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '编辑');
				$('#fm').form('load', row);
				url = './banner.do?saveBanner&id=' + row.id;
			} 
		}
		function saveUser() {
			$('#fm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success) {
						$('#banner_manage').datagrid('reload'); // reload the user data
						$('#dlg').dialog('close'); // close the dialog
						$.messager.show({
							title : '提示',
							msg : result.tip
						});
					} else {
						$('#dlg').dialog('close'); // close the dialog
						$('#banner_manage').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroyUser() {
			var row = $('#banner_manage').datagrid('getSelected');
			if (row) {
				$.messager.confirm('提示', '你确定要删除此条记录?',
						function(r) {
							if (r) {
								$.post('./banner.do?delete', {
									id : row.id
								}, function(result) {
									if (result.success) {
										$('#banner_manage').datagrid('reload'); // reload the user data
									} else {
										$.messager.show({ // show error message
											title : 'Error',
											msg : result.errorMsg
										});
									}
								}, 'json');
							}
						});
			}
		}
		
		
		 //关闭登录窗口
		function clearForm(){
			$('#dlg').dialog('close');
		}
	</script>

</body>
</html>

