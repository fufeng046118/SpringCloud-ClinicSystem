<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/9
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="https://cdn.bootcss.com/smalot-bootstrap-datetimepicker/2.4.4/js/locales/bootstrap-datetimepicker.zh-CN.js" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/jine.js"></script>
    <style>
        .jumbotron ul{
            list-style: none;
        }
        .jumbotron ul li{
            display: inline-block;
            width: 240px;
            margin-right: 10px;
            font-size: 15px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default" role="navigation" style="margin-bottom: 50px">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#example-navbar-collapse">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar">ss</span>
                <span class="icon-bar">s</span>
                <span class="icon-bar">ss</span>
            </button>
            <a class="navbar-brand" href="medicine">入库管理</a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        Operations<b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">入库管理</a></li>
                        <li class="divider"></li>
                        <li><a href="#">出库管理</a></li>
                        <li class="divider"></li>
                        <li><a href="#">库存管理</a></li>
                        <li class="divider"></li>
                        <li><a href="#">库存盘点</a></li>
                        <li class="divider"></li>
                        <li><a href="#">药品调价</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="jumbotron">
        <ul>
            <li>入库订单：${request.inStock.inStockNo}</li>
            <li>入库日期：${request.inStock.createDate}</li>
            <li style="width: 200px;margin-left: 30px">入库人员：${request.inStock.employee.eName}</li>
            <li style="width: 200px;">入库类型：${request.inStock.instocktype.typeName}</li>
        </ul>
        <ul>
            <li>供应商：${request.inStock.manufacturer.name}</li>
            <li>制单日期：${request.inStock.createDate}</li>
            <li style="width: 200px;margin-left: 30px">制单人员：${request.inStock.makeOrder.eName}</li>
            <li style="width: 200px">入库备注：${request.inStock.mark}</li>
        </ul>
        <s:if test="#request.inStock.statusId != 1">
            <ul>
                <li>审核日期：${request.inStock.auditDate}</li>
                <li>审核人员：${request.inStock.audit.eName}</li>
            </ul>
        </s:if>

    </div>
</div>
<table class="table table-striped">
    <thead>
    <tr>
        <td>序号</td>
        <td>药品编码</td>
        <td>药品名称</td>
        <td>生产厂家</td>
        <td>数量</td>
        <td>单位</td>
        <td>采购价</td>
        <td>零售价</td>
        <td>批号</td>
        <td>药品有效期</td>
        <td>采购金额</td>
        <td>零售金额</td>
    </tr>
    </thead>
    <tbody id="x1">
    <s:if test="#request.inStockMedicineList == null || #request.inStockMedicineList.size() == 0">
        <tr>
            <td colspan="12" align="center"><h2>没有该出库药品信息</h2></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="#request.inStockMedicineList" var="inStockMedicine">
            <tr>
                <td>${inStockMedicine.id}</td>
                <td>${inStockMedicine.medicine.medicineNo}</td>
                <td>${inStockMedicine.medicine.medicineName}</td>
                <td>${inStockMedicine.medicine.manufacturer.name}</td>
                <td>${inStockMedicine.count}</td>
                <td>盒</td>
                <td>${inStockMedicine.medicine.purchasePrice}</td>
                <td>${inStockMedicine.medicine.retailPrice}</td>
                <td>${inStockMedicine.lotNumber}</td>
                <td>${inStockMedicine.expirationDate}</td>
                <td>${inStockMedicine.purchasePrice}</td>
                <td>${inStockMedicine.price}</td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div id="jine">
    <span style="font-size: 18px;display: inline-block;margin-right: 30px"></span>
    <span style="font-size: 18px"></span>
</div>

    <div style="padding-top: 20px;padding-left: 20px">
        <button type="button" class="btn btn-primary" onclick="history.back()" style="margin-right: 10px">返回</button>
        <s:if test="#request.inStock.statusId == 1">
            <button type="button" class="btn btn-primary" onclick="location.href='updateStatus?id=${request.inStock.id}&status=2'" style="margin-right: 10px">审核通过</button>
            <button type="button" class="btn btn-primary" onclick="location.href='updateStatus?id=${request.inStock.id}&status=3'">审核不通过</button>
        </s:if>
    </div>
</body>
</html>
