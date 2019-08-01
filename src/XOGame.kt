class XOGame {
    var currentTurn: String = "X"
    var table: Array<Array<String>> = arrayOf(arrayOf("-", "-", "-"), arrayOf("-", "-", "-"), arrayOf("-", "-", "-"))


    init {
        showWelcome()
        showTable(table)
        showTurn()
        input()

    }
    fun input() {
        try {
            print("Please input Row Col : ")
            val input = readLine()!!
            val rowcol:List<String> = input.split(" ")
            if(rowcol.size != 2){
                showError()
            }
            val row = rowcol?.get(0).toInt()
            val col = rowcol?.get(1).toInt()
            if ((col > 0 && col < 4 ) && (row > 0 && row < 4 )){
               // print(detectDraw())
                putOX(row , col)
            }else{
                //print("input {in } =>")
                showError()
            }

        }catch (e : Throwable) {
           // print("input {out } => $e")
            showError()
        }
    }
    fun putOX(x: Int, y: Int) {
        if( table[x-1][y-1] !== "-"){
           // print("putOX {in } =>")
            showError()

        }else{
            table[x-1][y-1] = currentTurn
            if (detectWin()) {
                showResult()
            } else if (!detectDraw()) {
                showDrawResult()
            } else {
                switchTurn()
            }
        }
    }

    fun showError(){
        println("ERROR: You're enter the wrong number.")
        input()
    }

    fun showResult(){
        showTable(table)
        var loser: String = "??"
        if (currentTurn == "X"){
            loser = "O"
        }else if (currentTurn == "O"){
            loser = "X"
        }
        println(currentTurn + " Won " + loser + " Lose")
    }

    fun showDrawResult() {
        showTable(table)
        println("DRAW!")
    }
    fun showTable(table:Array<Array<String>>) {
        println("  1 2 3")
        for( i in table.indices){
            print("${i+1} ")
            for(j in table[i].indices){
                print("${table[i][j]} ")
            }
            println("")
        }
    }
    fun switchTurn() {
        if (currentTurn == "X") {
            currentTurn = "O"
        } else if (currentTurn == "O") {
            currentTurn = "X"
        }

        showTable(table)
        showTurn()
        input()
    }

    fun showTurn() {
        println(currentTurn + " Turn")
    }

    fun showWelcome() {
        println("Welcome to OX Game")
    }

    fun detectDraw(): Boolean {
        var flag: Boolean = false
        for (i in table.indices) {
            for (j in table[i].indices) {
                if (table[i][j] == "-") {
                    flag= true
                }
            }
        }
        return flag
    }
    fun detectWin(): Boolean {
        // horizontal
        for (i in 0..2) {
            if (table[i][0] == currentTurn && table[i][1] == currentTurn && table[i][2] == currentTurn) {
                return true
            }
        }
        // vertical
        for (i in 0..2) {
            if (table[0][i] == currentTurn && table[1][i] == currentTurn && table[2][i] == currentTurn) {
                return true
            }
        }
        // left diagonal
        if (table[0][2] == currentTurn && table[1][1] == currentTurn && table[2][0] == currentTurn) {
            return true
        }
        // right diagonal
        if (table[0][0] == currentTurn && table[1][1] == currentTurn && table[2][2] == currentTurn) {
            return true
        }
        return false
    }
}

fun main() {
    val xo = XOGame()
}