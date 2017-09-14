function seleccionarNivel() {
    alert(PF('selectWV').getSelectedValue());
    if (PF('selectWV').getSelectedValue() == "b") {
        PF('buttonWV').jq.show();
    } else {
        PF('buttonWV').jq.hide();
    }
}  