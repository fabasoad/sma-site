import RestClientBuilder from './rest-client-builder.js';

export const REST_CLIENT_RESOURCE = {
    NEWS: 'news',
    MAIN: 'main',
    VACANCIES: 'vacancies',
    REFERENCES: 'references',
    CONTACTS: 'contacts',
    APPLICATION_FORMS: 'application-forms',
    USERS: 'users'
};

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
        .build(),
    references: new RestClientBuilder('references')
        .add('getAll')
        .add('getById')
        .add('create')
        .add('update')
        .add('delete')
        .build(),
    contacts: new RestClientBuilder('contacts')
        .add('getAll')
        .add('update')
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
        .build()
};

export class RestClientFactory {
    static get(type) {
        return __resources[type];
    }
}