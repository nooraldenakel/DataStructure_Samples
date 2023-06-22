package queues

import exception.QueueUnderflowException

class LinkQueue<E> {

    private var head: Node<E>? = null
    private var tail: Node<E>? = null
    private var size = 0

    private inner class Node<E> constructor(
        internal var prev: Node<E>?,
        internal var element: E,
        internal var next: Node<E>?,
    )

    fun emQueue(element: E) {
        val t = tail
        val newNode = Node(prev = t, element = element, next = null)
        tail = newNode
        if (t == null) {
            head = newNode
        } else {
            t.next = newNode
        }
        size++
    }

    fun deQueue() =
        head?.let {
            val next = it.next
            it.next = null
            head = next
            if (next == null) {
                tail = null
            } else {
                next.prev = null
            }
        } ?: throw QueueUnderflowException()

    fun front() = head?.element ?: throw QueueUnderflowException()
    fun rare() = tail?.element ?: throw QueueUnderflowException()
    fun isEmpty() = size == 0
    fun size() = size

}