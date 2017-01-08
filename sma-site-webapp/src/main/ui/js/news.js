import {restClient} from './rest/news-rest-client.js';
import NewsBuilder from './news/view/news-builder.js';

restClient.getAll(data => {
    let div = document.getElementById('news-container');
    div.appendChild(new NewsBuilder().build(data));
});