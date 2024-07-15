import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

var table: Array<Array<Checker>> = Array(8) { Array(8) { Checker.Empty } }
var turn = Turn.First
var current: Pair<Int,Int> = Pair(0,0)
var gameEnded = false
var countFirst = 0
var countSecond= 0


enum class Checker{
    Empty,
    First,
    Second,
    FirstQueen,
    SecondQueen,
    MoveMaybe
}


enum class Turn{
    First,
    Second
}


fun clearTable(){
    for (x in 0..7){
        for (y in 5..7){
            if ((x+y)%2==1){
                table[x][y]=Checker.First
            }
        }
        for (y in 0..2){
            if ((x+y)%2==1){
                table[x][y]=Checker.Second
            }
        }
    }
}


fun nextByModule(x:Int): Int{
    if (x>0) return x+1
    if (x<0) return x-1
    return 0
}


fun canYouBeatInDirFirst(x:Int,y:Int,a:Int,b:Int,result:Array<Pair<Int,Int>>):Array<Pair<Int,Int>>{
    var answer:Array<Pair<Int,Int>> = result
    if ((table[x+a][y+b]==Checker.Second || table[x+a][y+b]==Checker.SecondQueen) && table[x+nextByModule(a)][y+nextByModule(b)]==Checker.Empty){
        answer = answer.plus(Pair(x+nextByModule(a),y+nextByModule(b)))
    }
    return answer
}
fun canYouBeatInDirSecond(x:Int,y:Int,a:Int,b:Int,result:Array<Pair<Int,Int>>):Array<Pair<Int,Int>>{
    var answer:Array<Pair<Int,Int>> = result
    if ((table[x+a][y+b]==Checker.First || table[x+a][y+b]==Checker.FirstQueen) && table[x+nextByModule(a)][y+nextByModule(b)]==Checker.Empty){
        answer = answer.plus(Pair(x+nextByModule(a),y+nextByModule(b)))
    }
    return answer
}

fun whatCanYouBeat(x:Int,y:Int): Array<Pair<Int,Int>>{
    var result:Array<Pair<Int,Int>> = arrayOf()
    if (table[x][y] == Checker.First) {
        for (a in 0..1) {
            for (b in 0..1) {
                val xDir = a * 2 - 1
                val yDir = b * 2 - 1
                if(x + nextByModule(xDir) in 0..7 && y + nextByModule(yDir) in 0..7) result = canYouBeatInDirFirst(x, y, xDir, yDir, result)
            }
        }
    }
    else {
        if (table[x][y] == Checker.Second) {
            for (a in 0..1) {
                for (b in 0..1) {
                    val xDir = a * 2 - 1
                    val yDir = b * 2 - 1
                    if(x + nextByModule(xDir) in 0..7 && y + nextByModule(yDir) in 0..7) result = canYouBeatInDirSecond(x, y, xDir, yDir, result)
                }
            }
        }else {
            if (table[x][y] == Checker.FirstQueen) {
                for (a in 0..1) {
                    for (b in 0..1) {
                        var xDir = a * 2 - 1
                        var yDir = b * 2 - 1
                        while (x + nextByModule(xDir) in 0..7 && y + nextByModule(yDir) in 0..7) {
                            if (table[x+xDir][y+yDir] == Checker.First || table[x+xDir][y+yDir] == Checker.FirstQueen) {
                                break
                            }
                            if ((table[x+xDir][y+yDir] == Checker.Second || table[x+xDir][y+yDir] == Checker.SecondQueen)&&(table[x+nextByModule(xDir)][y+nextByModule(yDir)] == Checker.Second || table[x+nextByModule(xDir)][y+nextByModule(yDir)] == Checker.SecondQueen)) break
                            if (!result.contentEquals(canYouBeatInDirFirst(x, y, xDir, yDir, result))) {
                                result=canYouBeatInDirFirst(x, y, xDir, yDir, result)
                                xDir = nextByModule(xDir)
                                yDir = nextByModule(yDir)
                                while ((x + xDir in 0..7) && (y + yDir  in 0..7)){
                                    if(table[x+xDir][y+yDir]==Checker.Empty) {
                                        result=result.plus(Pair(x+xDir,y+yDir))
                                        xDir = nextByModule(xDir)
                                        yDir = nextByModule(yDir)
                                    }
                                    else break
                                }
                                break
                            }
                            xDir = nextByModule(xDir)
                            yDir = nextByModule(yDir)
                        }
                    }
                }
            } else {
                if (table[x][y] == Checker.SecondQueen) {
                    for (a in 0..1) {
                        for (b in 0..1) {
                            var xDir = a * 2 - 1
                            var yDir = b * 2 - 1
                            while (x + nextByModule(xDir) in 0..7 && y + nextByModule(yDir) in 0..7) {
                                if (table[x+xDir][y+yDir] == Checker.Second || table[x+xDir][y+yDir] == Checker.SecondQueen) {
                                    break
                                }
                                if ((table[x+xDir][y+yDir] == Checker.First || table[x+xDir][y+yDir] == Checker.FirstQueen)&&(table[x+nextByModule(xDir)][y+nextByModule(yDir)] == Checker.First || table[x+nextByModule(xDir)][y+nextByModule(yDir)] == Checker.FirstQueen)) break
                                if (!result.contentEquals(canYouBeatInDirSecond(x, y, xDir, yDir, result))) {
                                    result=canYouBeatInDirFirst(x, y, xDir, yDir, result)
                                    xDir = nextByModule(xDir)
                                    yDir = nextByModule(yDir)
                                    while ((x + xDir in 0..7) && (y + yDir  in 0..7)){
                                        if(table[x+xDir][y+yDir]==Checker.Empty) {
                                            result=result.plus(Pair(x+xDir,y+yDir))
                                            xDir = nextByModule(xDir)
                                            yDir = nextByModule(yDir)
                                        }
                                        else break
                                    }
                                    break
                                }
                                xDir = nextByModule(xDir)
                                yDir = nextByModule(yDir)

                            }
                        }
                    }
                }
            }
        }
    }
    return result
}

fun haveYouTurn(x:Int,y:Int):Array<Pair<Int,Int>>{
    var result:Array<Pair<Int,Int>> = arrayOf()
    if (table[x][y] == Checker.First){
        for (a in 0..1) {
            val xDir = a * 2 - 1
            if (x + xDir in 0..7 && y>=1){
                if(table[x+xDir][y-1]==Checker.Empty){
                    result=result.plus(Pair(x+xDir,y-1))
                }
            }
        }
    }
    else{
        if(table[x][y] == Checker.Second){
            for(a in 0..1){
                val xDir = a * 2 - 1
                if(x + xDir in 0..7 && y<=6){
                    if(table[x+xDir][y+1]==Checker.Empty){
                        result=result.plus(Pair(x+xDir,y+1))
                    }
                }
            }
        }
        else{
            if((table[x][y] == Checker.FirstQueen) or (table[x][y] == Checker.SecondQueen)){
                for (a in 0..1) {
                    for (b in 0..1) {
                        var xDir = a * 2 - 1
                        var yDir = b * 2 - 1
                        while ((x + xDir in 0..7) && (y + yDir in 0..7)) {
                            if (table[x+xDir][y+yDir] != Checker.Empty){
                                break
                            }
                            else {
                                result=result.plus(Pair(x+xDir,y+yDir))
                            }
                            xDir = nextByModule(xDir)
                            yDir = nextByModule(yDir)
                        }
                    }
                }
            }
        }
    }
    return result
}
fun transformIntoQueens(){
    for(x in 0..7){
        if(table[x][7]==Checker.Second){
            table[x][7]=Checker.SecondQueen
        }
        if(table[x][0]==Checker.First){
            table[x][0]=Checker.FirstQueen
        }
    }
}
fun waitMove(){
    val last = current
    while(true)
        if(current!=last && current!=Pair(-1,-1)) break
}
fun move(){
    if (countFirst==12 || countSecond==12) {
        gameEnded = true
    }
    var whoCanBeat:Array<Pair<Int,Int>> = arrayOf()
    if (turn == Turn.First){
        for (a in 0..7){
            for(b in 0..7){
                if (table[a][b]==Checker.First || table[a][b]==Checker.FirstQueen){
                    if (whatCanYouBeat(a,b).isNotEmpty()) whoCanBeat=whoCanBeat.plus(Pair(a,b))
                }
            }
        }
    }
    else{
        if (turn == Turn.Second){
            for (a in 0..7){
                for(b in 0..7){
                    if (table[a][b]==Checker.Second || table[a][b]==Checker.SecondQueen){
                        if (whatCanYouBeat(a,b).isNotEmpty()) whoCanBeat=whoCanBeat.plus(Pair(a,b))
                    }
                }
            }
        }
    }
    if (whoCanBeat.isEmpty()){
        waitMove()
        maybeMove()
    }
    else{
        while(current !in whoCanBeat){
            waitMove()
        }
        mayBeat()
    }
}

fun changeTurn(){
    turn = if(turn==Turn.First) Turn.Second  else Turn.First
}
fun resetMaybe(){
    for (a in 0..7){
        for(b in 0..7){
            if (table[a][b]==Checker.MoveMaybe){
                table[a][b]=Checker.Empty
            }
        }
    }
}
fun maybeMove(){
    if (((table[current.first][current.second] == Checker.First || table[current.first][current.second] == Checker.FirstQueen) && turn == Turn.First) || (table[current.first][current.second] == Checker.Second || table[current.first][current.second] == Checker.SecondQueen) && turn == Turn.Second) {
        val answer = haveYouTurn(current.first, current.second)
        if (answer.isNotEmpty()) {
            for (ans in answer){
                table[ans.first][ans.second] = Checker.MoveMaybe
            }
            val last = current
            waitMove()
            if (current !in answer){
                resetMaybe()
                current=Pair(-1,-1)
            }
            else{
                resetMaybe()
                table[current.first][current.second]=table[last.first][last.second]
                table[last.first][last.second]=Checker.Empty
                transformIntoQueens()
                changeTurn()
                current=Pair(-1,-1)
            }
        }
    }
}
fun mayBeat(){
    if (((table[current.first][current.second] == Checker.First || table[current.first][current.second] == Checker.FirstQueen) && turn == Turn.First) || (table[current.first][current.second] == Checker.Second || table[current.first][current.second] == Checker.SecondQueen) && turn == Turn.Second) {
        val answer = whatCanYouBeat(current.first, current.second)
        for (ans in answer){
            table[ans.first][ans.second] = Checker.MoveMaybe
        }
        val last = current
        waitMove()
        while(table[current.first][current.second] != Checker.MoveMaybe){
            waitMove()
        }
        resetMaybe()
        if (turn == Turn.First) countFirst+=1
        else countSecond+=1
        table[current.first][current.second]=table[last.first][last.second]
        table[last.first][last.second]=Checker.Empty
        transformIntoQueens()
        val max:Pair<Int, Int>
        val min:Pair<Int, Int>
        if(last.first-current.first==last.second-current.second){
            if (last.first>current.first){
                max = last
                min = current
            }
            else{
                max = current
                min = last
            }
            var a=1
            while(min.first+a<max.first){
                table[min.first+a][min.second+a]=Checker.Empty
                a+=1
            }
        }
        else{
            if (last.first>current.first){
                max = last
                min = current
            }
            else{
                max = current
                min = last
            }
            var a=1
            while(min.first+a<max.first){
                table[min.first+a][min.second-a]=Checker.Empty
                a+=1
            }
        }
        resetMaybe()
        if (whatCanYouBeat(current.first,current.second).isNotEmpty()) mayBeat()
        else{
            changeTurn()
            current=Pair(-1,-1)
        }
    }
}

