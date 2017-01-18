import BaseDialogBox from '../core/base-dialog-box.js';

export default class VacancyDialogBox extends BaseDialogBox {

    constructor(item) {
        super({
            id: 'vacancy',
            message: `
                <div class="form-group vacancy-labeled-group">
                    <label for="vacancy-rank">Rank</label>
                    <div id="vacancy-error-rank" class="alert-danger"></div>
                    <input id="vacancy-rank" type="text" class="form-control" value="` + (item['rank'] || '') + `" placeholder="` + (item['rank'] || '') + `"/>
                </div>
                <div class="form-group vacancy-labeled-group">
                    <label for="vacancy-vessel-type">Vessel Type</label>
                    <div id="vacancy-error-vessel-type" class="alert-danger"></div>
                    <input id="vacancy-vessel-type" type="text" class="form-control" value="` + (item['vessel-type'] || '') + `" placeholder="` + (item['vessel-type'] || '') + `"/>
                </div>
                <div class="row">
                    <div class="col-sm-4 form-group vacancy-labeled-group">
                        <label for="vacancy-joining-date">Joining Date</label>
                        <div id="vacancy-error-joining-date" class="alert-danger"></div>
                        <div class="input-group date">
                            <input id="vacancy-joining-date" type="text" class="form-control" value="` + (item['joining-date'] || '') + `" placeholder="` + (item['joining-date'] || '') + `"/>
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>     
                    <div class="col-sm-1"></div>
                    <div class="col-sm-7 form-group vacancy-labeled-group">
                        <label for="vacancy-nationality">Nationality</label>
                        <div id="vacancy-error-nationality" class="alert-danger"></div>
                        <input id="vacancy-nationality" type="text" class="form-control" value="` + (item['nationality'] || '') + `" placeholder="` + (item['nationality'] || '') + `"/>
                    </div>                   
                </div>
                <div class="row">
                    <div class="col-sm-5 form-group vacancy-labeled-group">
                        <label for="vacancy-contract-duration">Contract Duration</label>
                        <div id="vacancy-error-contract-duration" class="alert-danger"></div>
                        <input id="vacancy-contract-duration" type="text" class="form-control" value="` + (item['contract-duration'] || '') + `" placeholder="` + (item['contract-duration'] || '') + `"/>
                    </div>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-6 form-group vacancy-labeled-group">
                        <label for="vacancy-wage">Wage</label>
                        <div id="vacancy-error-wage" class="alert-danger"></div>
                        <input id="vacancy-wage" type="text" class="form-control" value="` + (item['wage'] || '') + `" placeholder="` + (item['wage'] || '') + `"/>
                    </div>   
                </div>
                <div class="form-group vacancy-labeled-group">
                    <label for="vacancy-description">Description</label>
                    <div id="vacancy-error-description" class="alert-danger"></div>
                    <textarea id="vacancy-description" rows="4" class="form-control" placeholder="` + (item['description'] || '') + `">` + (item['description'] || '') + `</textarea>
                </div>
                <script>
                    $('#vacancy-joining-date').parent().datetimepicker({
                        ` + (item['joining-date'] ? 'defaultDate: "' + item['joining-date'] + '",' : '') + `
                        minDate: moment(),
                        format: 'YYYY-MM-DD'
                    });
                </script>
            `,
            properties: ['rank', 'vessel-type', 'joining-date', 'contract-duration', 'nationality', 'wage', 'description']
        });
        this.item = item;
    }

    getProperty(key) {
        return this.item[key] || '';
    }
}