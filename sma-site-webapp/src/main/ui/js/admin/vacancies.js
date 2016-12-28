import {restClient} from '../rest/vacancies-rest-client.js';
import VacanciesLoader from '../vacancies/vacancies-loader.js';
import VacanciesEditableBuilder from '../vacancies/table/editable/vacancies-editable-builder.js';
import Constants from '../core/constants.js';

let showMessage = data => {
    let config = {
        message: data.message,
        title: Constants.APPLICATION_NAME
    };
    if (data.type === 'error') {
        config.className = 'alert-error';
    } else {
        config.callback = refreshData;
    }
    bootbox.alert(config);
};

let editCallback = (item, event) => {
    bootbox.dialog({
        title: Constants.APPLICATION_NAME,
        message: `
            <div class="form-group">
                <label for="vacancy-rank">Rank:</label>
                <input id="vacancy-rank" type="text" class="form-control" placeholder="` + item['rank'] + `"/>
            </div>
            <div class="form-group">
                <label for="vacancy-vessel-type">Vessel Type:</label>
                <input id="vacancy-vessel-type" type="text" class="form-control" placeholder="` + item['vessel-type'] + `"/>
            </div>
            <div class="raw">
                <div class="col-sm-6 form-group">
                    <label for="vacancy-joining-date">Joining Date:</label>
                    <input id="vacancy-joining-date" type="text" class="form-control" placeholder="` + item['joining-date'] + `"/>
                </div>     
                <div class="col-sm-6 form-group">
                    <label for="vacancy-nationality">Nationality:</label>
                    <input id="vacancy-nationality" type="text" class="form-control" placeholder="` + item['nationality'] + `"/>
                </div>                   
            </div>
            <div class="form-group">
                <label for="vacancy-contract-duration">Contract Duration:</label>
                <input id="vacancy-contract-duration" type="text" class="form-control" placeholder="` + item['contract-duration'] + `"/>
            </div>
            <div class="form-group">
                <label for="vacancy-wage">Wage:</label>
                <input id="vacancy-wage" type="text" class="form-control" placeholder="` + item['wage'] + `"/>
            </div>
            <div class="form-group">
                <label for="vacancy-description">Description:</label>
                <textarea id="vacancy-description" rows="5" class="form-control" placeholder="` + item['description'] + `"/>
            </div>
        `,
        buttons: {
            cancel: {
                label: 'Cancel',
                className: 'btn-default'
            },
            confirm: {
                label: 'Save',
                className: 'btn-success',
                callback: event => {
                    let getVacancyValue = property => document.getElementById('vacancy-' + property).value;

                    let obj = {
                        'rank': getVacancyValue('rank') || item['rank'],
                        'vessel-type': getVacancyValue('vessel-type') || item['vessel-type'],
                        'joining-date': getVacancyValue('joining-date') || item['joining-date'],
                        'contract-duration': getVacancyValue('contract-duration') || item['contract-duration'],
                        'nationality': getVacancyValue('nationality') || item['nationality'],
                        'wage': getVacancyValue('wage') || item['wage'],
                        'description': getVacancyValue('description') || item['description'],
                    };

                    restClient.update(item['id'], obj, json => {
                        showMessage(json);
                    });
                }
            }
        }
    });
};

let removeCallback = (item, event) => {
    bootbox.confirm({
        title: Constants.APPLICATION_NAME,
        message: 'Do you really want to remove "' + item['rank'] + ' (' + item['wage'] + ')" record?',
        buttons: {
            cancel: {
                label: 'No',
                className: 'btn-success'
            },
            confirm: {
                label: 'Yes',
                className: 'btn-danger'
            }
        },
        callback: result => {
            if (result) {
                restClient.delete(item['id'], data => {
                    showMessage(data);
                });
            }
        }
    });
};

let refreshData = () => VacanciesLoader.load(new VacanciesEditableBuilder(editCallback, removeCallback));
refreshData();