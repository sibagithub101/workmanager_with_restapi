package com.example.workmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MusicPlayFirebaseActivity extends AppCompatActivity {
    FirebaseStorage storage;
    StorageReference storageReference;
    StorageReference audioRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play_firebase);

        storage = FirebaseStorage.getInstance("gs://iserveu_storage");
        storageReference = storage.getReference();
        audioRef = storageReference.child("kotak_audio/Odia_Kotak_Mahindra_Bank.mp3");
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAudioFileFromFirebase();
            }
        });



    }

    private void getAudioFileFromFirebase() {
        File localFile = null; // path to local file where the audio will be saved
        try {
            localFile = File.createTempFile("audio", "mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert localFile != null;
        File finalLocalFile = localFile;
        audioRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        try {
                            MediaPlayer mediaPlayer = new MediaPlayer();
                            mediaPlayer.setDataSource(finalLocalFile.getPath());
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // handle any errors that may occur during download
                    }
                });
    }
}