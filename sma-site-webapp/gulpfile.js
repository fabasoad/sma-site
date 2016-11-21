/**
* @author Yevhen Fabizhevskyi
* @date 16.11.2016.
*/
const gulp = require('gulp');
const install = require("gulp-install");
const uglify = require('gulp-uglify');
const babel = require('gulp-babel');
const merge = require('merge-stream');
const less = require('gulp-less');
const watch = require('gulp-watch');

const bowerComponents = ['jquery'];

gulp.task('install', () => gulp.src(['./bower.json', './package.json']).pipe(install()));

gulp.task('less', function () {
  return gulp.src('./src/main/ui/styles/**/*.less')
    .pipe(less())
    .pipe(gulp.dest('./src/main/webapp/public/styles'));
});

gulp.task('watch-css', function () {
	 gulp.watch('./src/main/ui/styles/**/*.less', ['less']);
});

gulp.task('bower-min', () => {
    let streams = [];
    for (let component of bowerComponents) {
        streams.push(gulp.src('bower_components/' + component + '/dist/' + component + '.min.js')
            .pipe(gulp.dest('src/main/webapp/public/js/lib')));
    }
    return merge(streams);
});

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

gulp.task('build-js', ['js-min', 'js-dev', 'bower-min']);
gulp.task('default', ['install', 'build-js', 'less']);
