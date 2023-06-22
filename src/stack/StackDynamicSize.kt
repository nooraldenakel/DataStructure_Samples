package stack

import exception.StackUnderflowException

class StackDynamicSize<E> {
    private val minCapacityIncrement = 12
    private var elements: Array<Any?>
    private var size = 0

    constructor() {
        this.elements = arrayOf()
    }

    constructor(initialCapacity: Int) {
        this.elements = arrayOfNulls(size = initialCapacity)
    }

    fun push(element: E) {
        if (size == elements.size) {
            val newArray = arrayOfNulls<Any>(
                size +
                        if (size < minCapacityIncrement / 2)
                            minCapacityIncrement
                        else
                            size shl 1
            )
            System.arraycopy(
                elements,
                0,
                newArray,
                0,
                size
            )
            elements = newArray
        }
        elements[size++] = element
    }

    fun pop(): E {
        if (size == 0) throw StackUnderflowException()
        val index = --size
        val obj = elements[index]
        elements[index] = null
        return obj as E
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