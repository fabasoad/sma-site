import NewsBuilder from './../news-builder.js';
import NewsEditableItem from './news-editable-item.js';

export default class NewsEditableBuilder extends NewsBuilder {

    constructor(editCallback, removeCallback) {
        super();
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    createNewsItem() {
        return new NewsEditableItem(this.editCallback, this.removeCallback);
    }
}