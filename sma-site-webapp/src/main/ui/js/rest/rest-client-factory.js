import NewsRestClient from './news-rest-client.js';

export const REST_CLIENT_RESOURCE = { NEWS: 'news' };

let __resources = {
    news: new NewsRestClient()
};

export function RestClientFactory() {
    return {
        get(type) {
            return __resources[type];
        }
    };
}