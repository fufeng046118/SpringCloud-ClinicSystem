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
        #info ul li{
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
    <div id="info" class="container">
        <ul style="list-style: none">
            <li>盘点单号:${inventory.inventoryno} </li>
            <li>盘点人:${inventory.employee.eName}</li>
            <li>创建时间:${inventory.inventorydate}</li>
        </ul>
    </div>
</div>
    <div align="center">
        <form id="frm" action="toinventorydetailbyinfo" method="post">
            处方类型:<select class="form-control" name="medicineVo.prescriptionId" style="width: 150px;display: inline-block">
            <option value="">全部</option>
            <option <s:if test="#medicineVo.prescriptionId==1">selected</s:if> value="1">西/成药</option>
            <option <s:if test="#medicineVo.prescriptionId==2">selected</s:if> value="2">中药</option>
        </select>
            <input type="hidden" name="inventoryVo.status" value="${inventory.status}"/>
            <input type="hidden" name="pageNo"/>
            <input type="hidden" name="inventoryVo.geo" value="${inventory.id}"/>
            <input type="text" value="${medicineVo.mno}" class="form-control" style="width: 150px;display: inline-block" placeholder="药品名称/药品编号" name="medicineVo.mno"/>
            <button id="fat-btn" class="btn btn-primary" data-loading-text="Loading..."
                    type="button"> 查询
            </button>
        </form>
    </div>
    <table class="table table-hover">
        <thead align="center">
        <tr id="tr">
            <th>序号</th>
            <th>药品编码</th>
            <th>药品名称</th>
            <th>生产厂家</th>
            <th>处方类别</th>
            <th>规格</th>
            <th>当前库存数量</th>
            <th>盘点库存数量</th>
            <th>盘盈盘亏</th>
            <th>备注</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="#inventoryMedicine" var="g">
            <tr class="tr">
                <td><s:property value="#g.id"/> </td>
                <td><s:property value="#g.medicine.medicineNo"/></td>
                <td><s:property value="#g.medicine.medicineName"/></td>
                <td><s:property value="#g.medicine.manufacturer.name"/> </td>
                <td><s:property value="#g.medicine.prescriptionType.prescriptionTypeName"/></td>
                <td>g/袋</td>
                <td><s:property value="#g.nowstock"/></td>
                <td><s:property value="#g.inventorystock"/></td>
                <td style="color: red"><s:property value="#g.difference"/>g/袋</td>
                <td><s:property value="#g.mark"/></td>
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
        $(function () {
            $("#fat-btn").click(function(){
                $(this).button('loading').delay(200).queue(function() {
                    $("#frm").submit();
                });

            });
        })
        function doPage(pageNo) {
            $("[name=pageNo]").val(pageNo);
            $("#frm").submit();
        }

    </script>
</div>
</body>
</html>
