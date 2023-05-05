package hautc.study.politicalpreparedness.representative.adapter

import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import hautc.study.politicalprepareness.R
import java.util.Date

@BindingAdapter("profileImage")
fun fetchImage(view: ImageView, src: String?) {
	val uri = src?.toUri()?.buildUpon()?.scheme("https")?.build()
	Glide.with(view.context)
		.load(uri)
		.circleCrop()
		.error(R.drawable.ic_profile)
		.placeholder(R.drawable.ic_profile)
		.into(view)
}

@BindingAdapter("stateValue")
fun Spinner.setNewValue(value: String?) {
	val adapter = toTypedAdapter<String>(this.adapter as ArrayAdapter<*>)
	val position = when (adapter.getItem(0)) {
		is String -> adapter.getPosition(value)
		else      -> this.selectedItemPosition
	}
	if (position >= 0) {
		setSelection(position)
	}
}

@BindingAdapter("electionDateStr")
fun bindElectionDayToStr(textView: TextView, date: Date) {
	textView.text = date.toString()
}

inline fun <reified T> toTypedAdapter(adapter: ArrayAdapter<*>): ArrayAdapter<T> {
	return adapter as ArrayAdapter<T>
}
