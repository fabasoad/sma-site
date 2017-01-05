import VacanciesLoader from './vacancies/vacancies-loader.js';
import VacanciesBuilder from './vacancies/table/vacancies-builder.js';
import Constants from './core/constants.js';

let showDetailsCallback = (item, event) => {
    bootbox.dialog({
        title: Constants.APPLICATION_NAME,
        message: `
            <div class="container vacancy-details">
                <div class="row">
                    <div class="col-sm-4"><strong>Rank:</strong></div>
                    <div class="col-sm-8">` + item['rank'] + `</div>
                </div>
                <div class="row">
                    <div class="col-sm-4"><strong>Vessel Type:</strong></div>
                    <div class="col-sm-8">` + item['vessel-type'] + `</div>
                </div>
                <div class="row">
                    <div class="col-sm-4"><strong>Joining Date:</strong></div>
                    <div class="col-sm-8">` + item['joining-date'] + `</div>
                </div>
                <div class="row">
                    <div class="col-sm-4"><strong>Nationality:</strong></div>
                    <div class="col-sm-8">` + item['nationality'] + `</div>
                </div>
                <div class="row">
                    <div class="col-sm-4"><strong>Contract Duration:</strong></div>
                    <div class="col-sm-8">` + item['contract-duration'] + `</div>
                </div>
                <div class="row">
                    <div class="col-sm-4"><strong>Wage:</strong></div>
                    <div class="col-sm-8">` + item['wage'] + `</div>
                </div>
                <div class="row">
                    <div class="col-sm-4"><strong>Description:</strong></div>
                    <div class="col-sm-8">` + item['description'] + `</div>
                </div>
            </div>
        `,
        buttons: {
            cancel: {
                label: 'OK',
                className: 'btn-primary'
            }
        }
    });
};

VacanciesLoader.load(new VacanciesBuilder(showDetailsCallback));