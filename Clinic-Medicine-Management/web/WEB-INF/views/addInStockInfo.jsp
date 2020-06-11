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
    <script>
        $(function () {
            $("#btn_add").click(function () {
                $("#myModalLabel").text("选择药品");
                $('#myModal').modal();
            });

            $("#myModal").on("hide.bs.modal",function () {
                $("[type=checkbox]").prop("checked",false);
                sum();
            });

            $("#myButtons3").click(function () {
                medicineList($("[name=name]").val(),$("[name=prescriptionTypeId]").val());
            })
            medicineList("","");
            function medicineList(name,prescriptionTypeId) {
                $.ajax({
                    url:'medicine?medicineVO.prescriptionTypeId='+prescriptionTypeId+'&medicineVO.name='+name,
                    type:'post',
                    success:function (data) {
                        var html = "";
                        for (var i = 0;i<data.length;i++){
                            var obj = "<tr>" +
                            "<td><input type='checkbox'></td>"+
                            "<td>"+data[i].id+"</td>"+
                            "<td>"+data[i].barCode+"</td>"+
                            "<td>"+data[i].medicineName+"</td>"+
                            "<td>"+data[i].medicineSpecifications+"</td>"+
                            "<td>"+data[i].prescriptionType.prescriptionTypeName+"</td>"+
                            "<td>"+data[i].purchasePrice+"</td>"+
                            "<td>"+data[i].retailPrice+"</td>"+
                            "<td>"+data[i].manufacturer.name+"</td>"+
                            "</tr>";
                            html += obj;
                        }
                        $("#tbody").html(html);
                    }
                })
            }

            $("[name=quanxuan]").click(function () {
                var flag = $(this).is(":checked");
                if(flag){
                    $("#tbody input").prop("checked",true);
                }else {
                    $("#tbody input").prop("checked",flag);
                }
            })
        });
        function checkout() {
            var html = "";
            $("#tbody input:checked").each(function (index,element) {
                var obj = $(this).parent().parent();
                html += "    <tr>\n" +
                    "        <td>"+obj.find("td:eq(1)").html()+"</td>\n" +
                    "        <td>"+obj.find("td:eq(2)").html()+"</td>\n" +
                    "        <td>"+obj.find("td:eq(3)").html()+"</td>\n" +
                    "        <td>"+obj.find("td:eq(8)").html()+"</td>\n" +
                    "        <td><input type=\"text\" class=\"form-control jiSuan\" name=\"count\" style=\"width: 70px;\" value='1'></td>\n" +
                    "        <td class='purchasePrice'>"+obj.find("td:eq(6)").html()+"</td>\n" +
                    "        <td class='retailPrice'>"+obj.find("td:eq(7)").html()+"</td>\n" +
                    "        <td><input type=\"text\" class=\"form-control\" name=\"inStockVO.name\" style=\"width: 110px;\"></td>\n" +
                    "        <td><input type=\"date\" name=\"medicineVO.startCreateTime\" class=\"form-control\" style=\"width: 150px;\"></td>\n" +
                    "        <td class='purchasePrice1'>"+obj.find("td:eq(6)").html()+"</td>\n" +
                    "        <td class='retailPrice1'>"+obj.find("td:eq(7)").html()+"</td>\n" +
                    "    </tr>";
            });
            $("#x1").append(html);
            $("[name=count]").on("blur",function () {
                var obj = $(this).parent().parent();
                var price1 = obj.find(".purchasePrice").html();
                var price2 = obj.find(".retailPrice").html();
                var count = $(this).val();
                obj.find(".purchasePrice1").html(count * price1);
                obj.find(".retailPrice1").html(count * price2);
                sum();
            });
        }

        function save() {
            var n = 0;
            $.ajax({
                url: 'doAddInStock',
                type: 'post',
                data:$("form").serialize()+"&inStock.purchasePrice="+$("#p1").html()+"&inStock.price="+$("#p2").html()+"&inStock.makeOrderId=1",
                success:function (data) {
                    alert(data);
                    n = data;
                    $("#x1 tr").each(function (index,element) {
                        $.ajax({
                            url:'addInStockMedicine',
                            data:{
                                "inStockMedicine.inStockId":n,
                                "inStockMedicine.medicineId":$(this).find("td:eq(0)").html(),
                                "inStockMedicine.count":$(this).find("td:eq(4) input").val(),
                                "inStockMedicine.lotNumber":$(this).find("td:eq(7) input").val(),
                                "inStockMedicine.expirationDate":$(this).find("td:eq(8) input").val(),
                                "inStockMedicine.purchasePrice":$(this).find("td:eq(9)").html(),
                                "inStockMedicine.price":$(this).find("td:eq(10)").html()
                            }
                        });
                    });
                    location.href="http://localhost:8080/Clinic/inStock"
                }
            });
        }

        function sum() {
            var sum1 = 0;
            var sum2 = 0;
            $("#x1 tr").each(function (index,element) {
                sum1 += parseFloat($(this).find("td:eq(9)").html());
                sum2 += parseFloat($(this).find("td:eq(10)").html());
            })
            $("#jine>span:eq(0)").html("采购金额合计：<span style='color: red;font-weight: bolder' id='p1'>"+sum1+"</span>元");
            $("#jine>span:eq(1)").html("零售金额合计：<span style='color: red;font-weight: bolder' id='p2'>"+sum2+"</span>元");
        }

    </script>
    <style>
        .jumbotron ul{
            list-style: none;
            margin-left: 50px;
        }
        .jumbotron ul li{
            display: inline-block;
            width: 205px;
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
        <form>
            <ul>
                <li>
                    <label class="control-label">入库单号:</label>
                    <input type="text" class="form-control" name="inStock.inStockNo" style="width: 150px;">
                </li>
                <li>
                    <label class="control-label">入库日期:</label>
                    <input type="date" name="inStock.createDate" class="form-control" style="width: 150px;">
                </li>
                <li>
                    <label class="control-label">入库人员:</label>
                    <select class="form-control" style="width: 150px;" name="inStock.employeeId">
                        <s:iterator value="#request.employeeList" var="employee">
                            <option value="${employee.id}">${employee.eName}</option>
                        </s:iterator>
                    </select>
                </li>
                <li>
                    <label class="control-label">入库类型:</label>
                    <select class="form-control" style="width: 150px;" name="inStock.type">
                        <s:iterator value="#request.inStockTypeList" var="inStockType">
                            <option value="${inStockType.id}">${inStockType.typeName}</option>
                        </s:iterator>
                    </select>
                </li>
            </ul>
            <ul>
                <li>
                    <label class="control-label">供应商:</label>
                    <select class="form-control" style="width: 150px;" name="inStock.manufacturerId">
                        <s:iterator value="#request.manufacturerList" var="manufacturer">
                            <option value="${manufacturer.id}">${manufacturer.name}</option>
                        </s:iterator>
                    </select>
                </li>
                <li>
                    <label class="control-label">制单日期:</label>
                    <input type="date" name="inStock.createDate" class="form-control" style="width: 150px;">
                </li>
                <li>
                    <label class="control-label">制单人员:</label>
                    <input type="text" class="form-control" style="width: 150px;" value="王冕">
                </li>
                <li>
                    <label class="control-label">备注:</label>
                    <input type="text" class="form-control" name="inStock.mark" style="width: 150px;">
                </li>
            </ul>
</form>
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
        <td>采购价</td>
        <td>零售价</td>
        <td>批号</td>
        <td>药品有效期</td>
        <td>采购金额</td>
        <td>零售金额</td>
    </tr>
    </thead>
    <tbody id="x1">
        
    </tbody>
</table>
<div align="center">
    <button type="button" class="btn btn-primary" id="btn_add">添加药品</button>
</div>

<%--弹出层--%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="myModalLabel">新增</h4>
                </div>
                <div class="modal-body">
                    <div style="height: 60px;margin-left: 210px">
                            <div class="form-group">
                                <div class="col-lg-3" style="display: inline-block;width: 210px">
                                    <input type="text" class="form-control" placeholder="药品名称/编码" name="name" style="display: inline-block;">
                                </div>

                                <div class="col-sm-6">
                                    <label class="control-label">处方类别</label>
                                    <select class="form-control" style="width: 110px;display: inline-block;" name="prescriptionTypeId">
                                        <option value="">全部</option>
                                        <option value="1">西/成药</option>
                                        <option value="2">中药</option>
                                    </select>
                                </div>
                            </div>
                            <div id="myButtons3" class="bs-example">
                                <button type="button" class="btn btn-primary"
                                        data-loading-text="Loading...">查询
                                </button>
                            </div>
                    </div>


                    <%-- 数据展示栏 --%>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <td><input type="checkbox" name="quanxuan"></td>
                            <td>ID</td>
                            <td>药品编码</td>
                            <td>药品名称</td>
                            <td>规格</td>
                            <td>类别</td>
                            <td>采购价</td>
                            <td>销售价</td>
                            <td>生产厂家</td>
                        </tr>
                        </thead>
                        <tbody id="tbody">

                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭</button>
                    <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal" onclick="checkout();"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存</button>
                </div>
        </div>
    </div>
</div>
</div>
<%--金额--%>
<div id="jine">
    <span style="font-size: 18px;display: inline-block;margin-right: 30px">采购金额合计：<span style='color: red;font-weight: bolder'>0</span>元</span>
    <span style="font-size: 18px">采购金额合计：<span style='color: red;font-weight: bolder'>0</span>元</span>
</div>
<div style="padding-top: 20px;padding-left: 20px">
    <button type="button" class="btn btn-primary" onclick="history.back()" style="margin-right: 10px">返回</button>
    <button type="button" class="btn btn-primary" onclick="save()">提交审核</button>
</div>
</body>
</html>
