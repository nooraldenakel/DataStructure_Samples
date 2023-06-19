class SingleLinkList<E> {
    private var size = 0
    private var head: Node<E>? = null
    private var tail: Node<E>? = null

    private inner class Node<E> constructor(
        var element: E, var next: Node<E>?
    )

    fun addFirst(element: E) {
        val h = head
        val newNode = Node(element = element, next = h)
        head = newNode
        if (h == null) {
            tail = newNode
        }
        size++
    }

    fun addLast(element: E) {
        val t = tail
        val newNode = Node(element = element, next = null)
        tail = newNode
        if (t == null) {
            head = newNode
        } else {
            t.next = newNode
        }
        size++
    }

    fun add(element: E) {
        addLast(element = element)
    }

    fun add(index: Int, element: E) {
        validatePositionIndex(index)
        if (index == 0) addFirst(element)
        else {
            var x = head
            val prevIndex = index - 1
            for (i in 0 until prevIndex) {
                x = x!!.next
            }
            val next = x!!.next
            val newNode = Node(element = element, next = next)
            x.next = newNode
            size++
        }
    }

    private fun validatePositionIndex(index: Int) {
        if (index < 0 || index > size) {
            throw IndexOutOfBoundsException(outOfBoundsMsg(index))
        }
    }

    private fun outOfBoundsMsg(index: Int): String {
        return "index : $index , size : $size"
    }

    fun getFirst() = head?.element

    fun getLast() = tail?.element

    operator fun get(index: Int): E {
        validatePositionIndex(index)
        var x = head
        for (i in 0 until index) {
            x = x!!.next
        }
        return x!!.element
    }

    fun removeFirst() {
        head?.let { firstElement ->
            val next = firstElement.next
            firstElement.next = null
            head = next
            if (next == null) {
                tail = null
            }
            size--

        }
    }

    fun removeLast() {
        tail?.let { lastElement ->
            val prev = getPreviousOfGivenNode(lastElement)
            tail = prev
            if (prev == null) {
                head == null
            } else {
                prev.next = null
            }
            size--
        }
    }

    fun remove(element: E): Boolean {
        var current = head
        while (current != null) {
            if (current.element == element) {
                unlink(current)
                return true
            }
            current = current.next
        }
        return false
    }

    fun removeAtIndex(index: Int): E {
        validatePositionIndex(index)
        val node = Node(element = get(index), next = null)
        return unlink(node)
    }

    fun remove(index: Int): E {
        validatePositionIndex(index)
        return unlink(node(index))
    }

    private fun indexOf(element: E): Int {
        var index = 0
        var x = head
        while (x != null) {
            if (element == x.element)
                return index
            index++
            x = x.next
        }
        return -1
    }

    operator fun contains(element: E) = indexOf(element) != -1

    fun clear() {
        var x = head
        while (x != null) {

        }
    }

    private fun node(index: Int): Node<E> {
        var x = head
        for (i in 0 until index) {
            x = x!!.next
        }
        return x!!
    }

    private fun unlink(current: Node<E>): E {
        val element = current.element
        val next = current.next
        val prev = getPreviousOfGivenNode(current)
        if (prev == null) {
            head = next
        } else {
            prev.next = next
            current.next = null
        }
        if (next == null) {
            prev?.next = null
            tail = prev
        } else {
            prev?.next = next
            current.next = null
        }
        size--
        return element
    }

    fun size() = size

    private fun getPreviousOfGivenNode(node: Node<E>): Node<E>? {
        if (head != null && node == head) return null
        var current = head
        while (current != null) {
            if (current.next == node) {
                return current
            }
            current = current.next
        }
        return null
    }
}