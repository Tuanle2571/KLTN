$(document).ready(function () {
  var url = window.location.origin;
  $(".btn-hidden").css("display", "none");
  $(".device-input").prop("readonly", true);

  $("#editBtn").text("Sửa");
  $("#dateBuy").datepicker({
    dateFormat: "dd/mm/y",
  });
  $("#warrantyEnd").datepicker({
    dateFormat: "dd/mm/y",
  });
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
$(document).ready(function () {
  var url = window.location.origin;
  $(".btn-hidden").css("display", "none");

  $("#editBtn").text("Sửa");
  $("#dateBuy").datepicker({
    dateFormat: "dd/mm/y",
  });
  $("#warrantyEnd").datepicker({
    dateFormat: "dd/mm/y",
  });
  $("#editBtn").click(function () {
    $(".btn-hidden").css("display", "inline-block");
    $(this).css("display", "none"); // Ẩn nút "editBtn"
  });

  $(".btn-hidden").click(function () {
    // Các hành động khác sau khi click nút "commitBtn"
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
