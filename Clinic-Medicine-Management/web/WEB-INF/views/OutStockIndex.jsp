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
<button style="float: right" id="add-btn" class="btn btn-primary" onclick="location.href='toaddoutstock'" data-loading-text="Loading..."
        type="button">新增入库
</button>
<div align="center">
    <form id="frm" action="outstock" method="post">
        审核状态:<select class="form-control" style="display: inline-block;width: 130px" name="outstockVo.statusId">
        <option value="">全部</option>
        <option value="3" <s:if test="outstockVo.statusId==3">selected</s:if>>未审核</option>
        <option value="1" <s:if test="outstockVo.statusId==1">selected</s:if>>审核通过</option>
        <option value="2" <s:if test="outstockVo.statusId==2">selected</s:if>>审核不通过</option>
    </select>
        <input type="hidden" name="pageNo" value="${pageNo}"/>
        出库类型:<select class="form-control" style="display: inline-block;width: 130px;" name="outstockVo.typeId">
        <option value="">全部</option>
        <s:iterator value="#typeList">
            <option value="${id}" <s:if test="outstockVo.typeId==id">selected</s:if>>${typename}</option>
        </s:iterator>

    </select>
        <input type="text" class="form-control" style="display: inline-block;width: 130px;" value="${outstockVo.outstockno}" placeholder="输入入库单号" name="outstockVo.outstockno"/>
        <button id="fat-btn" class="btn btn-primary" data-loading-text="Loading..."
                type="button">查询
        </button>
    </form>
</div>
<table class="table table-hover">
    <thead align="center">
    <tr>
        <th>出库单号</th>
        <th>出库类型</th>
        <th>制单人员</th>
        <th>采购金额</th>
        <th>零售金额</th>
        <th>出库人员</th>
        <th>创建时间</th>
        <th>审核状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="#outStockList" var="g">
        <tr>
            <td><s:property value="#g.outstockno"/></td>
            <td><s:property value="#g.outstocktype.typename"/></td>
            <td><s:property value="#g.makeorder.eName"/> </td>
            <td><s:property value="#g.purchaseprice"/></td>
            <td><s:property value="#g.price"/> </td>
            <td><s:property value="#g.employee.eName"/></td>
            <td><s:date name="#g.outdate" format="yyyy-MM-dd"/></td>
            <td><s:if test="#g.statusid==1">审核通过</s:if><s:if test="#g.statusid==2">审核不通过</s:if><s:if test="#g.statusid==3">待审核</s:if></td>
            <td>
                <s:if test="#g.statusid==2||#g.statusid==1">
                    <button type="button"onclick="location.href='outstockdetail?outstockVo.geo=${g.id}'" class="btn btn-primary btn-sm ">查看</button>|
                </s:if>
                <s:if test="#g.statusid==3">
                    <button type="button" onclick="location.href='toupdateoutstock?outstockVo.geo=${g.id}'" class="btn btn-primary btn-sm">编辑</button>|
                </s:if>
                <button type="button" onclick="location.href='deloutstock?outstockVo.geo=${g.id}'" class="btn btn-primary btn-sm ">删除</button> |
                <s:if test="#g.statusid==1||#g.statusid==2">
                    <button type="button" onclick="location.href='addagainoutstock?outstockVo.geo=${g.id}'" class="btn btn-primary btn-sm ">再次出库</button>
                </s:if>
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
        $("#fat-btn").click(function(){
            $(this).button('loading').delay(200).queue(function() {
                $("#frm").submit();
            });

        });
    });
    function doPage(pageNo) {
        $("[name=pageNo]").val(pageNo);
        $("#frm").submit();
    }

</script>
</body>
</html>