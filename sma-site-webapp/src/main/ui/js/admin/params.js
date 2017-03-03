import {restClient} from '../rest/params-rest-client.js';
import Constants from '../core/constants.js';
import BootboxAlert from '../core/bootbox-alert.js';

/* Company name handling */
restClient.getById(Constants.PARAMS.COMPANY_NAME, data => {
    document.getElementById('param-company-name').value = data.body;
});

document.getElementById('param-company-name-save-button').addEventListener('click', event => {
    restClient.update(
        Constants.PARAMS.COMPANY_NAME,
        { body: document.getElementById('param-company-name').value },
        data => { BootboxAlert.show(data); }
    );
});

/* Footer year handling */
restClient.getById(Constants.PARAMS.FOOTER_YEAR, data => {
    document.getElementById('param-footer-year').value = data.body;
});

document.getElementById('param-footer-year-save-button').addEventListener('click', event => {
    restClient.update(
        Constants.PARAMS.FOOTER_YEAR,
        { body: document.getElementById('param-footer-year').value },
        data => { BootboxAlert.show(data); }
    );
});