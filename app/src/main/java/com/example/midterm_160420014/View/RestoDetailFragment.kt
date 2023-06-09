package com.example.midterm_160420014.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.midterm_160420014.R
import com.example.midterm_160420014.ViewModel.RestoDetailViewModel
import com.squareup.picasso.Picasso


class RestoDetailFragment : Fragment() {

    private lateinit var restoVM:RestoDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resto_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = RestoDetailFragmentArgs.fromBundle(requireArguments()).id
        restoVM = ViewModelProvider(this)[RestoDetailViewModel::class.java]
        restoVM.refresh(id)
        observe()
        view.findViewById<Button>(R.id.btnPromoDetail).setOnClickListener {
            Navigation.findNavController(it).navigate(RestoDetailFragmentDirections.actionRestoDetailFragmentToPromoFragment(id))
        }
        view.findViewById<Button>(R.id.btnListMenu).setOnClickListener {
            Navigation.findNavController(it).navigate(RestoDetailFragmentDirections.actionRestoDetailFragmentToMenuFragment(id))
        }
        view.findViewById<Button>(R.id.btnReview).setOnClickListener {
            Navigation.findNavController(it).navigate(RestoDetailFragmentDirections.actionRestoDetailFragmentToReviewFragment(id))
        }
    }

    fun observe(){
        restoVM.restoData.observe(viewLifecycleOwner, Observer {
            view?.findViewById<TextView>(R.id.txtNamaDetail)?.text = it.name
            view?.findViewById<TextView>(R.id.txtAddressDetail)?.text = it.address
            view?.findViewById<TextView>(R.id.txtPhoneDetail)?.text = it.phone
            Picasso.get().load(it.link).into(view?.findViewById<ImageView>(R.id.imgRestoDetail))
        })
    }
}