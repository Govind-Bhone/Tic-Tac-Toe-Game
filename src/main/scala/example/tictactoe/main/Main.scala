package example.tictactoe.main

import akka.actor.{ActorRef, ActorSystem, Props}
import example.tictactoe.logic._


object Main extends App {
  val system = ActorSystem("tic-tac-toe")

  def createPlayers(): Array[Player] = {
    print("Enter player one's name: ")
    val player1Name = scala.io.StdIn.readLine()
    print("Enter player two's name: ")
    val player2Name = scala.io.StdIn.readLine()
    print(s"Select any letter as ${player1Name}'s marker[don't use digits]: ")
    val marker1 = scala.io.StdIn.readChar()
    if (marker1.isDigit) throw InvalidMarkerException(s"wrong marker letter used for player ${player1Name}")
    print(s"Select any letter as ${player2Name}'s marker[don't use digits]: ")
    val marker2 = scala.io.StdIn.readChar()
    if (marker2.isDigit) throw InvalidMarkerException(s"wrong marker letter used for player ${player2Name}")
    Array(
      Player(player1Name, system.actorOf(Props(new PlayerActor()), player1Name), marker1),
      Player(player2Name, system.actorOf(Props(new PlayerActor()), player2Name), marker2))
  }

  def createTicTacToeActor(): ActorRef = {
    system.actorOf(Props[TicTacToeActor], "tic-tac-toe")
  }

  def play(): Unit = {
    println("Welcome! Tic Tac Toe is a two player game.")
    val players = createPlayers()
    val ticTacToeActor = createTicTacToeActor()
    ticTacToeActor ! DisplayRules
    ticTacToeActor ! DisplayBoardStatus
    ticTacToeActor ! SetPlayers(players)
    ticTacToeActor ! Play
  }

  //start execution
  play()


  //shutdown hook
  Runtime.getRuntime.addShutdownHook(new Thread() {
    override def run() {
      system.whenTerminated
    }
  })

}
