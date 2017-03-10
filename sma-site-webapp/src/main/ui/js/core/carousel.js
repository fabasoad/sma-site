import CarouselLoader from '../carousel/carousel-loader.js';

CarouselLoader.loadView(json => {
    if (json['total-count'] > 0) {
        let $carouselElement = $('#sma-carousel');
        $carouselElement.carousel({ interval: 4000 });
        $carouselElement.removeClass('hide');
    }
});