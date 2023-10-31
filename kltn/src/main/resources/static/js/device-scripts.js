$(document).ready(function () {
  var url = window.location.origin;
  $("#FORM_TIME_INPUT").datepicker();
  $("#TIME_PICKER").datepicker();
  $("#deviceList").DataTable({
    language: {
      lengthMenu: "Hiển thị _MENU_ hàng mỗi trang",
      zeroRecords: "Không có thiết bị trong xưởng",
      info: "Trang _PAGE_ trong _PAGES_",
      infoEmpty: "Không có dữ liệu",
      infoFiltered: "(filtered from _MAX_ total records)",
    },
    pageLength: 5,
    data: devices,
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
        title: "Giá",
        data: "price",
      },
      {
        title: "Ngày mua ",
        data: "dateBuy",
      },
      {
        title: "Trạng thái",
        data: "deviceStatus.status",
      },
      {
        title: "Xem",
        data: "id",
        render: function (data, type, row) {
          return `
               <a
                       href= "${url}/device/detail/${data}"
                       target="blank_"
                       type="button"
                       class="btn btn-primary">
                   DETAIL
               </a>
              `;
        },
      },
    ],
  });
});
