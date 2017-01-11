import LoginRestClient from './rest/login-rest-client.js';
import BootboxAlert from './core/bootbox-alert.js';

let restClient = new LoginRestClient();

document.getElementById("login-button").addEventListener('click', event => {
    restClient.login({
        email: $('.form-signin input[type="text"]').val(),
        password: $('.form-signin input[type="password"]').val()
    }, json => {
        if (json.type === 'error') {
            json.message = 'Email or password is incorrect';
            BootboxAlert.show(json);
        } else {
            location.href = '/admin';
        }
    });
});