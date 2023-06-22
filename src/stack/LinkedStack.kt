package stack

import exception.StackUnderflowException

class LinkedStack<E> {
    private var head: Node<E>? = null
    private var tail: Node<E>? = null
    private var size = 0

    inner class Node<E> constructor(
        internal var prev: Node<E>?,
        internal var element: E,
        internal var next: Node<E>?,
    )

    fun push(element: E) {
        val t = tail
        val newNode = Node<E>(
            prev = t,
            element = element,
            next = null
        )
        tail = newNode
        if (t == null) {
            head = newNode
        } else {
            t.next = newNode
        }
        size++
    }

    fun pop(): E {
        tail?.let {
            val prev = it.prev
            tail = prev
            it.prev = null
            prev?.next = null
            size--
            return it.element
        } ?: throw StackUnderflowException()
    }

    fun peek(): E {
        tail?.let {
            return it.element
        } ?: throw StackUnderflowException()
    }

    fun isEmpty() = size == 0
    fun size() = size
}