$(document).ready(function () {
  var url = window.location.origin;
  // default config
  $(".btn-hidden").css("display", "none");
  $(".device-input").prop("readonly", true);
  $("#dateBuy").datepicker({
    dateFormat: "dd/mm/yy",
  });
  $("#warrantyEnd").datepicker({
    dateFormat: "dd/mm/yy",
  });


  $("#deviceMaintenanceList").DataTable({
      language: {
        lengthMenu: "Hiển thị _MENU_ hàng mỗi trang",
        zeroRecords: "Không có thiết bị trong xưởng",
        info: "Trang _PAGE_ trong _PAGES_",
        infoEmpty: "Không có dữ liệu",
        infoFiltered: "(filtered from _MAX_ total records)",
      },
      pageLength: 5,
      data: device.deviceMaintenanceList,
      columns: [
        {
          title: "Mã",
          data: "id",
        },
        {
          title: "Giá",
          data: "cost",
        },
        {
          title: "Ngày tạo ",
          data: "createDate",
        },
        {
          title: "Ghi chú",
          data: "note",
        },
      ],
    });






  // action
  $("#editBtn").click(function () {
    $(".device-input").prop("readonly", false);
    $(".btn-hidden").css("display", "inline-block");
    $(this).css("display", "none"); // Ẩn nút "editBtn"
  });

  $(".btn-hidden").click(function () {
    $(".device-input").prop("readonly", true);
    $(".btn-hidden").css("display", "none");
    $("#editBtn").css("display", "inline-block");
  });

  $("#printBtn").click(function () {
    $("#qr").printElement();
  });
});

function printDiv() {
  var divContents = document.getElementById("qr").innerHTML;
  var a = window.open("", "", "height=780, width=1360");
  a.document.write("<html>");
  a.document.write("<body>");
  a.document.write(divContents);
  a.document.write("</body></html>");
  a.document.close();
  a.print();
}