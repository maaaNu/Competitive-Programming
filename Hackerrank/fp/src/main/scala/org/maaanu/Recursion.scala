package org.maaanu

import scala.annotation.tailrec
import scala.language.postfixOps

object Recursion {

  @tailrec
  def gcd(x: Int, y: Int): Int = if (x == 0) y else gcd(y % x, x)

  def fibonacci(x: Int): Int = x match {
    case y if y > 2 => fibonacci(x - 1) + fibonacci(x - 2)
    case 2 => 1
    case _ => 0
  }

  def pascalTri(rows: Int): List[List[Int]] = (0 to rows toList).map(index => {
    (0 to index).toList.map(column => pascalVal(index, column))
  })

  def pascalTriNewRow(lastRow: List[Int]): List[Int] = 1 +: lastRow.zip(lastRow.drop(1)).map(x => x._1 + x._2) :+ 1

  def pascalVal(row: Int, column: Int): Int = {
    if (column == 0) return 1
    if (row == 0) return column

    row * pascalVal(row - 1, column - 1) / column
  }

}
