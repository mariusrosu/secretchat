package com.example.marosu.secretchat.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.marosu.secretchat.R
import com.example.marosu.secretchat.model.entity.User
import com.example.marosu.secretchat.util.AvatarView
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * Created by Marius-Andrei Rosu on 8/29/2017.
 */
class SearchAdapter(var users: List<User>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    val clickSubject = PublishSubject.create<User>()!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position], clickSubject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.search_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = users.size

    fun updateUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val searchAvatar: AvatarView = itemView.findViewById(R.id.search_avatar)
        val searchEmail: TextView = itemView.findViewById(R.id.search_email)
        val searchName: TextView = itemView.findViewById(R.id.search_name)

        fun bind(user: User, clickSubject: Subject<User>) {
            itemView.setOnClickListener({ clickSubject.onNext(user) })
            searchAvatar.setUser(user)
            searchName.text = user.fullName
            searchEmail.text = user.email
        }
    }
}
