$(document).ready(function () {
  var url = window.location.origin;
//  $("#FORM_TIME_INPUT").datepicker();
//  $("#TIME_PICKER").datepicker();
  $("#deviceList").DataTable({
    pageLength: 5,
    ajax: {
      url: url + "/api/inventory",
      dataSrc: "",
    },

    columns: [
      {
        title: "Id",
        data: "id",
      },
      {
        title: "Device",
        data: "device.name",
      },
      {
        title: "Date Create",
        data: "date",
      },
      {

      },
    ],
  });
});
