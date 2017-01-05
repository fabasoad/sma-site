import Constants from '../core/constants.js';

let handleCallback = (callback, item, event) => {
    let result = true;
    if (typeof callback === 'function') {
        let getVacancyValue =
            property => document.getElementById('vacancy-' + property).value || item[property];

        let properties = [
            'rank', 'vessel-type', 'joining-date', 'contract-duration', 'nationality', 'wage', 'description'
        ];
        let obj = {};
        for (let property of properties) {
            obj[property] = getVacancyValue(property);
        }

        result = callback(obj, event);
    }
    return result;
};

export default class VacancyDialogBox {

    static show(item, confirmButton) {
        bootbox.dialog({
            title: Constants.APPLICATION_NAME,
            onEscape: true,
            message: `
                <div class="form-group vacancy-labeled-group">
                    <label for="vacancy-rank">Rank</label>
                    <div id="vacancy-error-rank" class="alert-danger"></div>
                    <input id="vacancy-rank" type="text" class="form-control" placeholder="` + (item['rank'] || '') + `"/>
                </div>
                <div class="form-group vacancy-labeled-group">
                    <label for="vacancy-vessel-type">Vessel Type</label>
                    <div id="vacancy-error-vessel-type" class="alert-danger"></div>
                    <input id="vacancy-vessel-type" type="text" class="form-control" placeholder="` + (item['vessel-type'] || '') + `"/>
                </div>
                <div class="row">
                    <div class="col-sm-4 form-group vacancy-labeled-group">
                        <label for="vacancy-joining-date">Joining Date</label>
                        <div id="vacancy-error-joining-date" class="alert-danger"></div>
                        <div class="input-group date">
                            <input id="vacancy-joining-date" type="text" class="form-control" placeholder="` + (item['joining-date'] || '') + `"/>
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>     
                    <div class="col-sm-1"></div>
                    <div class="col-sm-7 form-group vacancy-labeled-group">
                        <label for="vacancy-nationality">Nationality</label>
                        <div id="vacancy-error-nationality" class="alert-danger"></div>
                        <input id="vacancy-nationality" type="text" class="form-control" placeholder="` + (item['nationality'] || '') + `"/>
                    </div>                   
                </div>
                <div class="row">
                    <div class="col-sm-5 form-group vacancy-labeled-group">
                        <label for="vacancy-contract-duration">Contract Duration</label>
                        <div id="vacancy-error-contract-duration" class="alert-danger"></div>
                        <input id="vacancy-contract-duration" type="text" class="form-control" placeholder="` + (item['contract-duration'] || '') + `"/>
                    </div>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-6 form-group vacancy-labeled-group">
                        <label for="vacancy-wage">Wage</label>
                        <div id="vacancy-error-wage" class="alert-danger"></div>
                        <input id="vacancy-wage" type="text" class="form-control" placeholder="` + (item['wage'] || '') + `"/>
                    </div>   
                </div>
                <div class="form-group vacancy-labeled-group">
                    <label for="vacancy-description">Description</label>
                    <div id="vacancy-error-description" class="alert-danger"></div>
                    <textarea id="vacancy-description" rows="4" class="form-control" placeholder="` + (item['description'] || '') + `"/>
                </div>
                <script>
                    $('#vacancy-joining-date').parent().datetimepicker({
                        ` + (item['joining-date'] ? 'defaultDate: "' + item['joining-date'] + '",' : '') + `
                        minDate: moment(),
                        format: 'YYYY-MM-DD'
                    });
                </script>
            `,
            buttons: {
                cancel: {
                    label: 'Cancel',
                    className: 'btn-default'
                },
                confirm: {
                    label: confirmButton.label,
                    className: 'btn-success',
                    callback: event => {
                        return handleCallback(confirmButton.callback, item, event);
                    }
                }
            }
        });
    }
}