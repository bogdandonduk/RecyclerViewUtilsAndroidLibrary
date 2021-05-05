package bogdandonduk.androidlibs.recyclerviewutilsandroid

import android.os.Parcelable
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItem(var list: RecyclerView) {
    var savedState: Parcelable? = null
}