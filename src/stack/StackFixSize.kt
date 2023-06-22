package stack

import exception.StackOverflowException
import exception.StackUnderflowException

class StackFixSize<E> {
    private val elements: Array<Any?>
    private var size = 0

    constructor(capacity: Int) {
        this.elements = arrayOfNulls(size = capacity)
    }

    fun push(element: E) {
        if (size == elements.size) {
            throw StackOverflowException()
        } else {
            elements[size++] = element
        }
    }

    fun pop(): E {
        if (size == 0) {
            throw StackUnderflowException()
        } else {
            val index = --size
            val obj = elements[index] //4
            elements[index] = null
            return obj as E
        }
    }

    fun peek() = try {
        elements[size - 1] as E
    } catch (e: IndexOutOfBoundsException) {
        throw StackUnderflowException()
    }

    fun isEmpty() = size == 0
    fun isFull() = size == elements.size
    fun size() = size
}