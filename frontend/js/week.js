export default class WeekView {

    constructor() {
        this.today = new Date();
        const day = this.today.getDay();
        this.startWeekDate = new Date();
        this.startWeekDate.setDate(this.today.getDate() - day + (day === 0 ? -6 : 1));
    }

    setCurrentWeekInView() {
        const weekDaysElements = document.getElementsByClassName("week-day");
        for (let i = 0; i < weekDaysElements.length; i++) {
            weekDaysElements.item(i).getElementsByClassName("week-day-number")[0].innerText = this.startWeekDate.getDate() + i;
            if (this.today.getDate() === this.startWeekDate.getDate() + i) {
                weekDaysElements.item(i).getElementsByClassName("week-day-number")[0].classList.add("today-number");
            }
        }
    }
}