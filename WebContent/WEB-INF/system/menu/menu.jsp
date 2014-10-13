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
		<table id="banner_manage"></table>
	</div>
	<script type="text/javascript">
		editing = false;
		$("#banner_manage").datagrid({
			id : 'getPager',
			title : '栏目管理',
			width : '100%',
			height : 500,
			iconCls : 'icon-edit',
			url : './menu.do?menuList',
			method : 'get',
			idField: 'id',
			frozenColumns: [[
                { field: 'ck', checkbox: true },
                { field: 'id', title: 'ID', width: 220, sortable: true }
            ]],
			columns : [ [ {
				field : 'menuname',
				title : '栏目名称',
				width : 100,
				editor : 'text'
			}, {
				field : 'icon',
				title : '栏目图标',
				width : 100,
				editor : 'text'
			}, {
				field : 'menuId',
				title : '父栏目',
				width : 100,
				editor : 'text'
			}, {
				field : 'subId',
				title : '子栏目',
				width : 100,
				editor : 'text'
			}, {
				field : 'url',
				title : '栏目地址',
				width : 200,
				editor : 'text'
			}, {
				field : 'menuIndex',
				title : '顺序',
				width : 150,
				editor : 'text',
				sortable: true
			}, {
				field : 'recordIndex',
				title : '栏目索引',
				width : 150,
				editor : 'text'
			}, {
				field : 'commitTime',
				title : '添加时间',
				width : 150,
				editor : 'text',formatter:function(value,row,index){  
                    var unixTimestamp = new Date(value);  
                    return unixTimestamp.toLocaleString();  
                    }  
				
			} ] ],
			pagination : true,
			rownumbers : true,
			toolbar : [ {
				id : 'btn_add',
				iconCls : 'icon-add',
				text : '新增',
				handler : function() {
					newMenu();
				}
			}, {
				id : 'btn_edit',
				iconCls : 'icon-edit',
				text : '编辑',
				handler : function() {
					editMenu();
				}
			}, {
				id : 'btn_delete',
				iconCls : 'icon-remove',
				text : '删除',
				handler : function() {
					destroy();
				}
			} ],
			 enableHeaderClickMenu: true,        //此属性开启表头列名称右侧那个箭头形状的鼠标左键点击菜单
            enableHeaderContextMenu: true,      //此属性开启表头列名称右键点击菜单
            enableRowContextMenu: false
		});  
		 
	</script>
	<!-- //添加 -->
	<div id="dlg" class="easyui-window"
		style="width: 500px; height: 340px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">添加栏目</div>
		<form id="fm" name="fm" method="POST" novalidate>
			<input type="hidden" id="id" name="id" /> <input type="hidden"
				id="recordIndex" name="recordIndex" value="0" />
			<div class="fitem">
				<label>栏目名称:</label> <input id="menuname" name="menuname"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>栏目图标:</label> <input id="icon" name="icon"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>栏目地址:</label> <input id="url" name="url"
					class="easyui-validatebox">
			</div>
			<div class="fitem">
				<label>父栏目:</label> <select id="menuId" name="menuId"
					class="easyui-combotree"
					data-options="url:'./menu.do?loadingMenu'," style="width: 160px;"></select>
			</div>
			<!-- <div class="fitem">
				<label>子栏目:</label> <input id= "subId" name="subId"
					class="easyui-validatebox" required="true">
			</div> -->
			<div class="fitem">
				<label>顺序:</label> <input id="menuIndex" name="menuIndex"
					class="easyui-validatebox" required="true">
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="dlg-buttons" href="javascript:void(0)"
					class="easyui-linkbutton" icon="icon-ok" onclick="saveMenu()">确定</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					icon="icon-cancel" onclick="clearForm()">取消</a>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var url;
		function newMenu() {
			$('#dlg').dialog('open').dialog('setTitle', '添加');
			$('#fm').form('clear');
			url = './menu.do?saveMenu';
		}
		function editMenu() {
			var row = $('#banner_manage').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '编辑');
				$('#fm').form('load', row);
				$('#recordIndex').val(row.recordIndex);
				url = './menu.do?saveMenu&id=' + row.id;
			}
		}
		function saveMenu() {
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
		function destroy() {
			var row = $('#banner_manage').datagrid('getSelected');
			if (row) {
				$.messager.confirm('提示', '你确定要删除此条记录?', function(r) {
					if (r) {
						$.post('./menu.do?delete', {
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
		function clearForm() {
			$('#dlg').dialog('close');
		}
	</script>


</body>
</html>