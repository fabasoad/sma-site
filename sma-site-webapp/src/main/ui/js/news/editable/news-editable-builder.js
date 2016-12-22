import NewsBuilder from './../news-builder.js';
import NewsEditableItem from './news-editable-item.js';
import NewsEditableAddPanel from './news-editable-add-panel.js';

export default class NewsEditableBuilder extends NewsBuilder {

    constructor(addCallback, editCallback, removeCallback) {
        super();
        this.addCallback = addCallback;
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    createNewsAddPanel() {
        return new NewsEditableAddPanel(this.addCallback);
    }

    createNewsItem() {
        return new NewsEditableItem(this.editCallback, this.removeCallback);
    }
}