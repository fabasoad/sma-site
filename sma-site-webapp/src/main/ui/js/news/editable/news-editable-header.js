import NewsHeader from './../news-header.js';
import NewsEditableTitle from './news-editable-title.js';
import NewsEditableCreationDate from './news-editable-creation-date.js';
import NewsEditableControlPanel from './news-editable-control-panel.js';

export default class NewsEditableHeader extends NewsHeader {

    constructor(editCallback, removeCallback) {
        super();
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    createNewsTitle() {
        return new NewsEditableTitle();
    }

    createNewsCreationDate() {
        return new NewsEditableCreationDate();
    }

    createNewsControlPanel() {
        return new NewsEditableControlPanel(this.editCallback, this.removeCallback);
    }
}
