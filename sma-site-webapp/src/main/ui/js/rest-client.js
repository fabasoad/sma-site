export const REST_CLIENT_RESOURCE = { NEWS: 'news' };

let __newsRestClient = {};

let __resources = {
    news: __newsRestClient
};

function RestClientFactory() {
    return {
        get(restClientType) {
            return __resources[restClientType];
        }
    };
}

export let restClientFactory = new RestClientFactory();