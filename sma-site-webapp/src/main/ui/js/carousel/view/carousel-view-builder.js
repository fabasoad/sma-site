import CarouselViewItem from './carousel-view-item.js';
import CarouselIndicatorItem from './carousel-indicator-item.js';

export default class CarouselViewBuilder {

    static build(data) {
        let carouselContainer = document.querySelector('#sma-carousel > .carousel-inner');
        let carouselIndicators = document.querySelector('#sma-carousel > .carousel-indicators');
        for (let i = 0; i < data.length; i++) {
            carouselContainer.appendChild(new CarouselViewItem(data[i], i === 0));
            carouselIndicators.appendChild(new CarouselIndicatorItem(i, i === 0));
        }
    }
}