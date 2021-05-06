package xyz.stasiak.cobudgetbackend.entry

import spock.lang.Ignore
import spock.lang.Specification


class EntrySpec extends Specification {

    @Ignore
    def "acceptance test"() {
        when: "add new category"
        then: "it should be stored in repository"

        when: "add expense with not existing category"
        then: "throw a EntryCategoryNotExistException"

        when: "add expense with existing category"
        then: "entry validator return true"

        when: "entry validator return true"
        then: "facade call another module to save this entry"

        false
    }
}