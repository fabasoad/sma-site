import RestUtils from './rest-utils.js';

export default class LoginRestClient {

    login(obj, callback) {
        $.ajax({
            type: 'POST',
            url: '/authenticate',
            contentType: "application/json",
            dataType: 'json',
            data: obj,
            complete: data => RestUtils.handleResponse(data, callback)
        });
    }
}