package example.tictactoe.logic

/**
  * Created by govind.bhone on 5/27/2017.
  */

sealed trait TicTacToeMessage

case object DisplayRules extends TicTacToeMessage

case object DisplayBoardStatus extends TicTacToeMessage

case class SetPlayers(players: Array[Player]) extends TicTacToeMessage

case object Play extends TicTacToeMessage

case object PlayYourMoves extends TicTacToeMessage

case object GameOver extends TicTacToeMessage

case class PlaceAt(squarePosition: Int) extends TicTacToeMessage



sealed trait ControlMessages
case object ExecuteNextMove extends ControlMessages
case object RepeatCurrentMove extends ControlMessages
case object StopGame extends ControlMessages
