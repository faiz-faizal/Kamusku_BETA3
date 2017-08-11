package com.maxibi.kamusku_beta3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 8/11/2017.
 */

public class DictionaryActivity extends Activity{

    EditText searchEditText;
    Button searchButton;
    ListView dictionaryListView;

    String logTagString = "DICTIONARY";
    ArrayList<Word> allWordDefinition = new ArrayList<Word>();

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dictionary_list);

        searchEditText = (EditText)findViewById(R.id.searchEditText);
        searchButton =(Button)findViewById(R.id.seacrh_button);

        dictionaryListView = (ListView)findViewById(R.id.dictionaryListView);

        databaseHelper = new DatabaseHelper(this);

        allWordDefinition = databaseHelper.getAllWord();
        dictionaryListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return allWordDefinition.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = getLayoutInflater().inflate(R.layout.list_item, null);
                }

                TextView textView = (TextView) view.findViewById(R.id.listItemTextView);
                textView.setText(allWordDefinition.get(i).word_MS);
                return view;
            }
        });

        dictionaryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DictionaryActivity.this,WordDefinitionDetails.class);
                intent.putExtra("Word", wordDefination.word );
                intent.putExtra("Definition", wordDefinition.definition);

                startActivity(intent);


            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = searchEditText.getText().toString();

                Word word = databaseHelper.getWord(string);

                if (word == null)
                {
                    Toast.makeText(DictionaryActivity.this,"Word Not Found", Toast.LENGTH_SHORT).show;
                }
                else
                {
                    Intent intent  = new Intent(DictionaryActivity.this, WordDefinitionDetails.class);
                    intent.putExtra("Word", wordDefinition.word);
                    intent.putExtra("Definition", wordDefinition.definition);
                }
            }
        });

    }


}

