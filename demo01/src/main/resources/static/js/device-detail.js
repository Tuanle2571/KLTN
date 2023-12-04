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

  $("#printBtn").click(function () {
    $("#qr").printElement();
  });
$("#modal-open").click(function () {
	$('#maintenance').modal("toggle");
	  $("#maintenanceDate").datepicker({
        dateFormat: "dd/mm/yy",
      });

});

$(".modal-dismiss").click(function () {
	$('#maintenance').modal("toggle");
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
