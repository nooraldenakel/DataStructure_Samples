package queues

import exception.QueueUnderflowException

class QueueDynamicSize<E> {
    private val minCapacityIncrement = 12
    private var elements: Array<Any?>
    private var size = 0

    constructor() {
        this.elements = arrayOf()
    }

    constructor(initialCapacity: Int) {
        this.elements = arrayOfNulls(size = initialCapacity)
    }

    fun enQueue(element: E) {
        if (size == elements.size) {
            val newArray = arrayOfNulls<Any>(
                size + if (size < minCapacityIncrement / 2) minCapacityIncrement
                else size shl 1
            )
            System.arraycopy(elements, 0, newArray, 0, size)
            elements = newArray
        }
        elements[size++] = element
    }

    fun deQueue(): E {
        if (size == 0) throw QueueUnderflowException()
        val oldValue = elements[0]
        elements[0] = null
        System.arraycopy(elements, 1, elements, 0, --size)
        return oldValue as E
    }

    fun front() = try {
        elements[0] as E
    } catch (e: QueueUnderflowException) {
        throw e
    }

    fun rare() = try {
        elements[size - 1] as E
    } catch (e: QueueUnderflowException) {
        throw e
    }

    fun isFull() = size == elements.size
    fun iseEmpty() = size == 0
    fun size() = size
}