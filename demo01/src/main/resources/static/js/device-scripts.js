$(document).ready(function () {
  var url = window.location.origin + window.location.pathname;
  $("#FORM_TIME_INPUT").datepicker();
  $("#TIME_PICKER").datepicker();
  $("#deviceList").DataTable({
    fixedColumns: true,
  pageLength: 5,
    language: {
      lengthMenu: "Hiển thị _MENU_ hàng mỗi trang",
      zeroRecords: "Không có thiết bị trong xưởng",
      info: "Trang _PAGE_ trong _PAGES_",
      infoEmpty: "Không có dữ liệu",
      infoFiltered: "(filtered from _MAX_ total records)",
    },
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
        data: "status.status",
      },
      {
      width: 20,
className: 'dt-center',
        title: "Xem",
        data: "id",
        render: function (data, type, row) {
          return `
                <a
                                               href= "${url}/detail/${data}"
                                              target="blank_">
                                        <i class="fa-regular fa-eye"></i>
                                      </a>
              `;
        },
      },
    ],
  });

});
