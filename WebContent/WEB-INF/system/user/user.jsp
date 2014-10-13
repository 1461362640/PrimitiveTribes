<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="./source/easyui/themes/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"	href="./source/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"	href="./source/easyui/themes/icon.css" />

<script type="text/javascript" src="./source/easyui/jquery.min.js"></script>
<script type="text/javascript" src="./source/easyui/jquery.easyui.min.js"></script>
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
		<table id="user_manage"></table>
	</div>
	<script type="text/javascript">
		editing = false;
		$("#user_manage").datagrid({
			id : 'getPager',
			title : '用户管理',
			width : '100%',
			height : 500,
			singleSelect : true,
			iconCls : 'icon-edit',
			url : './user.do?userList',
			method : 'get',
			columns : [ [ {
				field : 'username',
				title : '昵称',
				width : 100,
				editor : 'text'
			}, {
				field : 'email',
				title : '邮箱',
				width : 100,
				editor : 'text'
			}, {
				field : 'mobilephone',
				title : '手机号',
				width : 200,
				editor : 'text'
			}, {
				field : 'name',
				title : '真实姓名',
				width : 150,
				editor : 'text'
			}, {
				field : 'sex',
				title : '性别',
				width : 50,
				editor : 'text'
			}, {
				field : 'recordIndex',
				title : '记录索引',
				width : 50,
				editor : 'text'
			}, {
				field : 'registerDate',
				title : '注册时间',
				width : 150,
				editor : 'text'
			}, {
				field : 'modifyDate',
				title : '修改时间',
				width : 150,
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
					destroy();
				}
			} ]
		});

	</script>
	<!-- //添加 -->
	<div id="dlg" class="easyui-window"
		style="width: 500px; height:340px; padding: 10px 20px" closed="true" buttons="#dlg-buttons">
		<div class="ftitle">添加用户</div>
		<form id="fm" name="fm" method="POST" novalidate>
		<input type="hidden" id="id" name="id" />
		<input type="hidden" id="recordIndex" name="recordIndex"  />
			<div class="fitem">
				<label>昵称:</label> <input id= "username" name="username"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>邮箱:</label> <input id= "email" name="email"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>手机号:</label> <input id= "mobilephone" name="mobilephone"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>真实姓名:</label> <input id= "name" name="name"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>性别:</label> <input id= "sex" name="sex"
					class="easyui-validatebox" required="true">
			</div>
			<p></p>
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
			url = './user.do?saveUser';
		}
		function editUser() {
			var row = $('#user_manage').datagrid('getSelected');
			 if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '编辑');
				$('#fm').form('load', row);
				$('#recordIndex').val(row.recordIndex);
				url = './user.do?saveUser&id=' + row.id;
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
						$('#user_manage').datagrid('reload'); // reload the user data
						$('#dlg').dialog('close'); // close the dialog
						$.messager.show({
							title : '提示',
							msg : result.tip
						});
					} else {
						$('#dlg').dialog('close'); // close the dialog
						$('#user_manage').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroy() {
			var row = $('#user_manage').datagrid('getSelected');
			if (row) {
				$.messager.confirm('提示', '你确定要删除此条记录?',
						function(r) {
							if (r) {
								$.post('./user.do?delete', {
									id : row.id
								}, function(result) {
									if (result.success) {
										$('#user_manage').datagrid('reload'); // reload the user data
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