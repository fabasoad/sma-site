import NewsBuilder from './../news-builder.js';
import NewsEditableItem from './news-editable-item.js';

export default class NewsEditableBuilder extends NewsBuilder {

    createNewsItem() {
        return new NewsEditableItem();
    }
}