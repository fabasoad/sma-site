import NewsHeader from './../news-header.js';
import NewsEditableTitle from './news-editable-title.js';
import NewsEditableCreationDate from './news-editable-creation-date.js';
import NewsEditableEditButton from './news-editable-edit-button.js';

export default class NewsEditableHeader extends NewsHeader {

    createNewsTitle() {
        return new NewsEditableTitle();
    }

    createNewsCreationDate() {
        return new NewsEditableCreationDate();
    }

    createNewsEditButton() {
        return new NewsEditableEditButton();
    }
}
