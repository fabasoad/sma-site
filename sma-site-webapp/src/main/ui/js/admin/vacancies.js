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
                <div class="col-sm-3 form-group vacancy-form-group">
                    <label for="vacancy-joining-date">Joining Date:</label>
                    <input id="vacancy-joining-date" type="text" class="form-control" placeholder="` + item['joining-date'] + `"/>
                </div>     
                <div class="col-sm-1"></div>
                <div class="col-sm-8 form-group vacancy-form-group">
                    <label for="vacancy-nationality">Nationality:</label>
                    <input id="vacancy-nationality" type="text" class="form-control" placeholder="` + item['nationality'] + `"/>
                </div>                   
            </div>
            <div class="raw">
                <div class="col-sm-5 form-group vacancy-form-group">
                    <label for="vacancy-contract-duration">Contract Duration:</label>
                    <input id="vacancy-contract-duration" type="text" class="form-control" placeholder="` + item['contract-duration'] + `"/>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-6 form-group vacancy-form-group">
                    <label for="vacancy-wage">Wage:</label>
                    <input id="vacancy-wage" type="text" class="form-control" placeholder="` + item['wage'] + `"/>
                </div>                   
            </div>
            <div class="form-group">
                <label for="vacancy-description">Description:</label>
                <textarea id="vacancy-description" rows="4" class="form-control" placeholder="` + item['description'] + `"/>
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
                    let getVacancyValue =
                        property => document.getElementById('vacancy-' + property).value || item[property];

                    let properties = [
                        'rank', 'vessel-type', 'joining-date', 'contract-duration', 'nationality', 'wage', 'description'
                    ];
                    let obj = {};
                    for (let property of properties) {
                        obj[property] = getVacancyValue(property);
                    }

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