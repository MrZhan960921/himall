<%@ page language="java"  contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
    <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
</head>
<body>
<div class="demo-info" style="margin-bottom:10px">
    <div class="demo-tip icon-tip">&nbsp;</div>
    <div>Click the node expand button to load its child nodes.</div>
</div>
<table id="tt" toolbar="#tb" title="Products" class="easyui-treegrid" style="width:700px;height:300px"
       data-options="
				url: 'http://localhost:8080/manage/category/getChild.do',
				rownumbers: true,
				pagination: true,
				pageSize: 2,
				pageList: [2,10,20],
				idField: 'id',
				treeField: 'name',
				onBeforeLoad: function(row,param){
					if (!row) {	// load top level rows
						param.id = 0;	// set id=0, indicate to load new page rows
					}
				}
			">
    <thead>
    <tr>
        <th field="id" width="250">Name</th>
        <th field="name" width="100" >Quantity</th>
        <th field="createTime" width="150"  >Price</th>
        <th field="updateTime" width="150"  >Total</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCategory()">Add</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="editCategory()">Edit</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:alert('Save')">Save</a>
</div>


<div id="win" class="easyui-window" title="Login" style="width:300px;height:180px;" closed="true">
    <form id="ff" style="padding:10px 20px 10px 40px;" action="">
        <select id="cc" class="easyui-combotree" style="width:200px;"
                data-options="url:'http://localhost:8080/manage/category/combotree.do',required:true">
        </select>
        <p>Name: <input type="text" name="name"></p>
        <tr>
            <td></td>
            <td><input type="submit" value="Submit"></input></td>
        </tr>
    </form>
</div>

<div id="win2" class="easyui-window" title="Login" style="width:300px;height:180px;" closed="true">
    <form style="padding:10px 20px 10px 40px;">
        <select id="cc2" class="easyui-combotree" style="width:200px;"
                data-options="url:'http://localhost:8080/manage/category/combotree.do',required:true,multiple: true">
        </select>

        <p>Name: <input id="categoryName" class="easyui-textbox" type="text" name="name"  required="true" validType="length[1,4]"></p>
        <tr>
            <td></td>
            <td> <input type="submit" value="Submit"></td>
        </tr>
    </form>
</div>

</body>
<script>
function addCategory() {
    $('#win').window('open');
}
var categoryId;
function editCategory() {
    $('#win2').window('open');
    var row = $('#tt').datagrid('getSelected');
    categoryId=row.id;
    alert(categoryId);
    $('#categoryName').val(row.name);

    $.ajax({
        url: "http://localhost:8080/manage/category/get_parent.do?id="+row.id,
        success: function(data){
            $('#cc2').combotree('setValue', data.name);
        }});
}


    $("#ff").form({
        url:"http://localhost:8080/manage/category/update_category.do?id="+categoryId,
        onSubmit:function(){
            return $(this).form('validate');
        },
        success:function(data){
            $.messager.alert('Info', data, 'info');
        }
    });

</script>
</html>