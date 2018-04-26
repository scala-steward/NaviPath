package onextent.data.navipath

import com.fasterxml.jackson.databind.ObjectMapper
import com.typesafe.scalalogging.LazyLogging
import io.gatling.jsonpath.JsonPath

object FieldsByPath extends LazyLogging {

  def apply[T](json: Object, path: String): Option[List[T]] = {

    JsonPath
      .query(path, json)
      .right
      .map(_.toVector.toSeq) match {
      case Right(ids) if ids.nonEmpty => Some(ids.toList.asInstanceOf[List[T]])
      case _          => None
    }

  }

  def apply[T](data: String, path: String): Option[List[T]] = {

    val json: Object = (new ObjectMapper).readValue(data, classOf[Object])

    apply[T](json, path)

  }


}
