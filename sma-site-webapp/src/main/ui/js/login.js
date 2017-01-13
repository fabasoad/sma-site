import LoginRestClient from './rest/login-rest-client.js';
import BootboxAlert from './core/bootbox-alert.js';

let restClient = new LoginRestClient();

let onKeyPressHandler = e => {
    if (e.which === 13) {
        document.getElementById("login-button").click();
    }
};

let $emailInput = $('.form-signin input[type="text"]');
$emailInput.keypress(onKeyPressHandler);

let $passwordInput = $('.form-signin input[type="password"]');
$passwordInput.keypress(onKeyPressHandler);

document.getElementById("login-button").addEventListener('click', event => {
    restClient.login({
        email: $emailInput.val(),
        password: $passwordInput.val()
    }, json => {
        if (json.type === 'error') {
            json.message = 'Email or password is incorrect';
            BootboxAlert.show(json);
        } else {
            location.href = '/admin';
        }
    });
});