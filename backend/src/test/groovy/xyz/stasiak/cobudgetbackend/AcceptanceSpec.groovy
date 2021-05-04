package xyz.stasiak.cobudgetbackend

import spock.lang.Specification


class AcceptanceSpec extends Specification {
    def "main acceptance test"() {
        when: "I create category for expense: Food, subcategory: Food-Home and category for earning: Work"
        then: "Request from configuration return given categories"

        when: "I set the notification time for 5pm"
        then: "I can read the notification time from notification config"

        when: "I create an expense: 04.05.2021, 20 zl, Food, Home"
        then: "I have one expense on my monthly report for May"

        when: "I create an expense: 04.02.2021, 20 zl, Food, Home"
        then: "I have one expense on my monthly report for May"

        when: "I create an earning: 02.05.2021, 40 zl, Work, bonus"
        then: "I have one expense and one earning on my monthly report for May"

        when: "I list my entries for May"
        then: "I see one corresponding expense and one earning"

        when: "I modify the earning amount to 60 zl"
        then: "In the monthly report I can see earning with changed value"

        when: "I request report for May"
        then: "I can see sum of expenses equal 20 and sum of earnings equal 60"

        when: "I request category report for May"
        then: "I can see 20 zl spend on food, 20 zl spend on food-home and 60 zl earned from work"
       
        false
    }
}