package lists

import scala.annotation.tailrec
import scala.Option

/**
Problem described here: http://projecteuler.net/problem=67
  */
object MaximumPathSum {

  // sum - is maximum sum of numbers along this path.
  // value - is last number in this path.
  // previous - is link to previous number from this path.
  case class Path(sum:Long, value: Int, previous: Option[Path])

  // Extends previous path by one number of triangle
  def extendPath(newValue: Int, previous: Option[Path]): Path = Path(previous.get.sum + newValue, newValue, previous)

  // Find optimal solution (path with maximum sum) for every number from the bottom line of triangle.
  // You can split this function to several ones.
  def calculateOptimalPaths(data:List[List[Int]]): List[Path] = {
    def fork(data: List[List[Path]]): List[List[Path]] = data match {
      case first :: second :: rest => fork(join(first, second) :: rest)
      case singleRow :: Nil => List(singleRow)
    }
    def join(firstRow: List[Path], secondRow: List[Path]): List[Path] = (firstRow, secondRow) match {
      case (first :: second :: rest, secondRawFirst :: secondRest) =>
        extendPath(secondRawFirst.value, Some(if(first.sum > second.sum) first else second)) :: join(second :: rest, secondRest)
      case (first, Nil) => Nil
    }
    val dataPaths = (for (i <- 0 until data.length) yield data(i).map(x => Path(x, x, None))).toList.reverse
    if(data.length == 1 && data(0).length == 1) List(Path(data(0)(0), data(0)(0), None))
    else (for (j <- 0 to dataPaths(0).length - 1) yield fork(dataPaths).head.head).toList
  }

  // Return list of number along the path (staring from top of triangle to bottom).
  def numbersForPath(path:Option[Path]): List[Int] = {
    def traverse(path:Option[Path], list: List[Int]): List[Int] = {
      if(path.get.previous == None) list :+ path.get.value
      else traverse(path.get.previous, list :+ path.get.value)
    }
    traverse(path, List()).toList
  }

  // Select path that have maximum sum. Should return empty list if paths is empty list.
  def bestPath(paths:List[Path]): Option[Path] = {
    if(paths.isEmpty) List()
    Some(paths.filter(x => x.sum == paths.map(x => x.sum).max).head)
  }

  // Return sum for best path from paths. Should return zero if paths is empty list. Reuse bestPath to find best path.
  def bestSum(paths:List[Path]): Long = {
    if(paths.isEmpty) 0
    else bestPath(paths).get.sum
  }

  // Return number along best path. Should return empty list if paths is empty list. Reuse both bestPath and numbersForPath.
  def bestSumNumbers(paths:List[Path]): List[Int] = {
    if(paths.isEmpty) List()
    else numbersForPath(bestPath(paths))
  }

}