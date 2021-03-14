export default class WeekView {

    constructor() {
        this.today = this._dateWithZeroTime(new Date());
        this.setCurrentWeek();
        document.getElementById("previous-week").addEventListener("click", () => this.setPreviousWeek());
        document.getElementById("today-button").addEventListener("click", () => this.setCurrentWeek());
        document.getElementById("next-week").addEventListener("click", () => this.setNextWeek());
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
        const currentDate = new Date(this.startWeekDate);
        for (let i = 0; i < weekDaysElements.length; i++) {
            const element = weekDaysElements.item(i);
            element.getElementsByClassName("week-day-number")[0].innerText = currentDate.getDate();
            if (this.today.getTime() === currentDate.getTime()) {
                element.getElementsByClassName("week-day-number")[0].classList.add("today-number");
            } else {
                element.getElementsByClassName("week-day-number")[0].classList.remove("today-number");
            }
            currentDate.setDate(currentDate.getDate() + 1);
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
        let startDate = this._dateWithZeroTime(new Date());
        startDate.setDate(this.today.getDate() - day + (day === 0 ? -6 : 1));
        return startDate;
    }

    _dateWithZeroTime(date) {
        date.setHours(0, 0, 0, 0);
        return date;
    }
}