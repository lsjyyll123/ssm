<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="copyright" content="lsj" />
<title>修改i密码</title>
<link rel="stylesheet" type="text/css" href="../../resources/admin/easyui/easyui/1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../../resources/admin/easyui/css/wu.css" />
<link rel="stylesheet" type="text/css" href="../../resources/admin/easyui/css/icon.css" />
<script type="text/javascript" src="../../resources/admin/easyui/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../../resources/admin/easyui/easyui/1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../resources/admin/easyui/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
<body class="easyui-layout">
<div class="easyui-layout" data-options="fit:true" style="padding: 20px;" >
	<form id="edit-form" method="post">
        <table>
             <tr>
                <td width="60" align="right">用户名:</td>
                <td><input type="text" name="username" readonly="readonly" class="wu-text easyui-validatebox" value="${admin.username }" /></td>
            </tr>
            <tr>
                <td width="60" align="right">原密码:</td>
                <td><input type="password" name="password" id="password" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写原密码'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">新密码:</td>
                <td><input type="password" id="newnewPassword" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写新密码'" /></td>
            </tr>
            <tr>
                <td width="60" align="right">重复密码:</td>
                <td><input type="password" id="reNewnewPassword" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请再次填写新密码'" /></td>
            </tr>
            <tr style="text-align:center;margin-top:20px;">
                <td colspan="2">
                <a   class="easyui-linkbutton" iconCls="icon-tick"
                 onclick="editeditPassword()">保存</a></td>
            </tr>
        </table>
    </form>
 </div>
</body>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	
	/**
	*  修改密码
	*/
	function editeditPassword(){
		 var oldP=$("#password").val();
		 var roldP="${admin.password}";
		 var newp=$("#newnewPassword").val();
		 var renewp=$("#reNewnewPassword").val();
		 
		if(oldP!=roldP){
			$("#password").val("");
			$.messager.alert("消息提醒","你输入的原密码错误!","warning");
			return;
		}
		if(newp!=renewp){
			$("#reNewnewPassword").val("")
			$.messager.alert("消息提醒","两次输入的密码不一致!","warning");
			return;
		}
		$("#password").val(renewp);
		var validate = $("#edit-dialog").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
		
		var data = $("#edit-form").serialize();
		$.ajax({
			url:'../edit_password',
			dataType:'json',
			type:'post',
			data:{newpassword:renewp,oldpassword:oldP},
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','密码修改成功！','info');
					 
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
 
</script>