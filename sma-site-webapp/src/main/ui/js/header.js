import {restClient} from './rest/params-rest-client.js';
import Constants from './core/constants.js';

let paths = location.pathname.split("/");
let $navbar = $('.navbar .navbar-nav');

let showNavbar = headerText => {
    document.querySelector('.navbar-brand').appendChild(document.createTextNode(headerText));
    $navbar.removeClass('hide');
    $('.navbar .navbar-header').removeClass('hide');
};

if (paths[1] === 'admin') {
    let attributes = paths[2] === 'params' || paths[2] === 'users' ? '' : 'target="_blank" href="/' + paths[2] + '"';
    $navbar.prepend('<li><a class="glyphicon glyphicon-open-file" ' + attributes + '></a></li>');
    $navbar.append('<li><a href="/admin/params">Configuration</a></li>');
    $navbar.append('<li><a href="/admin/users">Users</a></li>');
    showNavbar('Administration');
} else {
    restClient.getById(Constants.PARAMS.COMPANY_NAME, data => showNavbar(data.body));
}

$('.navbar-nav li a[href="/' + (paths[1] === 'admin' ? 'admin/' + paths[2] : paths[1]) + '"]').parent().addClass('active');