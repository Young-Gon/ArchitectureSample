package com.example.architecturesample.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturesample.util.Event
import com.example.architecturesample.util.EventObserver

abstract class BaseFragment(
    @LayoutRes contentLayoutId: Int
):Fragment(contentLayoutId) {

    abstract val viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel is IMessageBridge) {
            val messageBridge:IMessageBridge = viewModel as IMessageBridge
            messageBridge.toastMessageFromResource.observe(
                viewLifecycleOwner,
                EventObserver { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                })

            messageBridge.toastMessageFromString.observe(
                viewLifecycleOwner,
                EventObserver { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                })
        }
    }
}

interface IMessageBridge {
    val toastMessageFromResource: LiveData<Event<Int>>
    val toastMessageFromString: LiveData<Event<String>>

    fun toastMessage(@StringRes message: Int)
    fun toastMessage(message: String)
}

class MessageBridge: IMessageBridge{

    override val toastMessageFromResource = MutableLiveData<Event<Int>>()

    override val toastMessageFromString = MutableLiveData<Event<String>>()

    override fun toastMessage(message: Int) {
        toastMessageFromResource.value = Event(message)
    }

    override fun toastMessage(message: String) {
        toastMessageFromString.value = Event(message)
    }
}