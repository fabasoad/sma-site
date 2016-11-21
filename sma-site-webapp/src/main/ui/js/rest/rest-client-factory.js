import RestClientBuilder from './rest-client-builder.js';

export const REST_CLIENT_RESOURCE = { NEWS: 'news', MAIN: 'main', VACANCIES: 'vacancies' };

let __resources = {
    news: new RestClientBuilder('news')
        .add('getAll')
        .add('getById')
        .add('create')
        .add('update')
        .add('delete')
        .build(),
    main: new RestClientBuilder('main')
        .add('getAll')
        .add('update')
        .build(),
    vacancies: new RestClientBuilder('vacancies')
        .add('getAll')
        .add('getById')
        .add('create')
        .add('update')
        .add('delete')
        .build()
};

export function RestClientFactory() {
    return {
        get(type) {
            return __resources[type];
        }
    };
}