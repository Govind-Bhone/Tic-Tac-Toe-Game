package example.tictactoe.logic

import akka.actor.{Actor, ActorSystem}
import example.tictactoe.main.Main

class TicTacToeActor extends Actor with TicTacToeActorImpl {

  def placeAt(pos: Int): Boolean = {
    if (!ifWins() && getPlays < 9) {
      val marked = placeMarker(pos)
      if (!marked) {
        return false
      } else {
        println(displayBoard)
      }
    }
    val won = ifWins()
    if (won || getPlays == 8) {
      if (!won) {
        println("Game Over - Draw")
      } else {
        println("Game Over - " + currentPlayer.name + " WINS!!!")
      }
      self ! GameOver
      return false
    }
    return true
  }

  override def receive: Receive = {
    case DisplayRules => println(displayRules)
    case DisplayBoardStatus => println(displayBoard)
    case SetPlayers(players) => setPlayers(players)
    case Play =>
      currentPlayer.reference ! PlayYourMoves
    case PlaceAt(pos: Int) =>
      val continueNextMove = placeAt(pos)
      if (continueNextMove) {
        setPlayerTurnIndex
        self ! Play
      } else {
        self ! Play
      }
    case GameOver => System.exit(0)
  }
}
