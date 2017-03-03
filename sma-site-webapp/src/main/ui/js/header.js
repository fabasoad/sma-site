import {restClient} from './rest/params-rest-client.js';
import Constants from './core/constants.js';

let paths = location.pathname.split("/");

if (paths[1] === 'admin') {
    let $navbar = $('.navbar .navbar-nav');
    $navbar.append('<li><a href="/admin/params">Configuration</a></li>');
    $navbar.append('<li><a href="/admin/users">Users</a></li>');
    document.querySelector('.navbar-brand').appendChild(document.createTextNode('Administration'));
} else {
    restClient.getById(Constants.PARAMS.COMPANY_NAME, data => {
        document.querySelector('.navbar-brand').appendChild(document.createTextNode(data.body));
    });
}

$('.navbar-nav li a[href="/' + (paths[1] === 'admin' ? 'admin/' + paths[2] : paths[1]) + '"]').parent().addClass('active');