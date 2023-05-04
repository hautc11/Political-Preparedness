package hautc.study.politicalpreparedness.election.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hautc.study.politicalpreparedness.network.models.Election
import hautc.study.politicalprepareness.databinding.ElectionItemLayoutBinding

class ElectionListAdapter : ListAdapter<Election, ElectionListAdapter.ElectionViewHolder>(DiffCallback) {

	var onElectionItemClicked: (Election) -> Unit = {}

	class ElectionViewHolder(private val binding: ElectionItemLayoutBinding) :
		RecyclerView.ViewHolder(binding.root) {

		fun bind(item: Election) {
			binding.election = item
		}

		companion object {
			fun from(parent: ViewGroup): ElectionViewHolder {
				val layoutInflater = LayoutInflater.from(parent.context)
				val binding = ElectionItemLayoutBinding.inflate(layoutInflater, parent, false)
				return ElectionViewHolder(binding)
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionViewHolder {
		return ElectionViewHolder.from(parent)
	}

	override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
		val currentElection = getItem(position)
		holder.bind(currentElection)
		holder.itemView.setOnClickListener {
			onElectionItemClicked.invoke(currentElection)
		}
	}

	companion object {
		object DiffCallback : DiffUtil.ItemCallback<Election>() {
			override fun areItemsTheSame(oldItem: Election, newItem: Election): Boolean {
				return oldItem.id == newItem.id
			}

			override fun areContentsTheSame(oldItem: Election, newItem: Election): Boolean {
				return oldItem == newItem
			}
		}
	}
}