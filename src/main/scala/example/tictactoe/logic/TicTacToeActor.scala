package example.tictactoe.logic

import akka.actor.{Actor}

class TicTacToeActor extends Actor with TicTacToeActorImpl {

  def placeAt(pos: Int): ControlMessages = {
    if (!ifWins() && getPlays < 9) {
      val marked = placeMarker(pos)
      if (!marked) {
        return RepeatCurrentMove
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
      return StopGame
    }
    return ExecuteNextMove
  }

  override def receive: Receive = {
    case DisplayRules => println(displayRules)
    case DisplayBoardStatus => println(displayBoard)
    case SetPlayers(players) => setPlayers(players)
    case Play =>
      currentPlayer.reference ! PlayYourMoves
    case PlaceAt(pos: Int) =>
      val status = placeAt(pos)
      status match {
        case RepeatCurrentMove =>
          self ! Play
        case ExecuteNextMove =>
          setPlayerTurnIndex
          self ! Play
        case StopGame => self ! GameOver
      }
    case GameOver => context.system.terminate()
  }
}
