import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uts.userModel.User
import com.example.uts.R

class UserAdapter(private val users: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val emailTextView: TextView = view.findViewById(R.id.emailTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.nameTextView.text = "${user.first_name} ${user.last_name}"
        holder.emailTextView.text = user.email
        Glide.with(holder.imageView.context).load(user.avatar).into(holder.imageView)
    }

    override fun getItemCount() = users.size
}
