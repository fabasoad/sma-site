import NewsItem from './../news-item.js';
import NewsEditableHeader from './news-editable-header.js';

export default class NewsEditableItem extends NewsItem {

    constructor(editCallback, removeCallback) {
        super();
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    createNewsHeader() {
        return new NewsEditableHeader(this.editCallback, this.removeCallback);
    }
}