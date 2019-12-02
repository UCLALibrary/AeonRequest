function validateForm(formObj) {
  var customDate = 0;
  var selectedDate = 0;
  var pattern = /\d{2}\/\d{2}\/\d{4}/;
  var dupe = document.getElementById("dupe").style.display;

  for (var i = 0; i < formObj.theDate.length; i++) {
    if (formObj.theDate[i].checked) selectedDate += 1;
  }

  if (formObj.textDate.value.replace(/^(\s+)/, "").replace(/(\s+)$/, "") != "") {
    if (!pattern.test(formObj.textDate.value)) {
      alert("The date value must be in MM/DD/YYYY format");
      formObj.textDate.select();
      formObj.textDate.focus();
      return false;
    } else customDate += 1;
  }

  if (customDate > 0 || selectedDate > 0 || dupe == "block") return true;
  else {
    alert("You must either select a listed date or enter a desired date for your visit");
    return false;
  }
}
function clearRadioDates() {
  var formObj = document.getElementById("bibForm");
  var textDate = document.getElementById("textDate").value;

  if (textDate != "") {
    for (i = 0; i < formObj.theDate.length; i++) {
      formObj.theDate[i].checked = false;
    }
  }
}
