$(function () {
    var sum1 = 0;
    var sum2 = 0;
    var content = $("#x1 tr:first>td").html();
    if( content != "没有该出库药品信息"){
        $("#x1 tr").each(function (index,element) {
            sum1 += parseFloat($(this).find("td:eq("+10+")").html());
            sum2 += parseFloat($(this).find("td:eq("+11+")").html());
        })
    }
    $("#jine>span:eq(0)").html("采购金额合计：<span style='color: red;font-weight: bolder'>"+sum1+"</span>元");
    $("#jine>span:eq(1)").html("零售金额合计：<span style='color: red;font-weight: bolder'>"+sum2+"</span>元");
})