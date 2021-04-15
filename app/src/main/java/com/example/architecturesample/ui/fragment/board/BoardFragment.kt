package com.example.architecturesample.ui.fragment.board

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.DiffUtil
import com.example.architecturesample.BR
import com.example.architecturesample.R
import com.example.architecturesample.databinding.BoardFragmentBinding
import com.example.architecturesample.databinding.BoardItemBinding
import com.example.architecturesample.model.network.response.PostData
import com.example.architecturesample.util.ItemClickListener
import com.gondev.imagelist.util.DataBindingPagedListAdapter
import com.gondev.imagelist.util.dataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BoardFragment : Fragment(R.layout.fragment_board) {

    private val binding: BoardFragmentBinding by dataBinding()
    private val viewModel: BoardViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = DataBindingPagedListAdapter<PostData, BoardItemBinding>(
            layoutResId = R.layout.item_board,
            bindingVariableId = BR.post,
            diffCallback = object : DiffUtil.ItemCallback<PostData>() {
                override fun areItemsTheSame(oldItem: PostData, newItem: PostData) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: PostData, newItem: PostData) =
                    oldItem == newItem
            },
            lifecycleOwner = viewLifecycleOwner
        ){
            vm = viewModel
            itemClickListener = ItemClickListener { view, item ->
                BoardFragmentDirections.actionFragmentBoardToCreateBoardFragment(item.id)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_board, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when (item.itemId) {
            R.id.create_board_fragment ->{
                //BoardFragmentDirections.actionFragmentBoardToCreateBoardFragment()
                val navController = requireActivity().findNavController(R.id.nav_host_container)
                item.onNavDestinationSelected(navController)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}