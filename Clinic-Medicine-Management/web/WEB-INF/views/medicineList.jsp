<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/4
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>用户列表</h1>
    <s:if test="#request.medicineList == null || #request.medicineList.size() == 0">
        没有用户信息
    </s:if>
    <s:else>
        <table border="1">
            <tr>
                <td>ID</td>
                <td>药品编码</td>
                <td>药品名称</td>
                <td>规格</td>
                <td>类别</td>
                <td>采购价</td>
                <td>销售价</td>
                <td>生产厂家</td>
                <td>状态</td>
                <td>创建时间</td>
                <td>操作</td>
            </tr>
            <s:iterator value="#request.medicineList">
                <tr>
                    <td>${id}</td>
                    <td>${medicineNo}</td>
                    <td>${medicineName}</td>
                    <td>${medicineSpecifications}</td>
                    <td>${prescriptionType.prescriptionTypeName}</td>
                    <td>${purchasePrice}</td>
                    <td>${retailPrice}</td>
                    <td>${manufacturer.name}</td>
                    <td>${medicineStatus}</td>
                    <td>${createTime}</td>
                </tr>
            </s:iterator>
        </table>
    </s:else>
</body>
</html>
