package com.example.uts.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.uts.databinding.FragmentDashboardBinding
import com.example.uts.R

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val user = User("user_name", "github_user", "12345678", "user@email.com")

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        // Inisialisasi tampilan dan perbarui dengan data pengguna
        val profileInitial = view.findViewById<TextView>(R.id.profileInitial)
        val username = view.findViewById<TextView>(R.id.username)
        val githubUsername = view.findViewById<TextView>(R.id.githubUsername)
        val nim = view.findViewById<TextView>(R.id.nim)
        val email = view.findViewById<TextView>(R.id.email)

        // Set huruf depan dari username sebagai gambar profil
        profileInitial.text = user.username.first().toString()
        // Set teks untuk detail pengguna
        username.text = "Username: ${user.username}"
        githubUsername.text = "GitHub Username: ${user.githubUsername}"
        nim.text = "NIM: ${user.nim}"
        email.text = "Email: ${user.email}"

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class User(val username: String, val githubUsername: String, val nim: String, val email: String)