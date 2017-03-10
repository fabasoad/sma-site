import CarouselViewItem from './carousel-view-item.js';

export default class CarouselViewBuilder {

    static build(data) {
        let carousel = document.querySelector('#sma-carousel > .carousel-inner');
        for (let i = 0; i < data.length; i++) {
            carousel.appendChild(new CarouselViewItem(data[i], i === 0));
        }
    }
}