package com.wattpad.ca.util

class Utils {
    companion object{
        fun extractOffset(nextUrl: String) : Int {
            var offset = 0
            if(nextUrl != null && nextUrl.contains("&")){
                var partString = nextUrl.split("&")
                val fullOffset = partString.find { it.contains("offset")}
                var partOffset = fullOffset?.split("=")
                offset = partOffset!![1].toInt()
            }
            return offset
        }
    }
}