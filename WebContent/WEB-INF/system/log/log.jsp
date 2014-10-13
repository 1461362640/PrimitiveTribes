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
<script type="text/javascript"
	src="./source/easyui/jquery.easyui.min.js"></script>
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
		<table id="log_manage"></table>
	</div>
	<script type="text/javascript">
		editing = false;
		$("#log_manage").datagrid({
			id : 'getPager',
			title : '系统日志',
			width : '100%',
			height : 500,
			singleSelect : true,
			sortName:'menuIndex',
			multiSort:true,
			iconCls : 'icon-edit',
			url : './log.do?logList',
			method : 'get',
			columns : [ [ {
				field : 'username',
				title : '用户名',
				width : 100,
				editor : 'text'
			}, {
				field : 'operation',
				title : '操作',
				width : 260,
				editor : 'text'
			}, {
				field : 'ip',
				title : 'ip地址',
				width : 260,
				editor : 'text'
			}, {
				field : 'loginTime',
				title : '操作时间',
				width : 300,
				editor : 'text',formatter:function(value,row,index){  
                    var unixTimestamp = new Date(value);  
                    return unixTimestamp.toLocaleString();  
                    }  
			}] ],
			pagination : true,
			rownumbers : true,
			offset : {
				width : -250,
				height : -150
			} 
		});  
		 
	</script>

</body>
</html>