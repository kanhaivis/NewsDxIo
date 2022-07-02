package ns.kk.utils

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateTimeMethod {

    fun getMinDays(milliseconds : Long) : String{


        var datetime : String = ""

        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)

        // long minutes = (milliseconds / 1000) / 60;
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)

        // long seconds = (milliseconds / 1000);
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)

        println("$milliseconds Milliseconds = $minutes minutes")





        return "${hours} hrs: ${minutes} min: ${seconds} sec"

    }

    fun getMinDay(milliseconds : Long) : String{
        val showDateTime : DateFormat = SimpleDateFormat("dd MMM yyyy")
        val hours: DateFormat = SimpleDateFormat("HH")
        val minuts: DateFormat = SimpleDateFormat("mm")
        val second: DateFormat = SimpleDateFormat("ss")
        // we create instance of the Date and pass milliseconds to the constructor
        // we create instance of the Date and pass milliseconds to the constructor
        val olddate = Date(milliseconds)
        val newDate = Date(System.currentTimeMillis())

        val finelDate = olddate.compareTo(newDate)
        println(finelDate)
        var dataTimeStr  : String = ""
        if (finelDate != -1) {


       val hrs = hours.format(olddate)
       val min = minuts.format(olddate)
       val sec = second.format(olddate)
        val countHours = hrs.toInt()
        val countMinuts = hrs.toInt()

        if (countHours < 24) {
            if(countHours == 0) {
                if (countMinuts == 0) {
                    dataTimeStr = sec+"sec"
                    println("Kanhai Get date -> "+second.format(olddate))
                }
                dataTimeStr = min+"min" +":"+sec+"sec"
                println("Kanhai Get date -> "+ min +":"+sec)

            } else {
                dataTimeStr = hrs+"hrs" +":"+ min+"min" +":"+sec+"sec"
                println("Kanhai Get date -> "+hrs +":"+ min +":"+sec)

            }

        }  else {

            println("Kanhai Get date -> "+showDateTime.format(olddate))
        }

        } else {
            dataTimeStr = showDateTime.format(olddate)
        }

        return dataTimeStr

    }

}