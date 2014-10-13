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
		<table id="links_manage"></table>
	</div>
	<script type="text/javascript">
		editing = false;
		$("#links_manage").datagrid({
			id : 'getPager',
			title : '链接列表',
			height : 500,
			iconCls : 'icon-edit',
			url : './links.do?linksList',
			method : 'get',
			idField: 'id',
			frozenColumns: [[
                { field: 'ck', checkbox: true },
                { field: 'id', title: 'ID', width: 220, sortable: true }
            ]],
			columns : [ [ {
				field : 'title',
				title : '链接标题',
				width : 200,
				editor : 'text'
			}, {
				field : 'href',
				title : '链接地址',
				width : 250,
				editor : 'text'
			}, {
				field : 'bold',
				title : '字体加粗',
				width : 250,
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
					newLink();
				}
			}, {
				id : 'btn_edit',
				iconCls : 'icon-edit',
				text : '编辑',
				handler : function() {
					editLink();
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
		style="width: 600px; height: 530px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">添加链接</div>
		<form id="fm" name="fm" method="POST" novalidate>
		<input type="hidden" id="id" name="id" />
			<div class="fitem">
				<label>链接标题:</label> <input id= "title" name="title"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>链接地址:</label> <input id= "href" name="href"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>是否加粗:</label> <input id= "bold" name="bold" type="checkbox"
					class="easyui-validatebox">是否加粗
			</div>
			<div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
            <a id="dlg-buttons" href="javascript:void(0)" class="easyui-linkbutton" icon="icon-ok" onclick="saveLink()">确定</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-cancel" onclick="clearForm()">取消</a>
            </div>
		</form>
	</div>
	<script type="text/javascript">
		var url;
		function newLink() {
			$('#dlg').dialog('open').dialog('setTitle', '添加');
			$('#fm').form('clear');
			url = './links.do?saveLinks';
		}
		function editLink() {
			var row = $('#links_manage').datagrid('getSelected');
			 if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '编辑');
				$('#fm').form('load', row);
				url = './links.do?saveLinks&id=' + row.id;
			} 
		}
		function saveLink() {
			$('#fm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success) {
						$('#links_manage').datagrid('reload'); // reload the user data
						$('#dlg').dialog('close'); // close the dialog
						$.messager.show({
							title : '提示',
							msg : result.tip
						});
					} else {
						$('#dlg').dialog('close'); // close the dialog
						$('#links_manage').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroy() {
			var rows = $('#links_manage').datagrid('getSelections');
			var ids="";
			//console.log(rows.length);
			for(var i=0; i<rows.length; i++){
					ids+=","+rows[i].id;
			}
			ids=ids.replace('undefined','').substring(1);
			if (rows.length>0) {
				$.messager.confirm('提示', '你确定要删除此条记录?',
						function(r) {
							if (r) {
								$.post('./links.do?delete', {
									'ids' : ids
								}, function(result) {
									if (result.success) {
										$('#links_manage').datagrid('reload'); // reload the user data
									} else {
										$.messager.show({ // show error message
											title : 'Error',
											msg : result.errorMsg
										});
									}
								}, 'json');
							}
						});
			}else{
				$.messager.show({title : '提示',msg : '请选择您要删除的记录!'});
			}
		}
		
		
		 //关闭登录窗口
		function clearForm(){
			$('#dlg').dialog('close');
		}
	</script>

</body>
</html>

