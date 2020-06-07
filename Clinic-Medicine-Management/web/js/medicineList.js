$(function () {
    var pageNo = $("[name=pageNo]").val();
    var totalPageCount = $("[name=totalPageCount]").val();
    if( pageNo == 1){
        $(".pagination li:first").addClass("disabled").find("a").attr("href","javascript:void(0)");
    }
    if(pageNo == totalPageCount){
        var num = parseInt(totalPageCount)+parseInt(1);
        $(".pagination li:eq("+num+")").addClass("disabled").find("a").attr("href","javascript:void(0)");
    }
    $(".pagination li:eq("+$("[name=pageNo]").val()+")").addClass("active");
});