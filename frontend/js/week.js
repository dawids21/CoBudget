export default class WeekView {

    constructor() {
        this.today = new Date();
        const day = this.today.getDay();
        this.startWeekDate = new Date();
        this.startWeekDate.setDate(this.today.getDate() - day + (day === 0 ? -6 : 1));
    }

    setWeekInView() {
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
}