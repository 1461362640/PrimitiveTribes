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
		<table id="permission_manage"></table>
	</div>
	
	<!-- //添加 -->
	<div id="dlg" class="easyui-window"
		style="width: 400px; height: 260px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">权限管理</div>
		<form id="fm" name="fm" method="POST" novalidate>
			<input type="hidden" id="id" name="id" />
			<input type="hidden" id="menuIds" name="menuIds" />
			<input type="hidden" id="userIds" name="userIds" />
			<div class="fitem">
				<label>权限组名称:</label><input id="name" name="name"
					class="easyui-validatebox" required="true">
			</div>
			<p></p>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="dlg-buttons" href="javascript:void(0)"
					class="easyui-linkbutton" icon="icon-ok" onclick="savePermission()">确定</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					icon="icon-cancel" onclick="clearForm()">取消</a>
			</div>
		</form>
	</div>
	<!-- 权限分配 -->
	<div id="reload_pre" class="easyui-window" collapsible="false"
		minimizable="false" maximizable="false"
		style="width: 850px; height: 600px; padding: 10px 20px" closed="true"
		buttons="#per-buttons">
		<div id="pannel" class="easyui-layout"
			style="width: 750px; height: 500px;">
			<div data-options="region:'west',iconCls:'',title:'选择栏目'"
				style="width: 300px;">
				<ul id="datTree"></ul>
			</div>
			
			<div data-options="region:'east',iconCls:'',title:'选择用户组'"
				style="width: 400px;">
				<table id="userData"></table>
			</div>
			<divdata-options="region:'center',title:'center title'" style="padding:5px;background:#eee;"></div>
			<p></p>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="per-buttons" href="javascript:void(0)"
					class="easyui-linkbutton" icon="icon-ok" onclick="saveRule()">确定</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					icon="icon-cancel" onclick="clearForm()">取消</a>
			</div>
		</div>
	</div>
	
<div id="searchbar">
                    <input id="topSearchbox" name="topSearchbox" class="easyui-searchbox" data-options="width: 350, height: 26, prompt: '请输入您要查找的内容关键词', menu: '#topSearchboxMenu'" />
                    <div id="topSearchboxMenu" style="width: 85px;">
                        <div data-options="name:'0', iconCls: 'icon-hamburg-zoom'">查询类型</div>
                        <div data-options="name:'username'">昵称</div>
                        <div data-options="name:'name'">真实姓名</div>
                    </div>
                </div>
	<script type="text/javascript">
	var treeNo=[];
	var userNo=[];
		editing = false;
		$("#permission_manage").datagrid({
			id : 'getPager',
			title : '权限组管理',
			width : '100%',
			height : 500,
			singleSelect : true,
			iconCls : 'icon-edit',
			url : './permission.do?permissionList',
			method : 'get',
			onLoadSuccess:function(data){
				initParams(data);
			},
			columns : [ [ {
				field : 'name',
				title : '权限组名称',
				width : 200,
				editor : 'text'
			}, {
				field : 'menuIds',
				title : '栏目组id',
				width : 250,
				editor : 'text'
			}, {
				field : 'userIds',
				title : '用户组id',
				width : 250,
				editor : 'text'
			} ] ],
			pagination : true,
			rownumbers : true,
			offset : {
				width : -250,
				height : -150
			},
			toolbar : [ {
				id : 'btn_add',
				iconCls : 'icon-add',
				text : '新增',
				handler : function() {
					newPermission();
				}
			}, {
				id : 'btn_edit',
				iconCls : 'icon-edit',
				text : '编辑',
				handler : function() {
					editPermission();
				}
			}, {
				id : 'btn_delete',
				iconCls : 'icon-remove',
				text : '删除',
				handler : function() {
					destroyPermission();
				}
			}, {
				id : 'btn_reload',
				iconCls : 'icon-reload',
				text : '分配权限',
				handler : function() {
					reloadPermission();
				}
			} ]
		});
		
		function initParams(data){
			//选中后的树的id
			treeNo=data.rows[0].menuIds.split(",");
			//选中的人
			userNo=data.rows[0].userIds.split(",");
		}

		//栏目组的树结构
		/*  $(function(){
				$('#trees').tree({    
				    url: './menu.do?loadMenu',    
				    loadFilter: function(data){    
				        if (data.d){    
				            return data.d;    
				        } else {    
				            return data;    
				        }    
				    }    
				});  
			}); */
 
		var url;
		function newPermission() {
			$('#dlg').dialog('open').dialog('setTitle', '添加');
			$('#fm').form('clear');
			url = './permission.do?savePermission';
		}
		function editPermission() {
			var row = $('#permission_manage').datagrid('getSelected');
			if (row) {
				$('#menuIds').val(row.menuIds);
				$('#userIds').val(row.userIds);
				$('#dlg').dialog('open').dialog('setTitle', '编辑');
				$('#fm').form('load', row);
				url = './permission.do?savePermission&id=' + row.id;
			}
		}
		function savePermission() {
			$('#fm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success) {
						$('#permission_manage').datagrid('reload'); // reload the user data
						$('#dlg').dialog('close'); // close the dialog
						$.messager.show({
							title : '提示',
							msg : result.tip
						});
					} else {
						$('#dlg').dialog('close'); // close the dialog
						$('#permission_manage').datagrid('reload'); // reload the user data
					}
				}
			});
		}

		
		
		//权限分配
		function reloadPermission() {
			var row = $('#permission_manage').datagrid('getSelected');
			if (row) {
				//选中后的树的id
				treeNo=row.menuIds.split(",");
				$('#reload_pre').dialog('open').dialog('setTitle', '分配权限');
				$('#permiss').form('load', row);
				url = './permission.do?saveRule&id=' + row.id;
			}
		}

		function destroyPermission() {
			var row = $('#permission_manage').datagrid('getSelected');
			if (row) {
				$.messager.confirm('提示', '你确定要删除此条记录?', function(r) {
					if (r) {
						$.post('./permission.do?delete', {
							id : row.id
						}, function(result) {
							if (result.success) {
								$('#permission_manage').datagrid('reload'); // reload the user data
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
			$('#reload_pre').dialog('close');
		}
		$(function() {
			//加载树形菜单
			$("#datTree").tree({
				url : "./permission.do?loadMenu",
				animate : true,
				checkbox : true,
				lines : true,
				loadFilter : function(rows) {
					return convert(rows);
				}
			});

			//加载用户列表 
			$('#userData').datagrid({
				url : './permission.do?userDatas',
				autoRowHeight: true,
				toolbar:'#searchbar',
				striped:true,
				idField:'recordIndex',
				sortName:'name',
				multiSort:true,
				showHeader:true,
				pagination:true,
				rownumbers:true,
				checkOnSelect:true,
				height : 440,
				onLoadSuccess:function(data){
					console.log(data);
					userCheck(data);
				},
				columns : [ [  {
					field:'ck',
					checkbox:true
					},{
					field : 'username',
					title : '昵称',
					sortable:true,
					width : 100
				}, {
					field : 'name',
					title : '真实名称',
					sortable:true,
					width : 100
				}
				 ] ]
			});
		});
		/////////////////////////////////////////////////////////////
		//用户列表
		function userCheck(data){
			for(var i=0;i<data.rows.length;i++){
				//console.log(data.rows[i]);
				if(userNo.toString().indexOf(data.rows[i].recordIndex) != -1){
					$("[name='ck']").eq(i).attr("checked","checked");
				}
			}
		}
		
		
		///////////////////////////////////////////////////////////
		//栏目树
		function convert(rows) {
			var nodes = [];
			// get the top level nodes以下原始代码
					//console.log(rows.length);
			for (var i = 0; i < rows.length; i++) {
				//console.log(treeNo[i]);
				var row = rows[i];
				//rows, row.parentId
				if (!exists(rows, row.parentId)) {
					 //遍历已经选中的节点
						 //判断是否选中
						// console.log(flag);
					 nodes.push({
							id : row.id,
							text : row.name
							//checked:true//选中效果
						});
				}
			}
			var toDo = [];
			for (var i = 0; i < nodes.length; i++) {
				toDo.push(nodes[i]);
			}
			while (toDo.length) {
				var node = toDo.shift(); // the parent node
				// get the children nodes
				for (var i = 0; i < rows.length; i++) {
					var row = rows[i];
					if (row.parentId == node.id) {
						var child ;
						if (node.children) {
							//console.log(row.id+"#"+row.name+"#"+(treeNo.toString().indexOf(row.id) > -1));
							if(treeNo.toString().indexOf(row.id) > -1) {
								 child = {
											id : row.id,
											text : row.name,
											checked:true
										};
							 }else{
								 child = {
											id : row.id,
											text : row.name
										};
							 }
							node.children.push(child);
						} else {
							 child = {
										id : row.id,
										text : row.name
									};
							node.children = [ child ];
						}
						toDo.push(child);
					}
				}
			}
			return nodes;
		}
		//判断是否选中节点(原始实现)
		function exists(rows, parentId) {
			for (var i = 0; i < rows.length; i++) {
				if (rows[i].id == parentId){
					return true;
				}
			}
			return false;
		}

		//保存规则权限
		function saveRule() {
			var menuNodes = $('#datTree').tree('getChecked'); // get checked nodes
			var menuSolidNodes = $('#datTree').tree('getChecked','indeterminate');//solid checked nodes
			//栏目组的id
			var nodeData = [];
			for (var i = 0; i < menuNodes.length; i++) {
				nodeData.push(menuNodes[i].id);
			}
			for (var i = 0; i < menuSolidNodes.length; i++) {
				console.log(menuSolidNodes[i].id);
				nodeData.push(menuSolidNodes[i].id);
			}
			//选中的用户
			var userRows = $('#userData').datagrid('getChecked');
			console.log(userRows);
			var rowData = [];
			for (var i = 0; i < userRows.length; i++) {
				rowData.push(userRows[i].recordIndex);
			}
			//发送到后台
			$.post(url,{'menuIds':nodeData+"",'userIds':rowData+""},function(result){
				if (result.success) {
					$('#banner_manage').datagrid('reload'); // reload the user data
					$('#dlg').dialog('close'); // close the dialog
					$.messager.show({
						title : '提示',
						msg : result.tip
					});
					$('#reload_pre').dialog('close'); // close the dialog
					$('#permission_manage').datagrid('reload'); // reload the user data
				} else {
					$('#reload_pre').dialog('close'); // close the dialog
					$('#permission_manage').datagrid('reload'); // reload the user data
				}
			},'json');
		}
	</script>

</body>
</html>

