package com.example.uts.ui.profile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.uts.LoginActivity
import com.example.uts.R
import com.example.uts.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val user = User("user_name", "github_user", "12345678", "user@email.com")

    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi tombol logout
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            // Lakukan aksi logout
            logout()
        }
    }

    private fun logout() {
        // Hapus informasi login pengguna
        // Misalnya, jika Anda menggunakan SharedPreferences:
        val sharedPreferences = requireActivity().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            remove("username")
            remove("password")
            apply()
        }

        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)

        activity?.finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class User(val username: String, val githubUsername: String, val nim: String, val email: String)