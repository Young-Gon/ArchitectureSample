package com.example.architecturesample.ui.fragment.board

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturesample.BR
import com.example.architecturesample.R
import com.example.architecturesample.databinding.BoardFragmentBinding
import com.example.architecturesample.databinding.BoardItemBinding
import com.example.architecturesample.model.network.response.PostData
import com.example.architecturesample.util.ItemClickListener
import com.gondev.imagelist.util.DataBindingPagedListAdapter
import com.gondev.imagelist.util.dataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class BoardFragment : Fragment(R.layout.fragment_board) {

    private val binding: BoardFragmentBinding by dataBinding()
    private val viewModel: BoardViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.vm=viewModel
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
                Timber.i("clicked item=${item}")
                findNavController().navigate(
                    BoardFragmentDirections.actionFragmentBoardToCreateBoardFragment(item.id)
                )
            }
        }.apply {
            registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    Timber.i("positionStart=$positionStart itemCount=$itemCount")

                    // index=0에 추가 되면서 화면이 0인 경우만 스크롤 해주자
                    // 인서트 되는 느낌이 든다
                    if (positionStart == 0 && (binding.recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() == 0) {
                        binding.recyclerView.scrollToPosition(0)
                    }
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_board, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when (item.itemId) {
            R.id.create_board_fragment -> {
                findNavController().navigate(
                    BoardFragmentDirections.actionFragmentBoardToCreateBoardFragment()
                )
                true
                //item.onNavDestinationSelected(findNavController())
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}