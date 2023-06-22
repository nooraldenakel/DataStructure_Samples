package queues

import exception.QueueOverflowException
import exception.QueueUnderflowException

class QueueFixSize<E> {
    private val elements: Array<Any?>
    private var size = 0

    constructor(initialCapacity: Int) {
        this.elements = arrayOfNulls(size = initialCapacity)
    }

    fun enQueue(element: E) {
        if (elements.size == size) throw QueueOverflowException()
        else {
            elements[size++] = element
        }
    }

    fun deQueue(): E {
        if (size == 0) throw QueueUnderflowException()
        else {
            val oldValue = elements[0]
            elements[0] = null
            System.arraycopy(elements, 1, elements, 0, --size)
            return oldValue as E
        }
    }

    fun front() = try {
        elements[0] as E
    } catch (e: QueueUnderflowException) {
        throw e
    }

    fun rear() = try {
        elements[size - 1] as E
    } catch (e: QueueOverflowException) {
        throw e
    }

    fun isEmpty() = size == 0
    fun isFull() = size == elements.size
    fun size() = size
}