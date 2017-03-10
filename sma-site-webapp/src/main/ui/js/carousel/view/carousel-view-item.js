export default class CarouselViewItem {

    constructor(item, active) {
        let container = document.createElement('div');
        container.classList.add('item');
        if (active) {
            container.classList.add('active');
        }

        let img = document.createElement('img');
        img.setAttribute('src', item.src);
        container.appendChild(img);

        if (item.title) {
            let div = document.createElement('div');
            div.classList.add('carousel-caption');

            let h3 = document.createElement('h3');
            h3.appendChild(document.createTextNode(captionObj['header']));
            div.appendChild(h3);

            let p = document.createElement('p');
            p.appendChild(document.createTextNode(captionObj['body']));
            div.appendChild(p);

            container.appendChild(div);
        }

        return container;
    }
}