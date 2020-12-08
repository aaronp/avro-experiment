package app

import kuzzle.Json

object Example {

  def main(array: Array[String]): Unit = {
    apply()
  }

  def apply() = {
    val list = new java.util.ArrayList[AnyRef]()
    list.add(address())
    list.add(city("Eyam"))
    list.add(map("foo", "bar"))
    list.add(map("fizz", map("buzz", "bazz")))
    list.add(city("Platteville"))
    list.add("Oconto Falls")
    val doc: Json = kuzzle.Json.newBuilder().setValue(list).build()
    println(doc.toString)
    val foo = Resolve(List("2", "foo"), doc)
    println(foo)
    val two = Resolve(List("3", "fizz", "buzz"), doc)
    println(two)
    doc
  }

  def address(): Json = {
    val map = new java.util.HashMap[String, Json]()
    map.put("hello", city("Wisconsin"))
    kuzzle.Json.newBuilder().setValue(map).build()
  }

  def city(name: String): Json = {
    kuzzle.Json.newBuilder().setValue(name).build()
  }

  def map(field: String, name: Any) = {
    val map = new java.util.HashMap[String, Any]()
    map.put(field, name)
    map
  }
}
