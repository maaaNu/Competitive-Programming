package org.maaanu

object EasyTask {
  def listRep(num:Int,arr:List[Int]):List[Int] = arr.flatMap(ele => List.fill(num)(ele))
  def filterArr(delim:Int,arr:List[Int]):List[Int] = arr.filter(ele => ele < delim)
  def filterPos(arr:List[Int]):List[Int] = arr.drop(1).grouped(2).map(_.head).toList
  def reverseList(arr:List[Int]):List[Int] = arr.reverse
  def sumOddElements(arr:List[Int]):Int = arr.filter(ele => ele % 2 == 1 || ele % 2 == -1).sum
}
