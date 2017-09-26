
function trataLogin(xhr, status, args) {
    if (args.validationFailed || !args.flgLogin) {
        dlg.jq.effect("shake", {times: 5}, 100);
    }
    else {
        dlg.hide();
        $('#login').fadeOut();
    }
}

function trataCadastro(xhr, status, args) {

    if (args.validationFailed || !args.flgCadastro) {
        dlg.jq.effect("shake", {times: 5}, 100);
    }
    else {
        dlg.hide();
        $('#cadastro').fadeOut();
    }
}
