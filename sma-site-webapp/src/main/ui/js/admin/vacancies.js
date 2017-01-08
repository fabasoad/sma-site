import {restClient} from '../rest/vacancies-rest-client.js';
import VacanciesLoader from '../vacancies/vacancies-loader.js';
import VacanciesEditableBuilder from '../vacancies/table/editable/vacancies-editable-builder.js';
import Constants from '../core/constants.js';
import BootboxAlert from '../core/bootbox-alert.js';
import VacancyDialogBox from '../vacancies/vacancy-dialog-box.js';

document.getElementById('vacancy-confirm-button').addEventListener('click', event => {
    new VacancyDialogBox({}).show({
        label: 'Create',
        callback: (obj, event) => {
            let result = true;

            let clearErrors = () => {
                $('div[id^="vacancy-error-"]').html('');
                let labeledGroups = document.getElementsByClassName('vacancy-labeled-group');
                for (let i = 0; i < labeledGroups.length; i++) {
                    labeledGroups.item(i).classList.remove('alert-error');
                }
            };

            clearErrors();

            restClient.create(obj, json => {
                if (json.type === 'validation-error') {
                    let findParent =
                        el => el.classList.contains('vacancy-labeled-group') ? el : findParent(el.parentNode);

                    result = false;
                    for (let error of json.errors) {
                        let errorDiv = document.getElementById('vacancy-error-' + error.id);
                        errorDiv.innerHTML = error.message;
                        findParent(errorDiv.parentNode).classList.add('alert-error');
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
    new VacancyDialogBox(item).show({
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