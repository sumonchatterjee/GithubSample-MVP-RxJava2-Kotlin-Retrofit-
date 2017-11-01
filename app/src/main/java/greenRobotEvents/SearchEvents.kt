package greenRobotEvents

import com.google.gson.JsonObject

/**
 * Created by sumon.chatterjee on 01/11/17.
 */
class SearchEvents {
    class SearchKeywordEvent(val keyword: String)
    class SearchResultEvent(val data: JsonObject)
    class SearchShowProgress
    class SearchHideProgress
}