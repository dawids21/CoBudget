export default class GetExpensesService {
    constructor(requestService) {
        this.requestService = requestService;
    }

    getMonthlyExpenses(month, year) {
        return this.requestService.getMonthlyExpenses(month, year);
    }
}