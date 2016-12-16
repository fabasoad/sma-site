import NewsHeader from './news-header.js';
import NewsBody from './news-body.js';

export default class NewsItem {
    static build(item) {
        let div = document.createElement('div');
        div.setAttribute('class', 'panel');
        div.appendChild(NewsHeader.build(item['title'], item['creation-date']));
        div.appendChild(NewsBody.build(item['id'], item['body']));
        return div;
    }
}