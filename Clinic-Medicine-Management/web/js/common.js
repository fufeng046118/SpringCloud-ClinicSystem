$(function () {
    var pageNo = $("[name=pageNo]").val();
    var totalPageCount = $("[name=totalPageCount]").val();
    if( pageNo == 1){
        $(".pagination li:first").addClass("disabled").find("a").attr("href","javascript:void(0)");
    }
    if(pageNo == totalPageCount){
        //var num = parseInt(totalPageCount)+parseInt(1);
        $(".pagination>li:last").addClass("disabled").find("a").attr("href","javascript:void(0)");
    }
    $(".pagination li:eq("+pageNo+")").addClass("active");
    $("#myButtons3 .btn:first").click(function(){
        $(this).button('loading').delay(1000).queue(function() {
            $(this).button('reset');
            $(this).dequeue();
            $("form").submit();
        });
    });
});

/*  分页   */
function page(pageNo) {
    $("#pageNo").val(pageNo);
    $("form").submit();
}


