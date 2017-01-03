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
const cssmin = require('gulp-cssmin');

gulp.task('install', () => gulp.src(['./bower.json', './package.json']).pipe(install()));

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

gulp.task('css-bower', () => {
    let streams = [];
    let locations = [
        'bower_components/bootstrap/dist/css/bootstrap.min.css',
        'bower_components/bootstrap-fileinput/css/fileinput.min.css',
        'bower_components/ekko-lightbox/dist/ekko-lightbox.min.css',
        'bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css'
    ];
    for (let location of locations) {
        streams.push(gulp.src(location).pipe(gulp.dest('src/main/webapp/public/css')));
    }
    return merge(streams);
});

gulp.task('js-bower', () => {
    let streams = [];
    let locations = [
        'bower_components/bootbox.js/bootbox.min.js',
        'bower_components/bootstrap/dist/js/bootstrap.min.js',
        'bower_components/bootstrap/js/carousel.js',
        'bower_components/bootstrap/js/modal.js',
        'bower_components/bootstrap-fileinput/js/fileinput.min.js',
        'bower_components/bootstrap-wysiwyg/bootstrap-wysiwyg.js',
        'bower_components/bootstrap-wysiwyg/external/jquery.hotkeys.js',
        'bower_components/ekko-lightbox/dist/ekko-lightbox.min.js',
        'bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js',
        'bower_components/jquery/dist/jquery.min.js',
        'bower_components/moment/min/moment.min.js',
        'bower_components/require.js/require.js',
        'bower_components/system.js/dist/system.js'
    ];
    for (let location of locations) {
        streams.push(gulp.src(location).pipe(gulp.dest('src/main/webapp/public/js')));
    }
    return merge(streams);
});

gulp.task('fonts-bower', () =>
    gulp.src('bower_components/bootstrap/fonts/**/*.{eot,svg,ttf,woff,woff2}')
        .pipe(gulp.dest('src/main/webapp/public/fonts'))
);

gulp.task('images-bower', () =>
    gulp.src('bower_components/bootstrap-fileinput/img/**/*.*')
        .pipe(gulp.dest('src/main/webapp/public/img'))
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
gulp.task('build-bower', ['js-bower', 'css-bower', 'fonts-bower', 'images-bower']);
gulp.task('default', ['install', 'build-js', 'less', 'build-bower', 'build-img', 'build-fonts']);
