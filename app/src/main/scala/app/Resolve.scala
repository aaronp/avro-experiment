package app

import kuzzle.Json

object Resolve {

  def apply(path: List[String], value: Any): Any = {
    value match {
      case list: java.util.List[Any] => resolveList(path, list)
      case list: java.util.Map[String, Any] => resolveMap(path, list)
      case json: Json => apply(path, json.getValue)
      case primitive =>
        require(path.isEmpty, path.mkString(s"Couldn't resolve '", ".", s"' ${primitive}"))
        primitive
    }

  }

  private def resolveMap(path: List[String], value: java.util.Map[String, Any]) = {
    path match {
      case Nil => value
      case head :: tail =>
        val next = value.get(head)
        require(next != null, s"null value found for key '$head' from $value")
        apply(tail, next)
    }
  }

  private def resolveList(path: List[String], value: java.util.List[Any]) = {
    path match {
      case Nil => value
      case head :: tail =>
        head.toIntOption match {
          case None => sys.error(s"array index couldn't be resolve for '$head'")
          case Some(idx) =>
            val next = value.get(idx)
            require(next != null, s"Null value found for key $head")
            apply(tail, next)
        }
    }
  }

}
