const APPLICATION_NAME = 'Southern Maritime Agency';

export default class Constants {

    static get APPLICATION_NAME() {
        return APPLICATION_NAME;
    }

    static get PARAMS() {
        return {
            MAIN: 'SMA_MAIN_BODY',
            CONTACTS: 'SMA_CONTACTS_BODY',
            COMPANY_NAME: 'SMA_COMPANY_NAME',
            FOOTER_YEAR: 'SMA_FOOTER_YEAR'
        };
    }
}