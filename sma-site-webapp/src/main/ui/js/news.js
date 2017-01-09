import NewsBuilder from './news/view/news-builder.js';
import NewsLoader from './news/news-loader.js';

let loadData = () => NewsLoader.load(new NewsBuilder());

window.addEventListener('hashchange', loadData);

loadData();