<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <c:forEach items="${thirdMenuList }" var="thirdMenu">
   <a href="#" class="easyui-linkbutton" iconCls="${thirdMenu.icon }" onclick="${thirdMenu.url}" plain="true">${thirdMenu.name }</a>
</c:forEach>  
  <!--  <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
   <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEdit()" plain="true">修改</a>
   <a href="#" class="easyui-linkbutton" iconCls="icon-delete" onclick="remove()" plain="true">删除</a>
   <a href="#" class="easyui-linkbutton" iconCls="icon-add1" onclick="openAddMenu()" plain="true">添加按钮</a>
   <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="window.location.reload()" plain="true">刷新</a> -->