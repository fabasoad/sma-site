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

const jsBowerComponents = {
    target: 'js',
    extensions: ['js'],
    data: [
        {
            component: 'jquery',
            locations: [{
                from: '/dist/',
                to: '/'
            }]
        },
        {
            component: 'require.js',
            locations: [{
                from: '/',
                to: '/'
            }]
        },
        {
            component: 'system.js',
            locations: [{
                from: '/dist/',
                to: '/'
            }]
        },
        {
            component: 'bootstrap',
            locations: [{
                from: '/dist/js/',
                to: '/'
            },{
                from: '/js/',
                to: '/'
            }]
        },
        {
            component: 'bootstrap-fileinput',
            locations: [{
                from: '/js/',
                to: '/'
            }]
        },
        {
            component: 'ekko-lightbox',
            locations: [{
                from: '/dist/',
                to: '/'
            }]
        }
    ]
};
const cssBowerComponents = {
    target: 'css',
    extensions: ['css'],
    data: [
        {
            component: 'bootstrap',
            locations: [{
                from: '/dist/css/',
                to: '/'
            }]
        },
        {
            component: 'bootstrap-fileinput',
            locations: [{
                from: '/css/',
                to: '/'
            }]
        },
        {
            component: 'ekko-lightbox',
            locations: [{
                from: '/dist/',
                to: '/'
            }]
        }
    ]
};
const fontsBowerComponents = {
    target: 'css',
    extensions: ['eot','svg','ttf','woff','woff2'],
    data: [
        {
            component: 'bootstrap',
            locations: [{
                from: '/fonts/',
                to: '/../fonts/'
            }]
        }
    ]
};
const imagesBowerComponents = {
    target: 'css',
    extensions: ['gif'],
    data: [
        {
            component: 'bootstrap-fileinput',
            locations: [{
                from: '/img/',
                to: '/../img/'
            }]
        }
    ]
};

gulp.task('install', () => gulp.src(['./bower.json', './package.json']).pipe(install()));

gulp.task('less', function () {
  return gulp.src('./src/main/ui/less/**/*.less')
    .pipe(less())
    .pipe(gulp.dest('./src/main/webapp/public/css'));
});

gulp.task('watch-css', function () {
	 gulp.watch('./src/main/ui/less/**/*.less', ['less']);
});

let buildBower = components => {
    let streams = [];
    for (let extension of components.extensions) {
        for (let item of components.data) {
            for (let location of item.locations) {
                streams.push(gulp.src('bower_components/' + item.component + location.from + '*.' + extension)
                    .pipe(gulp.dest('src/main/webapp/public/' + components.target + '/' + item.component + location.to)));
            }
        }
    }
    return merge(streams);
};

gulp.task('js-bower', () => buildBower(jsBowerComponents));
gulp.task('css-bower', () => buildBower(cssBowerComponents));
gulp.task('fonts-bower', () => buildBower(fontsBowerComponents));
gulp.task('images-bower', () => buildBower(imagesBowerComponents));

gulp.task('js-min', () =>
    gulp.src('src/main/ui/js/main/**/*.js')
        .pipe(babel({
            presets: ['es2015']
        }))
        .pipe(uglify())
        .pipe(gulp.dest('src/main/webapp/public/js/min'))
);

gulp.task('js-dev', () =>
    gulp.src('src/main/ui/js/main/**/*.js')
        .pipe(babel({
            presets: ['es2015']
        }))
        .pipe(gulp.dest('src/main/webapp/public/js/dev'))
);

gulp.task('watch-js', () => gulp.watch('src/main/ui/js/main/rest/**/*.js', ['js-dev']));

gulp.task('build-js', ['js-min', 'js-dev']);
gulp.task('build-bower', ['js-bower', 'css-bower', 'fonts-bower', 'images-bower']);
gulp.task('default', ['install', 'build-js', 'less', 'build-bower']);
