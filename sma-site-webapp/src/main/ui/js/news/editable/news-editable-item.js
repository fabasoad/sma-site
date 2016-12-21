import NewsItem from './../news-item.js';
import NewsEditableHeader from './news-editable-header.js';

export default class NewsEditableItem extends NewsItem {

    createNewsHeader() {
        return new NewsEditableHeader();
    }
}