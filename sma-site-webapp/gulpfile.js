/**
* @author Yevhen Fabizhevskyi
* @date 16.11.2016.
*/
const gulp = require('gulp');
const install = require("gulp-install");
const concat = require('gulp-concat');
const uglify = require('gulp-uglify');
const babel = require('gulp-babel');
const merge = require('merge-stream');

const bowerComponents = ['jquery'];

gulp.task('default', () => gulp.src(['./bower.json', './package.json']).pipe(install()));

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