package example.tictactoe.logic

trait TicTacToeActorImpl {
  this: TicTacToeActor =>

  val board: Array[Array[Char]] = initializeBoard
  val players: Array[Player] = new Array[Player](2)
  var playerTurnIndex: Int = 0
  var noOfPlays: Int = 0

  private def initializeBoard: Array[Array[Char]] = {
    var count = 48
    Array.fill(3, 3) {
      count += 1
      count.toChar
    }
  }

  def currentPlayer = players(playerTurnIndex)

  def setPlayers(plrs: Array[Player]): Unit = Array.copy(plrs, 0, players, 0, 2)

  def setPlayerTurnIndex = {
    if (playerTurnIndex == 0) {
      playerTurnIndex = 1
    } else {
      playerTurnIndex = 0
    }
  }

  def getPlays: Int = noOfPlays

  def setPlays = noOfPlays += 1

  private def checkingRows(): Boolean = {
    var current = board(0)(0)
    if (Character.isLetter(current) && board(0)(1) == current && board(0)(2) == current) return true
    current = board(1)(0)
    if (Character.isLetter(current) && board(1)(1) == current && board(1)(2) == current) return true

    current = board(2)(0)
    if (Character.isLetter(current) && board(2)(1) == current && board(2)(2) == current) return true
    false
  }

  private def checkingColumns(): Boolean = {
    var current = board(0)(0)
    if (Character.isLetter(current) && board(1)(0) == current && board(2)(0) == current) return true
    current = board(0)(1)
    if (Character.isLetter(current) && board(1)(1) == current && board(2)(1) == current) return true

    current = board(0)(2)
    if (Character.isLetter(current) && board(1)(2) == current && board(2)(2) == current) return true
    false
  }

  private def checkingDiagonals(): Boolean = {
    //checking diagonals
    var current = board(0)(0)
    if (Character.isLetter(current) && board(1)(1) == current && board(2)(2) == current) return true
    current = board(2)(0)
    if (Character.isLetter(current) && board(1)(1) == current && board(0)(2) == current) return true
    return false
  }

  def ifWins(): Boolean = checkingRows() || checkingColumns() || checkingDiagonals()


  def displayRules: String = {
    val builder = new StringBuilder
    builder.append("\n\nPlayers take turns marking a square. Only squares \n")
    builder.append("not already marked can be picked. Once a player has \n")
    builder.append("marked three squares in a row, the player wins! If all squares \n")
    builder.append("are marked and no three squares are the same, a tie game is declared.\n")
    builder.append("Have Fun! \n\n")
    builder.result()
  }


  def displayBoard: String = {
    val builder = new StringBuilder("Game board: \n")
    for (row <- 0 until board.length) {
      for (column <- 0 until board(0).length) {
        builder.append(s"[${board(row)(column)}]")
      }
      builder.append("\n")
    }
    builder.result()
  }

  def placeMarker(play: Int): Boolean = {
    for (row <- 0 until board.length) {
      for (column <- 0 until board(0).length) {
        val currentSquare = board(row)(column).toInt - 48
        if (currentSquare.toInt == play) {
          board(row)(column) = currentPlayer.marker
          return true
        }
      }
    }
    println("Invalid place detected for marker...try again[0-9]")
    return false
  }
}
