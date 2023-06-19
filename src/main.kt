fun main() {
    val myList = SingleLinkList<String>()
    myList.addFirst("ali") //0
    myList.add("Ahmed")
    myList.addLast("Kamaal")
    myList.add(element = "Jassem", index = 1)
    for (i in 0 until myList.size()) {
        println("this index $i and vallue is ${myList[i]}")
    }
    println("---------------")
    myList.remove("ali")
    for (i in 0 until myList.size()) {
        println("this index $i and vallue is ${myList[i]}")
    }

    println("Ahmed" !in myList)
}

