export default class VacanciesRow {
    constructor(vacancy) {
        this.vacancy = vacancy;
    }

    build(index) {
        let tr = document.createElement('tr');

        let th = document.createElement('th');
        th.setAttribute('scope', 'row');
        th.innerHTML = index;
        tr.appendChild(th);

        let rank = document.createElement('td');
        rank.innerHTML = this.vacancy['rank'];
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

        return tr;
    }
}
