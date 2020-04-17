package com.wattpad.ca.util

class Constants {
    companion object{
        /*
        const val AUTHORIZATION = "Basic COmAsfoTl5bHFOoHoKl8uQCo12cA8sl2ytzk2RPu3uRB"
        const val BASE_URL = "https://api.wattpad.com/v4/"

        const val OFFSET = "?offset="//0
        const val LIMIT = "&limit="//10
        const val FIELDS = "&fields="//stories(id,title,cover,user)
        const val FILTER = "&filter="//new
        */

        const val FULL_URL = "https://www.wattpad.com/api/v3/stories?offset=0&limit=10&fields=stories(id,title,cover,user)&filter=new"
        const val BASE_URL = "https://www.wattpad.com/api/v3/"

        const val STORIES_ENDPOINT = "stories"
        const val CATEGORIES_ENDPOINT = "stories"
        const val LANGUAGES_ENDPOINT = "stories"
        const val LISTS_ENDPOINT = "stories"
        const val USERS_ENDPOINT = "stories"

        const val OFFSET = 0
        const val LIMIT = 10
        const val FIELDS = "stories(id,title,cover,user)"
        const val FILTER = "new"
    }
}