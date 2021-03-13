export default class WeekView {

    constructor() {
        this.today = new Date();
        this.startWeekDate = this._getStartOfTheWeek(this.today);
        this.setCurrentWeek();
    }

    setCurrentWeek() {
        this.startWeekDate = this._getStartOfTheWeek(this.today);
        this._setWeekInView();
    }

    setPreviousWeek() {
        this.startWeekDate.setDate(this.startWeekDate.getDate() - 7);
        this._setWeekInView();
    }

    setNextWeek() {
        this.startWeekDate.setDate(this.startWeekDate.getDate() + 7);
        this._setWeekInView();
    }


    _setWeekInView() {
        const weekDaysElements = document.getElementsByClassName("week-day");
        for (let i = 0; i < weekDaysElements.length; i++) {
            weekDaysElements.item(i).getElementsByClassName("week-day-number")[0].innerText = this.startWeekDate.getDate() + i;
            if (this.today.getDate() === this.startWeekDate.getDate() + i) {
                weekDaysElements.item(i).getElementsByClassName("week-day-number")[0].classList.add("today-number");
            }
        }
        const monthNames = ["January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        ];
        const weekViewMonth = document.getElementById("week-view-month");
        const weekViewYear = document.getElementById("week-view-year");
        weekViewMonth.innerText = monthNames[this.startWeekDate.getMonth()];
        weekViewYear.innerText = this.startWeekDate.getFullYear().toString();
    }

    _getStartOfTheWeek(date) {
        const day = date.getDay();
        let startDate = new Date();
        startDate.setDate(this.today.getDate() - day + (day === 0 ? -6 : 1));
        return startDate;
    }
}