export default class VacanciesRow {

    constructor(vacancy, showDetailsCallback) {
        this.vacancy = vacancy;
        this.showDetailsCallback = showDetailsCallback;
    }

    createEditColumn() {
    }

    createRemoveColumn() {
    }

    buildRank() {
        let button = document.createElement('button');
        button.setAttribute('type', 'button');
        button.classList.add('btn');
        button.classList.add('btn-link');
        button.innerHTML = this.vacancy['rank'];
        button.addEventListener('click', event => this.showDetailsCallback(this.vacancy, event));
        return button;
    }

    build(index) {
        let tr = document.createElement('tr');

        let th = document.createElement('th');
        th.setAttribute('scope', 'row');
        th.innerHTML = index;
        tr.appendChild(th);

        let rank = document.createElement('td');
        rank.appendChild(this.buildRank());
        tr.appendChild(rank);

        let vesselType = document.createElement('td');
        vesselType.innerHTML = this.vacancy['vessel-type'];
        tr.appendChild(vesselType);

        let joiningDate = document.createElement('td');
        joiningDate.innerHTML = this.vacancy['joining-date'];
        tr.appendChild(joiningDate);

        let contractDuration = document.createElement('td');
        contractDuration.innerHTML = this.vacancy['contract-duration'];
        tr.appendChild(contractDuration);

        let nationality = document.createElement('td');
        nationality.innerHTML = this.vacancy['nationality'];
        tr.appendChild(nationality);

        let wage = document.createElement('td');
        wage.innerHTML = this.vacancy['wage'];
        tr.appendChild(wage);

        let editColumn = this.createEditColumn();
        if (editColumn) {
            tr.appendChild(editColumn)
        }

        let removeColumn = this.createRemoveColumn();
        if (removeColumn) {
            tr.appendChild(removeColumn)
        }

        return tr;
    }
}
