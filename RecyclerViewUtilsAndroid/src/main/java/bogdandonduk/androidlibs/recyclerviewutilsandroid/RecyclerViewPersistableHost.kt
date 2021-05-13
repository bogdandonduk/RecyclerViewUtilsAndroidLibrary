package bogdandonduk.androidlibs.recyclerviewutilsandroid

import android.os.Parcelable

interface RecyclerViewPersistableHost {
    var containedListsStateMap: MutableMap<String, Parcelable?>

    fun getListStateFromMap(tag: String) = containedListsStateMap[tag]

    fun restoreSavedStateToLists(vararg taggedLists: RecyclerViewItem) {
        taggedLists.forEach {
            it.list.layoutManager?.onRestoreInstanceState(containedListsStateMap[it.tag])
        }
    }

    fun saveListStateInMap(vararg taggedLists: RecyclerViewItem) {
        taggedLists.forEach {
            containedListsStateMap[it.tag] = it.list.layoutManager?.onSaveInstanceState()
        }
    }

    fun removeListStateFromMap(tag: String) {
        containedListsStateMap.remove(tag)
    }
}