$(document).ready(function () {
  $("#dateBuy").datepicker({
    dateFormat: "dd/mm/yy",
  });
  $("#warrantyEnd").datepicker({
    dateFormat: "dd/mm/yy",
  });
  const price =  $("#i-price").val();
  const formated = new Intl.NumberFormat("vi-VN").format(price);
  $("#i-price").val(formated);
  //
  $("#price").change(function () {
    const value = this.value.replace(/\D/g, "");
    const formated = new Intl.NumberFormat("vi-VN").format(value);
    $(this).val(formated);
    $("#i-price").val(value);
  });
});
