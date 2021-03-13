export default class WeekView {

    constructor() {
        this.today = new Date();
        const day = this.today.getDay();
        this.startWeekDate = new Date();
        this.startWeekDate.setDate(this.today.getDate() - day + (day === 0 ? -6 : 1));
    }
}