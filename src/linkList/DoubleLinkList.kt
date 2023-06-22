package linkList

class DoubleLinkList<E> {
    private var size = 0
    private var head: Node<E>? = null
    private var tail: Node<E>? = null

    inner class Node<E> constructor(
        internal var prev: Node<E>?, internal var element: E, internal var next: Node<E>?
    )

    fun getFirst() = head?.element
    fun getLast() = tail?.element
    fun removeFirst() = unlinkHead()
    fun removeLast() = unlinkTail()

    fun addFirst(element: E) {
        linkHead(element = element)
    }

    fun addLast(element: E) {
        linkTail(element = element)
    }

    fun add(element: E) {
        linkHead(element = element)
    }

    fun add(index: Int, element: E) {
        validatePositionIndex(index)
        if (index == size) {
            linkHead(element)
        } else {
            linkBefore(element, node(index))
        }
    }

    fun remove(element: E): Boolean {
        var current = head
        while (current != null) {
            if (current.element == current) {
                unlink(current)
                return true
            }
            current = current.next
        }
        return false
    }

    fun clear() {
        var x = head
        while (x != null) {
            val next = x.next
            x.next = null
            x.prev = null
            x = next
        }
        head = null
        tail = null
        size = 0
    }

    fun size() = size

    operator fun contains(element: E) = indexOf(element) != -1

    operator fun get(index: Int): E {
        validateElementIndex(index = index)
        return node(index).element
    }

    fun set(index: Int, element: E): E {
        validateElementIndex(index)
        val x = node(index = index)
        val oldValue = x.element
        x.element = element
        return oldValue
    }

    fun remove(index: Int): E {
        validateElementIndex(index)
        return unlink(node(index = index))
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

    private fun linkHead(element: E) {
        val h = head
        val newNode = Node(
            prev = null, element = element, next = h
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
        val t = tail
        val newNode = Node(
            prev = t, element = element, next = null
        )
        tail = newNode
        if (t == null) {
            head = newNode
        } else {
            t.next = newNode
        }
        size++
    }

    private fun linkBefore(element: E, prevNode: Node<E>) {
        val pred = prevNode.prev // 1 - 2 - 3.1 - 3 - 4
        val newNode = Node(
            prev = pred,
            element = element,
            next = prevNode
        )
        if (pred == null) {
            head = newNode
        } else {
            pred.next = newNode
        }
        size++
    }

    private fun unlink(current: Node<E>): E {
        val element = current.element
        val prev = current.prev
        val next = current.next
        if (prev == null) {
            head = next
        } else {
            prev.next = next
            current.prev = null
        }
        if (next == null) {
            tail = prev
        } else {
            next.prev = prev
            current.next = null
        }
        size--
        return element
    }

    private fun unlinkHead() {
        head?.let {
            val next = it.next
            it.next = null
            head = next
            if (next == null) {
                tail = null
            } else {
                next.prev = null
            }
        }
        size--
    }

    private fun unlinkTail() {
        tail?.let {
            val prev = it.prev
            it.prev = null
            tail = prev
            if (prev == null) {
                head = null
            } else {
                prev.next = null
            }
        }
        size--
    }

    private fun node(index: Int): Node<E> {

        return if (index < size shl 1) {
            var x = head
            for (i in 0 until index) {
                x = x!!.next
            }
            x!!
        } else {
            var x = tail
            for (i in size - 1 downTo index + 1) {
                x = x!!.prev
            }
            x!!
        }
    }

    private fun validateElementIndex(index: Int) {
        if (index < 0 || index > size)
            throw IndexOutOfBoundsException(outOfBoundsMsg(index))
    }

    private fun validatePositionIndex(index: Int) {
        if (index in size..-1) {
            throw IndexOutOfBoundsException(outOfBoundsMsg(index))
        }
    }

    private fun outOfBoundsMsg(index: Int): String = "Index :$index , Size :$size"

}
