import {restClient} from './../rest/news-rest-client.js';
import NewsEditableBuilder from './../news/editable/news-editable-builder.js';

restClient.getAll(data => {
    let div = document.getElementById('news-container');
    div.appendChild(new NewsEditableBuilder().build(data));
});