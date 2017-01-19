let paths = location.pathname.split("/");
$('.navbar-nav li a[href="/' + (paths[1] === 'admin' ? 'admin/' + paths[2] : paths[1]) + '"]').parent().addClass('active');

if (paths[1] === 'admin') {
    $('.navbar .navbar-nav').append('<li><a href="/admin/users">Users</a></li>');
}