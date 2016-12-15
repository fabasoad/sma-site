let paths = location.pathname.split("/");
let endPath;
let locationPage;
if (paths[1] === 'admin') {
    locationPage = '/admin/';
    endPath = paths[2];
} else {
    locationPage = '/';
    endPath = paths[1];
}
$('.navbar-nav li a[href="/' + endPath + '"]').parent().addClass('active');
let navbarElements = $('.navbar-nav li a');
navbarElements.each(i => {
    let temp = navbarElements[i].href.split('/');
    navbarElements[i].href = locationPage + temp[temp.length - 1];
});
$('.navbar-brand').attr('href', locationPage);
