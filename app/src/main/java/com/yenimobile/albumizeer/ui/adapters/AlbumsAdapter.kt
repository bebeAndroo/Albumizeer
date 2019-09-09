package com.yenimobile.albumizeer.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yenimobile.albumizeer.R
import com.yenimobile.albumizeer.databinding.ItemAlbumBinding
import com.yenimobile.albumizeer.models.DeezAlbum
import com.yenimobile.albumizeer.ui.DeezAlbumViewModel

class AlbumsAdapter(private val clickListener: OnAlbumClickListener) : RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>(){


    interface OnAlbumClickListener{
        fun onAlbumClick(deezAlbum: DeezAlbum)
    }


    private lateinit var albumList: List<DeezAlbum>





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
      val binding : ItemAlbumBinding =
          DataBindingUtil.inflate(
              LayoutInflater.from(parent.context),
              R.layout.item_album, parent, false
          )
        return AlbumsViewHolder(binding, clickListener )
    }

    override fun getItemCount(): Int {
        return if(::albumList.isInitialized) albumList.size else 0
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(albumList[position])
    }

    fun updateAlbumList(newList: List<DeezAlbum>){
        albumList = newList
        notifyDataSetChanged()
    }


    class AlbumsViewHolder(private val binding: ItemAlbumBinding, private val clickListener: OnAlbumClickListener)
        : RecyclerView.ViewHolder(binding.root){

        private val viewmodel = DeezAlbumViewModel()

        fun bind(deezAlbum: DeezAlbum){
            viewmodel.bind(deezAlbum)
            binding.deezviewmodel = viewmodel
            Picasso.get()
                .load(deezAlbum.cover_medium)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.albumImage)

            itemView.setOnClickListener{
                clickListener.onAlbumClick(deezAlbum)

            }
        }//bind


    }




}//adapter