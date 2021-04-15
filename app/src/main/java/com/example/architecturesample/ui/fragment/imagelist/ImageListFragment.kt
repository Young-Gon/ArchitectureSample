package com.example.architecturesample.ui.fragment.imagelist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.example.architecturesample.BR
import com.example.architecturesample.R
import com.example.architecturesample.databinding.ImageItemBinding
import com.example.architecturesample.databinding.ImageListBinding
import com.example.architecturesample.model.network.response.ImageData
import com.example.architecturesample.util.ItemClickListener
import com.gondev.imagelist.util.DataBindingListAdapter
import com.gondev.imagelist.util.dataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ImageListFragment : Fragment(R.layout.fragment_image_list) {

    private val binding: ImageListBinding by dataBinding()
    private val viewModel: ImageListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
        binding.recyclerView.adapter = DataBindingListAdapter<ImageData, ImageItemBinding>(
            layoutResId = R.layout.item_image,
            bindingVariableId = BR.item,
            diffCallback = object : DiffUtil.ItemCallback<ImageData>() {
                override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData) =
                    oldItem == newItem

            },
            viewLifecycleOwner,
        ) {
            vm = viewModel
            itemClickListener = ItemClickListener { view, item ->
                Timber.i("Item Clicked: ${item.id}")
                view.findNavController().navigate(
                    ImageListFragmentDirections.actionImageListFragmentToGalleryFragment(item.id)
                )
            }
        }
    }
}