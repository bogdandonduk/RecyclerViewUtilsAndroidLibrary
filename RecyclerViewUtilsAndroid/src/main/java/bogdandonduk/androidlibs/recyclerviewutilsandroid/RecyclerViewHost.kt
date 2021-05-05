package bogdandonduk.androidlibs.recyclerviewutilsandroid

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

interface RecyclerViewHost {
    var containedListsMap: MutableMap<String, RecyclerView>

    fun <T : RecyclerView.Adapter<RecyclerView.ViewHolder>> initializeList(
        recyclerView: RecyclerView,
        adapter: T,
        tag: String,
        layoutManager: RecyclerView.LayoutManager? = null,
        changeAnimationsEnabled: Boolean = false,
        canReuseUpdatedViewHolder: Boolean = true
    ) {
        with(recyclerView) {
            containedListsMap[tag] = this

            if(layoutManager != null) this.layoutManager = layoutManager

            this.adapter = adapter

            itemAnimator = object : DefaultItemAnimator() {
                override fun canReuseUpdatedViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean {
                    return canReuseUpdatedViewHolder
                }
            }

            (itemAnimator as DefaultItemAnimator).run {
                supportsChangeAnimations = changeAnimationsEnabled
            }
        }
    }

    fun <T : RecyclerView.Adapter<RecyclerView.ViewHolder>> updateLists(vararg adapters: T) {
        adapters.forEach {
            CoroutineScope(Main).launch {
                for(i in 0 until it!!.itemCount) {
                    it.notifyItemChanged(i)
                }
            }
        }
    }

    fun addListToMap(tag: String, list: RecyclerView, override: Boolean = false) {
        if(override || !containedListsMap.containsKey(tag))
            containedListsMap[tag] = list
    }

    fun getListFromMap(tag: String) = containedListsMap[tag]

    fun removeFromListMap(tag: String) {
        containedListsMap.remove(tag)
    }
}