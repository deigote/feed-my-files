/*
 * This Spock specification was auto generated by running 'gradle init --type groovy-library'
 * by 'deigote' at '7/1/15 7:19 PM' with Gradle 2.4
 *
 * @author deigote, @date 7/1/15 7:19 PM
 */

import spock.lang.Specification

class LibraryTest extends Specification{
    def "someLibraryMethod returns true"() {
        setup:
        Library lib = new Library()
        when:
        def result = lib.someLibraryMethod()
        then:
        result == true
    }
}