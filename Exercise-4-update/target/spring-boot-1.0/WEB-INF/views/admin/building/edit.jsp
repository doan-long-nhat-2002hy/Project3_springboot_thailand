<%--
  Created by IntelliJ IDEA.
  User: doanlongnhat
  Date: 10/11/2024
  Time: 10:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: doanlongnhat
  Date: 10/11/2024
  Time: 10:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Thêm tòa nhà</title>
</head>
<body>
<div class="main-content" id="main-container">


    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Dashboard</li>
                </ul><!-- /.breadcrumb -->
            </div>

            <div class="page-content">


                <div class="page-header">
                    <h1 style="font-family: 'Times New Roman', Times, serif;">
                        Thêm hoặc cập nhật tòa nhà
                    </h1>
                </div><!-- /.page-header -->

                <%--                bảng danh sách--%>
                <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                    <form:form modelAttribute="buildingEdit" id="listForm" method="GET">
                        <div class="col-xs-12">
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Tên tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="name"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Quận Quận hiện có</label>
                                    <div class="col-xs-2" id="">
                                        <form:select class="form-control" path="district">
                                            <form:option value="">---Chọn quận---</form:option>
                                            <form:options items="${districts}"></form:options>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Phường</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="ward" name="ward">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Đường</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="street" name="street">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Kết cấu</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="structure" name="structure">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Số tầng hầm</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="numberOfBasement"
                                               name="numberOfBasement">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Diện tích sàn</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="number" id="floorArea" name="floorArea">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Hướng</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="direction" name="direction">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Hạng</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="level" name="level">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Diện tích thuê</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="areaPrice" name="areaPrice">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Giá thuê</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="number" id="rentPrice" name="rentPrice">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Mô tả giá</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="rentPriceDescription"
                                               name="rentPriceDescription">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Phí dịch vụ</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="serviceFee" name="serviceFee">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Phí ô tô</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="carFee" name="carFee">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Phí mô tô</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="motorbikeFee" name="motorbikeFee">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Phí ngoài giờ</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="overtimeFee" name="overtimeFee">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Tiền điện</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="electricityFee"
                                               name="electricityFee">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Đặt cọc</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="deposit" name="deposit">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Thanh toán</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="payment" name="payment">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Thời hạn thuê</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="rentTime" name="rentTime">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Thời gian trang trí</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="decorationTime"
                                               name="decorationTime">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Tên quản lý</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="managerName" name="managerName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">SĐT quản lý</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="number" id="managerPhoneNumber"
                                               name="managerPhoneNumber">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Phí môi giới</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="brokerageFee" name="brokerageFee">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Loại tòa nhà</label>
                                    <div class="col-xs-6">
                                        <form:checkboxes items="${typecodes}" path="typeCode"></form:checkboxes>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3">Ghi chú</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" type="text" id="note" name="note">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-xs-3"></label>
                                    <div class="col-xs-9">
                                        <c:if test="${not empty buildingEdit.id}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">
                                                Cập nhật
                                            </button>
                                            <button type="button" class="btn btn-danger" id="btnCancel">Hủy</button>
                                        </c:if>
                                        <c:if test="${empty buildingEdit.id}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">
                                                Thêm tòa
                                                nhà
                                            </button>
                                            <button type="button" class="btn btn-danger" id="btnCancel">Hủy</button>
                                        </c:if>

                                    </div>
                                </div>
                                <form:hidden path="id" id="buildingId"></form:hidden>
                            </form>
                        </div>
                    </form:form>

                </div>


            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->
</div><!-- /.main-container -->
<script>
    $('#btnAddOrUpdateBuilding').click(function () {
        var data = {};
        var typeCode = [];
        var formData = $('#listForm').serializeArray();

        $.each(formData, function (i, v) {
            if (v.name != 'typeCode') {
                data[v.name] = v.value;
            } else {
                typeCode.push(v.value);
            }
        });
        data['typeCode'] = typeCode;
        if(typeCode != ''){
        addOrUpdateBuilding(data)}
        else{
            window.location.href = "<c:url value= "/admin/building-edit?typeCode=require"/>";
        }

    });

    function addOrUpdateBuilding(data) {
        $.ajax({
            type: "POST", // hay dùng nhất
            url: "${buildingAPI}", // đường dẫn đến api
            data: JSON.stringify(data), // dữ liệu gửi lên server
            contentType: "application/json", // kiểu dữ liệu gửi lên server
            dataType: "JSON", // kiểu dữ liệu trả về từ server, chưa cần thiết
            success: function (respond) {
                console.log("success");
            },
            error: function (respond) {
                console.log("error");
                console.log(respond);
            }
        })
    }

    $('#btnCancel').click(function () {
        window.location.href = "/admin/building-list"
    });
</script>
</body>
</html>