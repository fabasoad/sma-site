import NewsBuilder from './news/view/news-builder.js';
import NewsLoader from './news/news-loader.js';

let loader = new NewsLoader();
let loadData = () => loader.load(new NewsBuilder());

window.addEventListener('hashchange', loadData);

loadData();