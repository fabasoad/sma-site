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
const concat = require('gulp-concat');

const jsBowerComponents = ['jquery', 'require.js', 'system.js'];
const cssBowerComponents = ['bootstrap'];

gulp.task('install', () => gulp.src(['./bower.json', './package.json']).pipe(install()));

gulp.task('less', function () {
  return gulp.src('./src/main/ui/less/**/*.less')
    .pipe(less())
    .pipe(gulp.dest('./src/main/webapp/public/css'));
});

gulp.task('watch-css', function () {
	 gulp.watch('./src/main/ui/less/**/*.less', ['less']);
});

gulp.task('js-bower', () => {
    let streams = [];
    for (let component of jsBowerComponents) {
        streams.push(gulp.src('bower_components/' + component + '/dist/*.js')
            .pipe(gulp.dest('src/main/webapp/public/js/lib')));
    }
    return merge(streams);
});

gulp.task('css-bower', () => {
    let streams = [];
    for (let component of cssBowerComponents) {
        streams.push(gulp.src('bower_components/' + component + '/dist/css/*.css')
            .pipe(gulp.dest('src/main/webapp/public/css/' + component)));
    }
    return merge(streams);
});

gulp.task('js-min', () =>
    gulp.src('src/main/ui/js/main/rest/**/*.js')
        .pipe(babel({
            presets: ['es2015']
        }))
        .pipe(uglify())
        .pipe(gulp.dest('src/main/webapp/public/js/min'))
);

gulp.task('js-dev', () =>
    gulp.src('src/main/ui/js/main/rest/**/*.js')
        .pipe(babel({
            presets: ['es2015']
        }))
        .pipe(gulp.dest('src/main/webapp/public/js/dev'))
);

gulp.task('watch-js', () => gulp.watch('src/main/ui/js/main/rest/**/*.js', ['js-dev']));

gulp.task('build-js', ['js-min', 'js-dev', 'js-bower']);
gulp.task('build-css', ['less', 'css-bower']);
gulp.task('default', ['install', 'build-js', 'build-css']);
