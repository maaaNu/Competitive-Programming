package org.maaanu

import org.scalatest.FlatSpec

class EasyTaskTests extends FlatSpec {

  "ListRep" should "repeat each element in the list n amount of times." in {
    assert(EasyTask.listRep(2, List(1, 2, 3, 4)) === List(1, 1, 2, 2, 3, 3, 4, 4))
  }

  "Filter Array" should "Filter a given array of integers and output only those values that are less than a specified value X" in {
    assert(EasyTask.filterArr(3, List(10, 9, 8, 2, 7, 5, 1, 3, 0)) === List(2, 1, 0)
    )
  }

  "Filter Positions in a List" should "For a given list with  integers, return a new list removing the elements at odd positions." in {
    assert(EasyTask.filterPos(List(2,5,3,4,6,7,9,8)) === List(5,4,7,8))
  }

  it should "reverse a array" in {
    assert(EasyTask.reverseList(List(1,2,3)) === List(3,2,1))
  }

  it should "return the sum of odd elements" in {
    assert(EasyTask.sumOddElements(List(3,2,4,6,5,7,8,0,1)) === 16)
  }

  "Sum of Odd Elements 11, 25, 18, -1, 26, -20, -19, 23, -24,-8" should "return 39" in {
    assert(EasyTask.sumOddElements(List(11, 25, 18, -1, 26, -20, -19, 23, -24,-8)) === 39)
  }
}
