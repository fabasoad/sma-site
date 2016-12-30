import ApplicationFormRow from './application-form-row.js';

export default class ApplicationFormsBuilder {

    static build(json, removeCallback) {
        let tbody = document.createElement('tbody');
        for (let i = 0; i < json.data.length; i++) {
            tbody.appendChild(new ApplicationFormRow(json.data[i], removeCallback).build(i + 1));
        }
        return tbody;
    }
}