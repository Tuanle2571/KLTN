$(document).ready(function () {
  var url = window.location.origin + window.location.pathname;
  $("#warehouseList").DataTable({
  scrollX: true,
    pageLength: 5,
    language: {
          lengthMenu: "Hiển thị _MENU_ hàng mỗi trang",
          zeroRecords: "Không có thiết bị trong xưởng",
          info: "Trang _PAGE_ trong _PAGES_",
          infoEmpty: "Không có dữ liệu",
          infoFiltered: "(filtered from _MAX_ total records)",
        },
    data: warehouses,
    columns: [
      {
        title: "Mã xưởng",
        data: "id",
      },
      {
        title: "Tên xưởng",
        data: "name",
      },
      {
          title: "Người quản lí ",
          data: "user.username",
        },
        {
                title: "Địa chỉ",
                data: "address",
              },
      {
        title: "Số lượng thiết bị",
        data: "devices.length",
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
