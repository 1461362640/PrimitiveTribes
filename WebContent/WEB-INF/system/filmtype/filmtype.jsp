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
		<table id="filmtype"></table>
	</div>
	<script type="text/javascript">
		editing = false;
		$("#filmtype").datagrid({
			id : 'getPager',
			title : '类型列表',
			width : '100%',
			height : 500,
			singleSelect : true,
			iconCls : 'icon-edit',
			url : './filmtype.do?filmTypeList',
			method : 'get',
			columns : [ [ {
				field : 'text',
				title : '类型名称',
				width : 200,
				editor : 'text'
			}, {
				field : 'descs',
				title : '视频简介',
				width : 700,
				editor : 'text'
			} ] ],
			pagination : true,
			rownumbers : true,
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
		/* var p = $('#filmtype').datagrid('getPager');
		if (p) {
			$(p).pagination({
				onBeforeRefresh : function() {
					//$.messager.alert('数据刷新', '数据刷新');
				}
			});
		} */
	</script>
	<!-- //添加 -->
	<div id="dlg" class="easyui-window"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">添加类型</div>
		<form id="fm" name="fm" method="post"
			action="./filmtype.do?saveFilmType" novalidate>
			<div class="fitem">
				<label>视频类型名称:</label> <input id="text" name="text"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>描述:</label>
				<textarea id="descs" name="descs" class="easyui-validatebox"></textarea>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="dlg-buttons" href="javascript:void(0)"
					class="easyui-linkbutton" icon="icon-ok" onclick="saveUser()">确定</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					icon="icon-cancel" onclick="clearForm()">取消</a>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var url;
		function newUser() {
			$('#dlg').dialog('open').dialog('setTitle', '添加');
			$('#fm').form('clear');
			url = './filmtype.do?saveFilmType';
		}
		function editUser() {
			var row = $('#filmtype').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '编辑');
				$('#fm').form('load', row);
				url = './filmtype.do?saveFilmType&id=' + row.id;
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
					if (result.errorMsg) {
						$.messager.show({
							title : 'Error',
							msg : result.errorMsg
						});
					} else {
						$('#dlg').dialog('close'); // close the dialog
						$('#filmtype').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroyUser() {
			var row = $('#filmtype').datagrid('getSelected');
			if (row) {
				$.messager.confirm('提示', '你确定要删除此条记录?', function(r) {
					if (r) {
						$.post('./filmtype.do?delete', {
							id : row.id
						}, function(result) {
							if (result.success) {
								$('#filmtype').datagrid('reload'); // reload the user data
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
		function clearForm() {
			$('#dlg').dialog('close');
		}
	</script>

</body>
</html>

