import RestClient from './rest-client.js';

export default function NewsRestClient() {
    this.__url = 'news';
}
NewsRestClient.prototype = RestClient;