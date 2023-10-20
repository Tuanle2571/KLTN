$(document).ready(function () {
  $("#warehouse-table").DataTable({
    pageLength: 5,
    data: warehouses,
    columns: [
      {
        title: "Ma xuong",
        data: "id",
      },
      {
        title: "Ten xuong",
        data: "name",
      },
      {
        title: "so luong thiet bi",
        data: "devices.length",
      },
      {
        title: "Trang thai",
        data: "status",
      },
      {
        title: "Xem",
        data: "",
      },
      {
        title: "Xoa",
        data: "",
      },
    ],
  });
});
