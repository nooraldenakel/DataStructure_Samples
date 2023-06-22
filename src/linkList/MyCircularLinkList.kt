package linkList

class MyCircularLinkList<E> {
    private var size = 0
    private var head: Node<E>? = null
    private var tail: Node<E>? = null

    inner class Node<E> constructor(
        internal var prev: Node<E>?, internal var element: E, internal var next: Node<E>?
    )

    fun getFirst() = head?.element
    fun getLast() = tail?.element

    fun addFirst(element: E) {
        linkHead(element)
    }

    fun addLast(element: E) {
        linkTail(element)
    }

    fun add(element: E) {
        addLast(element = element)
    }

    fun removeFirst() = unlinkHead()

    fun size() = size
    private fun linkHead(element: E) {
        val h = head
        val t = tail
        val newNode = Node<E>(
            prev = t,
            element = element,
            next = h
        )
        head = newNode
        if (h == null) {
            tail = newNode
        } else {
            h.prev = newNode
        }
        size++
    }

    private fun linkTail(element: E) {
        val h = head
        val t = tail
        val newNode = Node<E>(
            prev = t,
            element = element,
            next = h
        )
        tail = newNode
        if (t == null) {
            head = newNode
        } else {
            t.next = newNode
        }
        size++
    }

    private fun unlinkHead() {
        head?.let {
            val next = it.next //2
            it.next = null //1
            head = next
            if (next == null) {
                tail = null
            } else {
                next.prev = tail
            }
            size--
        }

    }
}