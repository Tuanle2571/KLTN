$(document).ready(function () {
  var url = window.location.origin;
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

  $("#warehouseDeviceList").DataTable({
   language: {
        lengthMenu: "Hiển thị _MENU_ hàng mỗi trang",
        zeroRecords: "Không có thiết bị trong xưởng",
        info: "Trang _PAGE_ trong _PAGES_",
        infoEmpty: "Không có dữ liệu",
        infoFiltered: "(filtered from _MAX_ total records)",
      },
    pageLength: 5,
    data: warehouse.devices,
    columns: [
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
});
