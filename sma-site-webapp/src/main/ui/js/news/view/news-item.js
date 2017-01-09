import NewsHeader from './news-header.js';
import NewsBody from './news-body.js';

export default class NewsItem {

    createNewsHeader() {
        return new NewsHeader();
    }

    build(item, minimized) {
        let div = document.createElement('div');
        div.setAttribute('class', 'panel');
        div.appendChild(this.createNewsHeader().build(item));
        div.appendChild(new NewsBody(item['id'], item['body'], minimized));
        return div;
    }
}