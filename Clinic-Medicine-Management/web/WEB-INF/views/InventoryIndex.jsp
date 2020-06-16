<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>药品系统</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="common.jsp"%>
<div align="center">
    <form id="frm" action="findinventory" method="post">
        创建时间:
        <input name="inventoryVo.inventorydate1" id="data1" class="form-control" style="display: inline-block;width: 130px" type="date" value="<s:date name="#inventoryVo.inventorydate1" format="yyyy-MM-dd"></s:date>"/>到
        <input name="inventoryVo.inventorydate2" id="data2" class="form-control" style="display: inline-block;width: 130px" type="date" value="<s:date name="#inventoryVo.inventorydate2" format="yyyy-MM-dd"></s:date>"/>
        <input type="hidden" name="pageNo"/>
        <input name="inventoryVo.inventoryno" class="form-control" placeholder="请输入盘点单号" style="display: inline-block;width: 130px" type="text" value="${inventoryVo.inventoryno}"/>
        <button id="sub" class="btn btn-primary" data-loading-text="Loading..."
                type="button">查询
        </button>
    </form>
</div>
<table class="table table-hover">
    <thead align="center">
    <tr>
        <th>序号</th>
        <th>盘点单号</th>
        <th>盘点日期</th>
        <th>制单人</th>
        <th>盘点状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="#inventoryList" var="g">
        <tr>
            <td><s:property value="#g.id"/></td>
            <td><s:property value="#g.inventoryno"/></td>
            <td><s:date name="#g.inventorydate" format="yyyy-MM-dd"/></td>
            <td><s:property value="#g.employee.ename"/></td>
            <td><s:if test="#g.status==1">盘点完成</s:if><s:if test="#g.status==2">正在盘点</s:if> </td>
            <td>

                <button type="button"onclick="location.href='toinventorydetailbyinfo?inventoryVo.status=${g.status}&inventoryVo.geo=${g.id}'" class="btn btn-primary btn-sm ">查看</button>
                <s:if test="#g.status==2">|<button type="button" onclick="location.href='delinventory?inventoryVo.geo=${g.id}'" class="btn btn-primary btn-sm ">删除</button></s:if>
            </td>
        </tr>
    </s:iterator>
    </tbody>
</table>
<div class="container">
    <ul class="pagination pagination-lg">
        <li><a href="javascript:void(0)" onclick="doPage(${pageNo-1})">«</a></li>
        <s:iterator begin="1" end="#pageCount" var="g">
            <li><a href="javascript:void(0)" onclick="doPage(${g})"><s:property value="#g"/></a></li>
        </s:iterator>
        <li><a href="javascript:void(0)" onclick="doPage(${pageNo+1})">»</a></li>

    </ul>
</div>

<script>
    $(function(){
        $("#sub").click(function(){
                $("#frm").submit();

        });
    });
    function doPage(pageNo) {
        $("[name=pageNo]").val(pageNo);
        $("#frm").submit();
    }

</script>
</body>
</html>