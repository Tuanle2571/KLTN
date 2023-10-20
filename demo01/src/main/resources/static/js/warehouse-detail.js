$(document).ready(function () {
  $("#warehouse-devices").DataTable({
    pageLength: 5,
    ajax: {
      url: url + "/api/device",
      dataSrc: "",
    },
    columns: [
      {
        title: "Ma thiet bi",
        data: "id",
      },
      {
        title: "Device",
        data: "device.name",
      },
      {
        title: "Loai thiet bi",
        data: "status",
      },
      {
        title: "Trang thai",
        data: "status",
      },
      {
        title: "Ngay nhap kho",
        data: "status",
      },
      {
        title: "Mo ta",
        data: "status",
      },
      {
      },
    ],
  });
});
