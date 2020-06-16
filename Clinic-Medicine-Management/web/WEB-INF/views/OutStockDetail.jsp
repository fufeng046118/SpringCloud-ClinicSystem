<%--
  Created by IntelliJ IDEA.
  User: moumoumou
  Date: 2020/6/9
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .container ul li{
            display: inline-block;
            width: 250px;
        }
    </style>
</head>
<body>
<%@ include file="common.jsp"%>
<div>
    <button style="float: right" id="comback" class="btn btn-primary" onclick="history.back()" data-loading-text="Loading..."
            type="button">返回
    </button>
<div class="jumbotron">
    <div class="container">
        <ul style="list-style: none">
            <li>出库订单:${outstockdetail.outstockno} </li>
            <%--<li>供应商:${outstockdetail.manufacturer.manufacturername} </li>--%>
            <li>审核日期:${outstockdetail.auditdate}</li>
            <li>出库备注:${outstockdetail.mark}</li>
        </ul>
        <ul style="list-style: none">
            <li>出库日期:${outstockdetail.outdate}</li>
            <li>制单日期:${outstockdetail.outdate}</li>
            <li>审核人员:${outstockdetail.audit.eName}</li>
        </ul>
        <ul style="list-style: none">
            <li>出库人员:${outstockdetail.employee.eName} </li>
            <li>制单人员:${outstockdetail.makeorder.eName}</li>
            <li>出库类型:${outstockdetail.outstocktype.typename}</li>
        </ul>
    </div>
</div>
    <table class="table table-hover">
        <thead align="center">
        <tr id="tr">
            <th>序号</th>
            <th>药品编码</th>
            <th>药品名称</th>
            <th>生产厂家</th>
            <th>数量</th>
            <th>单位</th>
            <th>采购价</th>
            <th>零售价</th>
            <th>批号</th>
            <th>药品有效期</th>
            <th>采购金额</th>
            <th>零售金额</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="#outstockmedicine" var="g">
            <tr class="tr">
                <td><s:property value="#g.id"/> </td>
                <td><s:property value="#g.medicine.medicineno"/></td>
                <td><s:property value="#g.medicine.medicinename"/></td>
                <td><s:property value="#g.medicine.manufacturer.manufacturername"/> </td>
                <td><s:property value="#g.count"/></td>
                <td>g/袋</td>
                <td><s:property value="#g.medicine.purchaseprice"/> </td>
                <td><s:property value="#g.medicine.price"/></td>
                <td><s:property value="#g.lotnumber"/></td>
                <td><s:property value="#g.expirationdate"/></td>
                <td><s:property value="#g.purchaseprice"/></td>
                <td><s:property value="#g.price"/></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    采购金额合计：<label id="price1" style="color: red">10000</label>元
    零售金额合计：<label id="price2" style="color: red">20000</label>元
    <script>
        $(function () {
            var price1=0;
            var price2=0;
            $(".tr").each(function (i) {
                price1+=parseFloat($(this).find("td:eq(10)").html());
                price2+=parseFloat($(this).find("td:eq(11)").html());
            });
            $("#price1").html(price1);
            $("#price2").html(price2)
        })

    </script>
</div>
</body>
</html>
