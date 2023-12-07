$(document).ready(function () {
  var url = window.location.origin;
  const table = new DataTable("#deviceList", {
  scrollX: true,
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
    data: devices,
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
        render: function (data, type, row) {
          if (data == null) {
            return "";
          }
          return data.name;
        },
      },
      {
 title: "Giá",
        data: "price",
        className: "dt-body-right",
        render: $.fn.dataTable.render.number( ',', '.', 3, null, " đ" )
      },
      {
        title: "Ngày mua ",
        data: "dateBuy",
      },
      {
        title: "Trạng thái",
        data: "status.status",
      },
    ],
  });

  $("#submit").click(function () {
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
      url: "",
      data: sData,
      timeout: 100000,
      success: function (data) {
        console.log("SUCCESS: ", data);
                history.back();
      },
      error: function (e) {
        console.log("ERROR: ", e);
      },
    });
  });
});
