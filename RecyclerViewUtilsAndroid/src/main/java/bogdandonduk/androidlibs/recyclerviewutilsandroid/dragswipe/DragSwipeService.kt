package bogdandonduk.androidlibs.recyclerviewutilsandroid.dragswipe

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

object DragSwipeService {
    fun <T> initializeDraggerAdapter(recyclerView: RecyclerView, draggerAdapter: DraggerAdapter<T>) {
        ItemTouchHelper(DraggerItemTouchHelperCallback(draggerAdapter)).attachToRecyclerView(recyclerView)
    }
}