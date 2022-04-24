package com.nada.technicaltest.ui

import android.app.DownloadManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.net.Uri
import android.os.Environment
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Log
import com.google.android.exoplayer2.util.Util
import com.nada.technicaltest.R
import com.nada.technicaltest.data.entities.Item
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader

class ItemAdapter(private val itemList: List<Item>) : RecyclerView.Adapter<ItemAdapter.DataHolder>() {

    var mediaControls: MediaController? = null

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        context = parent.context
        return DataHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: DataHolder, position: Int) {

        if(itemList[position]!!.type.equals("VIDEO")) {
            Log.e("--- VIDEO ---", "")
            holder.exoPlayer.visibility = View.VISIBLE
            holder.pdfViewer.visibility = View.GONE
            // trigger video
            try {
                val uri = Uri.parse(itemList[position].url)
                val player = ExoPlayerFactory.newSimpleInstance(context)
                holder.exoPlayer.setPlayer(player)
                // Produces DataSource instances through which media data is loaded.
                val dataSourceFactory: DataSource.Factory =
                    DefaultDataSourceFactory(
                        context,
                        Util.getUserAgent(context, "Appname")
                    )

                val videoSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(uri)
                // Prepare the player with the source.
                player.prepare(videoSource)
            } catch (E: Exception) {
                Log.e("Video Error", E.toString())
            }
        } else if(itemList[position]!!.type.equals("PDF")) {
            Log.e("--- PDF ---", "")
            holder.exoPlayer.visibility = View.GONE
            holder.pdfViewer.visibility = View.VISIBLE
            holder.pdfViewer.setImageResource(R.drawable.ic_pdf);
        }

        holder.type.text = itemList[position].name

        holder.itemView.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, itemList[position]!!.type, Toast.LENGTH_SHORT).show()

            if(itemList[position]!!.type.equals("VIDEO")) {
                val request = DownloadManager.Request(Uri.parse(itemList[position].url))
                    .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                    .setTitle(itemList[position].name)
                    .setDescription("desc")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(false)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,itemList[position].name)
                val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                val downloadID = downloadManager.enqueue(request)

                Toast.makeText(context, "downloaded" + itemList[position].name, Toast.LENGTH_SHORT).show()

            } else if(itemList[position]!!.type.equals("PDF")) {
                Toast.makeText(context, "downloading.." + itemList[position].name, Toast.LENGTH_SHORT).show()
            }

        })
    }

    class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val type: TextView = itemView.findViewById(R.id.tv_type)
        var exoPlayer: PlayerView = itemView.findViewById(R.id.exoPlayer)
        var pdfViewer: ImageView = itemView.findViewById(R.id.pdfViewer)
    }

}