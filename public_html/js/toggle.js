function toggle_visibility(id, id2, doSwitch) {
  var e = document.getElementById(id);
  var visit = document.getElementById(id2);
  var form = document.getElementById("AeonRequest");

  if (doSwitch == 2) {
    e.style.display = "block";
    visit.style.display = "none";
    form.Format.disabled = false;
    form.ForPublication.disabled = false;
    form.ItemInfo3.disabled = false;
    form.RequestType.disabled = false;
    form.SkipOrderEstimate.disabled = false;
  } else {
    e.style.display = "none";
    visit.style.display = "block";
    form.Format.disabled = true;
    form.ForPublication.disabled = true;
    form.ItemInfo3.disabled = true;
    form.RequestType.disabled = true;
    form.SkipOrderEstimate.disabled = true;
  }
}
