$(document).ready(function () {
  var url = window.location.href;
  $(".btn-hidden").css("display", "none");
  $(".input-field").css("pointer-events", "none");
  $(".input-field").addClass("input-field-readonly");

  $("#editBtn").click(function () {
    $(".input-field").css("pointer-events", "auto");
    $(".input-field").removeClass("input-field-readonly");
    $(".name-input").addClass("form-control");
    $(".name-input").css("pointer-events", "auto");
    $(".btn-hidden").css("display", "inline-block");
    $(this).css("display", "none");
  });

  console.log(warehouse.devices);
  const table = new DataTable("#warehouseDeviceList", {
    order: [1, "asc"],
    select: {
      style: "multi",
      selector: "td.select-checkbox",
    },
    language: {
      lengthMenu: "Hiển thị _MENU_ hàng mỗi trang",
      zeroRecords: "Không có thiết bị trong xưởng",
      info: "",
      infoEmpty: "Không có dữ liệu",
      infoFiltered: "(filtered from _MAX_ total records)",
    },
    pageLength: 5,
    data: warehouse.devices,
    columns: [
      {
        defaultContent: "",
        className: "select-checkbox",
        orderable: false,
        data: "",
      },
      {
        title: "Mã",
        data: "id",
      },
      {
        title: "Tên",
        data: "name",
      },
      {
        title: "Loại",
        data: "type",
      },
      {
        title: "Mô tả",
        data: "note",
      },
      {
        title: "Ngày mua ",
        data: "dateBuy",
      },
      {
        title: "Giá",
        data: "price",
      },
      {
        title: "Trạng thái",
        data: "status.status",
      },
    ],
  });

  $("#removeDevices").click(function () {

    data = table.rows({ selected: true }).data();
    var newarray = [];
    for (var i = 0; i < data.length; i++) {
      newarray.push({
        id: data[i].id,
        name: data[i].name,
      });
    }
    var sData = JSON.stringify(newarray);
    $.ajax({
      type: "POST",
      contentType: "application/json",
      url: url + "/removedevices",
      data: sData,
      timeout: 100000,
      success: function (data) {
        location.reload();
      },
      error: function (e) {
        console.log("ERROR: ", e);
      },
    });
  });
});
