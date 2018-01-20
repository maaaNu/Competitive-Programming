package org.maaanu

import org.maaanu.Recursion.{fibonacci, gcd, pascalTri, pascalTriNewRow, pascalVal}
import org.scalatest.FlatSpec

class RecursionTests extends FlatSpec {

  it should "GCD(1,5) = 1" in assert(gcd(1, 5) == 1)
  it should "GCD(10,100) = 10" in assert(gcd(10, 100) == 10)
  it should "GCD(22, 131) = 1" in assert(gcd(22, 131) == 1)

  it should "fibonacci(3) = (0+1) = 1 " in assert(fibonacci(3) == 1)
  it should "fibonacci(4) = (1+1) = 2" in assert(fibonacci(4) == 2)
  it should "fibonacci(5) = (1+2) = 3  " in assert(fibonacci(5) == 3)

  it should "1 2 1 => 1 3 3 1" in assert(pascalTriNewRow(List(1, 2, 1)) == List(1, 3, 3, 1))
  it should "1 3 3 1 => 1 4 6 4 1" in assert(pascalTriNewRow(List(1, 3, 3, 1)) == List(1, 4, 6, 4, 1))

  it should "pascalVal(0,0) == 1" in assert(pascalVal(0,0) == 1)
  it should "pascalVal(1,1) == 1" in assert(pascalVal(1,1) == 1)
  it should "pascalVal(2,0) == 2" in assert(pascalVal(2,0) == 1)
  it should "pascalVal(2,1) == 2" in assert(pascalVal(2,1) == 2)
  it should "pascalVal(2,2) == 2" in assert(pascalVal(2,2) == 1)

  it should "pascalTri(3) == List(List(1), List(1,1), List(1,2,1), List(1,3,3,1)" in assert(pascalTri(3) == List(List(1), List(1,1), List(1,2,1), List(1,3,3,1)))
}
