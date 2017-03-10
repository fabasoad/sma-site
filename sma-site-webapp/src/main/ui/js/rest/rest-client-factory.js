import RestClientBuilder from './rest-client-builder.js';

export const REST_CLIENT_RESOURCE = {
    NEWS: 'news',
    PARAMS: 'params',
    VACANCIES: 'vacancies',
    REFERENCES: 'references',
    APPLICATION_FORMS: 'application-forms',
    USERS: 'users',
    USER_ROLES: 'user-roles',
    CAROUSEL_IMAGES: 'carousel-images'
};

let __resources = {
    news: new RestClientBuilder('news')
        .add('getById')
        .add('getLimit')
        .add('create')
        .add('update')
        .add('delete')
        .build(),
    params: new RestClientBuilder('params')
        .add('getById')
        .add('update')
        .build(),
    vacancies: new RestClientBuilder('vacancies')
        .add('getAll')
        .add('getById')
        .add('create')
        .add('update')
        .add('delete')
        .build(),
    references: new RestClientBuilder('references')
        .add('getAll')
        .add('getById')
        .add('create')
        .add('update')
        .add('delete')
        .build(),
    'application-forms': new RestClientBuilder('application-forms')
        .add('getAll')
        .add('getById')
        .add('create')
        .add('update')
        .add('delete')
        .build(),
    users: new RestClientBuilder('users')
        .add('getAll')
        .add('getById')
        .add('create')
        .add('update')
        .add('delete')
        .add('patch')
        .build(),
    'user-roles': new RestClientBuilder('user-roles')
        .add('getAll')
        .add('getById')
        .build(),
    'carousel-images': new RestClientBuilder('carousel-images')
        .add('getAll')
        .add('getById')
        .add('create')
        .add('update')
        .add('delete')
        .build()
};

export class RestClientFactory {
    static get(type) {
        return __resources[type];
    }
}