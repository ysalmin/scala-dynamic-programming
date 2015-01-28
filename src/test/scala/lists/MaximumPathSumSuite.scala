package lists

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class implements a ScalaTest test suite for the methods in object
 * `MaximumPathSum` that need to be implemented as part of this assignment. A test
 * suite is simply a collection of individual tests for some specific
 * component of a program.
 *
 * A test suite is created by defining a class which extends the type
 * `org.scalatest.FunSuite`. When running ScalaTest, it will automatically
 * find this class and execute all of its tests.
 *
 * Adding the `@RunWith` annotation enables the test suite to be executed
 * inside eclipse using the built-in JUnit test runner.
 *
 * You have two options for running this test suite:
 * 
 * - Start the sbt console and run the "test" command
 * - Right-click this file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class MaximumPathSumSuite extends FunSuite {
  
  import PathData._
  import MaximumPathSum._

/*
  test("empty problem also have best sum") {
    assert(bestSum(List()) === 0)
  }
  
  test("empty problem solution numbers") {
    assert(bestSumNumbers(List()) === List())
  }
  
 test("one number solution") {
    val paths = calculateOptimalPaths(List(List(42)))
    assert(bestSum(paths) === 42)
  }*/
  
  test("small problem") {
    val paths = calculateOptimalPaths(smallProblemData)
    assert(bestSum(paths) === 23)
  }

  test("path of small problem") {
    val paths = calculateOptimalPaths(smallProblemData)
    assert(bestSumNumbers(paths) === List(3, 7, 4, 9))
  }

   test("medium problem") {
      val paths = calculateOptimalPaths(mediumProblemData)
      assert(bestSum(paths) === 1074)
    }

    test("large problem") {
      val paths = calculateOptimalPaths(largeProblemData)
      assert(bestSum(paths) === 7273)
    }
}
