export default class GetExpensesService {
    constructor(requestService) {
        this.requestService = requestService;
    }

    getMontlhlyExpenses(month, year) {
        return this.requestService.getMontlhlyExpenses(month, year);
    }
}