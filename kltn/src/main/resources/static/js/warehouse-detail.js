$(document).ready(function () {
  $("#warehouse-devices").DataTable({
    pageLength: 5,
    ajax: {
      data: warehouse.devices,
      dataSrc: "",
    },
    columns: [
      {
        title: "Mã thiết bị",
        data: "id",
      },
      {
        title: "Tên thiết bị",
        data: "name",
      },
      {
        title: "Loại thiết bị",
        data: "status",
      },
      {
        title: "Trạng thái",
        data: "status",
      },
      {
        title: "Ngay nhap kho",
        data: "status",
      },
    ],
  });
});
