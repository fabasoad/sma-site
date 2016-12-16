import {restClient} from './rest/news-rest-client.js';
import NewsBuilder from './news/news-builder.js';

restClient.getAll(data => {
    let div = document.getElementById('news-container');
    div.appendChild(NewsBuilder.build(data));
});