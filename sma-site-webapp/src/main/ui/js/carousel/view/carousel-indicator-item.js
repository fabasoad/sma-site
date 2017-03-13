export default class CarouselIndicatorItem {

    constructor(index, active) {
        let li = document.createElement('li');
        li.setAttribute('data-target', '#sma-carousel');
        li.setAttribute('data-slide-to', index);
        if (active) {
            li.classList.add('active')
        }
        return li;
    }
}