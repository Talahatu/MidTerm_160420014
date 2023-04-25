package com.example.midterm_160420014.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.midterm_160420014.Model.Users
import com.example.midterm_160420014.R
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LoginFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnRegister).setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }
        view.findViewById<Button>(R.id.btnLogin).setOnClickListener {
            val email = view.findViewById<TextInputEditText>(R.id.emailEditTextLogin).text.toString()
            val password =  view.findViewById<TextInputEditText>(R.id.passwordEditTextLogin).text.toString()
            val req = StringRequest(
                Request.Method.GET,
                "http://10.0.2.2:8080/ANMP/users.json",
                { response ->
                    val sType = object: TypeToken<ArrayList<Users>>(){}.type
                    val result = Gson().fromJson<ArrayList<Users>>(response,sType)
                    if(result.any{user->user.email==email && user.password==password}){
                        val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
                        Navigation.findNavController(it).navigate(action)
                    }
                    else{
                        Toast.makeText(requireContext(),"User doesn't exists!",Toast.LENGTH_SHORT).show()
                    }
                },{ error -> Log.d("Error: ",error.toString())})
            Volley.newRequestQueue(requireContext()).add(req)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
}