package example.tictactoe.main

/**
  * Created by govind.bhone on 6/19/2017.
  */
object Solution extends App {

  val list = List[(Long, Int)](
    (1497843420, 10),
    (1497843424, 1),
    (1497843428, 1),
    (1497843432, 1),
    (1497843436, 1),
    (1497843440, 1),
    (1497843450, 1),
    (1497843460, 1),

    (1497843480, 1),
    (1497843500, 1),
    (1497843520, 1),
    (1497843540, 1),
    (1497843530, 1)
  )

  /*
List(
(1497843420,10,10,10,1),
(1497843424,1,10,11,2),
(1497843428,1,10,12,3),
(1497843432,1,10,13,4),
(1497843436,1,10,14,5),
(1497843440,1,10,15,6),
(1497843450,1,10,16,7),
(1497843460,1,10,17,8),
(1497843540,1,1,1,1),
(1497843480,1,1,1,1),
(1497843500,1,1,2,2),
(1497843520,1,1,3,3),
(1497843530,1,1,4,4)
)




   */


  def processList(list: List[(Long, Int)], index: Int, outputList: List[(Long, Int, Int, Int, Int)]): List[(Long, Int, Int, Int, Int)] = {
    index match {
      case index if index < 0 => outputList
      case _ =>
        val currentElement = list(index)
        val requiredList = list.filter(_._1 <= currentElement._1).map(_._2)
        processList(list, index - 1, outputList.::(currentElement._1, requiredList.min, requiredList.max, requiredList.sum, requiredList.length))
    }

  }

  val res = list
    .groupBy(_._1 / 60)
    .map(f => (f._2.map(_._1).zip(f._2.map(_._2))))
  val res1 = res.flatMap(list => processList(list, list.length - 1, List.empty[(Long, Int, Int, Int, Int)]))
  println(res1)

}
