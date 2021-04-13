package com.example.architecturesample.ui.fragment.imagelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class ImageListFragment : Fragment() {

    private val binding: ImageListBinding by dataBinding()
    private val viewModel: ImageListViewModel by viewModel()

    /**
     * Inflate the layout for this fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        inflater.inflate(R.layout.fragment_image_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm=viewModel
        binding.recyclerView.adapter=DataBindingListAdapter(
            layoutResId = R.layout.item_image,
            bindingVariableId = BR.item,
            diffCallback = object : DiffUtil.ItemCallback<ImageData>(){
                override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData) =
                    oldItem == newItem

            },
            viewLifecycleOwner,
        ){ itemBinding : ImageItemBinding ->
            itemBinding.vm=viewModel
            itemBinding.itemClickListener = ItemClickListener { view, item ->
                Timber.i("Item Clicked: ${item.id}")
                view.findNavController().navigate(ImageListFragmentDirections.actionImageListFragmentToGalleryFragment(item.id))
            }
        }
    }
}