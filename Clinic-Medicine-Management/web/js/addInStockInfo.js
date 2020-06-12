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
            "        <td>盒</td>\n"+
            "        <td class='purchasePrice'>"+obj.find("td:eq(6)").html()+"</td>\n" +
            "        <td class='retailPrice'>"+obj.find("td:eq(7)").html()+"</td>\n" +
            "        <td><input type=\"text\" class=\"form-control\" name=\"inStockVO.name\" style=\"width: 110px;\"></td>\n" +
            "        <td><input type=\"date\" name=\"medicineVO.startCreateTime\" class=\"form-control\" style=\"width: 150px;\"></td>\n" +
            "        <td class='purchasePrice1'>"+obj.find("td:eq(6)").html()+"</td>\n" +
            "        <td class='retailPrice1'>"+obj.find("td:eq(7)").html()+"</td>\n" +
            "       <td><img src='img/remove.jpg' width='20px' height='20px' class='remove'></td>"
        "    </tr>";
    });
    $("#x1").append(html);
    $("[name=count]").on("blur",function () {
        xiaoJi($(this));
    });
    $(".remove").on('click',function () {
        remove($(this))
    });
}
function remove(node) {
    node.parent().parent().remove();
    sum();
}
function xiaoJi(node) {
    var obj = node.parent().parent();
    var price1 = obj.find(".purchasePrice").html();
    var price2 = obj.find(".retailPrice").html();
    var count = node.val();
    obj.find(".purchasePrice1").html(count * price1);
    obj.find(".retailPrice1").html(count * price2);
    sum();
}
function save() {
    var n = 0;
    $.ajax({
        url: 'doAddInStock',
        type: 'post',
        data:$("form").serialize()+"&inStock.purchasePrice="+$("#p1").html()+"&inStock.price="+$("#p2").html()+"&inStock.makeOrderId=1&inStock.statusId=1",
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
                        "inStockMedicine.lotNumber":$(this).find("td:eq(8) input").val(),
                        "inStockMedicine.expirationDate":$(this).find("td:eq(9) input").val(),
                        "inStockMedicine.purchasePrice":$(this).find("td:eq(10)").html(),
                        "inStockMedicine.price":$(this).find("td:eq(11)").html(),
                        "inStock.statusId":0
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
        sum1 += parseFloat($(this).find("td:eq(10)").html());
        sum2 += parseFloat($(this).find("td:eq(11)").html());
    })
    $("#jine>span:eq(0)").html("采购金额合计：<span style='color: red;font-weight: bolder' id='p1'>"+sum1+"</span>元");
    $("#jine>span:eq(1)").html("零售金额合计：<span style='color: red;font-weight: bolder' id='p2'>"+sum2+"</span>元");
}