import {restClient} from './rest/params-rest-client.js';
import Constants from './core/constants.js';

restClient.getById(Constants.PARAMS.COMPANY_NAME, companyNameData => {
    restClient.getById(Constants.PARAMS.FOOTER_YEAR, footerYearData => {
        document.querySelector('.footer p').innerHTML = companyNameData.body + ' &copy ' + footerYearData.body;
    });
});