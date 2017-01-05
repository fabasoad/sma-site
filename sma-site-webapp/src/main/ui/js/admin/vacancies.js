import {restClient} from '../rest/vacancies-rest-client.js';
import VacanciesLoader from '../vacancies/vacancies-loader.js';
import VacanciesEditableBuilder from '../vacancies/table/editable/vacancies-editable-builder.js';
import Constants from '../core/constants.js';
import BootboxAlert from '../core/bootbox-alert.js';
import VacancyDialogBox from '../vacancies/vacancy-dialog-box.js';

document.getElementById('vacancy-add-button').addEventListener('click', event => {
    VacancyDialogBox.show({}, {
        label: 'Create',
        callback: (obj, event) => {
            let result = true;
            restClient.create(obj, json => {
                if (json.type === 'validation-error') {
                    let findParent =
                        el => el.classList.contains('vacancy-labeled-group') ? el : findParent(el.parentNode);

                    result = false;
                    for (let error of json.errors) {
                        findParent(document.getElementById('vacancy-' + error.id).parentNode).classList.add('alert-error');
                    }
                } else {
                    BootboxAlert.show(json, refreshData);
                }
            });
            return result;
        }
    });
});

let editCallback = (item, event) => {
    VacancyDialogBox.show(item, {
        label: 'Save',
        callback: (obj, event) => {
            restClient.update(item['id'], obj, json => {
                BootboxAlert.show(json, refreshData);
            });
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
                restClient.delete(item['id'], json => {
                    BootboxAlert.show(json, refreshData);
                });
            }
        }
    });
};

let refreshData = () => VacanciesLoader.load(new VacanciesEditableBuilder(editCallback, removeCallback));
refreshData();