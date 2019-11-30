package com.example.cours1android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends BaseAdapter {
    List<News> sourceNews;
    // LayoutInflater aura pour mission de charger notre fichier XML
    LayoutInflater inflater;
    /**
     * Elle nous servira à mémoriser les éléments de la liste en mémoire pour
     * qu’à chaque rafraichissement l’écran ne scintille pas
     *
     * @author patrice
     */
    private class ViewHolder {
        //TextView imageUrl;
        TextView titre;
        TextView autor;
        TextView date;
    }
    public NewsAdapter(Context context, List<News> objects) {
        inflater = LayoutInflater.from(context);
        this.sourceNews = objects;
    }
    /**
     * Génère la vue pour un objet
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            if(position%2==0){
                convertView = inflater.inflate(R.layout.rightleft_left, null);
            }else{
                convertView = inflater.inflate(R.layout.rightleft_right, null);
            }

            holder.titre = (TextView) convertView
                    .findViewById(R.id.titre);
            holder.autor = (TextView) convertView
                    .findViewById(R.id.autor);
            holder.date = (TextView) convertView
                    .findViewById(R.id.date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        News oneNews = sourceNews.get(position);
        holder.titre.setText(oneNews.getTitre());
        holder.autor.setText(oneNews.getAutor());
        holder.date.setText(oneNews.getDate());
        return convertView;
    }
    /**
     * Retourne le nombre d'éléments
     */
    @Override
    public int getCount() {
// TODO Auto-generated method stub
        return sourceNews.size();
    }
    /**
     * Retourne l'item à la position
     */
    @Override
    public News getItem(int position) {
// TODO Auto-generated method stub
        return sourceNews.get(position);
    }
    /**
     * Retourne la position de l'item
     */
    @Override
    public long getItemId(int position) {
// TODO Auto-generated method stub
        return position;
    }
}

