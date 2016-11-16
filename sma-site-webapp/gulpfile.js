/**
* @author Yevhen Fabizhevskyi
* @date 16.11.2016.
*/
var gulp = require('gulp');
var install = require("gulp-install");

gulp.task('default', function () {
    return gulp.src(['./bower.json', './package.json'])
        .pipe(install());
});