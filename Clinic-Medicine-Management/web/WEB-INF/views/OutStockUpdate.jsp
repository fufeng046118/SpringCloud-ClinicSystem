<%--
  Created by IntelliJ IDEA.
  User: 易云凡
  Date: 2020/6/10
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/struts-tags" %>
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
    <button style="float: right" id="add-btn" class="btn btn-primary" onclick="updateOutstock(${outstockdetail.id},1)" data-loading-text="Loading..."
            type="button">审核通过
    </button>
    <button style="float: right" id="add-btn1" class="btn btn-primary" onclick="updateOutstock(${outstockdetail.id},2)" data-loading-text="Loading..."
            type="button">审核不通过
    </button>
    <button style="float: right"  class="btn btn-primary" onclick="history.back()" data-loading-text="Loading..."
            type="button">返回
    </button>
    <div class="jumbotron">
        <div class="container">
            <ul style="list-style: none">
                <li>出库订单:<input name="outstockno" type="text" class="form-control" style="display: inline-block;width: 130px" value="CB201911190029"/></li>
                <li>出库备注:<input name="mark" value="${outstockdetail.mark}"  class="form-control" style="display: inline-block;width: 130px" type="text"/></li>
                <li>出库类型:
                    <select name="typeid" class="form-control" style="display: inline-block;width: 130px">
                    <s:iterator value="#typeList" var="g">
                        <option <s:if test="#outstockdetail.typeid==#g.id">selected</s:if> value="${id}">${typename}</option>
                    </s:iterator>
                    </select>
                </li>
            </ul>
            <ul style="list-style: none">

                <li>出库日期:<input name="outdate" value="<s:date name="#outstockdetail.outdate" format="yyyy-MM-dd"></s:date>" type="date" class="form-control" style="display: inline-block;width: 130px"/></li>
                <li>制单日期:<input type="date" class="form-control" style="display: inline-block;width: 130px" disabled value="2018-01-01"></li>
            </ul>
            <ul style="list-style: none">
                <li>出库人员:
                    <select name="employeeid" class="form-control" style="display: inline-block;width: 130px">
                        <s:iterator value="#employeeList">
                            <option <s:if test="#outstockdetail.employeeid==#g.id}">selected</s:if> value="${id}">${eName}</option>
                        </s:iterator>
                    </select>
                </li>
                <li>制单人员:<input name="makeorderid" class="form-control" style="display: inline-block;width: 130px" type="text" disabled value="张三"/></li>

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
        <tbody id="body1">
        <s:iterator value="#outstockmedicine" var="g">
            <tr class="tr">
                <td><s:property value="#g.medicine.id"/> </td>
                <td><s:property value="#g.medicine.medicineNo"/></td>
                <td><s:property value="#g.medicine.medicineName"/></td>

                <td><s:property value="#g.medicine.manufacturer.name"/> </td>
                <td><input type='text' value="${g.count}" class='form-control' name='count' style='width: 130px;display: inline-block'/></td>
                <td><input type='text' class='form-control' style='width: 130px;display: inline-block' disabled value='g/袋'/></td>
                <td><input type='text' value="${g.medicine.purchasePrice}" class='form-control' style='width: 130px;display: inline-block' disabled value=''/></td>
                <td><input type='text' value="${g.medicine.retailPrice}" style='width: 130px;display: inline-block' class='form-control' disabled value=''/></td>
                <td><input class='form-control' value="${g.lotnumber}" style='width: 120px;display: inline-block' name=''/></td>
                <td><input type='date' value="<s:date name="#g.expirationdate" format="yyyy-MM-dd"/>" class='form-control' style='width: 120px;display: inline-block' name=''/></td>
                <td><s:property value="#g.purchaseprice"/></td>
                <td><s:property value="#g.price"/></td>
                <td><img style="width: 20px;height: 20px" onclick='remove(this)' src='img/timg.jpg'></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    采购金额合计：<label id="price1" style="color: red">0</label>元
    零售金额合计：<label id="price2" style="color: red">0</label>元
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">添加药品</button>

<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content animated bounceInRight">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span>
                </button>
                <i class="fa fa-laptop modal-icon"></i>
                <h4 class="modal-title">窗口标题</h4>
                <small class="font-bold">这里可以显示副标题。
                </small></div><small class="font-bold">
            <div class="modal-body">
                <div align="center">
                <form id="frm" action="outstock" method="post">
                    处方类型:<select class="form-control" id="prescriptionId" style="width: 150px;display: inline-block">
                    <option value="">全部</option>
                    <option value="1">西/成药</option>
                    <option value="2">中药</option>
                </select>
                    <input type="text" class="form-control" style="width: 150px;display: inline-block" placeholder="输入入库单号" id="mno"/>
                    <button id="fat-btn" class="btn btn-primary" data-loading-text="Loading..."
                            type="button"> 查询
                    </button>
                </form>
                </div>
                <table class="table table-hover">
                    <thead align="center">
                    <tr style="padding: 10px">
                        <th>全选<input type="checkbox" onclick="" name="checkAll" /></th>
                        <th>序号</th>
                        <th>药品编码</th>
                        <th>药品名称</th>
                        <th>收费类别</th>
                        <th>规格</th>
                        <th>厂家</th>
                        <th>库存</th>
                        <th>采购金</th>
                        <th>零售金</th>
                    </tr>
                    </thead>
                    <tbody id="body">
                    <s:iterator value="#medicines" var="g">
                        <tr>
                            <td><input type="checkbox" name="check"/></td>
                            <td><s:property value="#g.id"/> </td>
                            <td><s:property value="#g.medicineNo"/></td>
                            <td><s:property value="#g.medicineName"/></td>
                            <td><s:property value="#g.prescriptionType.prescriptionTypeName"/></td>
                            <td><s:property value="#g.medicineSpecifications"/></td>
                            <td><s:property value="#g.manufacturer.name"/> </td>
                            <td><s:property value="#g.stock"/></td>
                            <td><s:property value="#g.purchasePrice"/></td>
                            <td><s:property value="#g.retailPrice"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" id="baocun" class="btn btn-primary">保存</button>
            </div>
        </small></div><small class="font-bold">
    </small></div><small class="font-bold">
</small></div>
<button type="button" name="btn">获取数据</button>
<script>
    $(function () {
        jiSuan();
        $('input[name="checkAll"]').click(function(){
            if($(this).is(':checked')){
                $('input[name="check"]').each(function () {
                    $(this).prop("checked",true);
                });
            } else {
                $('input[name="check"]').each(function () {
                    $(this).prop("checked",false);
                });
            }
        });
        $("#fat-btn").click(function(){
                $.ajax({
                    url: 'findmedicine?MedicineVo.prescriptionId='+$("#prescriptionId").val()+"&medicineVo.mno="+$("#mno").val(),
                    type: 'post',
                    success: function (data) {
                        //alert(data.length);
                        //alert(data[0].id);
                        $("#body").html("");
                        var tr="";
                        for (var i=0;i<data.length;i++){
                            tr+="<tr><td><input type=\"checkbox\" name=\"check\"/></td><td>"+data[i].id+"</td><td>"+data[i].medicineNo+"</td><td>"+data[i].medicineName+"</td><td>"+data[i].prescriptionType.prescriptionTypeName+"</td><td>"
                                +data[i].medicineSpecifications+"</td><td>"+data[i].manufacturer.name+"</td><td>"+data[i].stock+"</td><td>"+data[i].purchasePrice+"</td><td>"+data[i].retailPrice+"</td></tr>"

                        }
                        $("#body").html(tr);
                    }
                })
            });
        $("#baocun").click(function () {
            var body="";
            $("input[name='check']:checked").each(function(i,em) {
                body+="<tr class='tr'>" +
                    "<td>"+$(em).parent().parent().find("td:eq(1)").html()+"</td>" +
                    "<td>"+$(em).parent().parent().find("td:eq(2)").html()+"</td>" +
                    "<td>"+$(em).parent().parent().find("td:eq(3)").html()+"</td>" +
                    "<td>"+$(em).parent().parent().find("td:eq(6)").html()+"</td>" +
                    "<td><input type='text' class='form-control' name='count' style='width: 130px;display: inline-block'/></td>" +
                    "<td><input type='text' class='form-control' style='width: 130px;display: inline-block' disabled value='g/袋'/></td>" +
                    "<td><input type='text' class='form-control' style='width: 130px;display: inline-block' disabled value='"+$(em).parent().parent().find("td:eq(8)").html()+"'/></td>" +
                    "<td><input type='text' style='width: 130px;display: inline-block' class='form-control' disabled value='"+$(em).parent().parent().find("td:eq(9)").html()+"'/></td>" +
                    "<td><input class='form-control' style='width: 120px;display: inline-block' name=''/></td>" +
                    "<td><input type='date' class='form-control' style='width: 120px;display: inline-block' name=''/></td>" +
                    "<td>0</td>" +
                    "<td>0</td>" +
                    "<td><img style='width: 20px;height: 20px' onclick='remove(this)' src='img/timg.jpg'></td>" +
                    "</tr>";
            });
            $("#body1").prepend(body);
            $('#myModal').modal('hide');
            $("[name=count]").on('blur',function () {
                $(this).parent().parent().find("td:eq(10)").html(parseFloat($(this).parent().parent().find("td:eq(4)").find("input").val())*parseFloat($(this).parent().parent().find("td:eq(6)").find("input").val()));
                $(this).parent().parent().find("td:eq(11)").html(parseFloat($(this).parent().parent().find("td:eq(4)").find("input").val())*parseFloat($(this).parent().parent().find("td:eq(7)").find("input").val()));
                jiSuan();
            })
        });

    });
    $("[name=count]").on('blur',function () {
        $(this).parent().parent().find("td:eq(10)").html(parseFloat($(this).parent().parent().find("td:eq(4)").find("input").val())*parseFloat($(this).parent().parent().find("td:eq(6)").find("input").val()));
        $(this).parent().parent().find("td:eq(11)").html(parseFloat($(this).parent().parent().find("td:eq(4)").find("input").val())*parseFloat($(this).parent().parent().find("td:eq(7)").find("input").val()));
        jiSuan();
    })
        $("[name=btn]").click(function () {
            $.ajax({
                url: 'findmedicine?outstockVo.outstockno=张三',
                type: 'post',
                success: function (data) {
                    alert(data)
                    alert(data[0].medicinename);
                }
            });
        });
    function remove(ss) {
        $(ss).parent().parent().remove();
        jiSuan();
    }
function jiSuan() { //计算左下角总价
    var price1=0;
    var price2=0;
    $(".tr").each(function (i) {
        price1+=parseFloat($(this).find("td:eq(10)").html());
        price2+=parseFloat($(this).find("td:eq(11)").html());
    });
    $("#price1").html(price1);
    $("#price2").html(price2)
}
function updateOutstock(id,statusId) {

    var outstockid=0;
    $.ajax({
        url: 'updateoutstock1',
        type: 'post',
        data:
            {
                "outstockVo.geo":id
                ,"outstock.outstockno":$("[name=outstockno]").val()
                ,"outstock.typeid":$("[name=typeid]").val()
                ,"outstock.employeeid":$("[name=employeeid]").val()
                ,"outstock.mark":$("[name=mark]").val()
                ,"outstock.outdate":$("[name=outdate]").val()
                ,"outstock.makeorderid":1
                ,"outstock.purchaseprice":$("#price1").html()
                ,"outstock.price":$("#price2").html()
                ,"outstock.statusid":statusId
        },
            success: function (data) {
                var outstockid=data;
                $(".tr").each(function (i) {
                    $.ajax({
                        url: 'updateoutstock2',
                        type: 'post',
                        data:
                            {   "updateStatusId":statusId,
                                "outstockMedicine.outstockid" : outstockid,
                                "outstockMedicine.medicineid" : $(this).find("td:eq(0)").html(),
                                "outstockMedicine.count" : $(this).find("td:eq(4)").find("input").val(),
                                "outstockMedicine.lotnumber" : $(this).find("td:eq(8)").find("input").val(),
                                "outstockMedicine.expirationdate" : $(this).find("td:eq(9)").find("input").val(),
                                "outstockMedicine.purchaseprice" : $(this).find("td:eq(10)").html(),
                                "outstockMedicine.price" : $(this).find("td:eq(11)").html()
                            },
                    });
                });
                    alert("提交成功！");
                    location.href='medicine/outstock';
            }
    });

}



</script>
</div>
</body>
</html>
