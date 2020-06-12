<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/4
  Time: 23:51
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
    <script src="js/common.js"></script>
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
            <a class="navbar-brand" href="medicine">药品信息维护</a>
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
<div style="height: 90px">
    <form role="form" action="medicine" method="post">
        <input name="medicineVO.pageNo" type="hidden" id="pageNo">
    <div class="form-group">

        <div class="col-lg-3" style="display: inline-block;width: 300px">
            <input type="text" class="form-control" placeholder="输入药品名称/编码/生产厂家" name="medicineVO.name" value="${request.name}">
        </div>

        <div class="col-sm-6">
            <label class="control-label">处方类别</label>
            <select class="form-control" style="width: 90px;display: inline-block;margin-right: 20px" name="medicineVO.prescriptionTypeId">
                <option value="">全部</option>
                <option value="1" <s:if test="#request.prescriptionTypeId == 1">selected</s:if>>西/成药</option>
                <option value="2" <s:if test="#request.prescriptionTypeId == 2">selected</s:if>>中药</option>
            </select>
            <label class="control-label">药品状态</label>
            <select class="form-control" style="width: 90px;display: inline-block;margin-right: 20px" name="medicineVO.medicineStatus">
                <option value="">全部</option>
                <option value="1" <s:if test="#request.medicineStatus == 1">selected</s:if>>启用</option>
                <option value="0" <s:if test="#request.medicineStatus == 0">selected</s:if>>停用</option>
            </select>
            <label class="control-label">创建时间</label>
            <input type="date" name="medicineVO.startCreateTime" value="<s:date name="#request.startCreateTime" format="yyyy-MM-dd" />" class="form-control" style="width: 150px;display: inline-block;margin-left: 10px">
            <label class="control-label">至</label>
            <input type="date" name="medicineVO.endCreateTime" value="<s:date name="#request.endCreateTime" format="yyyy-MM-dd" />" class="form-control" style="width: 150px;display: inline-block;position: absolute;left: 610px">
        </div>
    </div>
        <div id="myButtons3" class="bs-example" style="position: absolute;left: 1100px">
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
                <td>ID</td>
                <td>药品编码</td>
                <td>药品名称</td>
                <td>规格</td>
                <td>类别</td>
                <td>采购价</td>
                <td>销售价</td>
                <td>生产厂家</td>
                <td>状态</td>
                <td>创建时间</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <s:if test="#request.page.list == null || #request.page.list.size() == 0">
                <tr>
                    <td colspan="11" align="center"><h2>没有该药品信息</h2></td>
                </tr>
            </s:if>
            <s:else>
                <s:iterator value="#request.page.list">
                    <tr>
                        <td>${id}</td>
                        <td>${medicineNo}</td>
                        <td>${medicineName}</td>
                        <td>${medicineSpecifications}</td>
                        <td>${prescriptionType.prescriptionTypeName}</td>
                        <td>${purchasePrice}</td>
                        <td>${retailPrice}</td>
                        <td>${manufacturer.name}</td>
                        <td><s:if test="#request.medicineStatus == 1"><span style="color: springgreen">启用</span></s:if><s:else><span style="color: red">停用</span></s:else></td>
                        <td>${createTime}</td>
                        <td>
                            <div class="btn-group">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">选择
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="javascript:void(0)">编辑</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#">复制</a></li>
                                    <li class="divider"></li>
                                    <li><a href="javascript:void(0)" onclick="javascript:updateStatus(${id})">停用</a></li>
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
