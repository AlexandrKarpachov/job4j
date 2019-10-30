$(
    function () {
        var errorMsg = '${error}';
        if (errorMsg === 'wrong login') {
            var msg = "Such login does not exist";
            makeAlert("login-group", "emptyLoginAlert", msg);
        }
        if (errorMsg === 'wrong password') {
            var login = '${login}';
            $("#login").val(login);
            makeAlert("pass-group", "emptyPassAlert", errorMsg);
        }
    }
);

function authFormValidate() {
    var result = true;
    var name = $('#login');
    var password = $('#pwd');
    if (name.val() === '') {
        makeAlert("login-group", "emptyLoginAlert", "Please enter Login");
        result = false;
    }
    if (password.val() === '') {
        makeAlert("pass-group", "emptyPassAlert", "Please enter password");
        result = false;
    }
    return result;
}
function createFormValidate() {
    var result = true;
    var name = $('#name');
    var password = $('#pwd');
    var login = $('#login');
    var email = $('#email');
    if (name.val() === '') {
        makeAlert("name-group", "emptyNameAlert", "Please enter Your Name");
        result = false;
    }
    if (login.val() === '') {
        makeAlert("login-group", "emptyLoginAlert", "Please enter Your Login");
        result = false;
    }
    if (password.val() === '') {
        makeAlert("pass-group", "emptyPassAlert", "Please enter password");
        result = false;
    }
    if (email.val() === '') {
        makeAlert("email-group", "emptyEmailAlert", "Please enter Your Email");
        result = false;
    } else if (!validateEmail(email.val())) {
        makeAlert("email-group", "emptyEmailAlert", "Wrong email");
        result = false;
    }
    return result;
}
function makeAlert(inputID, alertID, alertMsg) {
    var input = document.getElementById(inputID);
    input.classList.add("has-error");
    var alert = document.getElementById(alertID);
    alert.classList.remove("hidden");
    alert.innerHTML = alertMsg;
}
function hideAlert(alertID, inputID) {
    var element = document.getElementById(alertID);
    if (!element.classList.contains("hidden")) {
        element.classList.add("hidden");
        var input = document.getElementById(inputID);
        input.classList.remove("has-error");
        result = false;
    }
    return result;
}
function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}