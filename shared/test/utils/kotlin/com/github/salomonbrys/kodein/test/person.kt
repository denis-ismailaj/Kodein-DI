package com.github.salomonbrys.kodein.test

interface IPerson { val name: String? }

data class Person(override val name: String? = null ) : IPerson

data class A(val b: B?)
data class B(val c: C?)
data class C(val a: A?)

interface IName {
    val firstName: String
}

open class Name(override val firstName: String) : IName {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Name) return false
        if (firstName != other.firstName) return false
        return true
    }

    override fun hashCode(): Int{
        return firstName.hashCode()
    }
}

interface IFullName : IName {
    val lastName: String
}

open class FullName(firstName: String, override val lastName: String) : Name(firstName), IFullName {
    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is FullName) return false
        if (!super.equals(other)) return false
        if (lastName != other.lastName) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 * super.hashCode() + lastName.hashCode()
    }
}

interface IAge {
    val age: Int
}

class FullInfos(firstName: String, lastName: String, override val age: Int) : FullName(firstName, lastName), IAge

typealias PersonEntry = Pair<String, Person>
typealias PersonEntries = Set<PersonEntry>