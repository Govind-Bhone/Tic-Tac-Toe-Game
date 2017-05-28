package example.tictactoe.logic

import akka.actor.{Actor, ActorLogging, ActorRef}

/**
  * Created by govind.bhone on 5/27/2017.
  */
case class Player(name: String, reference: ActorRef, marker: Char)

class PlayerActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case PlayYourMoves =>
      println("It is " + self.path.name + "'s turn. Pick a square: ")
      val square = scala.io.StdIn.readInt()
      sender() ! PlaceAt(square)
  }
}
