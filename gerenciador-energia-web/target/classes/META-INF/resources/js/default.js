/* Funções gerais do sistema */
function hideDialogOnSuccess(args, dialogName) {
    if (args && !args.validationFailed) {
        PF(dialogName).hide();
    }
}

function firstDayOnly(date) {
    var day = date.getDate();
    return [day == 1, '']
}

function lastDayOnly(date) {
    var day = date.getDate();
    var month = date.getMonth();
    var year = date.getFullYear();
    var lastDay = new Date(year, month + 1, 0);

    return [day == lastDay.getDate(), '']
}

function disableEnterKey(event) {
    return event.keyCode != 13;
}
