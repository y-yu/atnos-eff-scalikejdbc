package com.github.yyu.eff.scalikejdbc

import scalikejdbc.DBSession
import scala.concurrent.Future

sealed trait ScalikeJDBCFunction[+A]

object ScalikeJDBCFunction {
  case class FunctionValue[+A](
    f: DBSession => Future[A]
  ) extends ScalikeJDBCFunction[A]

  case class RecoverWith[+A](
    f: FunctionValue[A],
    recover: Throwable => A
  ) extends ScalikeJDBCFunction[A]
}