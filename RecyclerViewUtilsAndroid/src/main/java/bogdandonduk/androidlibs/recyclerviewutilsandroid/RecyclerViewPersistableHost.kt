package bogdandonduk.androidlibs.recyclerviewutilsandroid

import android.os.Parcelable
import androidx.recyclerview.widget.RecyclerView

interface RecyclerViewPersistableHost {
    var containedListsMap: MutableMap<String, RecyclerViewItem>

    fun addListToMap(tag: String, list: RecyclerView, override: Boolean = false) {
        if(override || !containedListsMap.containsKey(tag))
            containedListsMap[tag] = RecyclerViewItem(list)
    }

    fun getListFromMap(tag: String) = containedListsMap[tag]

    fun getListStateFromMap(tag: String) = containedListsMap[tag]?.savedState

    fun saveListStateInMap(tag: String) {
        containedListsMap[tag]?.savedState = containedListsMap[tag]?.list?.layoutManager?.onSaveInstanceState()
    }

    fun removeFromListMap(tag: String) {
        containedListsMap.remove(tag)
    }
}