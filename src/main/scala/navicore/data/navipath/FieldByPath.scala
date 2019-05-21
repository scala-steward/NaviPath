package navicore.data.navipath

import com.fasterxml.jackson.databind.ObjectMapper
import navicore.data.jsonpath.JsonPath

object FieldByPath {

  def apply[T](json: Object, path: String): Option[T] = {

    JsonPath
      .query(path, json)
      .right
      .map(_.toVector.toSeq) match {
      case Right(ids) => ids.headOption.asInstanceOf[Option[T]]
      case _          => None
    }

  }

  def apply[T](data: String, path: String): Option[T] = {

    val json = (new ObjectMapper).readValue(data, classOf[Object])

    apply[T](json, path)

  }

  def apply[T](data: String, paths: List[String]): List[Option[T]] = {

    val json = (new ObjectMapper).readValue(data, classOf[Object])

    paths.map(path => {

      apply[T](json, path)

    })

  }

}