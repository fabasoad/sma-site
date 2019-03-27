/**
* @author Yevhen Fabizhevskyi
* @date 16.11.2016.
*/
const gulp = require('gulp');
const install = require("gulp-install");
const uglify = require('gulp-uglify');
const babel = require('gulp-babel');
const less = require('gulp-less');
const cssmin = require('gulp-cssmin');

gulp.task('install', () => gulp.src(['./package.json']).pipe(install()));

gulp.task('less', function () {
  return gulp.src('./src/main/ui/less/**/*.less')
    .pipe(less())
    .pipe(cssmin())
    .pipe(gulp.dest('./src/main/webapp/public/css/min'));
});

gulp.task('watch', () => {
    gulp.watch('src/main/ui/less/**/*.less', ['less']);
    gulp.watch('src/main/ui/js/**/*.js', ['build-js'])
});

gulp.task('build-test-data', () =>
    gulp.src('src/main/ui/test-data/**/*.*').pipe(gulp.dest('src/main/webapp/public/data'))
);

gulp.task('js-min', () =>
    gulp.src('src/main/ui/js/**/*.js')
        .pipe(babel({
            presets: ['es2015']
        }))
        .pipe(uglify())
        .pipe(gulp.dest('src/main/webapp/public/js/min'))
);

gulp.task('js-dev', () =>
    gulp.src('src/main/ui/js/**/*.js')
        .pipe(babel({
            presets: ['es2015']
        }))
        .pipe(gulp.dest('src/main/webapp/public/js/dev'))
);

gulp.task('build-fonts', () => gulp.src('src/main/ui/fonts/**/*.*').pipe(gulp.dest('src/main/webapp/public/fonts')));
gulp.task('build-img', () => gulp.src('src/main/ui/img/**/*.*').pipe(gulp.dest('src/main/webapp/public/img')));
gulp.task('build-js', ['js-min', 'js-dev']);
gulp.task('default', ['install', 'build-js', 'less', 'build-img', 'build-fonts']);
