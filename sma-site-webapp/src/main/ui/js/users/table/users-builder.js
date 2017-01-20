import UsersRow from './users-row.js';

export default class UsersBuilder {

    constructor(editCallback, removeCallback) {
        this.editCallback = editCallback;
        this.removeCallback = removeCallback;
    }

    build(json) {
        let tbody = document.createElement('tbody');
        for (let i = 0; i < json.data.length; i++) {
            tbody.appendChild(new UsersRow(json.data[i], this.editCallback, this.removeCallback).build(i + 1));
        }
        return tbody;
    }
}