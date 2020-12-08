package app

import example.avro.Employee

object Example {

  def apply(): Employee = {
    Employee
      .newBuilder()
      .setFavoriteNumber(123)
      .build()
  }
}
