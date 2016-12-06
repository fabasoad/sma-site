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

const jsBowerComponents = [
    {
        component: 'jquery',
        path: '/dist/'
    },
    {
        component: 'require.js',
        path: '/'
    },
    {
        component: 'system.js',
        path: '/dist/'
    },
    {
        component: 'bootstrap-fileinput',
        path: '/js/',
        themes: '/themes/**/'
    }
];
const cssBowerComponents = [
    {
        component: 'bootstrap',
        path: '/dist/css/'
    },
    {
        component: 'bootstrap-fileinput',
        path: '/css/'
    }
];

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
    for (let item of jsBowerComponents) {
        streams.push(gulp.src('bower_components/' + item.component + item.path + '*.js')
            .pipe(gulp.dest('src/main/webapp/public/js/' + item.component)));

        if (item.themes) {
            streams.push(gulp.src('bower_components/' + item.component + item.themes + '*.js')
                .pipe(gulp.dest('src/main/webapp/public/js/' + item.component + '/themes')));
        }
    }
    return merge(streams);
});

gulp.task('css-bower', () => {
    let streams = [];
    for (let item of cssBowerComponents) {
        streams.push(gulp.src('bower_components/' + item.component + item.path + '*.css')
            .pipe(gulp.dest('src/main/webapp/public/css/' + item.component)));
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
