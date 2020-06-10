<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/4
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="S" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="https://cdn.bootcss.com/smalot-bootstrap-datetimepicker/2.4.4/js/locales/bootstrap-datetimepicker.zh-CN.js" rel="stylesheet">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/common.js"></script>
    <script src="js/component.js"></script>
    <script>
        function del(id) {
            Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
                if (!e) {
                    return;
                }
                location.href="http://localhost:8080/Clinic/deleteInStockById?inStockVO.inStockId="+id;
            });
        }
    </script>
</head>
<body>
<%-- 导航栏--%>
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
                        其他管理<b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">入库管理</a></li>
                        <li class="divider"></li>
                        <li><a href="#">出库管理</a></li>
                        <li class="divider"></li>
                        <li><a href="#">库存盘点</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>



<%-- 搜索栏 --%>
<div style="height: 90px;margin-left: 290px">
    <form role="form" action="inStock" method="post">
        <input name="inStockVO.pageNo" type="hidden" id="pageNo">
    <div class="form-group">

        <div class="col-lg-3" style="display: inline-block;width: 300px">
            <input type="text" class="form-control" placeholder="输入入库单号/供应商" name="inStockVO.name" value="${request.name}">
        </div>

        <div class="col-sm-6">
            <label class="control-label">审核状态</label>
            <select class="form-control" style="width: 130px;display: inline-block;margin-right: 20px" name="inStockVO.statusId">
                <option value="">全部</option>
                <option value="1" <s:if test="#request.statusId == 1">selected</s:if>>未审核</option>
                <option value="2" <s:if test="#request.statusId == 2">selected</s:if>>审核已通过</option>
                <option value="3" <s:if test="#request.statusId == 3">selected</s:if>>审核未通过</option>
            </select>
            <label class="control-label">入库类型</label>
            <select class="form-control" style="width: 90px;display: inline-block;margin-right: 20px" name="inStockVO.type">
                <option value="">全部</option>
                <S:iterator var="inStockType" value="#request.inStockTypeList">
                    <option value="${inStockType.id}" <s:if test="#inStockType.id == #request.type">selected</s:if>>${inStockType.typeName}</option>
                </S:iterator>
            </select>
        </div>
    </div>
        <div id="myButtons3" class="bs-example" style="position: absolute;left: 1000px">
            <button type="button" class="btn btn-primary"
                    data-loading-text="Loading...">查询
            </button>
        </div>
    </form>
</div>


<%-- 数据展示栏 --%>
    <input type="hidden" name="pageNo" value="${request.page.pageNo}">
    <input type="hidden" name="totalPageCount" value="${request.page.totalPageCount}">
        <table class="table table-striped">
            <thead>
            <tr>
                <td>序号</td>
                <td>入库单号</td>
                <td>入库类型</td>
                <td>供应商名称</td>
                <td>制单人员</td>
                <td>采购金额</td>
                <td>零售金额</td>
                <td>入库人员</td>
                <td>创建时间</td>
                <td>审核状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <s:if test="#request.page.list == null || #request.page.list.size() == 0">
                <tr>
                    <td colspan="11" align="center"><h2>没有该入库信息</h2></td>
                </tr>
            </s:if>
            <s:else>
                <s:iterator value="#request.page.list" var="inStock">
                    <tr>
                        <td>${inStock.id}</td>
                        <td>${inStock.inStockNo}</td>
                        <td>${inStock.instocktype.typeName}</td>
                        <td>${inStock.manufacturer.name}</td>
                        <td>${inStock.makeOrder.eName}</td>
                        <td>${inStock.purchasePrice}</td>
                        <td>${inStock.price}</td>
                        <td>${inStock.employee.eName}</td>
                        <td>${inStock.createDate}</td>
                        <td>
                            <s:if test="#inStock.statusId == 1"><span style="color: #999999">未审核</span></s:if>
                            <s:if test="#inStock.statusId == 2"><span style="color: springgreen">审核已通过</span></s:if>
                            <s:if test="#inStock.statusId == 3"><span style="color: red">审核未通过</span></s:if>
                        </td>
                        <td>
                            <div class="btn-group">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">选择
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><s:if test="#inStock.statusId == 1"><a href="javascript:void(0)">编辑</a></s:if><s:else><a href="showInStockInfoById?inStockVO.inStockId=${inStock.id}">查看</a></s:else></li>
                                    <li class="divider"></li>
                                    <li><a href="javascript:void(0)" onclick="del(${inStock.id})">删除</a></li>
                                    <li class="divider"></li>
                                    <li><a href="javascript:void(0)">再次入库</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </s:iterator>
            </s:else>
            </tbody>
        </table>


        <%-- 分页栏 --%>
    <s:if test="#request.page.list != null && #request.page.list.size() != 0">
        <div align="center">
            <ul class="pagination pagination-lg">
                <li><a href="#" onclick="javascript:page(${request.page.pageNo-1})">&laquo;</a></li>
                <s:iterator begin="1" end="#request.page.totalPageCount" var="currPageNo">
                    <li><a href="javascript:void(0)" onclick="javascript:page(${currPageNo})">${currPageNo}</a></li>
                </s:iterator>
                <li><a href="#" onclick="javascript:page(${request.page.pageNo+1})">&raquo;</a></li>
            </ul>
        </div>
    </s:if>
</body>
</html>
