const monthNames = ['January', 'February', 'March', 'April', 'May', 'June',
    'July', 'August', 'September', 'October', 'November', 'December',
];

export default class WeekView {

    constructor(getExpenseService) {
        this.getExpenseService = getExpenseService;
        this.today = this._dateWithZeroTime(new Date());
        this.setCurrentWeek();
        document.getElementById('previous-week').addEventListener('click', () => this.setPreviousWeek());
        document.getElementById('today-button').addEventListener('click', () => this.setCurrentWeek());
        document.getElementById('next-week').addEventListener('click', () => this.setNextWeek());
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

    async _setWeekInView() {
        const weekDaysElements = document.getElementsByClassName('week-day');
        const currentDate = new Date(this.startWeekDate);
        let currentExpenses = await this.getExpenseService.getMonthlyExpenses(currentDate.getMonth() + 1, currentDate.getFullYear());
        for (let i = 0; i < weekDaysElements.length; i++) {

            if (currentExpenses.month !== monthNames[currentDate.getMonth()].toUpperCase()) {
                currentExpenses = await this.getExpenseService.getMonthlyExpenses(currentDate.getMonth() + 1, currentDate.getFullYear());
            }

            const dayExpenses = currentExpenses.expenses.filter(e => e.day === currentDate.getDate());
            const weekDayNumber = weekDaysElements[i].getElementsByClassName('week-day-number')[0];
            const entryList = weekDaysElements[i].getElementsByClassName('entry-list')[0];

            weekDayNumber.innerText = currentDate.getDate();
            this._removeEntries(entryList);
            dayExpenses.forEach((expense) => this._createEntry(expense, entryList));
            this._markToday(currentDate, weekDayNumber);
            currentDate.setDate(currentDate.getDate() + 1);
        }

        const weekViewMonth = document.getElementById('week-view-month');
        const weekViewYear = document.getElementById('week-view-year');
        weekViewMonth.innerText = monthNames[this.startWeekDate.getMonth()];
        weekViewYear.innerText = this.startWeekDate.getFullYear().toString();
    }

    _removeEntries(entryList) {
        while (entryList.firstChild) {
            entryList.removeChild(entryList.firstChild);
        }
    }

    _createEntry(entryData, entryList) {
        let entry = document.createElement('li');
        entry.classList.add('week-day-entry', 'expense');
        entry.innerHTML = `<p>${entryData.amount} zł</p><p>${entryData.category}</p>`;
        entryList.appendChild(entry);
    }

    _markToday(currentDate, element) {
        if (this.today.getTime() === currentDate.getTime()) {
            element.classList.add('today-number');
        } else {
            element.classList.remove('today-number');
        }
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