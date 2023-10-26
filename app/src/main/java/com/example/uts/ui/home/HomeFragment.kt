package com.example.uts.ui.home

import ApiService
import UserAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uts.databinding.FragmentHomeBinding
import com.example.uts.userModel.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var apiService: ApiService

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val retrofit = Retrofit.Builder()

            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService  = retrofit.create(ApiService::class.java)
        // Inisialisasi RecyclerView
        val recyclerView = binding.rvList
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Memuat data dari API
        loadData()
        return root
    }

    private fun loadData() {
        apiService.getUsers().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val users = response.body()?.data ?: emptyList()
                    val adapter = UserAdapter(users)
                    binding.rvList.adapter = adapter
                } else {
                    Toast.makeText(requireContext(), "Failed to load data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}